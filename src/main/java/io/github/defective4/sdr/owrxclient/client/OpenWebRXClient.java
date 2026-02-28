package io.github.defective4.sdr.owrxclient.client;

import java.net.URI;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import io.github.defective4.sdr.owrxclient.command.ClientChatCommand;
import io.github.defective4.sdr.owrxclient.command.ConnectionPropertiesCommand;
import io.github.defective4.sdr.owrxclient.command.SelectProfileCommand;
import io.github.defective4.sdr.owrxclient.command.SetFrequencyCommand;
import io.github.defective4.sdr.owrxclient.compression.AudioCompression;
import io.github.defective4.sdr.owrxclient.compression.FFTCompression;
import io.github.defective4.sdr.owrxclient.event.OWRXAdapter;
import io.github.defective4.sdr.owrxclient.event.OWRXListener;
import io.github.defective4.sdr.owrxclient.model.Bandpass;
import io.github.defective4.sdr.owrxclient.model.ChatMessage;
import io.github.defective4.sdr.owrxclient.model.ReceiverMode;
import io.github.defective4.sdr.owrxclient.model.ReceiverProfile;
import io.github.defective4.sdr.owrxclient.model.param.ConnectionParams;
import io.github.defective4.sdr.owrxclient.model.param.DSPParams;
import io.github.defective4.sdr.owrxclient.model.param.FrequencyParams;
import io.github.defective4.sdr.owrxclient.model.param.ProfileParams;

public class OpenWebRXClient {
    private final OWRXListener coreListener;
    private final List<OWRXListener> listeners = new CopyOnWriteArrayList<>();
    private final Set<ReceiverMode> modes = new HashSet<>();

    private final OWRXSocket socket;

    public OpenWebRXClient(URI uri) {
        socket = new OWRXSocket(uri, this);
        coreListener = new OWRXAdapter() {
            @Override
            public void receiverModesUpdated(ReceiverMode[] modes) {
                synchronized (OpenWebRXClient.this.modes) {
                    OpenWebRXClient.this.modes.clear();
                    Collections.addAll(OpenWebRXClient.this.modes, modes);
                }
            }
        };
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

    public OWRXListener getCoreListener() {
        return coreListener;
    }

    public List<OWRXListener> getListeners() {
        return Collections.unmodifiableList(listeners);
    }

    public Optional<ReceiverMode> getModeByName(String name) {
        synchronized (modes) {
            return modes.stream().filter(mode -> mode.modulation().equalsIgnoreCase(name)).findAny();
        }
    }

    public Set<String> getModeNames() {
        synchronized (modes) {
            return modes.stream().map(ReceiverMode::modulation).collect(Collectors.toSet());
        }
    }

    public Set<ReceiverMode> getModes() {
        return Collections.unmodifiableSet(modes);
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

    public void setModulation(ReceiverMode mode) {
        String[] underlying = mode.underlying();
        String secondary = underlying != null && underlying.length > 0 ? underlying[0] : null;
        ReceiverMode secondaryMode = secondary == null || secondary.equalsIgnoreCase("empty") ? null
                : getModeByName(secondary).orElseThrow(
                        () -> new IllegalArgumentException("This mode has an unrecognized underlying mode"));
        setModulation(mode, secondaryMode);
    }

    public void setModulation(ReceiverMode mode, ReceiverMode underlying) {
        Bandpass bp = (underlying == null ? mode : underlying).bandpass();
        setDSP(new DSPParams(bp == null ? null : bp.highCut(), bp == null ? null : bp.lowCut(), null, mode.modulation(),
                null, null, underlying == null ? null : underlying.modulation(), null));
    }

    public void setOffsetFrequency(float offset) {
        setDSP(new DSPParams(null, null, (int) offset, null, null, null, null, null));
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
