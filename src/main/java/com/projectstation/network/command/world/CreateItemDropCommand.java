package com.projectstation.network.command.world;

import com.jevaengine.spacestation.entity.ItemDrop;
import com.projectstation.network.INetworkWorldHandler;
import com.projectstation.network.VisitException;
import com.projectstation.network.WorldVisit;
import io.github.jevaengine.math.Vector3F;
import io.github.jevaengine.rpg.item.IItemFactory;
import io.github.jevaengine.world.World;
import io.github.jevaengine.world.entity.IEntityFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class CreateItemDropCommand extends WorldVisit {
    private final Logger logger = LoggerFactory.getLogger(CreateItemDropCommand.class);
    private final Vector3F location;
    private final String item;
    private final String name;

    public CreateItemDropCommand(String name, Vector3F location, String item) {
        this.location = location;
        this.item = item;
        this.name = name;
    }

    @Override
    public List<WorldVisit> visit(INetworkWorldHandler handler, IEntityFactory entityFactory, World world, long timeElapsedSinceDispatch, boolean isServer) throws VisitException {

        try {
            ItemDrop drop = new ItemDrop(name, handler.getItemFactory().create(new URI(item)));
            handler.getWorld().addEntity(drop);
            drop.getBody().setLocation(location);
        } catch (URISyntaxException | IItemFactory.ItemContructionException ex) {
            logger.error("Error constructing item drop.", ex);
        }
        return new ArrayList<>();
    }
}
