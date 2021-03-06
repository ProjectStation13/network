package com.projectstation.network;

import com.jevaengine.spacestation.ui.selectclass.CharacterClassDescription;
import io.github.jevaengine.world.IEffectMapFactory;
import io.github.jevaengine.world.World;
import io.github.jevaengine.world.entity.IEntity;
import io.github.jevaengine.world.entity.IEntityFactory;
import io.github.jevaengine.world.entity.IParallelEntityFactory;
import io.github.jevaengine.world.physics.IPhysicsWorldFactory;

import java.util.List;

public interface IClientWorldHandler extends INetworkWorldHandler {
    void setPlayerEntity(String name);
    void setWorld(World world);

    void setEntityNickname(String instanceName, String nickname);

    void setServerTime(long time);
    long getServerTime();

    IPhysicsWorldFactory getPhysicsWorldFactory();
    IEffectMapFactory getEffectMapFactory();
    IParallelEntityFactory getParallelEntityFactory();

    void giveOwnership(String entityName);
    void revokeOwnership(String entityName);
    boolean isOwner(String entityName);

    void disconnect(String reason);
    void recieveChatMessage(String message);

    void requestRoleSelect(List<CharacterClassDescription> available);
}
