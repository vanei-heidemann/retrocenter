package br.com.javanei.retrocenter.common;

public enum DatafileCatalogEnum {
    NoIntro,
    TOSEC,
    MAME,
    HyperList,
    GoodSet;

    public static DatafileCatalogEnum fromName(String name) {
        if (name == null) {
            return null;
        }
        DatafileCatalogEnum r = parseName(name);
        if (r == null) {
            throw new IllegalArgumentException(name);
        }
        return r;
    }

    public static boolean isValid(String name) {
        return name != null && parseName(name) != null;
    }

    private static DatafileCatalogEnum parseName(String name) {
        if (name.toLowerCase().contains("no-intro") || name.toLowerCase().contains("nointro")) {
            return NoIntro;
        } else if (name.toUpperCase().contains("TOSEC")) {
            return TOSEC;
        } else if (name.toUpperCase().contains("MAME")) {
            return MAME;
        } else if (name.toLowerCase().equalsIgnoreCase("hyperlist")) {
            return HyperList;
        } else if (name.toLowerCase().equals("goodset")) {
            return GoodSet;
        }
        return null;
    }
}
