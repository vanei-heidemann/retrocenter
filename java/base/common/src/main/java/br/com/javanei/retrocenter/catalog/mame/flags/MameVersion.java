package br.com.javanei.retrocenter.catalog.mame.flags;

public final class MameVersion {
    private static final String[] versionPatterns = new String[]{
            "[vV]er [\\w\\?\\.\\-:]*",
            //"ver [\\.\\-\\.]+",
            "[vV]er. [\\w\\?\\.\\-:]*",
            "[vV]er.[\\w\\?\\.\\-:]*",
            //"ver. [\\w\\-\\?]+",
            //"ver. [\\w \\d.\\d\\d]+",
            //"ver. [\\.\\-\\.]+",
            //"ver.[\\.\\-\\.]+",
            //"ver.[\\w\\-\\.]+",
            "[vV]\\d",
            //"[vV]\\d[\\d\\?.]+",
            "[vV]\\d[\\w\\-\\.\\?]+",
            "[vV].\\d[\\w\\-\\.]+",
            "[vV]. \\d[\\w\\-\\.]+",
            "[vV]ersion [\\w\\?\\.\\-:]*",
            //"version [\\w\\?\\.-]*",
            //"version [\\w\\-\\.]+",
            //"Version \\w[\\w\\-\\.]+",
            //"Ver.[\\w\\-\\.]+",
            //"Ver. [\\w\\-\\.]+",
            //"Ver [\\w\\-\\.]+",
            "V\\d.[\\w\\-\\.]+",
            "V \\d.[\\w\\-\\.]+",
            "\\d\\.\\d\\d",
            "\\d\\.\\d\\d\\w",
            "\\d\\.\\d",
            "\\d\\.\\w",
            "\\d\\.\\d\\w",
            "\\d\\d\\.\\d\\d",
            "[\\d]* Ver.[\\w]*"
            /*
            "v\\d.\\w*",
            //"v\\d.\\d",
            //"V\\d.\\d\\d",
            //"V\\d.\\d\\w",
            //"V\\d.\\d\\d\\w",
            "V\\d\\d\\w*",
            //"V\\d\\d\\d\\w",
            "V \\d.\\w*",
            //"V \\d.\\d\\d",
            "ver \\d\\w+",
            //"ver \\d",
            //"ver \\d\\d",
            //"ver \\d.\\d",
            "ver \\d.\\w*",
            //"ver \\d\\d.\\d",
            //"ver \\d.\\d\\w",
            "v\\d\\d-\\d.\\d?",
            "v\\d.\\w+",
            //"v\\d.\\d\\d.\\d\\d",
            //"v\\d.\\w",
            "ver.\\d.\\w+",
            "ver \\w\\w+",
            "ver \\d\\w+",
            "ver \\[w\\-\\.]+",
            //"ver.\\d.\\d\\d",
            //"ver. \\w",
            //"ver. \\w+"
            //"v.+?",
            //"Rev.+?",
            //"r.+?",
            //"R\\d\\d",
            //"R\\d",
            //"A\\d\\d",
            //"Release \\d\\d",
            //"\\d\\d\\d.\\w\\d",
            //"\\d.\\d\\dl", // Sega Genesis
            //"\\d.\\d\\dS"// Sega Genesis
            */
    };

    private MameVersion() {
    }

    public static String parseVersion(String tag) {
        for (String s : versionPatterns) {
            if (tag.matches(s)) {
                return tag;
            }
        }
        return null;
    }
}
