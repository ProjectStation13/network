package com.projectstation.network.command.world;

import com.projectstation.network.EntityVisit;
import com.projectstation.network.INetworkWorldHandler;
import com.projectstation.network.VisitException;
import com.projectstation.network.WorldVisit;
import com.projectstation.network.entity.IEntityNetworkAdapter;
import io.github.jevaengine.math.Vector3F;
import io.github.jevaengine.rpg.entity.character.IRpgCharacter;
import io.github.jevaengine.rpg.item.IItem;
import io.github.jevaengine.rpg.item.IItemSlot;
import io.github.jevaengine.rpg.item.usr.UsrWieldTarget;
import io.github.jevaengine.world.entity.IEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class UseItemInHandsAtCommand extends EntityVisit<IRpgCharacter> {
    private static final Logger m_logger = LoggerFactory.getLogger(ClearInventoryCommand.class);

    private final Vector3F location;
    private final String targetEntityName;

    public UseItemInHandsAtCommand(String entityName, Vector3F location) {
        this(entityName, null, location);
    }

    public UseItemInHandsAtCommand(String entityName, String targetEntityName, Vector3F location) {
        super(IRpgCharacter.class, entityName);
        this.location = new Vector3F(location);
        this.targetEntityName = targetEntityName;
    }

    @Override
    public List<WorldVisit> visitEntity(INetworkWorldHandler handler, IRpgCharacter entity, IEntityNetworkAdapter netEntity, long deltaTime, boolean isOwner) throws VisitException {

        IItemSlot slot = entity.getLoadout().getSlot(UsrWieldTarget.LeftHand);

        if(slot.isEmpty())
            return new ArrayList<>();

        IItem item = slot.getItem();
        IItem.ItemUseAbilityTestResults result = item.getFunction().testUseAbility(entity, new IItem.ItemTarget(entity), item.getAttributes());

        IItem.ItemTarget target = new IItem.ItemTarget(location);

        if(targetEntityName != null)
            target = new IItem.ItemTarget(handler.getWorld().getEntities().getByName(IEntity.class, targetEntityName), location);

        if (result.isUseable())
            item.getFunction().use(entity, target, item.getAttributes(), item);

        return new ArrayList<>();
    }
}
