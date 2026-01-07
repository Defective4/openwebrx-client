package io.github.defective4.sdr.owrxclient.model.demod;

import com.google.gson.JsonObject;

public class UnknownDemodulatorResult extends DemodulatorResult {

    private final JsonObject raw;

    public UnknownDemodulatorResult(JsonObject raw) {
        super("thirdparty");
        this.raw = raw;
    }

    public JsonObject getRaw() {
        return raw;
    }

}
