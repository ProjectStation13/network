package com.projectstation.network;

import com.projectstation.network.entity.IEntityNetworkAdapter;
import io.github.jevaengine.util.Nullable;
import io.github.jevaengine.world.World;
import io.github.jevaengine.world.entity.IEntity;
import io.github.jevaengine.world.entity.IEntityFactory;

import java.util.List;
import java.util.Map;

public abstract class EntityVisit<T extends IEntity> extends WorldVisit {

    private final String entityName;
    private final Class<T> cls;
    private transient boolean isServer;

    public EntityVisit(Class<T> cls, String entityName) {
        this.cls = cls;
        this.entityName = entityName;
    }

    protected String getEntityName() {
        return entityName;
    }

    public boolean isServer() {
        return this.isServer;
    }

    @Override
    public final List<WorldVisit> visit(INetworkWorldHandler handler, IEntityFactory entityFactory, World world, long deltaTime, boolean isServer) throws VisitException {
        T entity = world.getEntities().getByName(cls, entityName);

        if(entity == null) {
            throw new VisitException("Entity of class " + cls.getName() + " and name " + entityName + " could not be found.");
        }

        IEntityNetworkAdapter netAdapter = handler.getAdapter(entityName);
        boolean isOwner = handler.isOwner(entityName);
        this.isServer = isServer;
        return visitEntity(handler, entity, netAdapter, deltaTime, isOwner);
    }

    public abstract List<WorldVisit> visitEntity(INetworkWorldHandler handler, T entity, @Nullable IEntityNetworkAdapter netEntity, long deltaTime, boolean isOwner) throws VisitException;
}
