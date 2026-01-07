package io.github.defective4.sdr.owrxclient.model.demod;

public enum Demodulator {
    CW(CWSkimmerResult.class), FT4(FTMessage.class), FT8(FTMessage.class), VDL2(VDL2Message.class);

    private final Class<? extends DemodulatorResult> resultClass;

    private Demodulator(Class<? extends DemodulatorResult> resultClass) {
        this.resultClass = resultClass;
    }

    public Class<? extends DemodulatorResult> getResultClass() {
        return resultClass;
    }

}
