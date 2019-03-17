package com.projectstation.network.command.world;

import com.projectstation.network.EntityVisit;
import com.projectstation.network.IClientVisit;
import com.projectstation.network.INetworkWorldHandler;
import com.projectstation.network.WorldVisit;
import com.projectstation.network.entity.IEntityNetworkAdapter;
import io.github.jevaengine.math.Vector3F;
import io.github.jevaengine.world.Direction;
import io.github.jevaengine.world.entity.IEntity;

import java.util.ArrayList;
import java.util.List;

public class SetEntityPositionCommand extends EntityVisit<IEntity> {
    private final Vector3F location;
    private final Direction direction;

    public SetEntityPositionCommand(String entityName, Vector3F location, Direction direction) {
        super(IEntity.class, entityName);
        this.location = location;
        this.direction = direction;
    }

    @Override
    public List<WorldVisit> visitEntity(INetworkWorldHandler handler, IEntity entity, IEntityNetworkAdapter netEntity, long deltaTime, boolean isOwner) {
        entity.getBody().setDirection(direction);
        entity.getBody().setLocation(location);

        return new ArrayList<>();
    }

    @Override
    public boolean overrides(WorldVisit v) {
        if(!(v instanceof SetEntityPositionCommand))
            return false;

        //Same entity?
        return ((SetEntityPositionCommand)v).getEntityName().compareTo(getEntityName()) == 0;
    }
}
