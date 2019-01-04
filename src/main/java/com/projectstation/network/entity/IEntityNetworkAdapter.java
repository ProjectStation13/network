package com.projectstation.network.entity;

import com.projectstation.network.WorldVisit;
import io.github.jevaengine.math.Vector2F;
import io.github.jevaengine.world.steering.ISteeringBehavior;

import java.util.List;

public interface IEntityNetworkAdapter {

    List<WorldVisit> createInitializeSteps() throws EntityNetworkAdapterException;
    List<WorldVisit> pollDelta(int deltaTime) throws EntityNetworkAdapterException;

    void setSteeringBehaviour(ISteeringBehavior behaviour);
    void setSpeed(float speed);

    boolean isOwner();
}
