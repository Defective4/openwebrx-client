package io.github.defective4.sdr.owrxclient.model.demod;

import java.awt.Color;
import java.util.Optional;

import com.google.gson.annotations.SerializedName;

public class VDL2Message extends DemodulatorResult {
    private final String aircraft;
    private final String color;
    private final String country;
    @SerializedName("ccode")
    private final String countryCode;
    private final String destination;
    private final String flight;
    @SerializedName("freq")
    private final int frequency;
    private final String icao;
    private final String message;
    private final long timestamp;
    private final long ttl;
    private final String type;

    public VDL2Message(long timestamp, String icao, String country, String countryCode, String aircraft,
            String type, String flight, int frequency, long ttl, String color, String destination, String message) {
        super("VDL2");
        this.timestamp = timestamp;
        this.icao = icao;
        this.country = country;
        this.countryCode = countryCode;
        this.aircraft = aircraft;
        this.type = type;
        this.flight = flight;
        this.frequency = frequency;
        this.ttl = ttl;
        this.color = color;
        this.destination = destination;
        this.message = message;
    }

    public Optional<String> getAircraft() {
        return Optional.ofNullable(aircraft);
    }

    public String getColor() {
        return color;
    }

    public Optional<String> getCountry() {
        return Optional.ofNullable(country);
    }

    public Optional<String> getCountryCode() {
        return Optional.ofNullable(countryCode);
    }

    public Optional<String> getDestination() {
        return Optional.ofNullable(destination);
    }

    public Optional<String> getFlight() {
        return Optional.ofNullable(flight);
    }

    public int getFrequency() {
        return frequency;
    }

    public String getIcao() {
        return icao;
    }

    public Optional<String> getMessage() {
        return Optional.ofNullable(message);
    }

    public Color getParsedColor() {
        return Color.decode(color);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getTTL() {
        return ttl;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "VDL2Message [timestamp=" + timestamp + ", icao=" + icao + ", country=" + country + ", countryCode="
                + countryCode + ", aircraft=" + aircraft + ", type=" + type + ", flight=" + flight + ", frequency="
                + frequency + ", ttl=" + ttl + ", color=" + color + ", destination=" + destination + ", message="
                + message + "]";
    }

}
