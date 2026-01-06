package io.github.defective4.sdr.owrxclient.event;

import java.util.List;

import io.github.defective4.sdr.owrxclient.message.server.ReceiverDetails;
import io.github.defective4.sdr.owrxclient.message.server.ServerConfig;
import io.github.defective4.sdr.owrxclient.model.Band;
import io.github.defective4.sdr.owrxclient.model.Bookmark;
import io.github.defective4.sdr.owrxclient.model.DialFrequency;
import io.github.defective4.sdr.owrxclient.model.Feature;
import io.github.defective4.sdr.owrxclient.model.ReceiverMode;
import io.github.defective4.sdr.owrxclient.model.ReceiverProfile;
import io.github.defective4.sdr.owrxclient.model.ServerChatMessage;
import io.github.defective4.sdr.owrxclient.model.metadata.RDSMetadata;

public abstract class OWRXAdapter implements OWRXListener {

    @Override
    public void bandsUpdated(Band[] bands) {}

    @Override
    public void bookmarksUpdated(Bookmark[] bookmarks) {}

    @Override
    public void chatMessageReceived(ServerChatMessage message) {}

    @Override
    public void clientErrored(Exception ex) {}

    @Override
    public void cpuUsageUpdated(float cpuUsage) {}

    @Override
    public void dialFrequenciesUpdated(DialFrequency[] frequencies) {}

    @Override
    public void featuresUpdated(List<Feature> features) {}

    @Override
    public void handshakeReceived(String server, String version) {}

    @Override
    public void highQualityAudioReceived(byte[] data) {}

    @Override
    public void lowQualityAudioReceived(byte[] data) {}

    @Override
    public void numberOfClientsUpdated(int clients) {}

    @Override
    public void rdsReceived(RDSMetadata rds) {}

    @Override
    public void receiverDetailsReceived(ReceiverDetails details) {}

    @Override
    public void receiverModesUpdated(ReceiverMode[] modes) {}

    @Override
    public void receiverProfilesUpdated(ReceiverProfile[] profiles) {}

    @Override
    public void serverConfigChanged(ServerConfig config) {}

    @Override
    public void signalMeterUpdated(float signalLevel) {}

    @Override
    public void temperatureUpdated(int temperatureC) {}

}
