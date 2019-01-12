package com.projectstation.network.command.server;

import com.projectstation.network.*;
import com.projectstation.network.command.client.ClientCreateWorld;
import com.projectstation.network.command.client.ClientDisconnect;
import com.projectstation.network.command.client.ClientWorldVisit;
import com.projectstation.network.entity.IEntityNetworkAdapter;
import com.projectstation.network.entity.EntityNetworkAdapterException;
import com.projectstation.network.entity.IServerEntityNetworkAdapter;
import io.github.jevaengine.world.World;
import io.github.jevaengine.world.entity.IEntity;
import io.github.jevaengine.world.entity.IEntityFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServerJoinWorldRequest implements IServerVisit {
    private final String selectedNickname;

    public ServerJoinWorldRequest(String selectedNickname) {
        this.selectedNickname = selectedNickname;
    }

    @Override
    public List<IClientVisit> visit(IServerWorldHandler handler) throws VisitException {

        ArrayList<IClientVisit> response = new ArrayList<>();

        if(!handler.hasWorld())
            throw new VisitException("No world to be initialized from.");

        if(!handler.setNickname(selectedNickname)) {
            response.add(new ClientDisconnect("The selected nickname is not valid or is currently in use. Please choose another nickname"));
            return response;
        }

        World world = handler.getWorld();

        response.add(new ClientCreateWorld(world.getBounds().width,
                world.getBounds().height,
                world.getPhysicsWorld().getMaxFrictionForce(),
                world.getMetersPerUnit(),
                world.getLogicPerUnit()));

        for(IEntity e : world.getEntities().all()) {
            IServerEntityNetworkAdapter net = handler.getAdapter(e.getInstanceName());

            try {
                if (net != null) {
                    for(IClientVisit v : net.createInitializeSteps()) {
                        response.add(v);
                    }
                }
            } catch(EntityNetworkAdapterException ex) {
                throw new VisitException(ex);
            }
        }

        return response;
    }
}
