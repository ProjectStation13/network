package com.projectstation.network.command.world;

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
import io.github.jevaengine.rpg.item.usr.UsrWieldTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class EquipItemCommand extends EntityVisit<IRpgCharacter> {
    private static final Logger m_logger = LoggerFactory.getLogger(EquipItemCommand.class);

    private final int slotIndex;

    public EquipItemCommand(String entityName, int slotIndex) {
        super(IRpgCharacter.class, entityName);
        this.slotIndex = slotIndex;
    }

    @Override
    public List<WorldVisit> visitEntity(INetworkWorldHandler handler, IRpgCharacter entity, IEntityNetworkAdapter netEntity, long deltaTime, boolean isOwner) throws VisitException {

        IItemSlot[] slots = entity.getInventory().getSlots();

        if(slotIndex >= slots.length || slotIndex < 0) {
            m_logger.error("Unable to carry out inventory command, invalid slot index.");
            return new ArrayList<>();
        }

        IItemSlot slot = slots[slotIndex];
        IItemStore inventory = entity.getInventory();
        ILoadout loadout = entity.getLoadout();

        if(slot.isEmpty()) {
            m_logger.error("Wield slot is empty.");
            return new ArrayList<>();
        }

        IItem.IWieldTarget[] tgt = slot.getItem().getFunction().getWieldTargets();

        if(tgt.length == 0) {
            m_logger.error("Item has no wield targets.");
            return new ArrayList<>();
        }

        IItemSlot loadoutSlot = loadout.getSlot(tgt[0]);

        if(loadoutSlot == null) {
            m_logger.error("Loadout slot does not exist for this item's wield targets.");
            return new ArrayList<>();
        }

        IItem item = slot.clear();
        if(!loadoutSlot.isEmpty())
            inventory.addItem(loadoutSlot.clear());

        loadoutSlot.setItem(item);

        return new ArrayList<>();
    }
}
