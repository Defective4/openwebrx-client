package io.github.defective4.sdr.owrxclient.model.demod;

public class DemodulatorResult {
    private final String mode;

    protected DemodulatorResult(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }

}
