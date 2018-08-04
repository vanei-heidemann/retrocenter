package br.com.javanei.retrocenter.catalog.nointro.flags;

public enum NoIntroProtectionEnum {
    DongleProtected("Dongle Protected"),
    RapidLok1("RapidLok 1"),
    RapidLok5("RapidLok 5"),
    RapidLok6("RapidLok 6"),
    VMAX("V-MAX"),
    VMAX2("V-MAX 2"),
    Vorpal("Vorpal");

    private String name;

    NoIntroProtectionEnum(String name) {
        this.name = name;
    }

    public static NoIntroProtectionEnum fromName(String name) {
        for (NoIntroProtectionEnum e : NoIntroProtectionEnum.values()) {
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
