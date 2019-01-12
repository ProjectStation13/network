package com.projectstation.network.command.world;

import com.projectstation.network.EntityVisit;
import com.projectstation.network.INetworkWorldHandler;
import com.projectstation.network.WorldVisit;
import com.projectstation.network.entity.IEntityNetworkAdapter;
import io.github.jevaengine.math.Vector3F;
import io.github.jevaengine.world.Direction;
import io.github.jevaengine.world.entity.IEntity;

import java.util.ArrayList;
import java.util.List;

public class RemoveEntityCommand extends EntityVisit<IEntity> {
    public RemoveEntityCommand(String entityName) {
        super(IEntity.class, entityName);
    }

    @Override
    public List<WorldVisit> visitEntity(INetworkWorldHandler handler, IEntity entity, IEntityNetworkAdapter netEntity, long deltaTime, boolean isOwner) {
        handler.getWorld().removeEntity(entity);

        return new ArrayList<>();
    }
}
