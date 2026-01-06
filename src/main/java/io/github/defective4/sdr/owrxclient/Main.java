package io.github.defective4.sdr.owrxclient;

import java.net.URI;
import java.util.Arrays;

import io.github.defective4.sdr.owrxclient.client.OpenWebRXClient;
import io.github.defective4.sdr.owrxclient.event.OWRXAdapter;
import io.github.defective4.sdr.owrxclient.message.client.DSPControlCommand;
import io.github.defective4.sdr.owrxclient.model.Modulation;
import io.github.defective4.sdr.owrxclient.model.ReceiverProfile;
import io.github.defective4.sdr.owrxclient.model.param.DSPParams;

public class Main {
    public static void main(String[] args) {
        try {
//            SourceDataLine sdl = AudioSystem.getSourceDataLine(new AudioFormat(48000, 16, 1, true, false));
//            sdl.open();
//            sdl.start();

            OpenWebRXClient client = new OpenWebRXClient(URI.create("wss://radio.raspberry.local/ws/"));
            client.addListener(new OWRXAdapter() {

                @Override
                public void handshakeReceived(String server, String version) {
                    client.startDSP();
                }

                @Override
                public void highQualityAudioReceived(byte[] data) {
//                    sdl.write(data, 0, data.length);
                }

                @Override
                public void lowQualityAudioReceived(byte[] data) {}

                @Override
                public void receiverProfilesUpdated(ReceiverProfile[] profiles) {
                    ReceiverProfile profile = Arrays.stream(profiles)
                            .filter(prof -> prof.name().equals("RTL-SDR Broadcast FM 86-89 MHz")).findAny()
                            .orElse(null);
                    client.switchProfile(profile);
                    client.sendCommand(new DSPControlCommand(new DSPParams((int) -100e3, Modulation.wfm, null)));
                }

            });
            client.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
