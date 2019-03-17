package com.projectstation.network.command.world;

import com.projectstation.network.EntityVisit;
import com.projectstation.network.INetworkWorldHandler;
import com.projectstation.network.WorldVisit;
import com.projectstation.network.entity.IEntityNetworkAdapter;
import io.github.jevaengine.world.Direction;
import io.github.jevaengine.world.entity.IEntity;
import io.github.jevaengine.world.scene.model.IAnimationSceneModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SetEntityAnimationDirection extends EntityVisit<IEntity> {
    private static final Logger logger = LoggerFactory.getLogger(SetEntityAnimationDirection.class);

    private final Direction direction;

    public SetEntityAnimationDirection(String name, Direction direction) {
        super(IEntity.class, name);
        this.direction = direction;
    }

    @Override
    public List<WorldVisit> visitEntity(INetworkWorldHandler handler, IEntity entity, IEntityNetworkAdapter netEntity, long deltaTime, boolean isOwner) {
        if(!(entity.getModel() instanceof IAnimationSceneModel)) {
            logger.error("Entity model is not an animation scene model.");
            return new ArrayList<>();
        }

        IAnimationSceneModel model = (IAnimationSceneModel) entity.getModel();

        model.setDirection(direction);

        return new ArrayList<>();
    }

    @Override
    public boolean overrides(WorldVisit v) {
        if(!(v instanceof SetEntityAnimationDirection))
            return false;

        //Same entity?
        return ((SetEntityAnimationDirection)v).getEntityName().compareTo(getEntityName()) == 0;
    }
}
