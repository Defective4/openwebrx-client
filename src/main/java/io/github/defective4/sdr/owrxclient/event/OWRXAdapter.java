package io.github.defective4.sdr.owrxclient.event;

import io.github.defective4.sdr.owrxclient.message.server.ReceiverDetails;
import io.github.defective4.sdr.owrxclient.message.server.ServerConfig;
import io.github.defective4.sdr.owrxclient.model.Band;
import io.github.defective4.sdr.owrxclient.model.Bookmark;
import io.github.defective4.sdr.owrxclient.model.DialFrequency;

public abstract class OWRXAdapter implements OWRXListener {

    @Override
    public void dialFrequenciesUpdated(DialFrequency[] frequencies) {}

    @Override
    public void handshakeReceived(String server, String version) {}

    @Override
    public void highQualityAudioReceived(byte[] data) {}

    @Override
    public void lowQualityAudioReceived(byte[] data) {}

    @Override
    public void receiverDetailsReceived(ReceiverDetails details) {}

    @Override
    public void serverConfigChanged(ServerConfig config) {}

    @Override
    public void bookmarksUpdated(Bookmark[] bookmarks) {}

    @Override
    public void bandsUpdated(Band[] bands) {}

}
