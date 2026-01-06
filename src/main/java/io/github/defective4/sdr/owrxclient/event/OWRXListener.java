package io.github.defective4.sdr.owrxclient.event;

import java.util.List;

import io.github.defective4.sdr.owrxclient.message.server.ReceiverDetails;
import io.github.defective4.sdr.owrxclient.message.server.ServerConfig;
import io.github.defective4.sdr.owrxclient.model.Band;
import io.github.defective4.sdr.owrxclient.model.Bookmark;
import io.github.defective4.sdr.owrxclient.model.DialFrequency;
import io.github.defective4.sdr.owrxclient.model.Feature;

public interface OWRXListener {
    void bandsUpdated(Band[] bands);

    void bookmarksUpdated(Bookmark[] bookmarks);

    void dialFrequenciesUpdated(DialFrequency[] frequencies);

    void featuresUpdated(List<Feature> features);

    void handshakeReceived(String server, String version);

    void highQualityAudioReceived(byte[] data);

    void lowQualityAudioReceived(byte[] data);

    void numberOfClientsUpdated(int clients);

    void receiverDetailsReceived(ReceiverDetails details);

    void serverConfigChanged(ServerConfig config);
}
