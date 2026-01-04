package io.github.defective4.sdr.owrxclient.model;

import java.util.Objects;

import io.github.defective4.sdr.owrxclient.message.server.ReceiverDetails;
import io.github.defective4.sdr.owrxclient.message.server.ServerConfig;

public enum ServerMessageType {
    CONFIG(ServerConfig.class),
    RECEIVER_DETAILS(ReceiverDetails.class);

    private final Class<?> modelClass;

    private ServerMessageType(Class<?> modelClass) {
        this.modelClass = Objects.requireNonNull(modelClass);
    }

    public Class<?> getModelClass() {
        return modelClass;
    }

}
