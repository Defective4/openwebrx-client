package io.github.defective4.sdr.owrxclient.model.param;

import com.google.gson.annotations.SerializedName;

import io.github.defective4.sdr.owrxclient.model.Modulation;

public record DSPParams(@SerializedName("high_cut") Integer highCut, @SerializedName("low_cut") Integer lowCut,
        @SerializedName("offset_freq") Integer offsetFrequency, @SerializedName("mod") Modulation modulation,
        @SerializedName("dmr_filter") Integer dmrFilter, @SerializedName("audio_service_id") Integer audioServiceId,
        @SerializedName("secondary_mod") Modulation secondaryModulation,
        @SerializedName("secondary_offset_freq") Integer secondaryOffsetFrequency) {

    public DSPParams(Integer offsetFrequency, Modulation modulation, Modulation secondaryModulation) {
        this(secondaryModulation == null ? modulation.getLowPass().orElse(null)
                : secondaryModulation.getLowPass().orElse(null),
                secondaryModulation == null ? modulation.getHighPass().orElse(null)
                        : secondaryModulation.getHighPass().orElse(null),
                offsetFrequency, modulation, 3, 0, secondaryModulation, 1000);
    }

}
