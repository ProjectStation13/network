package com.projectstation.network.entity;

import io.github.jevaengine.world.entity.IEntity;

public interface IEntityNetworkAdapterFactory {
    IEntityNetworkAdapter create(IEntity e, EntityConfigurationDetails config, IEntityNetworlAdapterHost pr);

    interface IEntityNetworlAdapterHost {
        void poll();
        boolean isOwner();
    }
}
