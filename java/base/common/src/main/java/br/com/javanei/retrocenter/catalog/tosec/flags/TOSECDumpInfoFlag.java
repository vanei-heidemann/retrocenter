package br.com.javanei.retrocenter.catalog.tosec.flags;

import java.util.StringTokenizer;

public class TOSECDumpInfoFlag {
    private final TOSECDumpInfoFlagEnum dumpInfo;
    private final Integer count;
    private final String complement;

    private TOSECDumpInfoFlag(TOSECDumpInfoFlagEnum dumpInfo, Integer count, String complement) {
        this.dumpInfo = dumpInfo;
        this.count = count;
        this.complement = complement;
    }

    public static TOSECDumpInfoFlag parseInfo(String s) {
        TOSECDumpInfoFlagEnum flag = null;
        DecomposedTag tag = decomposeTag(s);
        switch (tag.type) {
            case "cr":
                flag = TOSECDumpInfoFlagEnum.Cracked;
                break;
            case "f":
                flag = TOSECDumpInfoFlagEnum.Fixed;
                break;
            case "h":
                flag = TOSECDumpInfoFlagEnum.Hacked;
                break;
            case "m":
                flag = TOSECDumpInfoFlagEnum.Modified;
                break;
            case "p":
                flag = TOSECDumpInfoFlagEnum.Pirated;
                break;
            case "tr":
                flag = TOSECDumpInfoFlagEnum.Translated;
                break;
            case "t":
                flag = TOSECDumpInfoFlagEnum.Trained;
                break;
            case "o":
                flag = TOSECDumpInfoFlagEnum.OverDump;
                break;
            case "u":
                flag = TOSECDumpInfoFlagEnum.UnderDump;
                break;
            case "v":
                flag = TOSECDumpInfoFlagEnum.Virus;
                break;
            case "b":
                flag = TOSECDumpInfoFlagEnum.BadDump;
                break;
            case "a":
                flag = TOSECDumpInfoFlagEnum.Alternate;
                break;
            case "!":
                flag = TOSECDumpInfoFlagEnum.VerifiedGoodDump;
                break;
        }
        return flag != null ? new TOSECDumpInfoFlag(flag, tag.count, tag.complement) : null;
    }

    private static DecomposedTag decomposeTag(final String tag) {
        DecomposedTag r = new DecomposedTag();
        StringTokenizer st = new StringTokenizer(tag, " ");
        String s1 = st.nextToken();
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            if (c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == 8 || c == 9) {
                r.count = Integer.parseInt(s1.substring(i));
                i = s1.length();
                break;
            } else {
                r.type = r.type + c;
            }
        }

        if (st.hasMoreTokens()) {
            r.complement = tag.substring(tag.indexOf(" ")).trim();
        }
        return r;
    }

    public TOSECDumpInfoFlagEnum getDumpInfo() {
        return dumpInfo;
    }

    public String getComplement() {
        return complement;
    }

    public String getValue() {
        return dumpInfo.getName()
                + (count != null ? count.toString() : "")
                + (complement != null ? " " + complement : "");
    }

    private static class DecomposedTag {
        String type = "";
        Integer count;
        String complement;
    }
}
