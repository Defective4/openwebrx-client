package io.github.defective4.sdr.owrxclient.message.client;

public class ClientCommand {
    private final Object params;
    private final String type;

    public ClientCommand(String type, Object params) {
        this.type = type;
        this.params = params;
    }

    public Object getParams() {
        return params;
    }

    public String getType() {
        return type;
    }
}
