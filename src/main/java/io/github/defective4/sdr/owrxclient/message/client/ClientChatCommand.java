package io.github.defective4.sdr.owrxclient.message.client;

import java.util.Objects;

import io.github.defective4.sdr.owrxclient.model.ChatMessage;

public class ClientChatCommand extends ClientCommand {

    private final String name, text;

    public ClientChatCommand(ChatMessage message) {
        super("sendmessage");
        name = message.getUsername();
        text = Objects.requireNonNull(message.getMessage());
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

}
