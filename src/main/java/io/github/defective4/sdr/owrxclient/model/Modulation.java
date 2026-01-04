package io.github.defective4.sdr.owrxclient.model;

public enum Modulation {
    AM, CW, CWDECODER(false), EMPTY, FT4(false), FT8(false), LSB, NFM, PACKET(false), SAM, USB, USBD, VDL2(false), WFM;

    private final boolean primary;

    private Modulation() {
        this(true);
    }

    private Modulation(boolean primary) {
        this.primary = primary;
    }

    public boolean isPrimary() {
        return primary;
    }

}
