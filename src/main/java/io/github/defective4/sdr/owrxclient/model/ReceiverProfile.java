package io.github.defective4.sdr.owrxclient.model;

public record ReceiverProfile(String id, String name) {
    public String[] uuids() {
        return id.split("\\|");
    }

    @Override
    public String toString() {
        return name;
    }

}
