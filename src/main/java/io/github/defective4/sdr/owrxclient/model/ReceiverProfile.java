package io.github.defective4.sdr.owrxclient.model;

import java.util.Arrays;
import java.util.UUID;

public record ReceiverProfile(String id, String name) {
    public UUID[] uuids() {
        return Arrays.stream(id.split("\\|")).map(UUID::fromString).toArray(UUID[]::new);
    }
}
