package io.github.defective4.sdr.owrxclient.command;

import io.github.defective4.sdr.owrxclient.model.param.FrequencyParams;

public class SetFrequencyCommand extends ParameterClientCommand {

    public SetFrequencyCommand(FrequencyParams frequency) {
        super("setfrequency", frequency);
    }

}
