package com.projectstation.network.command.server;

import com.projectstation.network.IClientVisit;
import com.projectstation.network.IServerVisit;
import com.projectstation.network.IServerWorldHandler;
import com.projectstation.network.VisitException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class NewChatMessage implements IServerVisit {
    private final String message;

    public NewChatMessage(String message) {
        this.message = message;
    }

    @Override
    public List<IClientVisit> visit(IServerWorldHandler handler) throws VisitException {
        handler.transmitChatMessage(message);

        return new ArrayList<>();
    }
}
