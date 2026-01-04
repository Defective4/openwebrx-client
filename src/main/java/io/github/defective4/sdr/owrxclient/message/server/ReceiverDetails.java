package io.github.defective4.sdr.owrxclient.message.server;

import com.google.gson.annotations.SerializedName;

import io.github.defective4.sdr.owrxclient.model.ReceiverGPS;

public class ReceiverDetails {
    private final String locator;

    @SerializedName("photo_desc")
    private final String photoDescription;

    @SerializedName("photo_title")
    private final String photoTitle;

    @SerializedName("receiver_asl")
    private final int receiverElevation;

    @SerializedName("receiver_gps")
    private final ReceiverGPS receiverGPS;

    @SerializedName("receiver_location")
    private final String receiverLocation;

    @SerializedName("receiver_name")
    private final String receiverName;

    public ReceiverDetails(String receiverName, String photoTitle, String photoDescription, String receiverLocation,
            String locator, ReceiverGPS receiverGPS, int receiverElevation) {
        this.receiverName = receiverName;
        this.photoTitle = photoTitle;
        this.photoDescription = photoDescription;
        this.receiverLocation = receiverLocation;
        this.locator = locator;
        this.receiverGPS = receiverGPS;
        this.receiverElevation = receiverElevation;
    }

    public String getLocator() {
        return locator;
    }

    public String getPhotoDescription() {
        return photoDescription;
    }

    public String getPhotoTitle() {
        return photoTitle;
    }

    public int getReceiverElevation() {
        return receiverElevation;
    }

    public ReceiverGPS getReceiverGPS() {
        return receiverGPS;
    }

    public String getReceiverLocation() {
        return receiverLocation;
    }

    public String getReceiverName() {
        return receiverName;
    }

    @Override
    public String toString() {
        return "ReceiverDetails [receiverName=" + receiverName + ", photoTitle=" + photoTitle + ", photoDescription="
                + photoDescription + ", receiverLocation=" + receiverLocation + ", locator=" + locator
                + ", receiverGPS=" + receiverGPS + ", receiverElevation=" + receiverElevation + "]";
    }
}
