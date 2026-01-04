package io.github.defective4.sdr.owrxclient.model;

import java.util.Objects;

import io.github.defective4.sdr.owrxclient.model.message.server.ReceiverDetails;

public enum ServerMessageType {
    RECEIVER_DETAILS(ReceiverDetails.class);

    private final Class<?> modelClass;

    private ServerMessageType(Class<?> modelClass) {
        this.modelClass = Objects.requireNonNull(modelClass);
    }

    public Class<?> getModelClass() {
        return modelClass;
    }

}
