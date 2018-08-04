package br.com.javanei.retrocenter.gamedb.launchbox.parser;

public final class LaunchBoxNameParser {
    private static final String[] versions = {
            "[vV]\\d"
            , "[vV]\\d\\.\\d"
            , "[vV]\\d\\.\\d\\d"
            , "[vV]\\d\\d\\d"
            , "Version \\d\\.\\d"
            , "Version \\d\\d\\d\\d"
    };

    private static final String[] alternate = {
            "Alt \\d"
    };

    private static final String[] revision = {
            "Rev \\d"
            , "Rev [A-Z]"
    };

    private static final String[] dates = {
            "\\d\\d\\d\\d"
            , "\\d\\d\\d\\d-\\d\\d"
            , "\\d\\d\\d\\d-\\d\\d-\\d\\d"
            , "\\d\\d[xX][xX]"
            , "\\d\\d\\d[xX]"
    };

    public static String parseVersion(String tag) {
        for (String s : versions) {
            if (tag.matches(s)) return tag;
        }
        return null;
    }

    public static String parseAlt(String tag) {
        for (String s : alternate) {
            if (tag.matches(s)) return tag;
        }
        return null;
    }

    public static String parseRevision(String tag) {
        for (String s : revision) {
            if (tag.matches(s)) return tag;
        }
        return null;
    }

    public static String parseDate(String tag) {
        for (String s : dates) {
            if (tag.matches(s)) return tag;
        }
        return null;
    }

    public static boolean isDemo(String tag) {
        return tag.equalsIgnoreCase("demo");
    }

    public static boolean isBeta(String tag) {
        return tag.equalsIgnoreCase("beta");
    }

    public static boolean isPrototype(String tag) {
        return tag.equalsIgnoreCase("proto") || tag.equalsIgnoreCase("prototype");
    }
}
