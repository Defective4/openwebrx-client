package io.github.defective4.sdr.owrxclient.model.param;

import com.google.gson.annotations.SerializedName;

public record DSPParams(@SerializedName("high_cut") Integer highCut, @SerializedName("low_cut") Integer lowCut,
        @SerializedName("offset_freq") Integer offsetFrequency, @SerializedName("mod") String modulation,
        @SerializedName("dmr_filter") Integer dmrFilter, @SerializedName("audio_service_id") Integer audioServiceId,
        @SerializedName("secondary_mod") String secondaryModulation,
        @SerializedName("secondary_offset_freq") Integer secondaryOffsetFrequency) {
}
