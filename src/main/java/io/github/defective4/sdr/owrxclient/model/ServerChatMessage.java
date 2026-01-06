package io.github.defective4.sdr.owrxclient.model;

import java.awt.Color;

public class ServerChatMessage extends ChatMessage {
    private final String color;

    public ServerChatMessage(String username, String message, String color) {
        super(username, message);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public Color parsedColor() {
        return Color.decode(color);
    }
}
