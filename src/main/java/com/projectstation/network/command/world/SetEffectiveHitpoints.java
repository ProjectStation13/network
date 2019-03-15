package com.projectstation.network.command.world;

import com.jevaengine.spacestation.entity.character.ISpaceCharacterStatusResolver;
import com.jevaengine.spacestation.entity.character.SpaceCharacter;
import com.jevaengine.spacestation.entity.character.symptoms.ShellSymptom;
import com.projectstation.network.*;
import com.projectstation.network.entity.IEntityNetworkAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SetEffectiveHitpoints extends EntityVisit<SpaceCharacter> {
    private static final Logger m_logger = LoggerFactory.getLogger(SetEffectiveHitpoints.class);

    private final float hitpoints;

    public SetEffectiveHitpoints(String entityName, float hitpoints) {
        super(SpaceCharacter.class, entityName);
        this.hitpoints = hitpoints;
    }

    @Override
    public List<WorldVisit> visitEntity(INetworkWorldHandler handler, SpaceCharacter entity, IEntityNetworkAdapter netEntity, long deltaTime, boolean isOwner) throws VisitException {
        ISpaceCharacterStatusResolver resolver = entity.getStatusResolver();

        if(resolver instanceof IClientSpaceCharacterStatusResolver)
            ((IClientSpaceCharacterStatusResolver)resolver).setEffectiveHitpoints(hitpoints);
        else
            m_logger.error("Cannot interface to status resolver to set hitpoints.");

        return new ArrayList<>();
    }


    @Override
    public boolean overrides(WorldVisit v) {
        if(!(v instanceof SetEffectiveHitpoints))
            return false;

        //Same entity?
        return ((SetEffectiveHitpoints)v).getEntityName().compareTo(getEntityName()) == 0;
    }
}
