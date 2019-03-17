package com.projectstation.network.command.world;

import com.jevaengine.spacestation.entity.IInteractableEntity;
import com.projectstation.network.EntityVisit;
import com.projectstation.network.INetworkWorldHandler;
import com.projectstation.network.VisitException;
import com.projectstation.network.WorldVisit;
import com.projectstation.network.entity.IEntityNetworkAdapter;
import io.github.jevaengine.rpg.entity.Door;
import io.github.jevaengine.rpg.entity.character.IRpgCharacter;
import io.github.jevaengine.world.entity.IEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CharacterInteractedWith extends EntityVisit<IInteractableEntity> {
    private static final Logger logger = LoggerFactory.getLogger(CharacterInteractedWith.class);
    private final String interacter;

    public CharacterInteractedWith(String subject, String interacter) {
        super(IInteractableEntity.class, subject);
        this.interacter = interacter;
    }

    @Override
    public List<WorldVisit> visitEntity(INetworkWorldHandler handler, IInteractableEntity entity, IEntityNetworkAdapter netEntity, long deltaTime, boolean isOwner) throws VisitException {

        IRpgCharacter interacterEntity = handler.getWorld().getEntities().getByName(IRpgCharacter.class, interacter);
        if(interacterEntity == null) {
            logger.error("Unable to find interacter entity to carry out interaction with.");
        }

        entity.interactedWith(interacterEntity);

        return new ArrayList<>();
    }
    
    @Override
    public boolean trackHistory() {
        return false;
    }
}
