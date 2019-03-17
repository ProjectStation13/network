package com.projectstation.network.command.world;

import com.projectstation.network.EntityVisit;
import com.projectstation.network.INetworkWorldHandler;
import com.projectstation.network.WorldVisit;
import com.projectstation.network.entity.IEntityNetworkAdapter;
import io.github.jevaengine.rpg.entity.Door;
import io.github.jevaengine.world.entity.IEntity;
import io.github.jevaengine.world.scene.model.IAnimationSceneModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SetEntityAnimationState extends EntityVisit<IEntity> {
    private static final Logger logger = LoggerFactory.getLogger(SetEntityAnimationState.class);

    private final String animationName;
    private final IAnimationSceneModel.AnimationSceneModelAnimationState state;

    public SetEntityAnimationState(String name, String animationName, IAnimationSceneModel.AnimationSceneModelAnimationState state) {
        super(IEntity.class, name);
        this.animationName = animationName;
        this.state = state;
    }

    @Override
    public List<WorldVisit> visitEntity(INetworkWorldHandler handler, IEntity entity, IEntityNetworkAdapter netEntity, long deltaTime, boolean isOwner) {
        if(!(entity.getModel() instanceof IAnimationSceneModel)) {
            logger.error("Entity model is not an animation scene model.");
            return new ArrayList<>();
        }

        IAnimationSceneModel model = (IAnimationSceneModel) entity.getModel();

        if(!model.hasAnimation(animationName)) {
            logger.error("Entity does not have animation " + animationName + ", Ignoring command.");
            return new ArrayList<>();
        }

        IAnimationSceneModel.IAnimationSceneModelAnimation a = ((IAnimationSceneModel)entity.getModel()).getAnimation(animationName);

        a.setState(state);

        return new ArrayList<>();
    }

    @Override
    public boolean overrides(WorldVisit v) {
        if(!(v instanceof SetEntityAnimationState))
            return false;

        SetEntityAnimationState other = (SetEntityAnimationState)v;
        //Same entity?
        return other.getEntityName().compareTo(getEntityName()) == 0 && other.animationName.compareTo(animationName) == 0;
    }
}
