package io.github.defective4.sdr.owrxclient.model;

import com.google.gson.annotations.SerializedName;

public record FT8Message(long timestamp, double db, double dt, @SerializedName("freq") int frequency,
        @SerializedName("msg") String message, String mode, int interval) {

}
