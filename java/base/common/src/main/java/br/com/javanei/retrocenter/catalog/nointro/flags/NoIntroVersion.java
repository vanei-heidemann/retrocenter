package br.com.javanei.retrocenter.catalog.nointro.flags;

public final class NoIntroVersion {
    private static final String[] versionPatterns = new String[]{
            "v.+?",
            "Rev.+?",
            "r.+?",
            "R\\d\\d",
            "R\\d",
            "A\\d\\d",
            "Release \\d\\d",
            "\\d\\d\\d.\\w\\d",
            "\\d.\\d\\dl", // Sega Genesis
            "\\d.\\d\\dS"// Sega Genesis
    };

    private NoIntroVersion() {
    }

    public static String parseTag(String tag) {
        for (String s : versionPatterns) {
            if (tag.matches(s)) {
                return tag;
            }
        }
        return null;
    }
}
