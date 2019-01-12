package com.projectstation.network.command.world;

import com.projectstation.network.EntityVisit;
import com.projectstation.network.INetworkWorldHandler;
import com.projectstation.network.VisitException;
import com.projectstation.network.WorldVisit;
import com.projectstation.network.entity.IEntityNetworkAdapter;
import io.github.jevaengine.rpg.entity.Door;
import io.github.jevaengine.rpg.entity.character.IRpgCharacter;
import io.github.jevaengine.world.World;
import io.github.jevaengine.world.entity.IEntityFactory;

import java.util.ArrayList;
import java.util.List;

public class InteractDoorCommand extends EntityVisit<Door> {
    public InteractDoorCommand(String doorName) {
        super(Door.class, doorName);
    }

    @Override
    public List<WorldVisit> visitEntity(INetworkWorldHandler handler, Door entity, IEntityNetworkAdapter netEntity, long deltaTime, boolean isOwner) {
        if(entity.isOpen())
            entity.close();
        else
            entity.open();

        return new ArrayList<>();
    }
}
