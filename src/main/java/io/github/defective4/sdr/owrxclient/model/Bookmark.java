package io.github.defective4.sdr.owrxclient.model;

public record Bookmark(String name, int frequency, String modulation, String underlying, String description,
        boolean scannable) {
}
