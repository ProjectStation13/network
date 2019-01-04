package com.projectstation.network.command.client;

import com.projectstation.network.IClientVisit;
import com.projectstation.network.IClientWorldHandler;
import com.projectstation.network.IServerVisit;
import com.projectstation.network.VisitException;
import com.projectstation.network.entity.IEntityNetworkAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientSetServerTime implements IClientVisit {
    private final long serverTime;
    private final long requestTime;

    public ClientSetServerTime(long requestTime, long serverTime) {
        this.serverTime = serverTime;
        this.requestTime = requestTime;
    }

    @Override
    public List<IServerVisit> visit(IClientWorldHandler handler, Map<String, IEntityNetworkAdapter> networkEntityMap) throws VisitException {

        //Half because it is the time for there and back.
        long latency = (System.nanoTime() / 1000000 - requestTime) / 2;

        handler.setServerTime(serverTime + latency);

        return new ArrayList<>();
    }
}
