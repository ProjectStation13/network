package com.projectstation.network.entity;

import io.github.jevaengine.config.IImmutableVariable;
import io.github.jevaengine.util.Nullable;

import java.io.Serializable;
import java.net.URI;
import java.util.Objects;

public class EntityConfigurationDetails implements Serializable {
    private final String typeName;
    private final URI configContext;
    private final IImmutableVariable auxConfig;

    public EntityConfigurationDetails(String typeName) {
        this.typeName = typeName;
        this.configContext = null;
        this.auxConfig = null;
    }

    public EntityConfigurationDetails(String typeName, URI configContext, IImmutableVariable auxConfig) {
        this.typeName = typeName;
        this.configContext = configContext;
        this.auxConfig = auxConfig;
    }

    public EntityConfigurationDetails(String typeName, IImmutableVariable auxConfig) {
        this.typeName = typeName;
        this.configContext = null;
        this.auxConfig = auxConfig;
    }

    public EntityConfigurationDetails(String typeName, URI configContext) {
        this.typeName = typeName;
        this.configContext = configContext;
        this.auxConfig = null;
    }

    @Nullable
    public URI getConfigContext() {
        return configContext;
    }

    @Nullable
    public IImmutableVariable getAuxConfig() {
        return auxConfig;
    }

    public String getTypeName() {
        return typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityConfigurationDetails that = (EntityConfigurationDetails) o;
        return Objects.equals(configContext, that.configContext) &&
                Objects.equals(auxConfig, that.auxConfig);
    }

    @Override
    public int hashCode() {
        return Objects.hash(configContext, auxConfig);
    }

}
