package com.projectstation.network;

import com.projectstation.network.entity.IEntityNetworkAdapter;
import io.github.jevaengine.rpg.item.IItemFactory;
import io.github.jevaengine.world.World;
import io.github.jevaengine.world.entity.IEntityFactory;

public interface INetworkWorldHandler {

    World getWorld();
    boolean hasWorld();

    boolean isOwner(String entityName);

    IEntityFactory getEntityFactory();
    IItemFactory getItemFactory();

    IEntityNetworkAdapter getAdapter(String entityName);
}
