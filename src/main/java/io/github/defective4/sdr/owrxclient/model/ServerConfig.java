package io.github.defective4.sdr.owrxclient.model;

import com.google.gson.annotations.SerializedName;

public record ServerConfig(@SerializedName("audio_compression") String audioCompression,
        @SerializedName("allow_audio_recording") boolean audioRecordingEnabled,
        @SerializedName("callsign_url") String callsignUL,
        @SerializedName("allow_center_freq_changes") boolean centerFrequencyChangesEnabled,
        @SerializedName("allow_chat") boolean chatEnabled, @SerializedName("fft_compression") String fftCompression,
        @SerializedName("fft_size") int fftSize, @SerializedName("flight_url") String flightURL,
        @SerializedName("max_clients") int maxClients, @SerializedName("modes_url") String modesURL,
        @SerializedName("receiver_gps") ReceiverGPS receiverGPS, @SerializedName("ui_theme") String theme,
        @SerializedName("tuning_precision") int tuningPrecision, @SerializedName("vessel_url") String vesselURL,
        @SerializedName("waterfall_scheme") String waterfall,
        @SerializedName("waterfall_auto_levels") WaterfallLevels waterfallAutoLevels,
        @SerializedName("waterfall_auto_min_range") int waterfallAutoMinRange,
        @SerializedName("waterfall_colors") int[] waterfallColors) {
}