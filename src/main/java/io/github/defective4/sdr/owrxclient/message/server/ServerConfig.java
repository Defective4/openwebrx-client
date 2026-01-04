package io.github.defective4.sdr.owrxclient.message.server;

import java.util.Arrays;

import com.google.gson.annotations.SerializedName;

import io.github.defective4.sdr.owrxclient.model.ReceiverGPS;
import io.github.defective4.sdr.owrxclient.model.WaterfallLevels;

public class ServerConfig {

    @SerializedName("audio_compression")
    private final String audioCompression;

    @SerializedName("allow_audio_recording")
    private final boolean audioRecordingEnabled;

    @SerializedName("callsign_url")
    private final String callsignUL;

    @SerializedName("allow_center_freq_changes")
    private final boolean centerFrequencyChangesEnabled;

    @SerializedName("allow_chat")
    private final boolean chatEnabled;

    @SerializedName("fft_compression")
    private final String fftCompression;

    @SerializedName("fft_size")
    private final int fftSize;

    @SerializedName("flight_url")
    private final String flightURL;

    @SerializedName("max_clients")
    private final int maxClients;

    @SerializedName("modes_url")
    private final String modesURL;

    @SerializedName("receiver_gps")
    private final ReceiverGPS receiverGPS;

    @SerializedName("ui_theme")
    private final String theme;

    @SerializedName("tuning_precision")
    private final int tuningPrecision;

    @SerializedName("vessel_url")
    private final String vesselURL;

    @SerializedName("waterfall_scheme")
    private final String waterfall;

    @SerializedName("waterfall_auto_levels")
    private final WaterfallLevels waterfallAutoLevels;

    @SerializedName("waterfall_auto_min_range")
    private final int waterfallAutoMinRange;

    @SerializedName("waterfall_colors")
    private final int[] waterfallColors;

    public ServerConfig(String waterfall, String modesURL, boolean centerFrequencyChangesEnabled,
            boolean audioRecordingEnabled, String callsignUL, String fftCompression, String flightURL, String vesselURL,
            int waterfallAutoMinRange, WaterfallLevels waterfallAutoLevels, ReceiverGPS receiverGPS, int maxClients,
            String theme, int fftSize, boolean chatEnabled, String audioCompression, int tuningPrecision,
            int[] waterfallColors) {
        this.waterfall = waterfall;
        this.modesURL = modesURL;
        this.centerFrequencyChangesEnabled = centerFrequencyChangesEnabled;
        this.audioRecordingEnabled = audioRecordingEnabled;
        this.callsignUL = callsignUL;
        this.fftCompression = fftCompression;
        this.flightURL = flightURL;
        this.vesselURL = vesselURL;
        this.waterfallAutoMinRange = waterfallAutoMinRange;
        this.waterfallAutoLevels = waterfallAutoLevels;
        this.receiverGPS = receiverGPS;
        this.maxClients = maxClients;
        this.theme = theme;
        this.fftSize = fftSize;
        this.chatEnabled = chatEnabled;
        this.audioCompression = audioCompression;
        this.tuningPrecision = tuningPrecision;
        this.waterfallColors = waterfallColors;
    }

    public String getAudioCompression() {
        return audioCompression;
    }

    public String getCallsignUL() {
        return callsignUL;
    }

    public String getFftCompression() {
        return fftCompression;
    }

    public int getFftSize() {
        return fftSize;
    }

    public String getFlightURL() {
        return flightURL;
    }

    public int getMaxClients() {
        return maxClients;
    }

    public String getModesURL() {
        return modesURL;
    }

    public ReceiverGPS getReceiverGPS() {
        return receiverGPS;
    }

    public String getTheme() {
        return theme;
    }

    public int getTuningPrecision() {
        return tuningPrecision;
    }

    public String getVesselURL() {
        return vesselURL;
    }

    public String getWaterfall() {
        return waterfall;
    }

    public WaterfallLevels getWaterfallAutoLevels() {
        return waterfallAutoLevels;
    }

    public int getWaterfallAutoMinRange() {
        return waterfallAutoMinRange;
    }

    public int[] getWaterfallColors() {
        return waterfallColors;
    }

    public boolean isAudioRecordingEnabled() {
        return audioRecordingEnabled;
    }

    public boolean isCenterFrequencyChangesEnabled() {
        return centerFrequencyChangesEnabled;
    }

    public boolean isChatEnabled() {
        return chatEnabled;
    }

    @Override
    public String toString() {
        return "ServerConfig [waterfall=" + waterfall + ", modesURL=" + modesURL + ", centerFrequencyChangesEnabled="
                + centerFrequencyChangesEnabled + ", audioRecordingEnabled=" + audioRecordingEnabled + ", callsignUL="
                + callsignUL + ", fftCompression=" + fftCompression + ", flightURL=" + flightURL + ", vesselURL="
                + vesselURL + ", waterfallAutoMinRange=" + waterfallAutoMinRange + ", waterfallAutoLevels="
                + waterfallAutoLevels + ", receiverGPS=" + receiverGPS + ", maxClients=" + maxClients + ", theme="
                + theme + ", fftSize=" + fftSize + ", chatEnabled=" + chatEnabled + ", audioCompression="
                + audioCompression + ", tuningPrecision=" + tuningPrecision + ", waterfallColors="
                + Arrays.toString(waterfallColors) + "]";
    }

}
