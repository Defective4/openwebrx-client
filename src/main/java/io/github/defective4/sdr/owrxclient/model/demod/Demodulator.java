package io.github.defective4.sdr.owrxclient.model.demod;

public enum Demodulator {
    FT8(FTMessage.class), FT4(FTMessage.class);

    private final Class<? extends DemodulatorResult> resultClass;

    private Demodulator(Class<? extends DemodulatorResult> resultClass) {
        this.resultClass = resultClass;
    }

    public Class<? extends DemodulatorResult> getResultClass() {
        return resultClass;
    }

}
