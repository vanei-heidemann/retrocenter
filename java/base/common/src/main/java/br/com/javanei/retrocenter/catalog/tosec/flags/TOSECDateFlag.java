package br.com.javanei.retrocenter.catalog.tosec.flags;

/**
 * The date the software was released. If no exact year is known but the decade can be determined,
 * then use (199x) if from the 1990's, (200x) if from the 2000's etc.
 * If no information is available, use (19xx) or (20xx) until a year can be determined.
 * If more complete date information is known, then this can be shown using the format YYYY-MM-DD.
 * <p>
 * Also note that 19xx-MM and 19xx-MM-DD are allowed when only month or month and day are known,
 * this can happen in things like magazines and other monthly publications where year is unknown.
 * Additionally, if the exact day in the month is not known, but the day can be narrowed down to part of the month,
 * then 19xx-MM-Dx is also acceptable.
 * <p>
 * • Legend of TOSEC, The (19xx)
 * • Legend of TOSEC, The (200x)
 * • Legend of TOSEC, The (1986)
 * • Legend of TOSEC, The (199x)
 * • Legend of TOSEC, The (2001-01)
 * • Legend of TOSEC, The (1986-06-21)
 * • Legend of TOSEC, The (19xx-12)
 * • Legend of TOSEC, The (19xx-12-25)
 * • Legend of TOSEC, The (19xx-12-2x)
 */
public class TOSECDateFlag {
    public static String parseDate(String tag) {
        if (tag.matches("\\d\\d\\d\\d")
                || tag.matches("\\d\\d\\d\\w")
                || tag.matches("\\d\\d\\w\\w")
                || tag.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d")
                || tag.matches("\\d\\d\\d\\w-\\d\\d-\\d\\d")
                || tag.matches("\\d\\d\\w\\w-\\d\\d-\\d\\d")
                || tag.matches("\\d\\d\\d\\d-\\d\\d-\\d\\w")
                || tag.matches("\\d\\d\\d\\w-\\d\\d-\\d\\w")
                || tag.matches("\\d\\d\\w\\w-\\d\\d-\\d\\w")
                || tag.matches("\\d\\d\\d\\d-\\d\\d")
                || tag.matches("\\d\\d\\d\\w-\\d\\d")
                || tag.matches("\\d\\d\\w\\w-\\d\\d")
                || tag.matches("\\d\\d\\d\\d-\\d\\w")
                || tag.matches("\\d\\d\\d\\w-\\d\\w")
                || tag.matches("\\d\\d\\w\\w-\\d\\w")
        ) {
            return tag;
        }
        return null;
    }
}
