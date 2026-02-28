package io.github.defective4.sdr.owrxclient.model.metadata;

import java.util.Map;
import java.util.Optional;

import com.google.gson.annotations.SerializedName;

public class DABMetadata {
    @SerializedName("ensemble_id")
    private final Integer ensembleID;
    @SerializedName("ensemble_label")
    private final String ensembleLabel;
    private final Map<String, String> programmes;
    private final Long timestamp;

    public DABMetadata(Map<String, String> programmes, Integer ensembleID, Long timestamp, String ensembleLabel) {
        this.programmes = programmes;
        this.ensembleID = ensembleID;
        this.timestamp = timestamp;
        this.ensembleLabel = ensembleLabel;
    }

    public Optional<Integer> getEnsembleID() {
        return Optional.ofNullable(ensembleID);
    }

    public Optional<String> getEnsembleLabel() {
        return Optional.ofNullable(ensembleLabel);
    }

    public Optional<Map<String, String>> getProgrammes() {
        return Optional.ofNullable(programmes);
    }

    public Optional<Long> getTimestamp() {
        return Optional.ofNullable(timestamp);
    }

    @Override
    public String toString() {
        return "DABMetadata [programmes=" + programmes + ", ensembleID=" + ensembleID + ", timestamp=" + timestamp
                + ", ensembleLabel=" + ensembleLabel + "]";
    }

}
