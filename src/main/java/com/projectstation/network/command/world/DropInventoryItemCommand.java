package com.projectstation.network.command.world;

import com.jevaengine.spacestation.entity.ItemDrop;
import com.projectstation.network.EntityVisit;
import com.projectstation.network.INetworkWorldHandler;
import com.projectstation.network.VisitException;
import com.projectstation.network.WorldVisit;
import com.projectstation.network.entity.IEntityNetworkAdapter;
import io.github.jevaengine.math.Vector3F;
import io.github.jevaengine.rpg.entity.character.IRpgCharacter;
import io.github.jevaengine.rpg.item.IItem;
import io.github.jevaengine.rpg.item.IItemFactory;
import io.github.jevaengine.rpg.item.IItemSlot;
import io.github.jevaengine.world.entity.IEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class DropInventoryItemCommand extends EntityVisit<IRpgCharacter> {
    private static final Logger m_logger = LoggerFactory.getLogger(ClearInventoryCommand.class);

    private final int slotIndex;

    public DropInventoryItemCommand(String entityName, int slotIndex) {
        super(IRpgCharacter.class, entityName);
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

        if(!slots[slotIndex].isEmpty()) {
            IItem item = slots[slotIndex].clear();
            IEntity itemDrop = new ItemDrop(item);
            entity.getWorld().addEntity(itemDrop);
            itemDrop.getBody().setLocation(entity.getBody().getLocation().add(new Vector3F(0, 0, -.01F)));
        }


        return new ArrayList<>();
    }
}
