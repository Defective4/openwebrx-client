package io.github.defective4.sdr.owrxclient.model.param;

import com.google.gson.annotations.SerializedName;

public record ConnectionParams(@SerializedName("output_rate") int outputRate,
        @SerializedName("hd_output_rate") int hdOutputRate) {

}
