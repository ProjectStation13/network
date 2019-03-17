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

    public WorldVisit getWorldVisit() {
        return worldVisit;
    }

    @Override
    public List<IServerVisit> visit(IClientWorldHandler handler) throws VisitException {
        if(!handler.hasWorld())
            throw new VisitException("World not present to be visited.");

        long elapsedTime = Math.max(0, handler.getServerTime() - creationTime);

        List<IServerVisit> response = new ArrayList<>();
        List<WorldVisit> worldVisitResponse = worldVisit.visit(handler, handler.getEntityFactory(), handler.getWorld(), elapsedTime, false);
        for(WorldVisit v : worldVisitResponse)
            response.add(new ServerWorldVisit(v));

        return response;
    }

    @Override
    public boolean mutuallyEliminates(INetworkVisit v) {
        if(!(v instanceof ClientWorldVisit))
            return false;

        return worldVisit.mutuallyEliminates(((ClientWorldVisit)v).worldVisit);
    }

    @Override
    public boolean overrides(INetworkVisit v) {
        if(!(v instanceof ClientWorldVisit))
            return false;

        return worldVisit.overrides(((ClientWorldVisit)v).worldVisit);
    }

    @Override
    public boolean trackHistory() {
        return worldVisit.trackHistory();
    }
}
