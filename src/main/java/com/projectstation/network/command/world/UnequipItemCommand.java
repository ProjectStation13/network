package com.projectstation.network.command.world;

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

public class UnequipItemCommand extends EntityVisit<IRpgCharacter> {
    private static final Logger m_logger = LoggerFactory.getLogger(UnequipItemCommand.class);

    private final String wieldTarget;

    public UnequipItemCommand(String entityName, IItem.IWieldTarget wieldTarget) {
        super(IRpgCharacter.class, entityName);
        this.wieldTarget = wieldTarget.getName();
    }

    @Override
    public List<WorldVisit> visitEntity(INetworkWorldHandler handler, IRpgCharacter entity, IEntityNetworkAdapter netEntity, long deltaTime, boolean isOwner) throws VisitException {

        IItem.IWieldTarget target = null;

        for(IItem.IWieldTarget t : SpaceCharacterWieldTarget.values()) {
            if (t.getName().compareTo(wieldTarget) == 0)
            {
                target = t;
                break;
            }
        }

        if(target == null) {
            m_logger.error("Unable to carry out loadout command, wield target not found.");
            return new ArrayList<>();
        }

        IItemStore inventory = entity.getInventory();
        ILoadout loadout = entity.getLoadout();
        IItemSlot slot = loadout.getSlot(target);
        if(!slot.isEmpty() && inventory.addItem(slot.getItem())) {
            loadout.unequip(target);
        }

        return new ArrayList<>();
    }
}
