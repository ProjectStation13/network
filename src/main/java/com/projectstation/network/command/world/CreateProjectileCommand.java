package com.projectstation.network.command.world;

import com.jevaengine.spacestation.entity.projectile.IProjectile;
import com.projectstation.network.INetworkWorldHandler;
import com.projectstation.network.VisitException;
import com.projectstation.network.WorldVisit;
import io.github.jevaengine.config.IImmutableVariable;
import io.github.jevaengine.config.ValueSerializationException;
import io.github.jevaengine.config.json.JsonVariable;
import io.github.jevaengine.math.Vector3F;
import io.github.jevaengine.world.Direction;
import io.github.jevaengine.world.World;
import io.github.jevaengine.world.entity.IEntity;
import io.github.jevaengine.world.entity.IEntityFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CreateProjectileCommand extends WorldVisit {
    private final String entityName;
    private final String entityType;
    private final String configContext;
    private final String entityConfig;
    private final Vector3F location;
    private final Vector3F direction;
    private final String ignore;

    public CreateProjectileCommand(String ignore, String entityName, String entityType, String configContext, String entityConfig, Vector3F location, Vector3F direction) {
        this.entityConfig = entityConfig;
        this.configContext = configContext;
        this.entityName = entityName;
        this.entityType = entityType;
        this.location = location;
        this.direction = direction;
        this.ignore = ignore;
    }

    @Override
    public List<WorldVisit> visit(INetworkWorldHandler handler, IEntityFactory entityFactory, World world, long deltaTime, boolean isServer) throws VisitException {
        InputStream is = new ByteArrayInputStream(entityConfig.getBytes(StandardCharsets.UTF_8));

        try {
            IImmutableVariable confg = JsonVariable.create(is);
            IEntity entity = entityFactory.create(entityType, entityName, new URI(configContext), confg);
            if(entity instanceof IProjectile) {
                world.addEntity(entity);
                IProjectile projectile = (IProjectile)entity;
                projectile.setTravelDirection(direction);
                projectile.getBody().setLocation(location.add(direction.multiply(projectile.getSpeed() * deltaTime / 1000.0f)));
                projectile.setIgnore(world.getEntities().getByName(IEntity.class, ignore));
                projectile.setCollidable(false);
            }
        } catch (ValueSerializationException | IOException | IEntityFactory.EntityConstructionException | URISyntaxException ex) {
            throw new VisitException(ex);
        }

        return new ArrayList<>();
    }
}
