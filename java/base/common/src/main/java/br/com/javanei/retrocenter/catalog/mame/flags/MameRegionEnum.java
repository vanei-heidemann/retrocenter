package br.com.javanei.retrocenter.catalog.mame.flags;

import java.util.LinkedList;
import java.util.List;

public enum MameRegionEnum {
    America("America"),
    Asia("Asia"),
    Australia("Australia"),
    Austria("Austria"),
    Belgium("Belgium"),
    Brazil("Brazil"),
    Canada("Canada"),
    China("China"),
    Denmark("Denmark"),
    Europe("Europe"),
    Finland("Finland"),
    France("France"),
    Germany("Germany"),
    Holland("Holland"),
    HongKong("Hong Kong"),
    Hungary("Hungary"),
    Israel("Israel"),
    Italy("Italy"),
    Japan("Japan"),
    Korea("Korea"),
    Latvia("Latvia"),
    Montenegro("Montenegro"),
    Netherlands("Netherlands"),
    NewZealand("New Zealand"),
    Norway("Norway"),
    Poland("Poland"),
    Russia("Russia"),
    Slovakia("Slovakia"),
    Spain("Spain"),
    Sweden("Sweden"),
    Switzerland("Switzerland"),
    Taiwan("Taiwan"),
    Unknown("Unknown"),
    UK("UK"),
    USA("USA"),
    Venezuela("Venezuela"),
    World("World");

    private String name;

    MameRegionEnum(String name) {
        this.name = name;
    }


    public static List<MameRegionEnum> fromName(String name) {
        List<MameRegionEnum> result = new LinkedList<>();
        for (String s : name.split("/")) {
            if (name.equals("European")) {
                result.add(Europe);
                continue;
            }
            boolean achou = false;
            for (MameRegionEnum r : MameRegionEnum.values()) {
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

    public static List<String> toListString(List<MameRegionEnum> regions) {
        List<String> l = new LinkedList<>();
        for (MameRegionEnum r : regions) {
            l.add(r.getName());
        }
        return l;
    }

    public static String toString(List<MameRegionEnum> regions) {
        StringBuilder sb = new StringBuilder();
        for (MameRegionEnum r : regions) {
            if (sb.length() > 0) {
                sb.append("/");
            }
            sb.append(r.getName());
        }
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
