package io.github.defective4.sdr.owrxclient.model;

import java.util.Map;
import java.util.Objects;

import io.github.defective4.sdr.owrxclient.message.server.ReceiverDetails;
import io.github.defective4.sdr.owrxclient.message.server.ServerConfig;

public enum ServerMessageType {
    BANDS(Band[].class), BOOKMARKS(Bookmark[].class), CLIENTS(Integer.class), CONFIG(ServerConfig.class),
    CPUUSAGE(Float.class), DIAL_FREQUENCIES(DialFrequency[].class), FEATURES(Map.class),
    MODES(ReceiverMode[].class), RECEIVER_DETAILS(ReceiverDetails.class), SMETER(Float.class), TEMPERATURE(Integer.class);

    private final Class<?> modelClass;

    private ServerMessageType(Class<?> modelClass) {
        this.modelClass = Objects.requireNonNull(modelClass);
    }

    public Class<?> getModelClass() {
        return modelClass;
    }

}
