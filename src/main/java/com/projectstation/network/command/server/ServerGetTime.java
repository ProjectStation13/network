package com.projectstation.network.command.server;

import com.projectstation.network.IClientVisit;
import com.projectstation.network.IServerVisit;
import com.projectstation.network.IServerWorldHandler;
import com.projectstation.network.VisitException;
import com.projectstation.network.command.client.ClientSetServerTime;
import com.projectstation.network.entity.IEntityNetworkAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServerGetTime implements IServerVisit {
    private final long clientTime;
    private final long creationTime;

    public ServerGetTime(long clientTime) {
        this.creationTime = System.nanoTime() / 1000000;
        this.clientTime = clientTime;
    }

    @Override
    public List<IClientVisit> visit(IServerWorldHandler handler) throws VisitException {
        List<IClientVisit> response = new ArrayList<>();
        handler.setClientTime(clientTime);
        response.add(new ClientSetServerTime(creationTime, System.nanoTime() / 1000000));
        return response;
    }
}
