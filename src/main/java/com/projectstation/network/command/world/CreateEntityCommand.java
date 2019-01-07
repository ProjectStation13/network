package com.projectstation.network.command.world;

import com.projectstation.network.INetworkWorldHandler;
import com.projectstation.network.VisitException;
import com.projectstation.network.WorldVisit;
import com.projectstation.network.entity.IEntityNetworkAdapter;
import com.sun.imageio.plugins.common.InputStreamAdapter;
import io.github.jevaengine.config.IImmutableVariable;
import io.github.jevaengine.config.ValueSerializationException;
import io.github.jevaengine.config.json.JsonConfigurationFactory;
import io.github.jevaengine.config.json.JsonVariable;
import io.github.jevaengine.math.Vector3F;
import io.github.jevaengine.world.Direction;
import io.github.jevaengine.world.World;
import io.github.jevaengine.world.entity.IEntity;
import io.github.jevaengine.world.entity.IEntityFactory;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreateEntityCommand extends WorldVisit {
    private final String entityName;
    private final String entityType;
    private final String configContext;
    private final String entityConfig;
    private final Vector3F location;
    private final Direction direction;

    public CreateEntityCommand(String entityName, String entityType, String configContext, String entityConfig, Vector3F location, Direction direction) {
        this.entityConfig = entityConfig;
        this.configContext = configContext;
        this.entityName = entityName;
        this.entityType = entityType;
        this.location = location;
        this.direction = direction;
    }

    @Override
    public List<WorldVisit> visit(INetworkWorldHandler handler, IEntityFactory entityFactory, World world, long deltaTime, boolean isServer) throws VisitException {
        InputStream is = new ByteArrayInputStream(entityConfig.getBytes(StandardCharsets.UTF_8));

        try {
            IImmutableVariable confg = JsonVariable.create(is);
            IEntity entity = entityFactory.create(entityType, entityName, new URI(configContext), confg);
            world.addEntity(entity);

            entity.getBody().setDirection(direction);
            entity.getBody().setLocation(location);
        } catch (ValueSerializationException | IOException | IEntityFactory.EntityConstructionException | URISyntaxException ex) {
            throw new VisitException(ex);
        }

        return new ArrayList<>();
    }
}
