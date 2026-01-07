package io.github.defective4.sdr.owrxclient.model.demod;

import com.google.gson.annotations.SerializedName;

public class CWSkimmerResult extends DemodulatorResult {

    @SerializedName("freq")
    private final int frequency;
    private final String text;

    protected CWSkimmerResult(String text, int frequency) {
        super("CW");
        this.text = text;
        this.frequency = frequency;
    }

    public int getFrequency() {
        return frequency;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "CWSkimmerResult [text=" + text + ", frequency=" + frequency + "]";
    }

}
