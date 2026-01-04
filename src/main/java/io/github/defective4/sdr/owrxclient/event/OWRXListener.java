package io.github.defective4.sdr.owrxclient.event;

import io.github.defective4.sdr.owrxclient.model.ServerMessageType;

public interface OWRXListener {
    void handshakeReceived(String server, String version);

    void serverMessageReceived(ServerMessageType type, Object message);
}
