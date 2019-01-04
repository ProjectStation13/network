package com.projectstation.network.entity;

import io.github.jevaengine.util.Nullable;
import io.github.jevaengine.world.entity.IEntity;

import java.util.HashMap;
import java.util.Map;

public class EntityNetworkAdapterMapping {
    Map<Class<? extends IEntity>, IEntityNetworkAdapterFactory> factories = new HashMap<>();

    public void register(Class<? extends IEntity> cls, IEntityNetworkAdapterFactory factory) {
        factories.put(cls, factory);
    }

    @Nullable
    public IEntityNetworkAdapterFactory get(Class<? extends IEntity> cls) {
        return factories.containsKey(cls) ? factories.get(cls) : null;
    }
}
