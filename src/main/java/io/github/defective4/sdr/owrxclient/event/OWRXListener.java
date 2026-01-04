package io.github.defective4.sdr.owrxclient.event;

import io.github.defective4.sdr.owrxclient.message.server.ReceiverDetails;
import io.github.defective4.sdr.owrxclient.message.server.ServerConfig;
import io.github.defective4.sdr.owrxclient.model.Bookmark;
import io.github.defective4.sdr.owrxclient.model.DialFrequency;

public interface OWRXListener {
    void dialFrequenciesUpdated(DialFrequency[] frequencies);

    void handshakeReceived(String server, String version);

    void highQualityAudioReceived(byte[] data);

    void lowQualityAudioReceived(byte[] data);

    void receiverDetailsReceived(ReceiverDetails details);

    void serverConfigChanged(ServerConfig config);

    void bookmarksUpdated(Bookmark[] bookmarks);
}
