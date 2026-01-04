package io.github.defective4.sdr.owrxclient.event;

import io.github.defective4.sdr.owrxclient.message.server.ReceiverDetails;
import io.github.defective4.sdr.owrxclient.message.server.ServerConfig;

public interface OWRXListener {
    void handshakeReceived(String server, String version);

    void highQualityAudioReceived(byte[] data);

    void lowQualityAudioReceived(byte[] data);

    void receiverDetailsReceived(ReceiverDetails serverMessage);

    void serverConfigChanged(ServerConfig config);
}
