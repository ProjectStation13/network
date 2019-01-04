package com.projectstation.network.command.server;

import com.projectstation.network.*;
import com.projectstation.network.command.client.ClientCreateWorld;
import com.projectstation.network.command.client.ClientWorldVisit;
import com.projectstation.network.entity.IEntityNetworkAdapter;
import com.projectstation.network.entity.EntityNetworkAdapterException;
import io.github.jevaengine.world.World;
import io.github.jevaengine.world.entity.IEntity;
import io.github.jevaengine.world.entity.IEntityFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServerInitializeWorldRequest implements IServerVisit {
    @Override
    public List<IClientVisit> visit(IServerWorldHandler handler, Map<String, IEntityNetworkAdapter> networkEntityMap) throws VisitException {
        if(!handler.hasWorld())
            throw new VisitException("No world to be initialized from.");

        World world = handler.getWorld();
        ArrayList<IClientVisit> response = new ArrayList<>();

        response.add(new ClientCreateWorld(world.getBounds().width,
                world.getBounds().height,
                world.getPhysicsWorld().getMaxFrictionForce(),
                world.getMetersPerUnit(),
                world.getLogicPerUnit()));

        for(IEntity e : world.getEntities().all()) {
            IEntityNetworkAdapter net = networkEntityMap.containsKey(e.getInstanceName()) ? networkEntityMap.get(e.getInstanceName()) : null;

            try {
                if (net != null) {
                    for(WorldVisit v : net.createInitializeSteps()) {
                        response.add(new ClientWorldVisit(v));
                    }
                }
            } catch(EntityNetworkAdapterException ex) {
                throw new VisitException(ex);
            }
        }

        return response;
    }
}
