package io.github.defective4.sdr.owrxclient.event;

public abstract class OWRXAdapter implements OWRXListener {

    @Override
    public void handshakeReceived(String server, String version) {}

}
