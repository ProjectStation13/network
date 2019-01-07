package com.projectstation.network;

import com.projectstation.network.entity.IEntityNetworkAdapter;
import io.github.jevaengine.config.ISerializable;
import io.github.jevaengine.world.World;
import io.github.jevaengine.world.entity.IEntityFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IServerVisit extends INetworkVisit {
    List<IClientVisit> visit(IServerWorldHandler handler) throws VisitException;
}
