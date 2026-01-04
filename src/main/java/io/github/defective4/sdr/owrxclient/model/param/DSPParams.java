package io.github.defective4.sdr.owrxclient.model.param;

import com.google.gson.annotations.SerializedName;

import io.github.defective4.sdr.owrxclient.model.Modulation;

public class DSPParams implements Cloneable {

    @SerializedName("audio_service_id")
    private final Integer audioServiceId;

    @SerializedName("dmr_filter")
    private final Integer dmrFilter;

    @SerializedName("high_cut")
    private final Integer highCut;

    private transient final boolean immutable;

    @SerializedName("low_cut")
    private final Integer lowCut;

    @SerializedName("mod")
    private final Modulation modulation;

    @SerializedName("offset_freq")
    private final Integer offsetFrequency;

    @SerializedName("secondary_mod")
    private final Modulation secondaryModulation;

    public DSPParams(Integer lowCut, Integer highCut, Integer offsetFrequency, Modulation modulation, Integer dmrFilter,
            Integer audioServiceId, Modulation secondaryModulation) {
        this(lowCut, highCut, offsetFrequency, modulation, dmrFilter, audioServiceId, secondaryModulation, false);
    }

    public DSPParams(Integer offsetFrequency, Modulation modulation, Modulation secondaryModulation) {
        this(modulation.getLowPass(), modulation.getHighPass(), offsetFrequency, modulation, 3, 0, secondaryModulation);
    }

    private DSPParams(Integer lowCut, Integer highCut, Integer offsetFrequency, Modulation modulation,
            Integer dmrFilter, Integer audioServiceId, Modulation secondaryModulation, boolean immutable) {
        this.immutable = immutable;
        this.lowCut = lowCut;
        this.highCut = highCut;
        this.offsetFrequency = offsetFrequency;
        this.modulation = modulation == null ? null : modulation;
        this.dmrFilter = dmrFilter;
        this.audioServiceId = audioServiceId;
        this.secondaryModulation = secondaryModulation;
    }

    public Integer getAudioServiceId() {
        return audioServiceId;
    }

    public Integer getDmrFilter() {
        return dmrFilter;
    }

    public Integer getHighCut() {
        return highCut;
    }

    public Integer getLowCut() {
        return lowCut;
    }

    public Modulation getModulation() {
        return modulation == null ? Modulation.empty : modulation;
    }

    public Integer getOffsetFrequency() {
        return offsetFrequency;
    }

    public Modulation getSecondaryModulation() {
        return secondaryModulation == null ? null : secondaryModulation;
    }

    private void checkMutable() {
        if (immutable) throw new IllegalStateException("This DSP params instance is immutable");
    }

}
