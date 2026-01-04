package io.github.defective4.sdr.owrxclient.model;

public class DialFrequency {
    private final int frequency;
    private final String mode;

    public DialFrequency(int frequency, Modulation mode) {
        this.frequency = frequency;
        this.mode = mode.name().toLowerCase();
    }

    public int getFrequency() {
        return frequency;
    }

    public Modulation getMode() {
        if (mode == null) return Modulation.EMPTY;
        try {
            return Modulation.valueOf(mode);
        } catch (Exception e) {
            return Modulation.EMPTY;
        }
    }

}
