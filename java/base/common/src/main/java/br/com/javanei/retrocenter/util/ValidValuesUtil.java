package br.com.javanei.retrocenter.util;

public final class ValidValuesUtil {
    public static final String[] YES_NO = new String[]{"yes", "no"};
    public static final String[] YES_NO_PARTIAL = new String[]{"yes", "no", "partial"};
    public static final String[] BADDUMP_NODUMP_GOOD = new String[]{"baddump", "nodump", "good"};
    public static final String[] GOOD_IMPERFECT_PRELIMINARY = new String[]{"good", "imperfect", "preliminary"};
    public static final String[] SUPPORTED_UNSUPPORTED = new String[]{"supported", "unsupported"};
    public static final String[] ORIGINAL_COMPATIBLE = new String[]{"original", "compatible"};
    public static final String[] BADDUMP_NODUMP_GOOD_VERIFIED = new String[]{"baddump", "nodump", "good", "verified"};
    public static final String[] NONE_SPLIT_FULL = new String[]{"none", "split", "full"};
    public static final String[] OBSOLETE_REQUIRED_IGNORE = new String[]{"obsolete", "required", "ignore"};
    public static final String[] ZIP_UNZIP = new String[]{"zip", "unzip"};
    public static final String[] MERGED_SPLIT_UNMERGED = new String[]{"merged", "split", "unmerged"};
    public static final String[] MERGED_UNMERGED = new String[]{"merged", "unmerged"};

    public static String validateValue(String value, String[] valueList) {
        if (value == null) {
            return null;
        } else {
            for (String s : valueList) {
                if (s.equals(value)) {
                    return value;
                }
            }
        }
        throw new IllegalArgumentException(value);
    }
}
