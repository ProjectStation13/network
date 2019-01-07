package com.projectstation.network;

import com.projectstation.network.entity.IEntityNetworkAdapter;
import io.github.jevaengine.world.World;
import io.github.jevaengine.world.entity.IEntityFactory;

public interface INetworkWorldHandler {

    World getWorld();
    boolean hasWorld();

    boolean isOwner(String entityName);

    IEntityFactory getEntityFactory();

    IEntityNetworkAdapter getAdapter(String entityName);
}
