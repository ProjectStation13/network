package com.projectstation.network.command.client;

import com.projectstation.network.IClientVisit;
import com.projectstation.network.IClientWorldHandler;
import com.projectstation.network.IServerVisit;
import com.projectstation.network.VisitException;

import java.util.ArrayList;
import java.util.List;

public class GiveEntityNickname implements IClientVisit {
    private final String entityName;
    private final String nickname;

    public GiveEntityNickname(String entityName, String nickname) {
        this.entityName = entityName;
        this.nickname = nickname;
    }

    @Override
    public List<IServerVisit> visit(IClientWorldHandler handler) throws VisitException {
        handler.setEntityNickname(entityName, nickname);

        return new ArrayList<>();
    }
}
