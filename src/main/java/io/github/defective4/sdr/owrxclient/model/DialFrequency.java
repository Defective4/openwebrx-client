package io.github.defective4.sdr.owrxclient.model;

public class DialFrequency {
    private final int frequency;
    private final Modulation mode;

    public DialFrequency(int frequency, Modulation mode) {
        this.frequency = frequency;
        this.mode = mode;
    }

    public int getFrequency() {
        return frequency;
    }

    public Modulation getMode() {
        return mode == null ? Modulation.empty : mode;
    }

}
