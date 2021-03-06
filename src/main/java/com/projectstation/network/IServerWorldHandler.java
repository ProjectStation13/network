package com.projectstation.network;

import com.jevaengine.spacestation.ui.selectclass.CharacterClassDescription;
import com.projectstation.network.entity.IEntityNetworkAdapter;
import com.projectstation.network.entity.IServerEntityNetworkAdapter;
import io.github.jevaengine.world.World;
import io.github.jevaengine.world.entity.IEntityFactory;

import java.util.List;

public interface IServerWorldHandler extends INetworkWorldHandler {
    void setClientTime(long time);
    long getClientTime();

    void authorizeOwnership(String entityName);
    void unauthorizeOwnership(String entityName);

    boolean isOwner(String entityName);

    <T extends IServerEntityNetworkAdapter> T getAdapter(Class<T> cls, String entityName);
    IServerEntityNetworkAdapter getAdapter(String entityName);

    boolean setNickname(String selectedNickname);
    void transmitChatMessage(String message);

    WorldServerHistory getHistory();

    boolean selectClass(CharacterClassDescription cls);
    List<CharacterClassDescription> getAvailableRoles();
}
