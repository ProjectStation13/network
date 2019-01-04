package com.projectstation.network.command.client;

import com.projectstation.network.*;
import com.projectstation.network.command.server.ServerWorldVisit;
import com.projectstation.network.entity.IEntityNetworkAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientWorldVisit implements IClientVisit {
    private final WorldVisit worldVisit;
    private final long creationTime;

    public ClientWorldVisit(WorldVisit worldVisit) {
        this.worldVisit = worldVisit;
        creationTime = System.nanoTime() / 1000000;
    }

    @Override
    public List<IServerVisit> visit(IClientWorldHandler handler, Map<String, IEntityNetworkAdapter> networkEntityMap) throws VisitException {
        if(!handler.hasWorld())
            throw new VisitException("World not present to be visited.");

        long elapsedTime = Math.max(0, handler.getServerTime() - creationTime);

        List<IServerVisit> response = new ArrayList<>();
        List<WorldVisit> worldVisitResponse = worldVisit.visit(handler, networkEntityMap, handler.getEntityFactory(), handler.getWorld(), elapsedTime, false);
        for(WorldVisit v : worldVisitResponse)
            response.add(new ServerWorldVisit(v));

        return response;
    }

    @Override
    public boolean isPriorRedundant(INetworkVisit before) {
        if(before instanceof ClientWorldVisit) {
            return worldVisit.isPriorRedundant(((ClientWorldVisit) before).worldVisit);
        }

        return false;
    }
}
