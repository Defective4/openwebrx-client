package io.github.defective4.sdr.owrxclient.model;

import java.awt.Color;

import com.google.gson.annotations.SerializedName;

public record ChatMessage(@SerializedName("name") String username, @SerializedName("text") String message,
        String color) {

    public Color parsedColor() {
        return Color.decode(color);
    }
}
