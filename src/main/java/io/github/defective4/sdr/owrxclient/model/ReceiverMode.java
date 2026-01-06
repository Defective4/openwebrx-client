package io.github.defective4.sdr.owrxclient.model;

public record ReceiverMode(Modulation modulation, String name, Type type, String[] requirements, boolean squelch,
        Bandpass bandpass, Modulation[] underlying, boolean secondaryFft) {
    public static enum Type {

    }
}
