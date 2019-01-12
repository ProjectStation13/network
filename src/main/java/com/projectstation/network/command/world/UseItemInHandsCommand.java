package com.projectstation.network.command.world;

import com.projectstation.network.EntityVisit;
import com.projectstation.network.INetworkWorldHandler;
import com.projectstation.network.VisitException;
import com.projectstation.network.WorldVisit;
import com.projectstation.network.entity.IEntityNetworkAdapter;
import io.github.jevaengine.rpg.entity.character.IRpgCharacter;
import io.github.jevaengine.rpg.item.IItem;
import io.github.jevaengine.rpg.item.IItemSlot;
import io.github.jevaengine.rpg.item.usr.UsrWieldTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class UseItemInHandsCommand extends EntityVisit<IRpgCharacter> {
    private static final Logger m_logger = LoggerFactory.getLogger(ClearInventoryCommand.class);

    public UseItemInHandsCommand(String entityName) {
        super(IRpgCharacter.class, entityName);
    }

    @Override
    public List<WorldVisit> visitEntity(INetworkWorldHandler handler, IRpgCharacter entity, IEntityNetworkAdapter netEntity, long deltaTime, boolean isOwner) throws VisitException {

        IItemSlot slot = entity.getLoadout().getSlot(UsrWieldTarget.LeftHand);

        if(slot.isEmpty())
            return new ArrayList<>();

        IItem item = slot.getItem();
        IItem.ItemUseAbilityTestResults result = item.getFunction().testUseAbility(entity, new IItem.ItemTarget(entity), item.getAttributes());

        if (result.isUseable())
            item.getFunction().use(entity, new IItem.ItemTarget(entity), item.getAttributes(), item);

        return new ArrayList<>();
    }
}
