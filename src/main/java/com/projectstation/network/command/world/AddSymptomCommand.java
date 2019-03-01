package com.projectstation.network.command.world;

import com.jevaengine.spacestation.entity.character.SpaceCharacter;
import com.jevaengine.spacestation.entity.character.symptoms.ShellSymptom;
import com.jevaengine.spacestation.item.SpaceCharacterWieldTarget;
import com.projectstation.network.EntityVisit;
import com.projectstation.network.INetworkWorldHandler;
import com.projectstation.network.VisitException;
import com.projectstation.network.WorldVisit;
import com.projectstation.network.entity.IEntityNetworkAdapter;
import io.github.jevaengine.rpg.entity.character.ILoadout;
import io.github.jevaengine.rpg.entity.character.IRpgCharacter;
import io.github.jevaengine.rpg.item.IItem;
import io.github.jevaengine.rpg.item.IItemSlot;
import io.github.jevaengine.rpg.item.IItemStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AddSymptomCommand extends EntityVisit<SpaceCharacter> {
    private static final Logger m_logger = LoggerFactory.getLogger(AddSymptomCommand.class);

    private final String name;
    private final String description;

    public AddSymptomCommand(String entityName, String name, String description) {
        super(SpaceCharacter.class, entityName);
        this.name = name;
        this.description = description;
    }

    @Override
    public List<WorldVisit> visitEntity(INetworkWorldHandler handler, SpaceCharacter entity, IEntityNetworkAdapter netEntity, long deltaTime, boolean isOwner) throws VisitException {
        entity.getStatusResolver().addSymptom(new ShellSymptom(name, description));
        return new ArrayList<>();
    }
}
