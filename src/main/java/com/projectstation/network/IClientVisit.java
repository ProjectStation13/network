package com.projectstation.network;

import com.projectstation.network.entity.IEntityNetworkAdapter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IClientVisit extends INetworkVisit {
    List<IServerVisit> visit(IClientWorldHandler handler) throws VisitException;
}
