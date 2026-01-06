package io.github.defective4.sdr.owrxclient.model.metadata;

public class Metadata {
    public static enum Type {
        WFM;
    }

    private final Type type;

    protected Metadata(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

}
