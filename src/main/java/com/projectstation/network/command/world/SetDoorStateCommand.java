package com.projectstation.network.command.world;

import com.projectstation.network.EntityVisit;
import com.projectstation.network.INetworkWorldHandler;
import com.projectstation.network.VisitException;
import com.projectstation.network.WorldVisit;
import com.projectstation.network.entity.IEntityNetworkAdapter;
import io.github.jevaengine.rpg.entity.Door;

import java.util.ArrayList;
import java.util.List;

public class SetDoorStateCommand extends EntityVisit<Door> {
    private final boolean isOpen;

    public SetDoorStateCommand(String doorName, boolean isOpen) {
        super(Door.class, doorName);
        this.isOpen = isOpen;
    }

    @Override
    public List<WorldVisit> visitEntity(INetworkWorldHandler handler, Door entity, IEntityNetworkAdapter netEntity, long deltaTime, boolean isOwner) throws VisitException {

        boolean locked = entity.isLocked();
        entity.unlock();

        if(isOpen)
            entity.open();
        else
            entity.close();

        if(locked)
            entity.lock();

        return new ArrayList<>();
    }

    @Override
    public boolean overrides(WorldVisit v) {
        if(!(v instanceof SetDoorStateCommand))
            return false;

        //Same entity?
        return ((SetDoorStateCommand)v).getEntityName().compareTo(getEntityName()) == 0;
    }
}
