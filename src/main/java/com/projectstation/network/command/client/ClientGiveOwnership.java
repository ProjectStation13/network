package com.projectstation.network.command.client;

import com.projectstation.network.IClientVisit;
import com.projectstation.network.IClientWorldHandler;
import com.projectstation.network.IServerVisit;
import com.projectstation.network.VisitException;
import com.projectstation.network.entity.IEntityNetworkAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientGiveOwnership implements IClientVisit {
    private final String entityName;

    public ClientGiveOwnership(String entityName) {
        this.entityName = entityName;
    }

    @Override
    public List<IServerVisit> visit(IClientWorldHandler handler, Map<String, IEntityNetworkAdapter> networkEntityMap) throws VisitException {
        handler.giveOwnership(entityName);

        return new ArrayList<>();
    }
}
