package br.com.javanei.retrocenter.catalog.tosec.flags;

public enum TOSECSystemFlagEnum {
    Plus2("+2", "Sinclair ZX Spectrum"),
    Plus2a("+2a", "Sinclair ZX Spectrum"),
    Plus3("+3", "Sinclair ZX Spectrum"),
    XE130("130XE", "Atari 8-bit"),
    A1000("A1000", "Commodore Amiga"),
    A1200("A1200", "Commodore Amiga"),
    A1200_A4000("A1200-A4000", "Commodore Amiga"),
    A2000("A2000", "Commodore Amiga"),
    A2000_A3000("A2000-A3000", "Commodore Amiga"),
    A2024("A2024", "Commodore Amiga"),
    A2500_A3000UX("A2500-A3000UX", "Commodore Amiga"),
    A3000("A3000", "Commodore Amiga"),
    A4000("A4000", "Commodore Amiga"),
    A4000T("A4000T", "Commodore Amiga"),
    A500("A500", "Commodore Amiga"),
    A500Plus("A500+", "Commodore Amiga"),
    A500_A1000_A2000("A500-A1000-A2000", "Commodore Amiga"),
    A500_A1000_A2000_CDTV("A500-A1000-A2000-CDTV", "Commodore Amiga"),
    A500_A1200("A500-A1200", "Commodore Amiga"),
    A500_A1200_A2000_A4000("A500-A1200-A2000-A4000", "Commodore Amiga"),
    A500_A2000("A500-A2000", "Commodore Amiga"),
    A500_A600_A2000("A500-A600-A2000", "Commodore Amiga"),
    A570("A570", "Commodore Amiga"),
    A600("A600", "Commodore Amiga"),
    A600HD("A600HD", "Commodore Amiga"),
    AGA("AGA", "Commodore Amiga"),
    AGA_CD32("AGA-CD32", "Commodore Amiga"),
    AladdinDeckEnhancer("Aladdin Deck", "Enhancer Nintendo NES"),
    CD32("CD32", "Commodore Amiga"),
    CDTV("CDTV", "Commodore Amiga"),
    Computrainer("Computrainer", "Nintendo NES"),
    DoctorPCJr("Doctor PC Jr.", "Nintendo NES"),
    ECS("ECS", "Commodore Amiga"),
    ECS_AGA("ECS-AGA", "Commodore Amiga"),
    Executive("Executive", "Osborne 1 & Executive"),
    MegaST("Mega ST", "Atari ST"),
    MegaSTE("Mega-STE", "Atari ST"),
    OCS("OCS", "Commodore Amiga"),
    OCS_AGA("OCS-AGA", "Commodore Amiga"),
    ORCH80("ORCH80", "???"),
    Osbourne1("Osbourne 1", "Osborne 1 & Executive"),
    PIANO90("PIANO90", "???"),
    PlayChoice10("PlayChoice-10", "Nintendo NES"),
    Plus4("Plus4", "???"),
    PrimoA("Primo-A", "Microkey Primo"),
    PrimoA64("Primo-A64", "Microkey Primo"),
    PrimoB("Primo-B", "Microkey Primo"),
    PrimoB64("Primo-B64", "Microkey Primo"),
    ProPrimo("Pro-Primo", "Microkey Primo"),
    ST("ST", "Atari ST"),
    STE("STE", "Atari ST"),
    STEFalcon("STE-Falcon", "???"),
    TT("TT", "Atari ST"),
    TURBORGT("TURBO-R GT", "MSX"),
    TURBORST("TURBO-R ST", "MSX"),
    VSDualSystem("VS DualSystem", "Nintendo NES"),
    VSUniSystem("VS UniSystem", "Nintendo NES");

    private String name;
    private String description;

    TOSECSystemFlagEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static TOSECSystemFlagEnum fromName(String name) {
        for (TOSECSystemFlagEnum e : TOSECSystemFlagEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

    public static boolean isSystemFlag(String name) {
        return fromName(name) != null;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
