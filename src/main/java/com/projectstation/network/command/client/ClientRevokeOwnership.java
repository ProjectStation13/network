package com.projectstation.network.command.client;

import com.projectstation.network.IClientVisit;
import com.projectstation.network.IClientWorldHandler;
import com.projectstation.network.IServerVisit;
import com.projectstation.network.VisitException;
import com.projectstation.network.entity.IEntityNetworkAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientRevokeOwnership implements IClientVisit {
    private final String entityName;

    public ClientRevokeOwnership(String entityName) {
        this.entityName = entityName;
    }

    @Override
    public List<IServerVisit> visit(IClientWorldHandler handler) throws VisitException {
        handler.revokeOwnership(entityName);

        return new ArrayList<>();
    }
}
