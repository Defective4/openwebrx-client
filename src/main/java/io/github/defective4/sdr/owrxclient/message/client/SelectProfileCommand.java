package io.github.defective4.sdr.owrxclient.message.client;

import io.github.defective4.sdr.owrxclient.model.param.ProfileParams;

public class SelectProfileCommand extends ParameterClientCommand {

    public SelectProfileCommand(ProfileParams params) {
        super("selectprofile", params);
    }

}
