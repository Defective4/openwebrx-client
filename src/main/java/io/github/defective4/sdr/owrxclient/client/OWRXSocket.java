package io.github.defective4.sdr.owrxclient.client;

import java.net.URI;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import io.github.defective4.sdr.owrxclient.message.client.ClientCommand;
import io.github.defective4.sdr.owrxclient.model.ServerMessageType;

public class OWRXSocket extends WebSocketClient {

    private static final String HS_HEADER = "CLIENT DE SERVER";
    private final OpenWebRXClient client;

    private final Gson gson = new Gson();

    private boolean handshakeCompleted;
    private String serverFlavor, serverVersion;

    public OWRXSocket(URI serverUri, OpenWebRXClient client) {
        super(serverUri);
        this.client = client;
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
            case 0x02, 0x04 -> {
                boolean hi = type == 0x04;
                byte[] data = new byte[bytes.remaining()];
                bytes.get(data);
                client.getListeners().forEach(ls -> {
                    if (hi)
                        ls.highQualityAudioReceived(data);
                    else
                        ls.lowQualityAudioReceived(data);
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
            System.err.println(message); // TODO remove
            JsonObject root = JsonParser.parseString(message).getAsJsonObject();
            try {
                ServerMessageType type = ServerMessageType.valueOf(root.get("type").getAsString().toUpperCase());
                Object serverMessage = gson.fromJson(root.get("value"), type.getModelClass());
                client.getListeners().forEach(ls -> ls.serverMessageReceived(type, serverMessage));
            } catch (IllegalArgumentException e) {}
        } catch (NullPointerException | JsonParseException e) {
            System.err.println("Invalid JSON message received from server");
        }
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {}

    public void sendCommand(ClientCommand command) {
        send(gson.toJson(command));
    }

    public void startDSP() {
        JsonObject root = new JsonObject();
        root.addProperty("type", "dspcontrol");
        root.addProperty("action", "start");
        send(gson.toJson(root));
    }

}
