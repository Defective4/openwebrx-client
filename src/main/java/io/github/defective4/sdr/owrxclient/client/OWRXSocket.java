package io.github.defective4.sdr.owrxclient.client;

import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import io.github.defective4.sdr.owrxclient.command.ClientCommand;
import io.github.defective4.sdr.owrxclient.command.DSPControlCommand;
import io.github.defective4.sdr.owrxclient.compression.ADPCMDecoder;
import io.github.defective4.sdr.owrxclient.compression.AudioCompression;
import io.github.defective4.sdr.owrxclient.compression.FFTCompression;
import io.github.defective4.sdr.owrxclient.model.Band;
import io.github.defective4.sdr.owrxclient.model.BatteryInfo;
import io.github.defective4.sdr.owrxclient.model.Bookmark;
import io.github.defective4.sdr.owrxclient.model.DialFrequency;
import io.github.defective4.sdr.owrxclient.model.Feature;
import io.github.defective4.sdr.owrxclient.model.ReceiverDetails;
import io.github.defective4.sdr.owrxclient.model.ReceiverMode;
import io.github.defective4.sdr.owrxclient.model.ReceiverProfile;
import io.github.defective4.sdr.owrxclient.model.SecondaryConfig;
import io.github.defective4.sdr.owrxclient.model.ServerChatMessage;
import io.github.defective4.sdr.owrxclient.model.ServerConfig;
import io.github.defective4.sdr.owrxclient.model.ServerMessageType;
import io.github.defective4.sdr.owrxclient.model.demod.Demodulator;
import io.github.defective4.sdr.owrxclient.model.demod.DemodulatorResult;
import io.github.defective4.sdr.owrxclient.model.demod.PlaintextResult;
import io.github.defective4.sdr.owrxclient.model.metadata.Metadata.Type;
import io.github.defective4.sdr.owrxclient.model.metadata.RDSMetadata;
import io.github.defective4.sdr.owrxclient.model.param.DSPParams;

public class OWRXSocket extends WebSocketClient {

    private static final byte COMPRESS_FFT_PAD_N = 10;
    private static final String HS_HEADER = "CLIENT DE SERVER";

    long avg = 0;

    long sec = 0;
    private final ADPCMDecoder audioAdpcmDecoder = new ADPCMDecoder();
    private AudioCompression audioCompression = AudioCompression.NONE;

    private boolean audioCompressionForced;
    private final OpenWebRXClient client;

    private final ADPCMDecoder fftAdpcmDecoder = new ADPCMDecoder();

    private FFTCompression fftCompression = FFTCompression.NONE;
    private boolean fftCompressionForced;

    private int fftSize = 0;

    private final Gson gson = new Gson();
    private boolean handshakeCompleted;
    private int secondaryFFTSize = 0;
    private String serverFlavor, serverVersion;

    public OWRXSocket(URI serverUri, OpenWebRXClient client) {
        super(serverUri);
        this.client = client;
    }

    public void forceAudioCompression(AudioCompression audioCompression) {
        this.audioCompression = Objects.requireNonNull(audioCompression);
        audioCompressionForced = true;
    }

    public void forceFFTCompression(FFTCompression fftCompression) {
        this.fftCompression = Objects.requireNonNull(fftCompression);
        fftCompressionForced = true;
    }

    public Optional<AudioCompression> getAudioCompression() {
        return Optional.ofNullable(audioCompression);
    }

    public Optional<FFTCompression> getFftCompression() {
        return Optional.ofNullable(fftCompression);
    }

    public float getFftSize() {
        return fftSize;
    }

    public String getServerFlavor() {
        return serverFlavor;
    }

    public String getServerVersion() {
        return serverVersion;
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {}

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onMessage(ByteBuffer bytes) {
        byte type = bytes.get();
        switch (type) {
            case 0x01, 0x03 -> {
                boolean secondary = type == 0x03;
                float[] fft;
                if (fftCompression == FFTCompression.ADPCM) {
                    fftAdpcmDecoder.reset();
                    byte[] data = new byte[bytes.remaining()];
                    bytes.get(data);
                    short[] waterfallShort = fftAdpcmDecoder.decode(data);
                    fft = new float[waterfallShort.length - COMPRESS_FFT_PAD_N];
                    for (int i = 0; i < waterfallShort.length - COMPRESS_FFT_PAD_N; i++)
                        fft[i] = waterfallShort[i] / 100;
                } else {
                    bytes.order(ByteOrder.LITTLE_ENDIAN);
                    fft = new float[bytes.remaining() / 4];
                    for (int i = 0; i < fft.length; i++) {
                        fft[i] = bytes.getFloat();
                    }
                }
                float[] correctedFFT;
                int expected = secondary ? secondaryFFTSize : fftSize;
                if (fft.length != expected) {
                    correctedFFT = Arrays.copyOf(fft, expected);
                } else
                    correctedFFT = fft;
                client.getListeners().forEach(ls -> {
                    if (secondary)
                        ls.secondaryFFTUpdated(correctedFFT);
                    else
                        ls.fftUpdated(correctedFFT);
                });
            }
            case 0x02, 0x04 -> {
                boolean hi = type == 0x04;
                byte[] data = new byte[bytes.remaining()];
                bytes.get(data);
                client.getListeners().forEach(ls -> {
                    byte[] pcm = data;
                    if (audioCompression == AudioCompression.ADPCM) {
                        short[] decoded = audioAdpcmDecoder.decodeWithSync(data);
                        ByteBuffer buffer = ByteBuffer.allocate(decoded.length * 2).order(ByteOrder.LITTLE_ENDIAN);
                        for (short s : decoded) buffer.putShort(s);
                        pcm = buffer.array();
                    }
                    if (pcm.length % 2 != 0) {
                        pcm = Arrays.copyOf(pcm, pcm.length + 1);
                    }
                    if (hi)
                        ls.highQualityAudioReceived(pcm);
                    else
                        ls.lowQualityAudioReceived(pcm);
                });
            }
            default -> {}
        }
    }

    @Override
    public void onMessage(String message) {
        if (!handshakeCompleted) {
            if (!message.startsWith(HS_HEADER + " ")) {
                close();
                throw new IllegalStateException("Invalid message received");
            }
            String[] paramList = message.substring(HS_HEADER.length()).split(" ");
            Map<String, String> params = new HashMap<>();

            for (String param : paramList) {
                String[] kv = param.split("=");
                if (kv.length < 2) continue;
                String[] value = new String[kv.length - 1];
                System.arraycopy(kv, 1, value, 0, value.length);
                params.put(kv[0], String.join("=", value));
            }

            try {
                serverVersion = Objects.requireNonNull(params.get("version"));
                serverFlavor = Objects.requireNonNull(params.get("server"));
            } catch (NullPointerException e) {
                throw new IllegalStateException("Invalid handshake received");
            }

            handshakeCompleted = true;
            client.getListeners().forEach(ls -> ls.handshakeReceived(serverFlavor, serverVersion));
            return;
        }
        try {
            JsonObject root = JsonParser.parseString(message).getAsJsonObject();
            System.err.println(message);
            try {
                ServerMessageType type = ServerMessageType.valueOf(root.get("type").getAsString().toUpperCase());
                Object serverMessage = gson.fromJson(root.has("value") ? root.get("value") : root,
                        type.getModelClass());
                client.getListeners().forEach(ls -> {
                    switch (type) {
                        case CONFIG -> {
                            ServerConfig cfg = (ServerConfig) serverMessage;
                            if (cfg.audioCompression() != null && !audioCompressionForced) try {
                                audioCompression = AudioCompression.valueOf(cfg.audioCompression().toUpperCase());
                            } catch (IllegalArgumentException e) {
                                IOException ex = new IOException(
                                        "Server requested an unknown audio compression: " + cfg.audioCompression());
                                client.getListeners().forEach(l -> ls.clientErrored(ex));
                                close();
                            }

                            if (cfg.fftCompression() != null && !fftCompressionForced) try {
                                fftCompression = FFTCompression.valueOf(cfg.fftCompression().toUpperCase());
                            } catch (IllegalArgumentException e) {
                                IOException ex = new IOException(
                                        "Server requested an unknown FFT compression: " + cfg.fftCompression());
                                client.getListeners().forEach(l -> ls.clientErrored(ex));
                                close();
                            }
                            if (cfg.fftSize() != null) fftSize = cfg.fftSize();
                            ls.serverConfigChanged(cfg);
                        }
                        case RECEIVER_DETAILS -> ls.receiverDetailsReceived((ReceiverDetails) serverMessage);
                        case DIAL_FREQUENCIES -> ls.dialFrequenciesUpdated((DialFrequency[]) serverMessage);
                        case BOOKMARKS -> ls.bookmarksUpdated((Bookmark[]) serverMessage);
                        case BANDS -> ls.bandsUpdated((Band[]) serverMessage);
                        case CLIENTS -> ls.numberOfClientsUpdated((int) serverMessage);
                        case FEATURES -> {
                            Map<String, Boolean> featureMap = (Map<String, Boolean>) serverMessage;
                            List<Feature> features = new ArrayList<>();
                            for (Entry<String, Boolean> entry : featureMap.entrySet()) {
                                if (!entry.getValue()) continue;
                                try {
                                    features.add(Feature.valueOf(entry.getKey().toUpperCase().replace("-", "_")));
                                } catch (IllegalArgumentException e) {}
                            }
                            ls.featuresUpdated(Collections.unmodifiableList(features));
                        }
                        case MODES -> ls.receiverModesUpdated((ReceiverMode[]) serverMessage);
                        case CPUUSAGE -> ls.cpuUsageUpdated((float) serverMessage);
                        case SMETER -> ls.signalMeterUpdated((float) serverMessage);
                        case TEMPERATURE -> ls.temperatureUpdated((int) serverMessage);
                        case PROFILES -> ls.receiverProfilesUpdated((ReceiverProfile[]) serverMessage);
                        case METADATA -> {
                            JsonObject obj = (JsonObject) serverMessage;
                            Type metaType;
                            try {
                                metaType = Type.valueOf(obj.get("mode").getAsString().toUpperCase());
                            } catch (IllegalArgumentException e) {
                                metaType = null;
                            }

                            if (metaType != null) switch (metaType) {
                                case WFM -> ls.rdsReceived(gson.fromJson(obj, RDSMetadata.class));
                                default -> {}
                            }
                        }
                        case CHAT_MESSAGE -> ls.chatMessageReceived((ServerChatMessage) serverMessage);
                        case BATTERY -> ls.batteryStateUpdated((BatteryInfo) serverMessage);
                        case SECONDARY_CONFIG -> {
                            SecondaryConfig cfg = (SecondaryConfig) serverMessage;
                            if (cfg.secondaryFFTSize() != null) secondaryFFTSize = cfg.secondaryFFTSize();
                            ls.secondaryConfigChanged(cfg);
                        }
                        case SECONDARY_DEMOD -> {
                            JsonElement element = (JsonElement) serverMessage;
                            if (element != null) {
                                DemodulatorResult result = null;
                                if (element.isJsonPrimitive()) {
                                    result = new PlaintextResult(element.getAsString());
                                } else if (element.isJsonObject()) {
                                    JsonObject obj = element.getAsJsonObject();
                                    if (obj.has("mode")) {
                                        try {
                                            Demodulator demod = Demodulator
                                                    .valueOf(obj.get("mode").getAsString().toUpperCase());
                                            result = gson.fromJson(obj, demod.getResultClass());
                                        } catch (IllegalArgumentException e) {}
                                    }
                                }
                                if (result != null) ls.demodulatorResultReceived(result);
                            }
                        }
                        default -> {}
                    }
                });
            } catch (IllegalArgumentException e) {}
        } catch (NullPointerException | JsonParseException e) {
            e.printStackTrace();
            System.err.println("Invalid JSON message received from server");
        }
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {}

    public void sendCommand(ClientCommand command) {
        send(gson.toJson(command));
    }

    public void setDSP(DSPParams dspParams) {
        sendCommand(new DSPControlCommand(dspParams));
    }

    public void startDSP() {
        JsonObject root = new JsonObject();
        root.addProperty("type", "dspcontrol");
        root.addProperty("action", "start");
        send(gson.toJson(root));
    }

}
