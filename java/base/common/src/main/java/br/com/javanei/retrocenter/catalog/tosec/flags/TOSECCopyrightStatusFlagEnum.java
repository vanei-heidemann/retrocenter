package br.com.javanei.retrocenter.catalog.tosec.flags;

public enum TOSECCopyrightStatusFlagEnum {
    CW("CW", "Cardware"),
    CWR("CW-R", "Cardware-Registered"),
    FW("FW", "Freeware"),
    GW("GW", "Giftware"),
    GWR("GW-R", "Giftware-Registered"),
    LW("LW", "Licenceware"),
    PD("PD", "Public Domain"),
    SW("SW", "Shareware"),
    SWR("SW-R", "Shareware-Registered");

    private String name;
    private String description;

    TOSECCopyrightStatusFlagEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static TOSECCopyrightStatusFlagEnum fromName(String name) {
        for (TOSECCopyrightStatusFlagEnum e : TOSECCopyrightStatusFlagEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

    public static boolean isCopyrightStatus(String name) {
        return fromName(name) != null;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
