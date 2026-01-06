package io.github.defective4.sdr.owrxclient.model.metadata;

import java.util.Arrays;
import java.util.Optional;

import com.google.gson.annotations.SerializedName;

public class RDSMetadata extends Metadata {

    @SerializedName("alt_frequencies_a")
    private final int[] altFrequencies;
    @SerializedName("is_music;")
    private final Boolean music;
    private final String pi;
    @SerializedName("prog_type")
    private final String programType;
    private final String ps;

    private final String radiotext;

    private final Boolean ta;

    private final Boolean tp;

    public RDSMetadata(String pi, boolean tp, String programType, boolean ta, boolean music, String ps,
            int[] altFrequencies, String radiotext) {
        super(Metadata.Type.WFM);
        this.pi = pi;
        this.tp = tp;
        this.programType = programType;
        this.ta = ta;
        this.music = music;
        this.ps = ps;
        this.altFrequencies = altFrequencies;
        this.radiotext = radiotext;
    }

    public Optional<int[]> getAltFrequencies() {
        return Optional.ofNullable(altFrequencies);
    }

    public Optional<String> getPI() {
        return Optional.ofNullable(pi);
    }

    public Optional<String> getProgramType() {
        return Optional.ofNullable(programType);
    }

    public Optional<String> getRadiotext() {
        return Optional.ofNullable(radiotext);
    }

    public Optional<String> getStation() {
        return Optional.ofNullable(ps);
    }

    public Optional<Boolean> isMusic() {
        return Optional.ofNullable(music);
    }

    public Optional<Boolean> isTA() {
        return Optional.ofNullable(ta);
    }

    public Optional<Boolean> isTP() {
        return Optional.ofNullable(tp);
    }

    @Override
    public String toString() {
        return "RDSMetadata [pi=" + pi + ", tp=" + tp + ", programType=" + programType + ", ta=" + ta + ", music="
                + music + ", ps=" + ps + ", altFrequencies=" + Arrays.toString(altFrequencies)
                + ", radiotext=" + radiotext + "]";
    }

}
