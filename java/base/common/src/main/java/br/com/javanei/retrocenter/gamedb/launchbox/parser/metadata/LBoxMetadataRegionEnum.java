package br.com.javanei.retrocenter.gamedb.launchbox.parser.metadata;

import java.util.ArrayList;
import java.util.List;

public enum LBoxMetadataRegionEnum {

    SouthAmerica("South America"),
    TheNetherlands("The Netherlands"),
    HongKong("Hong Kong"),
    UnitedStates("United States"),
    World("World"),
    NorthAmerica("North America"),
    Russia("Russia"),
    Oceania("Oceania"),
    Greece("Greece"),
    Sweden("Sweden"),
    China("China"),
    Brazil("Brazil"),
    France("France"),
    Korea("Korea"),
    Europe("Europe"),
    Japan("Japan"),
    UnitedKingdom("United Kingdom"),
    Spain("Spain"),
    Canada("Canada"),
    Asia("Asia"),
    Norway("Norway"),
    Finland("Finland"),
    Italy("Italy"),
    Australia("Australia"),
    Germany("Germany");

    private final String name;

    LBoxMetadataRegionEnum(String name) {
        this.name = name;
    }

    public static LBoxMetadataRegionEnum[] fromName(String name) {
        List<LBoxMetadataRegionEnum> l = new ArrayList<>();
        for (String s : name.split(",")) {
            if (s.trim().equals("USA")) {
                l.add(UnitedStates);
            }
            boolean achou = false;
            for (LBoxMetadataRegionEnum r : LBoxMetadataRegionEnum.values()) {
                if (r.name.equals(s.trim())) {
                    l.add(r);
                    achou = true;
                }
            }
            if (!achou) return null;
        }
        return !l.isEmpty() ? l.toArray(new LBoxMetadataRegionEnum[0]) : null;
    }

    public static boolean isRegion(String name) {
        return fromName(name) != null;
    }
}
