package com.projectstation.network.command.world;

import com.jevaengine.spacestation.entity.character.SpaceCharacter;
import com.jevaengine.spacestation.entity.character.symptoms.ShellSymptom;
import com.projectstation.network.EntityVisit;
import com.projectstation.network.INetworkWorldHandler;
import com.projectstation.network.VisitException;
import com.projectstation.network.WorldVisit;
import com.projectstation.network.entity.IEntityNetworkAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class RemoveSymptomCommand extends EntityVisit<SpaceCharacter> {
    private static final Logger m_logger = LoggerFactory.getLogger(RemoveSymptomCommand.class);

    private final String name;

    public RemoveSymptomCommand(String entityName, String name) {
        super(SpaceCharacter.class, entityName);
        this.name = name;
    }

    @Override
    public List<WorldVisit> visitEntity(INetworkWorldHandler handler, SpaceCharacter entity, IEntityNetworkAdapter netEntity, long deltaTime, boolean isOwner) throws VisitException {
        entity.getStatusResolver().removeSymptom(name);
        return new ArrayList<>();
    }
}
