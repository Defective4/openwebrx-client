package io.github.defective4.sdr.owrxclient.message.server;

import com.google.gson.annotations.SerializedName;

import io.github.defective4.sdr.owrxclient.model.ReceiverGPS;

public record ReceiverDetails(String locator, @SerializedName("photo_desc") String photoDescription,
        @SerializedName("photo_title") String photoTitle, @SerializedName("receiver_asl") int receiverElevation,
        @SerializedName("receiver_gps") ReceiverGPS receiverGPS,
        @SerializedName("receiver_location") String receiverLocation,
        @SerializedName("receiver_name") String receiverName) {

}
