package br.com.javanei.retrocenter.catalog.nointro.flags;

import java.util.LinkedList;
import java.util.List;

public enum NoIntroLanguageEnum {
    En("En", "English"),
    Ja("Ja", "Japanese"),
    Fr("Fr", "French"),
    De("De", "German"),
    Es("Es", "Spanish"),
    It("It", "Italian"),
    Nl("Nl", "Dutch"),
    Pt("Pt", "Portuguese"),
    Sv("Sv", "Swedish"),
    No("No", "Norwegian"),
    Da("Da", "Danish"),
    Fi("Fi", "Finish"),
    Zh("Zh", "Chinese"),
    Ko("Ko", "Korean"),
    Pl("Pl", "Polish"),
    Gd("Gd", "Grenada"),
    Hr("Hr", "Croatian"),
    Ca("Ca", "Catalan");

    private String name;
    private String description;

    NoIntroLanguageEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static List<NoIntroLanguageEnum> fromName(String name) {
        List<NoIntroLanguageEnum> result = new LinkedList<>();
        for (String s : name.split(",")) {
            boolean achou = false;
            for (NoIntroLanguageEnum e : NoIntroLanguageEnum.values()) {
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

    public static List<String> toListString(List<NoIntroLanguageEnum> languages) {
        List<String> r = new LinkedList<>();
        for (NoIntroLanguageEnum e : languages) {
            r.add(e.getName());
        }
        return r;
    }

    public static String toString(List<NoIntroLanguageEnum> regions) {
        StringBuilder sb = new StringBuilder();
        for (NoIntroLanguageEnum r : regions) {
            if (sb.length() > 0) {
                sb.append(",");
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
}
