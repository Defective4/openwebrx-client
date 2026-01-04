package io.github.defective4.sdr.owrxclient.event;

import io.github.defective4.sdr.owrxclient.model.ServerMessageType;

public abstract class OWRXAdapter implements OWRXListener {

    @Override
    public void handshakeReceived(String server, String version) {}

    @Override
    public void serverMessageReceived(ServerMessageType type, Object message) {}

}
