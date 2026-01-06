package io.github.defective4.sdr.owrxclient.command;

public class ClientCommand {
    private final String type;

    protected ClientCommand(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
