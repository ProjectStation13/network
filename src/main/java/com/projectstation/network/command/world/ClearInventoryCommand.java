package com.projectstation.network.command.world;

import com.projectstation.network.EntityVisit;
import com.projectstation.network.INetworkWorldHandler;
import com.projectstation.network.VisitException;
import com.projectstation.network.WorldVisit;
import com.projectstation.network.entity.IEntityNetworkAdapter;
import io.github.jevaengine.rpg.entity.character.IRpgCharacter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ClearInventoryCommand extends EntityVisit<IRpgCharacter> {
    private static final Logger m_logger = LoggerFactory.getLogger(ClearInventoryCommand.class);

    public ClearInventoryCommand(String entityName) {
        super(IRpgCharacter.class, entityName);
    }

    @Override
    public List<WorldVisit> visitEntity(INetworkWorldHandler handler, IRpgCharacter entity, IEntityNetworkAdapter netEntity, long deltaTime, boolean isOwner) throws VisitException {

        entity.getInventory().clear();

        return new ArrayList<>();
    }
}
