package br.com.javanei.retrocenter.catalog.mame;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class MameGame implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String mainName;
    private String version;
    private String prototype;
    private String bootleg;
    private List<String> regions = new LinkedList<>();
    private List<String> complements = new LinkedList<>();

    public MameGame() {
    }

    public MameGame(String name) {
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPrototype() {
        return prototype;
    }

    public void setPrototype(String prototype) {
        this.prototype = prototype;
    }

    public String getBootleg() {
        return bootleg;
    }

    public void setBootleg(String bootleg) {
        this.bootleg = bootleg;
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

    public List<String> getRegions() {
        return regions;
    }

    public void setRegions(List<String> regions) {
        this.regions = regions;
    }

    public void addRegion(String region) {
        this.regions.add(region);
    }
}
