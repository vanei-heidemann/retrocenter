package br.com.javanei.retrocenter.catalog.nointro.flags;

import java.util.LinkedList;
import java.util.List;

public enum NoIntroSystemEnum {
    A1000("A1000"), //Commodore Amiga
    A1200("A1200"), //Commodore Amiga
    A1200_A4000("A1200-A4000"), //Commodore Amiga
    A2000("A2000"), //Commodore Amiga
    A2000_A3000("A2000-A3000"), //Commodore Amiga
    A2024("A2024"), //Commodore Amiga
    A2500_A3000UX("A2500-A3000UX"), //Commodore Amiga
    A3000("A3000"), //Commodore Amiga
    A4000("A4000"), //Commodore Amiga
    A4000T("A4000T"), //Commodore Amiga
    A500("A500"), //Commodore Amiga
    A500Plus("A500 Plus"), //Commodore Amiga
    A500_A1000_A2000("A500-A1000-A2000"), //Commodore Amiga
    A500_A1000_A2000_CDTV("A500-A1000-A2000-CDTV"), //Commodore Amiga
    A500_A1200("A500-A1200"), //Commodore Amiga
    A500_A1200_A2000_A4000("A500-A1200-A2000-A4000"), //Commodore Amiga
    A500_A2000("A500-A2000"), //Commodore Amiga
    A500_A600_A2000("A500-A600-A2000"), //Commodore Amiga
    A570("A570"), //Commodore Amiga
    A600("A600"), //Commodore Amiga
    A600HD("A600HD"), //Commodore Amiga
    AGA("AGA"), //Commodore Amiga
    AGA_CD32("AGA-CD32"), //Commodore Amiga
    Adam("Adam"); // Coleco Adam

    private String name;

    NoIntroSystemEnum(String name) {
        this.name = name;
    }

    public static List<NoIntroSystemEnum> fromName(String name) {
        List<NoIntroSystemEnum> result = new LinkedList<>();
        for (String s : name.split(",")) {
            boolean achou = false;
            for (NoIntroSystemEnum e : NoIntroSystemEnum.values()) {
                if (e.getName().equals(s.trim())) {
                    result.add(e);
                    achou = true;
                }
            }
            if (!achou) {
                return null;
            }
        }
        return result;
    }

    public static List<String> toListString(List<NoIntroSystemEnum> systems) {
        List<String> l = new LinkedList<>();
        for (NoIntroSystemEnum r : systems) {
            l.add(r.getName());
        }
        return l;
    }

    public static String toString(List<NoIntroSystemEnum> systems) {
        StringBuilder sb = new StringBuilder();
        for (NoIntroSystemEnum r : systems) {
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
