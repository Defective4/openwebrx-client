package io.github.defective4.sdr.owrxclient.model;

public enum Modulation {
    usb("USB", 150, 2750), am("AM", -4000, 4000), lsb("LSB", -2750, -150), nfm("FM", -4000, 4000),
    acars("ACARS", am, -6000, 6000), adsb("ADSB", -1000, 1000), ais("AIS", -6250, 6250), bpsk31("BPSK31", usb),
    bpsk63("BPSK63", usb), cw("CW", 700, 900), cwdecoder("CW Decoder", new Modulation[] { usb, lsb }, String.class),
    cwskimmer("CW Skimmer", 0, 48000), dab("DAB", -1000, 1000), dmr("DMR", -6250, 6250), drm("DRM", -5000, 5000),
    dsc("DSC", usb), dstar("D-Star", -3250, 3250), eas("EAS", nfm), empty("Empty", -1000, 1000), fax("Fax", usb),
    freedv("FreeDV", 300, 3000), fst4("FST4", usb), fst4w("FST4W", 1350, 1650), ft4("FT4", usb, FTMessage.class),
    ft8("FT8", usb, FTMessage.class), hdr("HDR", -200000, 200000), hfdl("HFDL", 0, 3000), ism("ISM", -1000, 1000),
    js8("JS8Call", usb), jt65("JT65", usb), jt9("JT9", usb), m17("M17", -6250, 6250), msk144("MSK144", usb),
    navtex("NAVTEX", usb), nxdn("NXDN", -3250, 3250), packet("Packet", -6250, 6250), page("Page", -6000, 6000),
    q65("Q65", usb), rtty170("RTTY-170 (45)", new Modulation[] { usb, lsb }),
    rtty450("RTTY-450 (50N)", new Modulation[] { usb, lsb }), rtty85("RTTY-85 (50N)", new Modulation[] { usb, lsb }),
    rttyskimmer("RTTY Skimmer", 0, 48000), sam("SAM", -4000, 4000), selcall("SelCall", nfm), sitorb("SITOR-B", usb),
    sstv("SSTV", new Modulation[] { usb, lsb, nfm }), uat("UAT", -1000, 1000), usbd("DATA", 0, 24000),
    vdl2("VDL2", -12500, 12500), wfm("WFM", -75000, 75000), wmbus("WMBus", -125000, 125000), wspr("WSPR", 1350, 1650),
    ysf("YSF", -6250, 6250), zvei("Zvei", nfm);

    private final String display;
    private final int lowPass, highPass;
    private final Class<?> secondaryDataClass;
    private final Modulation[] underlying;

    private Modulation(String display, int low, int high) {
        this(display, new Modulation[0], low, high, null);
    }

    private Modulation(String display, Modulation underlying) {
        this(display, underlying, underlying.lowPass, underlying.highPass);
    }

    private Modulation(String display, Modulation underlying, Class<?> secondaryDataClass) {
        this(display, new Modulation[] { underlying }, underlying.lowPass, underlying.highPass, secondaryDataClass);
    }

    private Modulation(String display, Modulation underlying, int low, int high) {
        this(display, new Modulation[] { underlying }, low, high, null);
    }

    private Modulation(String display, Modulation[] underlying) {
        this(display, underlying, underlying[0].lowPass, underlying[0].highPass, null);
    }

    private Modulation(String display, Modulation[] underlying, Class<?> secondaryDataClass) {
        this(display, underlying, underlying[0].lowPass, underlying[0].highPass, secondaryDataClass);
    }

    private Modulation(String display, Modulation[] underlying, int low, int high, Class<?> secondaryDataClass) {
        this.display = display;
        lowPass = low;
        highPass = high;
        this.underlying = underlying;
        this.secondaryDataClass = secondaryDataClass;
    }

    public String getDisplay() {
        return display;
    }

    public int getHighPass() {
        return highPass;
    }

    public int getLowPass() {
        return lowPass;
    }

    public Class<?> getSecondaryDataClass() {
        return secondaryDataClass;
    }

}
