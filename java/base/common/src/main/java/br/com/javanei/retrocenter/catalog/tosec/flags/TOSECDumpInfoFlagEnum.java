package br.com.javanei.retrocenter.catalog.tosec.flags;

/**
 * •[cr][f][h][m][p][t][tr][o][u][v][b][a][!]
 * <p>
 * Examples:
 * - Legend of TOSEC, The (1986)(Devstudio)(US)[a]
 * - Legend of TOSEC, The (1986)(Devstudio)(US)[b]
 * - Legend of TOSEC, The (1986)(Devstudio)(US)[f]
 * - Legend of TOSEC, The (1986)(Devstudio)(US)[f NTSC]
 * - Legend of TOSEC, The (1986)(Devstudio)(US)[u]
 * - Legend of TOSEC, The (1986)(Devstudio)(US)[cr]
 * - Legend of TOSEC, The (1986)(Devstudio)(US)[tr fr]
 * - Legend of TOSEC, The (1986)(Devstudio)(US)[tr de-partial someguy]
 * - Legend of TOSEC, The (1986)(Devstudio)(US)[h Fairlight]
 * - Legend of TOSEC, The (1986)(Devstudio)(US)[m save game]
 * - Legend of TOSEC, The (1986)(Devstudio)(US)[o]
 * <p>
 * In case where multiple images exist that need the same dump info flags, the flag is numbered as follows:
 * - Legend of TOSEC, The (1986)(Devstudio)(US)[a]
 * - Legend of TOSEC, The (1986)(Devstudio)(US)[a2]
 * - Legend of TOSEC, The (1986)(Devstudio)(US)[a3]
 * - Legend of TOSEC, The (1986)(Devstudio)(US)[a4]
 * - Legend of TOSEC, The (1986)(Devstudio)(US)[b]
 * - Legend of TOSEC, The (1986)(Devstudio)(US)[b2]
 * - Legend of TOSEC, The (1986)(Devstudio)(US)[b3]
 * - Legend of TOSEC, The (1986)(Devstudio)(US)[cr]
 * - Legend of TOSEC, The (1986)(Devstudio)(US)[cr2]
 */
public enum TOSECDumpInfoFlagEnum {
    /**
     * The original software has been deliberately hacked/altered to remove some form of copy protection.
     * <p>
     * [cr] - Cracked
     * [cr Cracker] - Cracked by Cracker (group or person)
     */
    Cracked("cr"),
    /**
     * The original software has been deliberately hacked/altered in some way to 'improve' or fix the image to work
     * in a non-standard way, e.g. 'fixing' a software that is supposed to run in PAL to run on a NTSC system.
     * <p>
     * [f] - Fixed
     * [f Fix] - Fix/amendment added
     * [f Fixer] - Fixed by Fixer (group or person)
     * [f Fix Fixer] - Fix added by Fixer (group or person)
     */
    Fixed("f"),
    /**
     * The original software has been deliberately hacked/altered in some way,
     * such as adding an intro or changing in game sprites or text.
     * <p>
     * [h Hack] – Description of hack
     * [h Hacker] – Hacked by (group or person)
     * [h Hack Hacker] – Description of hack, followed by hacker (group or person)
     */
    Hacked("h"),
    /**
     * The original software has been hacked/altered in some way (but not deliberately).
     * <p>
     * [m] - Modified (general hack)
     * [m Modification] - Modification added
     */
    Modified("m"),
    /**
     * The software is not legally licensed or violates some international IP.
     * <p>
     * [p] - Pirate version
     * [p Pirate] - Pirate version by Pirate (group or person)
     */
    Pirated("p"),
    /**
     * The original software has been deliberately hacked/altered to add cheats and/or a cheat.
     * <p>
     * [t] - Trained
     * [t Trainer] - Trained by trainer (group or person)
     * [t +x] - x denotes number of trainers added
     * [t +x Trainer] - Trained and x number of trainers added by trainer (group or person)
     */
    Trained("t"),
    /**
     * The original software has been deliberately hacked/altered to translate into a different language than
     * originally published/released.
     * <p>
     * [tr] - Translation
     * [tr language] - Translated to Language
     * [tr language-partial] - Translated to Language (partial translation)
     * [tr language Translator] - Translated to Language by Translator (group or person)
     * [tr language1-language2] - Translated to both Language1 and Language2.
     * [tr language1-partial-language2-partial Translator] - Partially translated to both Language1 and language2 by Translator (group or person).
     */
    Translated("tr"),
    /**
     * The image is damaged (duplicated data or too much data).
     * <p>
     * [o] - Over Dump (too much data dumped)
     */
    OverDump("o"),
    /**
     * The image is damaged (missing data).
     * <p>
     * [u] - Under Dump (not enough data dumped).
     */
    UnderDump("u"),
    /**
     * The image is damaged from the infection of a virus.
     * <p>
     * [v] - Virus (infected)
     * [v Virus] - Infected with virus
     * [v Virus Version] - Infected with virus of version
     */
    Virus("v"),
    /**
     * The image is damaged. This is a general 'damaged/bad' flag, to be used when the type of damage does not fit
     * into any of the other 'damaged' categories. It is likely this image will not work properly, or not at all.
     * <p>
     * [b] - Bad dump (incorrect data dumped)
     * [b Descriptor] - Bad dump (including reason)
     * <p>
     * Some examples of descriptors:
     * - corrupt file = Image contains a corrupt file
     * - read-write = Image has a read/write error
     */
    BadDump("b"),
    /**
     * An alternate ORIGINAL version of another ORIGINAL image, e.g. if a game was released, then re-released later
     * with a small change (and the revision/version number is not known).
     * <p>
     * [a] - Alternate version
     * [a Descriptor] - Alternate (including reason)
     * <p>
     * Descriptor example:
     * - no title screen = Game has no title screen, the non [a] image does
     * - readme = Only a readme file is different from a non [a] image
     */
    Alternate("a"),
    /**
     * Image has had multiply person/multi dump verification to confirm it is a 100% repeatable and correct dump.
     * This is currently only used in the TOSEC-ISO branch.
     * <p>
     * [!] - Verified good dump
     */
    VerifiedGoodDump("!");

    private String name;

    TOSECDumpInfoFlagEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
//TODO:
}
