package br.com.javanei.retrocenter.catalog.nointro.flags;

public final class NoIntroCompilation {
    public static String parseTag(String tag) {
        if (tag.startsWith("Compilation") || tag.endsWith("Compilation")) {
            return tag;
        }
        return null;
    }
}
