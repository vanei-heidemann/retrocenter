package br.com.javanei.retrocenter.catalog.mame.flags;

public enum MameLanguageEnum {
    Austrian("Austrian"),
    Belgian("Belgian"),
    Canadian("Canadian"),
    Danish("Danish"),
    English("English"),
    Finnish("Finnish"),
    French("French"),
    German("German"),
    Hungarian("Hungarian"),
    Italian("Italian"),
    Japanese("Japanese"),
    Korean("Korean"),
    Norwegian("Norwegian"),
    Polish("Polish"),
    Portuguese("Portuguese"),
    Spanish("Spanish"),
    Swedish("Swedish"),
    Swiss("Swiss");

    private String name;

    MameLanguageEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public MameLanguageEnum fromName(String name) {
        if (name.startsWith("/") || name.startsWith("-")) {
            name = name.substring(1).trim();
        }
        for (MameLanguageEnum e : MameLanguageEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }
}
