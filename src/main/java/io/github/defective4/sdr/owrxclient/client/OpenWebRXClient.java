package io.github.defective4.sdr.owrxclient.client;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import io.github.defective4.sdr.owrxclient.command.ClientChatCommand;
import io.github.defective4.sdr.owrxclient.command.ConnectionPropertiesCommand;
import io.github.defective4.sdr.owrxclient.command.SelectProfileCommand;
import io.github.defective4.sdr.owrxclient.command.SetFrequencyCommand;
import io.github.defective4.sdr.owrxclient.compression.AudioCompression;
import io.github.defective4.sdr.owrxclient.compression.FFTCompression;
import io.github.defective4.sdr.owrxclient.event.OWRXListener;
import io.github.defective4.sdr.owrxclient.model.ChatMessage;
import io.github.defective4.sdr.owrxclient.model.Modulation;
import io.github.defective4.sdr.owrxclient.model.ReceiverProfile;
import io.github.defective4.sdr.owrxclient.model.param.ConnectionParams;
import io.github.defective4.sdr.owrxclient.model.param.DSPParams;
import io.github.defective4.sdr.owrxclient.model.param.FrequencyParams;
import io.github.defective4.sdr.owrxclient.model.param.ProfileParams;

public class OpenWebRXClient {
    private final List<OWRXListener> listeners = new CopyOnWriteArrayList<>();
    private final OWRXSocket socket;

    public OpenWebRXClient(URI uri) {
        socket = new OWRXSocket(uri, this);
    }

    public boolean addListener(OWRXListener listener) {
        return listeners.add(listener);
    }

    public void close() {
        socket.close();
    }

    public void connect() throws InterruptedException {
        socket.connectBlocking();
        socket.send("SERVER DE CLIENT type=receiver");
        socket.sendCommand(new ConnectionPropertiesCommand(new ConnectionParams(12000, 48000)));
    }

    public void forceAudioCompression(AudioCompression audioCompression) {
        socket.forceAudioCompression(audioCompression);
    }

    public void forceFFTCompression(FFTCompression fftCompression) {
        socket.forceFFTCompression(fftCompression);
    }

    public Optional<AudioCompression> getAudioCompression() {
        return socket.getAudioCompression();
    }

    public List<OWRXListener> getListeners() {
        return Collections.unmodifiableList(listeners);
    }

    public boolean removeListener(OWRXListener listener) {
        return listeners.remove(listener);
    }

    public void sendChatMessage(ChatMessage message) {
        socket.sendCommand(new ClientChatCommand(message));
    }

    public void sendChatMessage(String username, String message) {
        sendChatMessage(new ChatMessage(username, message));
    }

    public void setAudioService(int service) {
        setDSP(new DSPParams(null, null, null, null, null, service, null, null));
    }

    public void setCenterFrequency(int frequency) {
        setCenterFrequency(frequency, null);
    }

    public void setCenterFrequency(int frequency, String key) {
        socket.sendCommand(new SetFrequencyCommand(new FrequencyParams(frequency, key)));
    }

    public void setDemodulatorScope(float highCut, float lowCut) {
        setDSP(new DSPParams((int) highCut, (int) lowCut, null, null, null, null, null, null));
    }

    public void setDSP(DSPParams dspParams) {
        socket.setDSP(Objects.requireNonNull(dspParams));
    }

    public void setModulation(Modulation modulation) {
        setDSP(new DSPParams(null, Objects.requireNonNull(modulation), null));
    }

    public void setModulation(Modulation primary, Modulation secondary) {
        setDSP(new DSPParams(null, Objects.requireNonNull(primary), Objects.requireNonNull(secondary)));
    }

    public void setOffsetFrequency(float offset) {
        setDSP(new DSPParams(null, null, (int) offset, null, null, null, null, null));
    }

    public void setSecondaryModulation(Modulation secondary) {
        Modulation[] a = Objects.requireNonNull(secondary).getUnderlying();
        setDSP(new DSPParams(null, a.length > 0 ? a[0] : Modulation.empty, secondary));
    }

    public void setSecondaryOffset(float offset) {
        setDSP(new DSPParams(null, null, null, null, null, null, null, (int) offset));
    }

    public void startDSP() {
        socket.startDSP();
    }

    public void switchProfile(ReceiverProfile profile) {
        socket.sendCommand(new SelectProfileCommand(new ProfileParams(profile)));
    }

    public void switchProfile(ReceiverProfile profile, String key) {
        socket.sendCommand(new SelectProfileCommand(new ProfileParams(profile, key)));
    }

    public void switchProfile(UUID[] profile) {
        socket.sendCommand(new SelectProfileCommand(new ProfileParams(profile)));
    }

    public void switchProfile(UUID[] profile, String key) {
        socket.sendCommand(new SelectProfileCommand(new ProfileParams(profile, key)));
    }

}
