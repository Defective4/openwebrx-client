package io.github.defective4.sdr.owrxclient;

import java.net.URI;

import io.github.defective4.sdr.owrxclient.client.OpenWebRXClient;
import io.github.defective4.sdr.owrxclient.event.OWRXAdapter;

public class Main {
    public static void main(String[] args) {
        try {
            OpenWebRXClient client = new OpenWebRXClient(URI.create("wss://radio.raspberry.local/ws/"));
            client.addListener(new OWRXAdapter() {
                @Override
                public void handshakeReceived(String server, String version) {
                    System.out.println(server + ": " + version);
                }
            });
            client.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
