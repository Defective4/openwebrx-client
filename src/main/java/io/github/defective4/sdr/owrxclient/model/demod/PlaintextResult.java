package io.github.defective4.sdr.owrxclient.model.demod;

public class PlaintextResult extends DemodulatorResult {

    private final String text;

    public PlaintextResult(String text) {
        super("text");
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "PlaintextResult [text=" + text + "]";
    }

}
