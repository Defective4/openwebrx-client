package io.github.defective4.sdr.owrxclient.client;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import io.github.defective4.sdr.owrxclient.command.ClientChatCommand;
import io.github.defective4.sdr.owrxclient.command.ClientCommand;
import io.github.defective4.sdr.owrxclient.command.ConnectionPropertiesCommand;
import io.github.defective4.sdr.owrxclient.command.SelectProfileCommand;
import io.github.defective4.sdr.owrxclient.command.SetFrequencyCommand;
import io.github.defective4.sdr.owrxclient.compression.AudioCompression;
import io.github.defective4.sdr.owrxclient.event.OWRXListener;
import io.github.defective4.sdr.owrxclient.model.ChatMessage;
import io.github.defective4.sdr.owrxclient.model.ReceiverProfile;
import io.github.defective4.sdr.owrxclient.model.param.ConnectionParams;
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

    public void sendCommand(ClientCommand command) {
        socket.sendCommand(command);
    }

    public void setCenterFrequency(int frequency) {
        setCenterFrequency(frequency, null);
    }

    public void setCenterFrequency(int frequency, String key) {
        socket.sendCommand(new SetFrequencyCommand(new FrequencyParams(frequency, key)));
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
