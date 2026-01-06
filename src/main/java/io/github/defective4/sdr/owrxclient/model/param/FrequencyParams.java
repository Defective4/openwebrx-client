package io.github.defective4.sdr.owrxclient.model.param;

public record FrequencyParams(int frequency, String key) {
    public FrequencyParams(int frequency) {
        this(frequency, null);
    }
}
