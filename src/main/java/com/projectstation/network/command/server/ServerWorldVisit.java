package com.projectstation.network.command.server;

import com.projectstation.network.*;
import com.projectstation.network.command.client.ClientWorldVisit;
import com.projectstation.network.entity.IEntityNetworkAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServerWorldVisit implements IServerVisit {
    private final WorldVisit worldVisit;
    private final long creationTime;

    public ServerWorldVisit(WorldVisit worldVisit) {
        this.worldVisit = worldVisit;
        creationTime = System.nanoTime() / 1000000;
    }

    @Override
    public List<IClientVisit> visit(IServerWorldHandler handler, Map<String, IEntityNetworkAdapter> networkEntityMap) throws VisitException {
        if(!handler.hasWorld())
            throw new VisitException("World not present to be visited.");


        long elapsedTime = handler.getClientTime() - creationTime;

        List<IClientVisit> response = new ArrayList<>();
        List<WorldVisit> worldVisitResponse = worldVisit.visit(handler, networkEntityMap, handler.getEntityFactory(), handler.getWorld(), elapsedTime, true);
        for(WorldVisit v : worldVisitResponse)
            response.add(new ClientWorldVisit(v));

        return response;
    }


    @Override
    public boolean isPriorRedundant(INetworkVisit before) {
        if(before instanceof ServerWorldVisit) {
            return worldVisit.isPriorRedundant(((ServerWorldVisit) before).worldVisit);
        }

        return false;
    }
}
