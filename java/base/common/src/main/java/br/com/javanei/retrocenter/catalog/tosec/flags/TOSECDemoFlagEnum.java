package br.com.javanei.retrocenter.catalog.tosec.flags;

public enum TOSECDemoFlagEnum {
    Demo("demo", "General demonstration version"),
    DemoKiosk("demo-kiosk", "Retail demo units and kiosks"),
    DemoPlayable("demo-playable", "General demonstration version, playable"),
    DemoRolling("demo-rolling", "General demonstration version, non-interactive"),
    DemoSlideshow("demo-slideshow", "General demonstration version, non-interactive slideshow");

    private String name;
    private String description;

    TOSECDemoFlagEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static TOSECDemoFlagEnum fromName(String name) {
        for (TOSECDemoFlagEnum e : TOSECDemoFlagEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

    public static boolean isDemoFlag(String name) {
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
