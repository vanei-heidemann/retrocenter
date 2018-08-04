package br.com.javanei.retrocenter.common;

import java.util.LinkedList;
import java.util.List;

/**
 * Supported Languages.
 * Based on no-intro.
 */
public enum LanguageEnum {
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

    private final String code;
    private final String name;

    LanguageEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static LanguageEnum fromName(String name) {
        for (LanguageEnum e : LanguageEnum.values()) {
            if (e.name.equals(name)) {
                return e;
            }
        }
        return null;
    }

    public static List<LanguageEnum> fromCode(String code) {
        List<LanguageEnum> result = new LinkedList<>();
        for (String s : code.split(",")) {
            boolean achou = false;
            for (LanguageEnum e : LanguageEnum.values()) {
                if (e.code.equals(s.trim())) {
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

    public static List<String> toListString(List<LanguageEnum> languages) {
        List<String> r = new LinkedList<>();
        for (LanguageEnum e : languages) {
            r.add(e.code);
        }
        return r;
    }

    public static String toString(List<LanguageEnum> regions) {
        StringBuilder sb = new StringBuilder();
        for (LanguageEnum r : regions) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(r.code);
        }
        return sb.toString();
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
