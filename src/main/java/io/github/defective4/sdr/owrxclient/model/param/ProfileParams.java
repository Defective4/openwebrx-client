package io.github.defective4.sdr.owrxclient.model.param;

import io.github.defective4.sdr.owrxclient.model.ReceiverProfile;

public class ProfileParams {
    private final String key;
    private final String profile;

    public ProfileParams(ReceiverProfile profile) {
        this(profile, null);
    }

    public ProfileParams(ReceiverProfile profile, String key) {
        this(profile.uuids(), key);
    }

    public ProfileParams(String[] profile) {
        this(profile, null);
    }

    public ProfileParams(String[] profile, String key) {
        this.profile = String.join("|", profile);
        this.key = key;
    }

}
