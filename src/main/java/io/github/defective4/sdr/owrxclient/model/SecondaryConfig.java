package io.github.defective4.sdr.owrxclient.model;

import com.google.gson.annotations.SerializedName;

public record SecondaryConfig(@SerializedName("secondary_fft_size") Integer secondaryFFTSize) {
}
