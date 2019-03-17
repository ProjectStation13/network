package com.projectstation.network.command.world;

import com.jevaengine.spacestation.entity.projectile.IProjectile;
import com.projectstation.network.EntityVisit;
import com.projectstation.network.INetworkWorldHandler;
import com.projectstation.network.VisitException;
import com.projectstation.network.WorldVisit;
import com.projectstation.network.entity.IEntityNetworkAdapter;
import io.github.jevaengine.math.Vector3F;
import io.github.jevaengine.rpg.entity.Door;
import io.github.jevaengine.world.entity.IEntity;

import java.util.ArrayList;
import java.util.List;

public class UpdateProjectileCommand extends EntityVisit<IProjectile> {
    private final Vector3F direction;
    private final Vector3F location;
    private final String ignore;

    public UpdateProjectileCommand(String name, Vector3F direction, Vector3F location, String ignore) {
        super(IProjectile.class, name);
        this.direction = direction;
        this.location = location;
        this.ignore = ignore;
    }

    @Override
    public List<WorldVisit> visitEntity(INetworkWorldHandler handler, IProjectile entity, IEntityNetworkAdapter netEntity, long deltaTime, boolean isOwner) throws VisitException {
        entity.setIgnore(handler.getWorld().getEntities().getByName(IEntity.class, ignore));
        entity.getBody().setLocation(location.add(direction.multiply(entity.getSpeed() * deltaTime / 1000)));
        entity.setTravelDirection(direction);
        return new ArrayList<>();
    }

    @Override
    public boolean trackHistory() {
        return false;
    }
}
