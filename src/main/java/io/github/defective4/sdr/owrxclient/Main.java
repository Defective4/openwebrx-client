package io.github.defective4.sdr.owrxclient;

import java.net.URI;

import io.github.defective4.sdr.owrxclient.client.OpenWebRXClient;
import io.github.defective4.sdr.owrxclient.event.OWRXAdapter;
import io.github.defective4.sdr.owrxclient.model.ServerMessageType;

public class Main {
    public static void main(String[] args) {
        try {
            OpenWebRXClient client = new OpenWebRXClient(URI.create("wss://radio.raspberry.local/ws/"));
            client.addListener(new OWRXAdapter() {
                @Override
                public void handshakeReceived(String server, String version) {
                    System.out.println(server + ": " + version);
                }

                @Override
                public void serverMessageReceived(ServerMessageType type, Object message) {
                    System.out.println(type + ": " + message);
                }

            });
            client.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
