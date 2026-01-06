package io.github.defective4.sdr.owrxclient.command;

public class ParameterClientCommand extends ClientCommand {
    private final Object params;

    protected ParameterClientCommand(String type, Object params) {
        super(type);
        this.params = params;
    }

    public Object getParams() {
        return params;
    }
}
