package com.projectstation.network.command.world;

import com.jevaengine.spacestation.entity.power.Dcpu;
import com.projectstation.network.EntityVisit;
import com.projectstation.network.INetworkWorldHandler;
import com.projectstation.network.WorldVisit;
import com.projectstation.network.entity.IEntityNetworkAdapter;
import de.codesourcery.jasm16.emulator.devices.impl.DefaultScreen;
import io.github.jevaengine.world.entity.IEntity;
import io.github.jevaengine.world.scene.model.IAnimationSceneModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WriteDcpuVramCommand extends EntityVisit<Dcpu> {
    private static final Logger logger = LoggerFactory.getLogger(WriteDcpuVramCommand.class);

    private final HashMap<Integer, Integer> writes;

    public WriteDcpuVramCommand(String name, HashMap<Integer, Integer> writes) {
        super(Dcpu.class, name);
        this.writes = new HashMap<>(writes);
    }

    @Override
    public List<WorldVisit> visitEntity(INetworkWorldHandler handler, Dcpu entity, IEntityNetworkAdapter netEntity, long deltaTime, boolean isOwner) {
        DefaultScreen screen = entity.getScreenDevice();
        DefaultScreen.VideoRAM ram = screen.getVram();

        if(ram == null)
        {
            logger.error("DCPU Has no VRAM. Cannot carry out vram write.");
            return new ArrayList<>();
        }

        for(Map.Entry<Integer, Integer> e : writes.entrySet()) {
            ram.write(e.getKey(), e.getValue());
        }

        return new ArrayList<>();
    }
}
