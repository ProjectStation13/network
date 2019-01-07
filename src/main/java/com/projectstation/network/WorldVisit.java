package com.projectstation.network;

import com.projectstation.network.entity.IEntityNetworkAdapter;
import io.github.jevaengine.world.World;
import io.github.jevaengine.world.entity.IEntityFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class WorldVisit implements Serializable {

    public abstract List<WorldVisit> visit(INetworkWorldHandler handler, IEntityFactory entityFactory, World world, long timeElapsedSinceDispatch, boolean isServer) throws VisitException;

    public boolean isPriorRedundant(WorldVisit prior) {
        return false;
    }
}
