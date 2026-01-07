package io.github.defective4.sdr.owrxclient.model.metadata;

import java.util.Map;
import java.util.Optional;

import com.google.gson.annotations.SerializedName;

public class DABMetadata {
    private final Map<String, String> programmes;
    @SerializedName("ensemble_id")
    private final Integer ensembleID;
    private final Long timestamp;
    @SerializedName("ensemble_label")
    private final String ensembleLabel;

    public DABMetadata(Map<String, String> programmes, Integer ensembleID, Long timestamp, String ensembleLabel) {
        this.programmes = programmes;
        this.ensembleID = ensembleID;
        this.timestamp = timestamp;
        this.ensembleLabel = ensembleLabel;
    }

    public Optional<Map<String, String>> getProgrammes() {
        return Optional.ofNullable(programmes);
    }

    public Optional<Integer> getEnsembleID() {
        return Optional.ofNullable(ensembleID);
    }

    public Optional<Long> getTimestamp() {
        return Optional.ofNullable(timestamp);
    }

    public Optional<String> getEnsembleLabel() {
        return Optional.ofNullable(ensembleLabel);
    }

    @Override
    public String toString() {
        return "DABMetadata [programmes=" + programmes + ", ensembleID=" + ensembleID + ", timestamp=" + timestamp
                + ", ensembleLabel=" + ensembleLabel + "]";
    }

}
