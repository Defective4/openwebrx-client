package io.github.defective4.sdr.owrxclient;

import java.awt.Color;
import java.awt.Graphics;
import java.net.URI;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

import io.github.defective4.sdr.owrxclient.client.OpenWebRXClient;
import io.github.defective4.sdr.owrxclient.command.DSPControlCommand;
import io.github.defective4.sdr.owrxclient.event.OWRXAdapter;
import io.github.defective4.sdr.owrxclient.model.Modulation;
import io.github.defective4.sdr.owrxclient.model.ReceiverProfile;
import io.github.defective4.sdr.owrxclient.model.param.DSPParams;

public class Main {

    private static final long[] fft = new long[8192];

    public static void main(String[] args) {
        try {
//            SourceDataLine sdl = AudioSystem.getSourceDataLine(new AudioFormat(48000, 16, 1, true, false));
//            sdl.open();
//            sdl.start();

            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel() {

                @Override
                protected void paintComponent(Graphics g) {
                    g.setColor(Color.black);
                    g.fillRect(0, 0, getWidth(), getHeight());
                    g.setColor(Color.white);
                    for (int i = 8; i < fft.length; i += 8) {
                        double pc = fft[i] / 20000d;
                        double pp = fft[i - 8] / 20000d;

                        int yc = (int) (pc * 480d);
                        int yp = (int) (pp * 480d);

                        g.drawLine(i / 8 - 1, yp, i / 8, yc);
                    }
                }

            };

            frame.setContentPane(panel);
            frame.setSize(640, 480);
            frame.setVisible(true);

            OpenWebRXClient client = new OpenWebRXClient(URI.create("wss://radio.raspberry.local/ws/"));
            client.addListener(new OWRXAdapter() {

                @Override
                public void clientErrored(Exception ex) {
                    ex.printStackTrace();
                }

                @Override
                public void fftUpdated(float[] fft) {
                    for (int i = 0; i < fft.length; i++) {
                        long val = 10000 - (long) (fft[i] * 100d);
                        Main.fft[i] = val;
                    }
                    panel.repaint();
                }

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
