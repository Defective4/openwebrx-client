package io.github.defective4.sdr.owrxclient;

import java.net.URI;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

import io.github.defective4.sdr.owrxclient.client.OpenWebRXClient;
import io.github.defective4.sdr.owrxclient.event.OWRXAdapter;
import io.github.defective4.sdr.owrxclient.message.client.DSPControlCommand;
import io.github.defective4.sdr.owrxclient.model.Modulation;
import io.github.defective4.sdr.owrxclient.model.param.DSPParams;

public class Main {
    public static void main(String[] args) {
        try {
            SourceDataLine sdl = AudioSystem.getSourceDataLine(new AudioFormat(48000, 16, 1, true, false));
            sdl.open();
            sdl.start();

            OpenWebRXClient client = new OpenWebRXClient(URI.create("wss://radio.raspberry.local/ws/"));
            client.addListener(new OWRXAdapter() {
                @Override
                public void handshakeReceived(String server, String version) {
                    System.out.println(server + ": " + version);
                    client.sendCommand(new DSPControlCommand(new DSPParams(-100000, Modulation.wfm, null)));
                    client.startDSP();
                }

                @Override
                public void highQualityAudioReceived(byte[] data) {
                    sdl.write(data, 0, data.length);
                }

                @Override
                public void lowQualityAudioReceived(byte[] data) {}
            });
            client.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
