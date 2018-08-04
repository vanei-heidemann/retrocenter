package br.com.javanei.retrocenter.catalog.nointro.flags;

public enum NoIntroVideoEnum {
    AGA("AGA"), //Commodore Amiga
    OCS("OCS"), //Commodore Amiga
    ECS("ECS"), //Commodore Amiga
    MCM("MCM"); //Commodore 64

    private String name;

    NoIntroVideoEnum(String name) {
        this.name = name;
    }

    public static NoIntroVideoEnum fromName(String name) {
        for (NoIntroVideoEnum e : NoIntroVideoEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
