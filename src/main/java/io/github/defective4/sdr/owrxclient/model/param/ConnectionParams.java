package io.github.defective4.sdr.owrxclient.model.param;

public class ConnectionParams {
    private final int output_rate, hd_output_rate;

    public ConnectionParams(int outputRate, int hdOutputRate) {
        output_rate = outputRate;
        hd_output_rate = hdOutputRate;
    }

}
