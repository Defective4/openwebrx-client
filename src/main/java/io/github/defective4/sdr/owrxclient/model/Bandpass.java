package io.github.defective4.sdr.owrxclient.model;

import com.google.gson.annotations.SerializedName;

public record Bandpass(@SerializedName("low_cut") int lowCut, @SerializedName("high_cut") int highCut) {
}
