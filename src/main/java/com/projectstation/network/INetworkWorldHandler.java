package com.projectstation.network;

import io.github.jevaengine.world.World;
import io.github.jevaengine.world.entity.IEntityFactory;

public interface INetworkWorldHandler {

    World getWorld();
    boolean hasWorld();

    boolean isOwner(String entityName);

    IEntityFactory getEntityFactory();
}
