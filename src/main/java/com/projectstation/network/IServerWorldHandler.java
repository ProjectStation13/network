package com.projectstation.network;

import com.projectstation.network.entity.IEntityNetworkAdapter;
import io.github.jevaengine.world.World;
import io.github.jevaengine.world.entity.IEntityFactory;

public interface IServerWorldHandler extends INetworkWorldHandler {
    void setClientTime(long time);
    long getClientTime();

    void authorizeOwnership(String entityName);
    void unauthorizeOwnership(String entityName);

    boolean isOwner(String entityName);

    IEntityNetworkAdapter getAdapter(String entityName);
}
