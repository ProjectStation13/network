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

public class AddInventoryItemCommand extends EntityVisit<IRpgCharacter> {
    private static final Logger m_logger = LoggerFactory.getLogger(ClearInventoryCommand.class);

    private final String item;
    private final int slotIndex;

    public AddInventoryItemCommand(String entityName, String item, int slotIndex) {
        super(IRpgCharacter.class, entityName);
        this.item = item;
        this.slotIndex = slotIndex;
    }

    @Override
    public List<WorldVisit> visitEntity(INetworkWorldHandler handler, IRpgCharacter entity, IEntityNetworkAdapter netEntity, long deltaTime, boolean isOwner) throws VisitException {

        IItemSlot[] slots = entity.getInventory().getSlots();

        if(slotIndex < 0 || slotIndex >= slots.length)
        {
            m_logger.error("Invalid slot index.");
            return new ArrayList<>();
        }

        try {
            IItem i = handler.getItemFactory().create(new URI(item));
            slots[slotIndex].setItem(i);
        } catch(IItemFactory.ItemContructionException | URISyntaxException ex) {
            m_logger.error("Error adding item to player inventory.", ex);
        }

        return new ArrayList<>();
    }
}
