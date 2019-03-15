package com.projectstation.network.command.world;

import com.projectstation.network.EntityVisit;
import com.projectstation.network.INetworkWorldHandler;
import com.projectstation.network.WorldVisit;
import com.projectstation.network.entity.IEntityNetworkAdapter;
import io.github.jevaengine.math.Vector2F;
import io.github.jevaengine.math.Vector3F;
import io.github.jevaengine.world.Direction;
import io.github.jevaengine.world.entity.IEntity;
import io.github.jevaengine.world.steering.*;

import java.util.ArrayList;
import java.util.List;

public class SetEntityVelocityCommand extends EntityVisit<IEntity> {
    private final Vector3F location;
    private final Vector3F velocity;

    private static final float SLAVE_OUT_OF_SYNC_DISTANCE = 3.0f;
    private static final float SLAVE_IDLE_OF_SYNC_DISTANCE = 0.5f;
    private static final float OWNER_OUT_OF_SYNC_DISTANCE = 6.0f;

    public SetEntityVelocityCommand(String entityName, Vector3F location, Vector3F velocity) {
        super(IEntity.class, entityName);
        this.location = location;
        this.velocity = velocity;
    }

    @Override
    public List<WorldVisit> visitEntity(INetworkWorldHandler handler, IEntity entity, IEntityNetworkAdapter netEntity, long deltaTime, boolean isOwner) {

        if(Math.abs(location.z - entity.getBody().getLocation().z) > 0.01f) {
            Vector3F newLoc = entity.getBody().getLocation();
            newLoc.z = location.z;
            entity.getBody().setLocation(newLoc);
        }

        Vector2F expectedLocation = location.getXy().add(velocity.getXy().multiply(deltaTime / 1000.0f));
        Vector2F deltaPos = expectedLocation.difference(entity.getBody().getLocation().getXy());

        //If I am owner of entity, then I control location / velocity details, however I must still sync location...
        if(isOwner) {
            if(deltaPos.getLength() > OWNER_OUT_OF_SYNC_DISTANCE)
                entity.getBody().setLocation(new Vector3F(expectedLocation, entity.getBody().getLocation().z));
        } else {
            Vector2F xyVel = this.velocity.getXy();

            if (xyVel.isZero()) {
                if (deltaPos.getLength() > SLAVE_IDLE_OF_SYNC_DISTANCE) {
                    netEntity.setSteeringBehaviour(new SeekBehavior(entity.getBody(), 1.0f, 0.1f, new PointSubject(expectedLocation)));
                    netEntity.setSpeed(-1);
                } else {
                    netEntity.setSteeringBehaviour(new ISteeringBehavior.NullSteeringBehavior());
                }
            } else {
                if (!isServer() && deltaPos.getLength() > SLAVE_OUT_OF_SYNC_DISTANCE) {
                    entity.getBody().setLocation(location);
                } else {
                    if (deltaPos.getLength() > 5)
                        xyVel = xyVel.add(deltaPos.normalize().multiply(12));
                    else if (deltaPos.getLength() > 3)
                        xyVel = xyVel.add(deltaPos.normalize().multiply(2));
                    else if (deltaPos.getLength() > 1)
                        xyVel = xyVel.add(deltaPos.normalize().multiply(0.5f));
                }

                netEntity.setSteeringBehaviour(new VectorSteeringBehavior(xyVel.isZero() ? new Vector2F() : xyVel.normalize()));
                netEntity.setSpeed(xyVel.getLength());
            }
        }
        return new ArrayList<>();
    }

    @Override
    public boolean overrides(WorldVisit newVisit) {
        if(!(newVisit instanceof SetEntityVelocityCommand))
            return false;

        SetEntityVelocityCommand c = (SetEntityVelocityCommand)newVisit;

        if(c.getEntityName().compareTo(getEntityName()) == 0) {
            return true;
        }

        return false;
    }
}
