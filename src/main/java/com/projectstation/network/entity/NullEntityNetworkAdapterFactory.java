package com.projectstation.network.entity;

import com.projectstation.network.WorldVisit;
import io.github.jevaengine.math.Vector2F;
import io.github.jevaengine.world.entity.IEntity;
import io.github.jevaengine.world.steering.ISteeringBehavior;

import java.util.ArrayList;
import java.util.List;

public class NullEntityNetworkAdapterFactory implements IEntityNetworkAdapterFactory {
    @Override
    public IEntityNetworkAdapter create(IEntity e, EntityConfigurationDetails config, IEntityNetworlAdapterHost pr) {
        return new NullEntityNetworkAdapter();
    }
}

class NullEntityNetworkAdapter implements IEntityNetworkAdapter {
    @Override
    public List<WorldVisit> createInitializeSteps() throws EntityNetworkAdapterException {
        return new ArrayList<>();
    }

    @Override
    public List<WorldVisit> pollDelta(int deltaTime) throws EntityNetworkAdapterException {
        return new ArrayList<WorldVisit>();
    }

    @Override
    public void setSteeringBehaviour(ISteeringBehavior behaviour) {

    }

    @Override
    public void setSpeed(float speed) {

    }

    @Override
    public boolean isOwner() {
        return false;
    }
}
