package io.github.defective4.sdr.owrxclient.model.param;

import java.util.Arrays;
import java.util.UUID;

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

    public ProfileParams(UUID[] profile) {
        this(profile, null);
    }

    public ProfileParams(UUID[] profile, String key) {
        this.profile = String.join("|", Arrays.stream(profile).map(UUID::toString).toArray(String[]::new));
        this.key = key;
    }

}
