package io.github.defective4.sdr.owrxclient.model;

import com.google.gson.annotations.SerializedName;

public class ChatMessage {
    @SerializedName("text")
    private final String message;
    @SerializedName("name")
    private final String username;

    public ChatMessage(String username, String message) {
        this.username = username;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

}
