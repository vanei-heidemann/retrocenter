package br.com.javanei.retrocenter.catalog.nointro.flags;

public enum NoIntroLoaderEnum {
    AceOfAcesLoader("Ace Of Aces Loader"),
    Anirog("Anirog"),
    AtlantisLoader("Atlantis Loader"),
    AudiogenicLoader("Audiogenic Loader"),
    Bleepload("Bleepload"),
    Burner("Burner"),
    CHR("CHR"),
    CHRLoader("CHR Loader"),
    Cyberload("Cyberload"),
    CyberloadROMLoader("Cyberload+ROM Loader"),
    EnigmaLoader("Enigma Loader"),
    Firebird("Firebird"),
    Flashload("Flashload"),
    Freeload("Freeload"),
    Graftgold("Graftgold"),
    HiTECLoader("Hi TEC Loader"),
    Hitload("Hit-load"),
    IKTapeLoader("IK Tape Loader"),
    InvadeALoad("Invade-A-Load!"),
    JetLoad("Jet-Load"),
    LoadNPlay("Load N Play"),
    Martech("Martech"),
    Mastertronic("Mastertronic"),
    Microload("Microload"),
    Novaload("Novaload"),
    OceanLoaderNew("Ocean Loader (New)"),
    OceanImagineLoader("Ocean-Imagine Loader"),
    PalaceLoader("Palace Loader"),
    Pavloda("Pavloda"),
    Rackit("Rack-it"),
    Rasterload("Rasterload"),
    ROMLoader("ROM Loader"),
    SEUCKLoader("SEUCK Loader"),
    Snakeload("Snakeload"),
    SuperPavloda("Super Pavloda"),
    SuperTape("Super Tape"),
    TDILoader("TDI Loader"),
    TengenDomarkLoader("Tengen-Domark Loader"),
    TurboTapeCompatible("Turbo-Tape Compatible"),
    TurricanLoader("Turrican Loader"),
    USGoldCyberloadLoaders("U.S. Gold & Cyberload Loaders"),
    USGoldLoader("U.S. Gold Loader"),
    Unknown("Unknown"),
    VirginLoader("Virgin Loader"),
    Visiload("Visiload"),
    Wildsave("Wildsave");

    private String name;

    NoIntroLoaderEnum(String name) {
        this.name = name;
    }

    public static NoIntroLoaderEnum fromName(String name) {
        for (NoIntroLoaderEnum e : NoIntroLoaderEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
