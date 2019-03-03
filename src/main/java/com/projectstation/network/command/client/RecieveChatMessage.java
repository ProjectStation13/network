package com.projectstation.network.command.client;

import com.projectstation.network.IClientVisit;
import com.projectstation.network.IClientWorldHandler;
import com.projectstation.network.IServerVisit;
import com.projectstation.network.VisitException;

import java.util.ArrayList;
import java.util.List;

public class RecieveChatMessage implements IClientVisit {
    private final String message;

    public RecieveChatMessage(String message) {
        this.message = message;
    }

    @Override
    public List<IServerVisit> visit(IClientWorldHandler handler) throws VisitException {
        handler.recieveChatMessage(message);
        return new ArrayList<>();
    }
}
