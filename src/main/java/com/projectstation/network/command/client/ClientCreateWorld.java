package com.projectstation.network.command.client;

import com.projectstation.network.*;
import com.projectstation.network.entity.EntityNetworkAdapterException;
import com.projectstation.network.entity.IEntityNetworkAdapter;
import io.github.jevaengine.world.IWeatherFactory;
import io.github.jevaengine.world.World;
import io.github.jevaengine.world.entity.IEntity;
import io.github.jevaengine.world.entity.IEntityFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientCreateWorld implements IClientVisit {

    private final int worldWidth;
    private final int worldHeight;
    private final float friction;
    private final float metersPerUnit;
    private final float logicPerUnit;

    public ClientCreateWorld(int worldWidth, int worldHeight, float friction, float metersPerUnit, float logicPerUnit) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.friction = friction;
        this.metersPerUnit = metersPerUnit;
        this.logicPerUnit = logicPerUnit;
    }

    @Override
    public List<IServerVisit> visit(IClientWorldHandler handler) throws VisitException {
        World w = new World(worldWidth, worldHeight, friction, metersPerUnit, logicPerUnit, new IWeatherFactory.NullWeather(), handler.getPhysicsWorldFactory(), handler.getEffectMapFactory(), handler.getParallelEntityFactory(), null);
        handler.setWorld(w);

        return new ArrayList<>();
    }
}
