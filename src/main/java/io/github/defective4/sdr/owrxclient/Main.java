package io.github.defective4.sdr.owrxclient;

import java.net.URI;
import java.util.Arrays;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

import io.github.defective4.sdr.owrxclient.client.OpenWebRXClient;
import io.github.defective4.sdr.owrxclient.event.OWRXAdapter;
import io.github.defective4.sdr.owrxclient.model.ReceiverProfile;
import io.github.defective4.sdr.owrxclient.model.demod.DemodulatorResult;

public class Main {

    public static void main(String[] args) {
        try {
            SourceDataLine sdl = AudioSystem.getSourceDataLine(new AudioFormat(12000, 16, 1, true, false));
            sdl.open();
            sdl.start();

            OpenWebRXClient client = new OpenWebRXClient(URI.create("wss://radio.raspberry.local/ws/"));

            client.addListener(new OWRXAdapter() {

                @Override
                public void clientErrored(Exception ex) {
                    ex.printStackTrace();
                }

                @Override
                public void demodulatorResultReceived(DemodulatorResult result) {
                    System.out.println(result);
                }

                @Override
                public void handshakeReceived(String server, String version) {
                    client.startDSP();
                }

                @Override
                public void highQualityAudioReceived(byte[] data) {}

                @Override
                public void lowQualityAudioReceived(byte[] data) {
                    sdl.write(data, 0, data.length);
                }

                @Override
                public void receiverProfilesUpdated(ReceiverProfile[] profiles) {
                    ReceiverProfile profile = Arrays.stream(profiles).filter(prof -> prof.name().equals("RTL-SDR 2m"))
                            .findAny().orElse(null);
                    client.switchProfile(profile);
                    try {
                        client.setModulation(client.getModeByName("packet").get());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    client.setOffsetFrequency(-200e3f);
//                    client.setModulation(Modulation.nfm);
                }

            });
            client.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
