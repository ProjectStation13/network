package com.projectstation.network.command.client;

import com.projectstation.network.IClientVisit;
import com.projectstation.network.IClientWorldHandler;
import com.projectstation.network.IServerVisit;
import com.projectstation.network.VisitException;
import com.projectstation.network.entity.IEntityNetworkAdapter;
import io.github.jevaengine.world.World;
import io.github.jevaengine.world.entity.IEntityFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientCharacterAssignment implements IClientVisit {
    private final String entityName;

    public ClientCharacterAssignment(String entityName) {
        this.entityName = entityName;
    }

    @Override
    public List<IServerVisit> visit(IClientWorldHandler handler) throws VisitException {
        handler.setPlayerEntity(entityName);
        return new ArrayList<>();
    }
}
