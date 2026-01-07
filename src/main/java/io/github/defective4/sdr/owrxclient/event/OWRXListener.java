package io.github.defective4.sdr.owrxclient.event;

import java.util.List;

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
import io.github.defective4.sdr.owrxclient.model.demod.DemodulatorResult;
import io.github.defective4.sdr.owrxclient.model.metadata.DABMetadata;
import io.github.defective4.sdr.owrxclient.model.metadata.RDSMetadata;

public interface OWRXListener {

    void bandsUpdated(Band[] bands);

    void batteryStateUpdated(BatteryInfo batteryInfo);

    void bookmarksUpdated(Bookmark[] bookmarks);

    void chatMessageReceived(ServerChatMessage message);

    void clientErrored(Exception ex);

    void cpuUsageUpdated(float cpuUsage);

    void demodulatorResultReceived(DemodulatorResult result);

    void dialFrequenciesUpdated(DialFrequency[] frequencies);

    void featuresUpdated(List<Feature> features);

    void fftUpdated(float[] fft);

    void handshakeReceived(String server, String version);

    void highQualityAudioReceived(byte[] data);

    void lowQualityAudioReceived(byte[] data);

    void numberOfClientsUpdated(int clients);

    void rdsReceived(RDSMetadata rds);

    void receiverDetailsReceived(ReceiverDetails details);

    void receiverModesUpdated(ReceiverMode[] modes);

    void receiverProfilesUpdated(ReceiverProfile[] profiles);

    void secondaryConfigChanged(SecondaryConfig config);

    void secondaryFFTUpdated(float[] fft);

    void serverConfigChanged(ServerConfig config);

    void signalMeterUpdated(float signalLevel);

    void temperatureUpdated(int temperatureC);

    void dabMetadataReceived(DABMetadata metadata);
}
