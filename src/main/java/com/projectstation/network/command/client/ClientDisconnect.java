package com.projectstation.network.command.client;

import com.projectstation.network.IClientVisit;
import com.projectstation.network.IClientWorldHandler;
import com.projectstation.network.IServerVisit;
import com.projectstation.network.VisitException;

import java.util.ArrayList;
import java.util.List;

public class ClientDisconnect implements IClientVisit {
    private final String reason;

    public ClientDisconnect(String reason) {
        this.reason = reason;
    }

    @Override
    public List<IServerVisit> visit(IClientWorldHandler handler) throws VisitException {
        handler.disconnect(reason);

        return new ArrayList<>();
    }

    @Override
    public boolean trackHistory() {
        return false;
    }
}
