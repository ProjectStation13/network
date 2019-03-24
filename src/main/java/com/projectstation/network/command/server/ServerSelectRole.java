package com.projectstation.network.command.server;

import com.jevaengine.spacestation.ui.selectclass.CharacterClassDescription;
import com.projectstation.network.IClientVisit;
import com.projectstation.network.IServerVisit;
import com.projectstation.network.IServerWorldHandler;
import com.projectstation.network.VisitException;
import com.projectstation.network.command.client.ClientRequestRoleSelect;

import java.util.ArrayList;
import java.util.List;

public class ServerSelectRole implements IServerVisit {
    private final CharacterClassDescription desc;

    public ServerSelectRole(CharacterClassDescription desc) {
        this.desc = desc;
    }

    @Override
    public List<IClientVisit> visit(IServerWorldHandler handler) throws VisitException {
        ArrayList<IClientVisit> response = new ArrayList<>();

        if(!handler.selectClass(desc))
            response.add(new ClientRequestRoleSelect(handler.getAvailableRoles()));

        return response;
    }
}
