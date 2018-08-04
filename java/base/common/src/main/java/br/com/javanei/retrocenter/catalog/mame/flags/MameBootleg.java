package br.com.javanei.retrocenter.catalog.mame.flags;

public final class MameBootleg {
    private MameBootleg() {
    }

    public static final String parseBootleg(String tag) {
        if (tag.toLowerCase().startsWith("bootleg")) {
            return tag;
        }
        return null;
    }
}
