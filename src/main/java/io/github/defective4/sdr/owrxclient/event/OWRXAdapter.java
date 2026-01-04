package io.github.defective4.sdr.owrxclient.event;

import io.github.defective4.sdr.owrxclient.message.server.ReceiverDetails;
import io.github.defective4.sdr.owrxclient.message.server.ServerConfig;

public abstract class OWRXAdapter implements OWRXListener {

    @Override
    public void handshakeReceived(String server, String version) {}

    @Override
    public void highQualityAudioReceived(byte[] data) {}

    @Override
    public void lowQualityAudioReceived(byte[] data) {}

    @Override
    public void receiverDetailsReceived(ReceiverDetails serverMessage) {}

    @Override
    public void serverConfigChanged(ServerConfig config) {}

}
