package io.github.defective4.sdr.owrxclient.model.demod;

import com.google.gson.annotations.SerializedName;

public class FTMessage extends DemodulatorResult {
    private final double db;
    private final double dt;
    @SerializedName("freq")
    private final int frequency;
    private final int interval;
    @SerializedName("msg")
    private final String message;
    private final long timestamp;

    public FTMessage(String mode, long timestamp, double db, double dt, int frequency, String message, int interval) {
        super(mode);
        this.timestamp = timestamp;
        this.db = db;
        this.dt = dt;
        this.frequency = frequency;
        this.message = message;
        this.interval = interval;
    }

    public double getDb() {
        return db;
    }

    public double getDt() {
        return dt;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getInterval() {
        return interval;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "FTMessage [timestamp=" + timestamp + ", db=" + db + ", dt=" + dt + ", frequency=" + frequency
                + ", message=" + message + ", interval=" + interval + "]";
    }

}
