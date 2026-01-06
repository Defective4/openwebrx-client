package io.github.defective4.sdr.owrxclient.client;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import io.github.defective4.sdr.owrxclient.event.OWRXListener;
import io.github.defective4.sdr.owrxclient.message.client.ClientChatCommand;
import io.github.defective4.sdr.owrxclient.message.client.ClientCommand;
import io.github.defective4.sdr.owrxclient.message.client.ConnectionPropertiesCommand;
import io.github.defective4.sdr.owrxclient.message.client.SetFrequencyCommand;
import io.github.defective4.sdr.owrxclient.model.ChatMessage;
import io.github.defective4.sdr.owrxclient.model.param.ConnectionParams;
import io.github.defective4.sdr.owrxclient.model.param.FrequencyParams;

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

}
