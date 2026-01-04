package io.github.defective4.sdr.owrxclient.message.client;

import io.github.defective4.sdr.owrxclient.model.param.ConnectionParams;

public class ConnectionPropertiesCommand extends ClientCommand {

    public ConnectionPropertiesCommand(ConnectionParams params) {
        super("connectionproperties", params);
    }

}
