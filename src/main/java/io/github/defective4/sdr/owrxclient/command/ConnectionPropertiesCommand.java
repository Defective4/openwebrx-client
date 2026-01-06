package io.github.defective4.sdr.owrxclient.command;

import io.github.defective4.sdr.owrxclient.model.param.ConnectionParams;

public class ConnectionPropertiesCommand extends ParameterClientCommand {

    public ConnectionPropertiesCommand(ConnectionParams params) {
        super("connectionproperties", params);
    }

}
