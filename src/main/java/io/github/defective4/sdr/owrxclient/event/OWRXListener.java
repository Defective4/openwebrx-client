package io.github.defective4.sdr.owrxclient.event;

public interface OWRXListener {
    void handshakeReceived(String server, String version);
}
