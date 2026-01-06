package io.github.defective4.sdr.owrxclient.model;

import com.google.gson.annotations.SerializedName;

public record BatteryInfo(int voltage, float current, @SerializedName("charger") boolean charging,
        @SerializedName("charge") int chargePercent) {
}
