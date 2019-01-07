package com.projectstation.network.entity;

import io.github.jevaengine.util.Nullable;
import io.github.jevaengine.world.entity.IEntity;

import java.util.HashMap;
import java.util.Map;

public class EntityNetworkAdapterMapping<X extends IEntityNetworkAdapter> {
    Map<Class<? extends IEntity>, IEntityNetworkAdapterFactory> factories = new HashMap<>();

    public <T extends Y, Y extends IEntity> void register(Class<T> cls, IEntityNetworkAdapterFactory<X, Y> factory) {
        factories.put(cls, factory);
    }

    @Nullable
    public <T extends IEntity> IEntityNetworkAdapterFactory<X, T> get(Class<T> cls) {
        return factories.containsKey(cls) ? factories.get(cls) : null;
    }
}
