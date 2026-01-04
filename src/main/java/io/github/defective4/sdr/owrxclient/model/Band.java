package io.github.defective4.sdr.owrxclient.model;

import com.google.gson.annotations.SerializedName;

public record Band(String name, @SerializedName("low_bound") int lowerFrequency,
        @SerializedName("high_bound") int higherFrequency, String[] tags) {
}
