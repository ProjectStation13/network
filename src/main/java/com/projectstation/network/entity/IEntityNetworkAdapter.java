package com.projectstation.network.entity;

import com.projectstation.network.WorldVisit;
import io.github.jevaengine.world.steering.ISteeringBehavior;

import java.util.List;

public interface IEntityNetworkAdapter {

    void setSteeringBehaviour(ISteeringBehavior behaviour);
    void setSpeed(float speed);

    boolean isOwner();
}
