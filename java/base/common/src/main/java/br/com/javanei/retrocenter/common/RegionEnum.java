package br.com.javanei.retrocenter.common;

import java.util.LinkedList;
import java.util.List;

/**
 * Supported regions.
 * Based on no-intro.
 */
public enum RegionEnum {
    Asia("Asia"),
    Australia("Australia"),
    Brazil("Brazil"),
    Canada("Canada"),
    China("China"),
    Denmark("Denmark"),
    Europe("Europe"),
    Finland("Finland"),
    France("France"),
    Germany("Germany"),
    HongKong("Hong Kong"),
    Italy("Italy"),
    Japan("Japan"),
    Korea("Korea"),
    Netherlands("Netherlands"),
    Russia("Russia"),
    Spain("Spain"),
    Sweden("Sweden"),
    Taiwan("Taiwan"),
    Unknown("Unknown"),
    USA("USA"),
    World("World");

    private String name;

    RegionEnum(String name) {
        this.name = name;
    }

    public static List<RegionEnum> fromName(String name) {
        List<RegionEnum> result = new LinkedList<>();
        for (String s : name.split(",")) {
            boolean achou = false;
            for (RegionEnum r : RegionEnum.values()) {
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

    public static List<String> toListString(List<RegionEnum> regions) {
        List<String> l = new LinkedList<>();
        for (RegionEnum r : regions) {
            l.add(r.getName());
        }
        return l;
    }

    public static String toString(List<RegionEnum> regions) {
        StringBuilder sb = new StringBuilder();
        for (RegionEnum r : regions) {
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

    @Override
    public String toString() {
        return this.name;
    }
}
