package com.projectstation.network;

import com.jevaengine.spacestation.entity.character.ISpaceCharacterStatusResolver;

public interface IClientSpaceCharacterStatusResolver extends ISpaceCharacterStatusResolver {
    void setEffectiveHitpoints(float hitpoints);
}
