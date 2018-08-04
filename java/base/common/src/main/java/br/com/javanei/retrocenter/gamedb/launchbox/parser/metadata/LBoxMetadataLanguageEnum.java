package br.com.javanei.retrocenter.gamedb.launchbox.parser.metadata;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public enum LBoxMetadataLanguageEnum {

    Cs("Cs"),
    Da("Da"),
    De("De"),
    El("El"),
    En("En"),
    Es("Es"),
    Fi("Fi"),
    Fr("Fr"),
    It("It"),
    Ja("Ja"),
    Nl("Nl"),
    No("No"),
    Pl("Pl"),
    Pt("Pt"),
    Ru("Ru"),
    Sv("Sv"),
    Tr("Tr");

    private final String name;

    LBoxMetadataLanguageEnum(String name) {
        this.name = name;
    }

    public static LBoxMetadataLanguageEnum[] fromName(String name) {
        List<LBoxMetadataLanguageEnum> l = new ArrayList<>();
        for (String s1 : name.split(",")) {
            StringTokenizer st = new StringTokenizer(s1, "+");
            while (st.hasMoreElements()) {
                String s2 = st.nextToken().trim();
                boolean achou = false;
                for (LBoxMetadataLanguageEnum e : LBoxMetadataLanguageEnum.values()) {
                    if (e.name.equals(s2.trim())) {
                        l.add(e);
                        achou = true;
                    }
                }
                if (!achou) return null;
            }
        }
        return !l.isEmpty() ? l.toArray(new LBoxMetadataLanguageEnum[0]) : null;
    }

    public static boolean isLanguage(String name) {
        return fromName(name) != null;
    }
}
