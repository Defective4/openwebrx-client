package io.github.defective4.sdr.owrxclient.model;

public record Bookmark(String name, int frequency, Modulation modulation, Modulation underlying, String description,
        boolean scannable) {
}
