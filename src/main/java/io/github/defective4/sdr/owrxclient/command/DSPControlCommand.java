package io.github.defective4.sdr.owrxclient.command;

import io.github.defective4.sdr.owrxclient.model.param.DSPParams;

public class DSPControlCommand extends ParameterClientCommand {

    public DSPControlCommand(DSPParams params) {
        super("dspcontrol", params);
    }

}
