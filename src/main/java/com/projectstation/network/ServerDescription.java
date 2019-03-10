package com.projectstation.network;

import io.github.jevaengine.config.*;

public class ServerDescription implements ISerializable {
    public String name;
    public String description;
    public int players = 0;
    public int maxPlayers = 0;
    public int port;

    public ServerDescription(int port, String name, String description, int maxPlayers) {
        this.name = name;
        this.port = port;
        this.description = description;
        this.players = 0;
        this.maxPlayers = maxPlayers;
    }

    @Override
    public void serialize(IVariable target) throws ValueSerializationException {
        if(name != null)
            target.addChild("name").setValue(name);

        if(description != null)
            target.addChild("description").setValue(description);

        target.addChild("players").setValue(players);

        target.addChild("port").setValue(port);

        target.addChild("max_players").setValue(maxPlayers);
    }

    @Override
    public void deserialize(IImmutableVariable source) throws ValueSerializationException {
        try {
            name = source.getChild("name").getValue(String.class);
            description = source.getChild("description").getValue(String.class);
            players = source.getChild("players").getValue(Integer.class);
            port = source.getChild("port").getValue(Integer.class);
            maxPlayers = source.getChild("max_players").getValue(Integer.class);
        } catch (NoSuchChildVariableException e) {
            throw new ValueSerializationException(e);
        }
    }
}
