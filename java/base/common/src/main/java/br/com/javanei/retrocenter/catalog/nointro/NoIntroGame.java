package br.com.javanei.retrocenter.catalog.nointro;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NoIntroGame implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String mainName;
    private Boolean isBios = Boolean.FALSE;
    private List<String> regions = new LinkedList<>();
    private List<String> languages = new LinkedList<>();
    private String version;
    private Map<String, String> devStatus = new HashMap<>();
    private String badDump;
    private String protection;
    private String loader;
    private String alternate;
    private String compilation;
    private String releaseDate;
    private List<String> systems;
    private String video;
    private List<String> complements = new LinkedList<>();

    public NoIntroGame() {
    }

    public NoIntroGame(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public Boolean getIsBios() {
        return isBios;
    }

    public void setIsBios(Boolean bios) {
        isBios = bios;
    }

    public List<String> getRegions() {
        return regions;
    }

    public void setRegions(List<String> regions) {
        this.regions = regions;
    }

    public void addRegion(String region) {
        this.regions.add(region);
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public void addLanguage(String language) {
        this.languages.add(language);
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getProto() {
        return this.devStatus.get("Proto");
    }

    public void setProto(String proto) {
        this.devStatus.put("Proto", proto);
    }

    public String getDemo() {
        return this.devStatus.get("Demo");
    }

    public void setDemo(String demo) {
        this.devStatus.put("Demo", demo);
    }

    public String getBeta() {
        return this.devStatus.get("Beta");
    }

    public void setBeta(String beta) {
        this.devStatus.put("BDeta", beta);
    }

    public String getPromo() {
        return this.devStatus.get("Promo");
    }

    public void setPromo(String promo) {
        this.devStatus.put("Promo", promo);
    }

    public String getUnl() {
        return this.devStatus.get("Unl");
    }

    public void setUnl(String unl) {
        this.devStatus.put("Unl", unl);
    }

    public String getSample() {
        return this.devStatus.get("Sample");
    }

    public void setSample(String sample) {
        this.devStatus.put("Sample", sample);
    }

    public String getPreview() {
        return this.devStatus.get("Preview");
    }

    public void setPreview(String preview) {
        this.devStatus.put("Preview", preview);
    }

    public Map<String, String> getDevStatus() {
        return devStatus;
    }

    public void setDevStatus(Map<String, String> devStatus) {
        this.devStatus = devStatus;
    }

    public void addDevStatus(String key, String value) {
        this.devStatus.put(key, value);
    }

    public String getBadDump() {
        return badDump;
    }

    public void setBadDump(String badDump) {
        this.badDump = badDump;
    }

    public String getProtection() {
        return protection;
    }

    public void setProtection(String protection) {
        this.protection = protection;
    }

    public String getLoader() {
        return loader;
    }

    public void setLoader(String loader) {
        this.loader = loader;
    }

    public String getAlternate() {
        return alternate;
    }

    public void setAlternate(String alternate) {
        this.alternate = alternate;
    }

    public String getCompilation() {
        return compilation;
    }

    public void setCompilation(String compilation) {
        this.compilation = compilation;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<String> getSystems() {
        return systems;
    }

    public void setSystems(List<String> systems) {
        this.systems = systems;
    }

    public void addSystem(String system) {
        this.systems.add(system);
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public List<String> getComplements() {
        return complements;
    }

    public void setComplements(List<String> complements) {
        this.complements = complements;
    }

    public void addComplement(String complement) {
        this.complements.add(complement);
    }

    @Override
    public String toString() {
        return "NoIntroGame{" +
                "name='" + name + '\'' +
                ", mainName='" + mainName + '\'' +
                ", isBios=" + isBios +
                ", regions=" + regions +
                ", languages=" + languages +
                ", version='" + version + '\'' +
                ", devStatus=" + devStatus +
                ", badDump='" + badDump + '\'' +
                ", protection='" + protection + '\'' +
                ", loader='" + loader + '\'' +
                ", alternate='" + alternate + '\'' +
                ", compilation='" + compilation + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", systems='" + systems + '\'' +
                ", video='" + video + '\'' +
                ", complements=" + complements +
                '}';
    }
}
