package io.github.defective4.sdr.owrxclient.model.param;

import com.google.gson.annotations.SerializedName;

import io.github.defective4.sdr.owrxclient.model.Modulation;

public class DSPParams implements Cloneable {

    public static final DSPParams AM = new DSPParams(-4000, 4000, 0, Modulation.AM, 3, 0, null, true);
    public static final DSPParams CW = new DSPParams(700, 900, 0, Modulation.CW, 3, 0, null, true);
    public static final DSPParams DATA = new DSPParams(0, 24000, 0, Modulation.USBD, 3, 0, null, true);
    public static final DSPParams FT4 = new DSPParams(0, 3000, 0, Modulation.USB, 3, 0, Modulation.FT4, true);
    public static final DSPParams FT8 = new DSPParams(0, 3000, 0, Modulation.USB, 3, 0, Modulation.FT8, true);
    public static final DSPParams LSB = new DSPParams(-2750, -150, 0, Modulation.LSB, 3, 0, null, true);
    public static final DSPParams NFM = new DSPParams(-4000, 4000, 0, Modulation.NFM, 3, 0, null, true);
    public static final DSPParams PACKET = new DSPParams(-6250, 6250, 0, Modulation.EMPTY, 3, 0, Modulation.PACKET,
            true);

    public static final DSPParams SAM = new DSPParams(-4000, 4000, 0, Modulation.SAM, 3, 0, null, true);
    public static final DSPParams USB = new DSPParams(150, 2750, 0, Modulation.USB, 3, 0, null, true);
    public static final DSPParams VDL2 = new DSPParams(-12500, 12500, 0, Modulation.EMPTY, 3, 0, Modulation.VDL2, true);
    public static final DSPParams WFM = new DSPParams(-75200, 74800, 0, Modulation.WFM, 3, 0, null, true);

    @SerializedName("audio_service_id")
    private Integer audioServiceId;

    @SerializedName("dmr_filter")
    private Integer dmrFilter;

    @SerializedName("high_cut")
    private Integer highCut;

    private transient final boolean immutable;

    @SerializedName("low_cut")
    private Integer lowCut;

    @SerializedName("mod")
    private String modulation;

    @SerializedName("offset_freq")
    private Integer offsetFrequency;

    @SerializedName("secondary_mod")
    private String secondaryModulation;

    public DSPParams(Integer lowCut, Integer highCut, Integer offsetFrequency, Modulation modulation, Integer dmrFilter,
            Integer audioServiceId, Modulation secondaryModulation) {
        this(lowCut, highCut, offsetFrequency, modulation, dmrFilter, audioServiceId, secondaryModulation, false);
    }

    private DSPParams(Integer lowCut, Integer highCut, Integer offsetFrequency, Modulation modulation,
            Integer dmrFilter, Integer audioServiceId, Modulation secondaryModulation, boolean immutable) {
        this.immutable = immutable;
        this.lowCut = lowCut;
        this.highCut = highCut;
        this.offsetFrequency = offsetFrequency;
        this.modulation = modulation == null ? null : modulation.name().toLowerCase();
        this.dmrFilter = dmrFilter;
        this.audioServiceId = audioServiceId;
        this.secondaryModulation = secondaryModulation == null ? null : secondaryModulation.name().toLowerCase();
    }

    @Override
    public DSPParams clone() {
        DSPParams pms = new DSPParams(lowCut, highCut, offsetFrequency, null, dmrFilter, audioServiceId, null);
        pms.modulation = modulation;
        pms.secondaryModulation = modulation;
        return pms;
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
        if (modulation == null) return null;
        try {
            return Modulation.valueOf(modulation.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Modulation.EMPTY;
        }
    }

    public Integer getOffsetFrequency() {
        return offsetFrequency;
    }

    public Modulation getSecondaryModulation() {
        if (secondaryModulation == null) return null;
        try {
            return Modulation.valueOf(secondaryModulation.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Modulation.EMPTY;
        }
    }

    public DSPParams withAudioServiceId(Integer audioServiceId) {
        checkMutable();
        this.audioServiceId = audioServiceId;
        return this;
    }

    public DSPParams withDmrFilter(Integer dmrFilter) {
        checkMutable();
        this.dmrFilter = dmrFilter;
        return this;
    }

    public DSPParams withHighCut(Integer highCut) {
        checkMutable();
        this.highCut = highCut;
        return this;
    }

    public DSPParams withLowCut(Integer lowCut) {
        checkMutable();
        this.lowCut = lowCut;
        return this;
    }

    public DSPParams withModulation(String modulation) {
        checkMutable();
        this.modulation = modulation;
        return this;
    }

    public DSPParams withOffsetFrequency(Integer offsetFrequency) {
        checkMutable();
        this.offsetFrequency = offsetFrequency;
        return this;
    }

    public DSPParams withSecondaryModulation(String secondaryModulation) {
        checkMutable();
        this.secondaryModulation = secondaryModulation;
        return this;
    }

    private void checkMutable() {
        if (immutable) throw new IllegalStateException("This DSP params instance is immutable");
    }

}
