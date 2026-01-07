package io.github.defective4.sdr.owrxclient.model;

import java.util.Optional;

public enum Modulation {
    usb("USB", 150, 2750), am("AM", -4000, 4000), nfm("FM", -4000, 4000), lsb("LSB", -2750, -150),
    acars("ACARS", am, -6000, 6000), adsb("ADSB", -1000, 1000), ais("AIS", -6250, 6250), bpsk31("BPSK31", usb),
    bpsk63("BPSK63", usb), cw("CW", 700, 900), cwdecoder("CW Decoder", new Modulation[] { usb, lsb }),
    cwskimmer("CW Skimmer", 0, 48000), dab("DAB"), dmr("DMR", -6250, 6250), drm("DRM", -5000, 5000), dsc("DSC", usb),
    dstar("D-Star", -3250, 3250), eas("EAS", nfm), empty("Empty", -1000, 1000), fax("Fax", usb),
    freedv("FreeDV", 300, 3000), fst4("FST4", usb), fst4w("FST4W", 1350, 1650), ft4("FT4", usb), ft8("FT8", usb),
    hdr("HDR", -200000, 200000), hfdl("HFDL", 0, 3000), ism("ISM", -1000, 1000), js8("JS8Call", usb), jt65("JT65", usb),
    jt9("JT9", usb), m17("M17", -6250, 6250), msk144("MSK144", usb), navtex("NAVTEX", usb), nxdn("NXDN", -3250, 3250),
    packet("Packet", -6250, 6250), page("Page", -6000, 6000), q65("Q65", usb),
    rtty170("RTTY-170 (45)", new Modulation[] { usb, lsb }), rtty450("RTTY-450 (50N)", new Modulation[] { usb, lsb }),
    rtty85("RTTY-85 (50N)", new Modulation[] { usb, lsb }), rttyskimmer("RTTY Skimmer", 0, 48000),
    sam("SAM", -4000, 4000), selcall("SelCall", nfm), sitorb("SITOR-B", usb),
    sstv("SSTV", new Modulation[] { usb, lsb, nfm }), uat("UAT", -1000, 1000), usbd("DATA", 0, 24000),
    vdl2("VDL2", -12500, 12500), wfm("WFM", -75000, 75000), wmbus("WMBus", -125000, 125000), wspr("WSPR", 1350, 1650),
    ysf("YSF", -6250, 6250), zvei("Zvei", nfm);

    private final String display;
    private final boolean hasBandpass;
    private final int lowPass, highPass;
    private final Modulation[] underlying;

    private Modulation(String display) {
        this(display, new Modulation[0], -1, 1, false);
    }

    private Modulation(String display, int low, int high) {
        this(display, new Modulation[0], low, high);
    }

    private Modulation(String display, Modulation underlying) {
        this(display, underlying, underlying.lowPass, underlying.highPass);
    }

    private Modulation(String display, Modulation underlying, int low, int high) {
        this(display, new Modulation[] { underlying }, low, high);
    }

    private Modulation(String display, Modulation[] underlying) {
        this(display, underlying, underlying[0].lowPass, underlying[0].highPass);
    }

    private Modulation(String display, Modulation[] underlying, int low, int high) {
        this(display, underlying, low, high, true);
    }

    private Modulation(String display, Modulation[] underlying, int low, int high, boolean hasBandpass) {
        this.display = display;
        lowPass = low;
        highPass = high;
        this.underlying = underlying == null ? new Modulation[0] : underlying;
        this.hasBandpass = hasBandpass;
    }

    public String getDisplay() {
        return display;
    }

    public Optional<Integer> getHighPass() {
        return Optional.ofNullable(hasBandpass ? highPass : null);
    }

    public Optional<Integer> getLowPass() {
        return Optional.ofNullable(hasBandpass ? lowPass : null);
    }

    public Modulation[] getUnderlying() {
        return underlying;
    }

    public boolean isHasBandpass() {
        return hasBandpass;
    }

}
