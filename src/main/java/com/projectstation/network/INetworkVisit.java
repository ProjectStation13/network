package com.projectstation.network;

import io.github.jevaengine.config.ISerializable;

import java.io.Serializable;

public interface INetworkVisit extends Serializable {
    default boolean isPriorRedundant(INetworkVisit before) {
        return false;
    }
}
