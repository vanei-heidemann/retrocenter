package br.com.javanei.retrocenter.catalog.goodset.flags;

import java.util.LinkedList;
import java.util.List;

public enum GoodSetGameRegionEnum {

    A("A", "Australia"),
    As("As", "Asia"),
    B("B", "Brazil"),
    C("C", "Canada"),
    Ch("Ch", "China"),
    D("D", "Netherlands (Dutch)"),
    E("E", "Europe"),
    F("F", "France"),
    G("G", "Germany"),
    Gr("Gr", "Greece"),
    HK("HK", "Hong Kong"),
    I("I", "Italy"),
    J("J", "Japan"),
    K("K", "Korea"),
    Nl("Nl", "Netherlands"),
    No("No", "Norway"),
    R("R", "Russia"),
    S("S", "Spain"),
    Sw("Sw", "Sweden"),
    U("U", "United States"),
    UK("UK", "United Kingdom"),
    W("W", "World"),
    Unk("Unk", "Unknown country");

    private String name;
    private String description;

    GoodSetGameRegionEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static List<GoodSetGameRegionEnum> fromName(String name) {
        List<GoodSetGameRegionEnum> result = new LinkedList<>();
        for (String s : name.split(",")) {
            boolean achou = false;
            for (GoodSetGameRegionEnum r : GoodSetGameRegionEnum.values()) {
                if (r.getName().equals(s.trim())) {
                    result.add(r);
                    achou = true;
                }
            }
            if (!achou) {
                return null;
            }
        }
        return result;
    }

    public static List<String> toListString(List<GoodSetGameRegionEnum> regions) {
        List<String> l = new LinkedList<>();
        for (GoodSetGameRegionEnum r : regions) {
            l.add(r.getName());
        }
        return l;
    }

    public static String toString(List<GoodSetGameRegionEnum> regions) {
        StringBuilder sb = new StringBuilder();
        for (GoodSetGameRegionEnum r : regions) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(r.getName());
        }
        return sb.toString();
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
