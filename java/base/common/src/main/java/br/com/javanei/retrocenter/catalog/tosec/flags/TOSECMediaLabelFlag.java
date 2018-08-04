package br.com.javanei.retrocenter.catalog.tosec.flags;

/**
 * If the disk label is required, this field should contain it.
 * This field is always the last flag using ( ) brackets, just before any existent [ ] flags.
 * <p>
 * This is mainly used when a "Save Disk", "Program Disk", "Scenery Disk" etc. might be requested by the
 * software when running.
 * For example (Disk 2 of 2) is not useful by itself when the program asks you to "Insert Character Disk".
 * <p>
 * • Legend of TOSEC, The (1986)(Devstudio)(Disk 3 of 3)(Character Disk)
 * • Legend of TOSEC, The (1986)(Devstudio)(US)(Disk 1 of 2)(Program)
 * • Legend of TOSEC, The (1986)(Devstudio)(US)(Disk 2 of 2)(Data)
 * • Legend of TOSEC, The (1986)(Devstudio)(US)(Disk 2 of 2)(Disk B)
 * • Legend of TOSEC, The (1986)(Devstudio)(US)(Bonus Disc)
 */
public class TOSECMediaLabelFlag {
    public static String parseMediaLabel(String tag) {
        if (tag.toLowerCase().contains("bonus disc")
                || tag.toLowerCase().contains("disk")
                || tag.toLowerCase().contains("disc")
                || tag.toLowerCase().contains("program")
                || tag.toLowerCase().contains("data")
                || tag.toLowerCase().contains("save")
            // Implementar outras validacoes
        ) {
            return tag;
        }
        return null;
    }
}
