package com.projectstation.network.command.world;

import com.projectstation.network.EntityVisit;
import com.projectstation.network.INetworkWorldHandler;
import com.projectstation.network.VisitException;
import com.projectstation.network.WorldVisit;
import com.projectstation.network.entity.IEntityNetworkAdapter;
import io.github.jevaengine.rpg.entity.character.IRpgCharacter;
import io.github.jevaengine.rpg.item.IItem;
import io.github.jevaengine.rpg.item.IItemFactory;
import io.github.jevaengine.rpg.item.IItemSlot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class RemoveInventoryItemCommand extends EntityVisit<IRpgCharacter> {
    private static final Logger m_logger = LoggerFactory.getLogger(RemoveInventoryItemCommand.class);

    private final int slotIndex;
    public RemoveInventoryItemCommand(String entityName, int slotIndex) {
        super(IRpgCharacter.class, entityName);
        this.slotIndex = slotIndex;
    }

    @Override
    public List<WorldVisit> visitEntity(INetworkWorldHandler handler, IRpgCharacter entity, IEntityNetworkAdapter netEntity, long deltaTime, boolean isOwner) throws VisitException {

        IItemSlot slots[] = entity.getInventory().getSlots();
        if(slotIndex < slots.length && slotIndex >= 0) {
            slots[slotIndex].clear();
        } else
        {
            m_logger.error("Invalid slot index.");
        }

        return new ArrayList<>();
    }
}
