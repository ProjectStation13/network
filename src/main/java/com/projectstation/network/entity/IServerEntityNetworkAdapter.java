package com.projectstation.network.entity;

import com.projectstation.network.IClientVisit;
import com.projectstation.network.entity.EntityNetworkAdapterException;
import com.projectstation.network.IServerPollable;
import com.projectstation.network.entity.IEntityNetworkAdapter;

import java.util.List;

public interface IServerEntityNetworkAdapter extends IEntityNetworkAdapter, IServerPollable {
    List<IClientVisit> createInitializeSteps() throws EntityNetworkAdapterException;
}
