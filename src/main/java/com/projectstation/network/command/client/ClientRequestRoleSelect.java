package com.projectstation.network.command.client;

import com.jevaengine.spacestation.ui.selectclass.CharacterClassDescription;
import com.projectstation.network.IClientVisit;
import com.projectstation.network.IClientWorldHandler;
import com.projectstation.network.IServerVisit;
import com.projectstation.network.VisitException;

import java.util.ArrayList;
import java.util.List;

public class ClientRequestRoleSelect implements IClientVisit {

    private final List<CharacterClassDescription> available;

    public ClientRequestRoleSelect(List<CharacterClassDescription> available) {
        this.available = new ArrayList<>(available);
    }

    @Override
    public List<IServerVisit> visit(IClientWorldHandler handler) throws VisitException {
        handler.requestRoleSelect(available);

        return new ArrayList<>();
    }
}
