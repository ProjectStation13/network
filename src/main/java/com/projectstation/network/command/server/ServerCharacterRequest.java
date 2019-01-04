package com.projectstation.network.command.server;

import com.projectstation.network.IClientVisit;
import com.projectstation.network.IServerVisit;
import com.projectstation.network.IServerWorldHandler;
import com.projectstation.network.VisitException;
import com.projectstation.network.command.client.ClientCharacterAssignment;
import com.projectstation.network.command.client.ClientGiveOwnership;
import com.projectstation.network.entity.IEntityNetworkAdapter;
import io.github.jevaengine.world.World;
import io.github.jevaengine.world.entity.IEntityFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServerCharacterRequest implements IServerVisit {
    @Override
    public List<IClientVisit> visit(IServerWorldHandler handler, Map<String, IEntityNetworkAdapter> networkEntityMap) throws VisitException {
        List<IClientVisit> response = new ArrayList<>();

        response.add(new ClientCharacterAssignment("player"));
        response.add(new ClientGiveOwnership("player"));
        handler.authorizeOwnership("player");

        return response;
    }
}
