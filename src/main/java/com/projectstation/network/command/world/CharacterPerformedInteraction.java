package com.projectstation.network.command.world;

import com.jevaengine.spacestation.entity.IInteractableEntity;
import com.jevaengine.spacestation.ui.playing.WorldInteractionBehaviorInjector;
import com.projectstation.network.EntityVisit;
import com.projectstation.network.INetworkWorldHandler;
import com.projectstation.network.VisitException;
import com.projectstation.network.WorldVisit;
import com.projectstation.network.entity.IEntityNetworkAdapter;
import io.github.jevaengine.math.Vector2F;
import io.github.jevaengine.math.Vector3F;
import io.github.jevaengine.rpg.entity.character.IRpgCharacter;
import io.github.jevaengine.world.Direction;
import io.github.jevaengine.world.search.RadialSearchFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CharacterPerformedInteraction extends EntityVisit<IRpgCharacter> {
    private static final Logger logger = LoggerFactory.getLogger(CharacterPerformedInteraction.class);
    private final Vector3F playerLocation;
    private final Direction playerDirection;

    public CharacterPerformedInteraction(String subject, Vector3F playerLocation, Direction direction) {
        super(IRpgCharacter.class, subject);
        this.playerDirection = direction;
        this.playerLocation = playerLocation;
    }

    @Override
    public List<WorldVisit> visitEntity(INetworkWorldHandler handler, IRpgCharacter entity, IEntityNetworkAdapter netEntity, long deltaTime, boolean isOwner) throws VisitException {

        float interactionDistance = entity.getBody().getBoundingCircle().radius * 3.0F;
        Vector2F playerPos = playerLocation.getXy();
        IInteractableEntity[] interactable = entity.getWorld().getEntities().search(IInteractableEntity.class,
                new RadialSearchFilter<IInteractableEntity>(playerPos, interactionDistance));


        for (IInteractableEntity e : interactable) {
            if (Direction.fromVector(e.getBody().getLocation().getXy().difference(playerPos)) == playerDirection) {
                e.interactedWith(entity);
                break;
            }
        }

        return new ArrayList<>();
    }
}
