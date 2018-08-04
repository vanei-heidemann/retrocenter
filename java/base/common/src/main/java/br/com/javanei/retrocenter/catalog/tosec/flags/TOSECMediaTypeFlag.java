package br.com.javanei.retrocenter.catalog.tosec.flags;

/**
 * This field is used if the software spans more than one optical, diskette, tape or file.
 * Note that apart from the normal possibilities (Disk, Disc, Tape …), "Side x of y" is also allowed.
 * <p>
 * Disc Optical disc based media
 * Disk Magnetic disk based media
 * File Individual files
 * Part Individual parts
 * Side Side of media
 * Tape Magnetic tape based media
 * <p>
 * For example, where there are 9 or less disks, the format of "(Disk x of y)" is used, if there are 10 or more disks then
 * (Disk xx of yy) should be used, there can also be the case where more than one volume is grouped in a single image,
 * so something like (Part 1-2 of 3) is also allowed.
 * <p>
 * • Legend of TOSEC, The (1986)(Devstudio)(US)(File 1 of 2)
 * • Legend of TOSEC, The (1986)(Devstudio)(US)(File 2 of 2)
 * • Legend of TOSEC, The (1986)(Devstudio)(US)(Disc 1 of 6)
 * • Legend of TOSEC, The (1986)(Devstudio)(US)(Disk 06 of 13)
 * • Legend of TOSEC, The (1986)(Devstudio)(US)(Side A)
 * • Legend of TOSEC, The (1986)(Devstudio)(US)(Side B)
 * • Legend of TOSEC, The (1986)(Devstudio)(US)(Tape 2 of 2 Side B)
 * • Legend of TOSEC, The (1986)(Devstudio)(US)(Side 1 of 2)
 */
public class TOSECMediaTypeFlag {
    private String name;

    private TOSECMediaTypeFlag(String name) {
        this.name = name;
    }

    public static TOSECMediaTypeFlag parseMediaType(String tag) {
        if (tag.startsWith("Disc ") //Optical disc based media
                || tag.startsWith("Disk ") //Magnetic disk based media
                || tag.startsWith("File ") //Individual files
                || tag.startsWith("Part ") //Individual parts
                || tag.startsWith("Side ") //Side of media
                || tag.startsWith("Tape ") //Magnetic tape based media
        ) {
            return new TOSECMediaTypeFlag(tag);
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }
}
