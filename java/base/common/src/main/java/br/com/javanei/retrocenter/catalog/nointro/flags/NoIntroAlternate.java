package br.com.javanei.retrocenter.catalog.nointro.flags;

public final class NoIntroAlternate {
    public static String parseTag(String tag) {
        if (tag.toLowerCase().equalsIgnoreCase("alt")) {
            return "Alt";
        }
        if (tag.matches("Alt.+?")) {
            return "Alt " + tag.substring(4);
        }
        return null;
    }
}
