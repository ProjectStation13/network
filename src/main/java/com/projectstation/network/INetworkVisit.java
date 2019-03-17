package com.projectstation.network;

import io.github.jevaengine.config.ISerializable;

import java.io.Serializable;

public interface INetworkVisit extends Serializable {
    default boolean mutuallyEliminates(INetworkVisit v) {
        return false;
    }

    default boolean overrides(INetworkVisit v) {
        return false;
    }

    default boolean trackHistory() {
        return true;
    }
}
