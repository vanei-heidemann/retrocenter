package br.com.javanei.retrocenter.gamedb.launchbox;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LBoxPlatform implements Serializable, Comparable<LBoxPlatform> {
    private static final long serialVersionUID = 1L;

    private String name;
    private String releaseDate;
    private LBoxCompany developer;
    private LBoxCompany manufacturer;
    private Set<String> alternateNames = new HashSet<>();

    public LBoxPlatform() {
    }

    public LBoxPlatform(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LBoxCompany getDeveloper() {
        return developer;
    }

    public void setDeveloper(LBoxCompany developer) {
        this.developer = developer;
    }

    public LBoxCompany getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(LBoxCompany manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Set<String> getAlternateNames() {
        return alternateNames;
    }

    public void setAlternateNames(Set<String> alternateNames) {
        this.alternateNames = alternateNames;
    }

    public boolean addAlternateName(String alternateName) {
        return this.alternateNames.add(alternateName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LBoxPlatform that = (LBoxPlatform) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(LBoxPlatform that) {
        if (this.name.compareTo(that.name) < 0) {
            return -1;
        } else if (this.name.compareTo(that.name) > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "LBoxPlatform{" +
                "name='" + name + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", developer=" + developer +
                ", manufacturer=" + manufacturer +
                ", alternateNames=" + alternateNames +
                '}';
    }
}
