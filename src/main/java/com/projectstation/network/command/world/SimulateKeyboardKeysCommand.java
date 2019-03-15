package com.projectstation.network.command.world;

import com.jevaengine.spacestation.entity.power.Dcpu;
import com.projectstation.network.EntityVisit;
import com.projectstation.network.INetworkWorldHandler;
import com.projectstation.network.WorldVisit;
import com.projectstation.network.entity.IEntityNetworkAdapter;
import de.codesourcery.jasm16.emulator.devices.impl.DefaultScreen;
import io.github.jevaengine.config.ISerializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulateKeyboardKeysCommand extends EntityVisit<Dcpu> {
    private static final Logger logger = LoggerFactory.getLogger(WriteDcpuVramCommand.class);

    private final List<SimulatedKey> keys;

    public SimulateKeyboardKeysCommand(String name, List<SimulatedKey> keys) {
        super(Dcpu.class, name);
        this.keys = new ArrayList<>(keys);
    }

    @Override
    public List<WorldVisit> visitEntity(INetworkWorldHandler handler, Dcpu entity, IEntityNetworkAdapter netEntity, long deltaTime, boolean isOwner) {
        for(SimulatedKey k : keys) {
            entity.simulateKeyTyped(k.code, k.charCode);
        }

        return new ArrayList<>();
    }

    @Override
    public boolean overrides(WorldVisit v) {
        if(!(v instanceof SimulateKeyboardKeysCommand))
            return false;

        SimulateKeyboardKeysCommand other = (SimulateKeyboardKeysCommand)v;

        if(other.getEntityName().compareTo(getEntityName()) != 0)
            return false;

        keys.addAll(0, other.keys);

        return true;
    }

    public static class SimulatedKey implements Serializable {
        int code;
        char charCode;

        public SimulatedKey(int code, char charCode) {
            this.code = code;
            this.charCode = charCode;
        }
    }
}