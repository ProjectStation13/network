package com.projectstation.network.entity;

import com.projectstation.network.IPollRequestHost;
import io.github.jevaengine.world.entity.IEntity;

public interface IEntityNetworkAdapterFactory<Y extends IEntityNetworkAdapter, T extends IEntity> {
    Y create(T e, EntityConfigurationDetails config, IEntityNetworkAdapterHost pr);

    interface IEntityNetworkAdapterHost extends IPollRequestHost {
        boolean isOwner();
    }
}
