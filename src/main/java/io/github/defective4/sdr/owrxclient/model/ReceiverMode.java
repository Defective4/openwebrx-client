package io.github.defective4.sdr.owrxclient.model;

public record ReceiverMode(String modulation, String name, Type type, String[] requirements, boolean squelch,
        Bandpass bandpass, String[] underlying, boolean secondaryFft) {
    
    public static final ReceiverMode EMPTY = new ReceiverMode("empty", "Empty", null, new String[0], false, null, null, false);
    
    public static enum Type {

    }
}
