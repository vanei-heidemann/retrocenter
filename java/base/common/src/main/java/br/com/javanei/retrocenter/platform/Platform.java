package br.com.javanei.retrocenter.platform;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Platform implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String shortName;
    @Deprecated
    //TODO: Deveria estar só na entidade?
    private String storageFolder;
    private Set<String> alternateNames = new HashSet<>();

    public Platform() {
    }

    public Platform(String name) {
        this.name = name;
        this.shortName = name;
        this.storageFolder = name;
    }

    public Platform(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
        this.storageFolder = shortName;
    }

    public Platform(String name, String shortName, String storageFolder) {
        this.name = name;
        this.shortName = shortName;
        this.storageFolder = storageFolder;
    }

    public Platform(String name, String shortName, String storageFolder, Set<String> alternateNames) {
        this.name = name;
        this.shortName = shortName;
        this.storageFolder = storageFolder;
        this.alternateNames = alternateNames;
    }

    public Platform(String name, String shortName, String storageFolder, String[] alternateNames) {
        this.name = name;
        this.shortName = shortName;
        this.storageFolder = storageFolder;
        this.alternateNames.addAll(Arrays.asList(alternateNames));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getStorageFolder() {
        return storageFolder;
    }

    public void setStorageFolder(String storageFolder) {
        this.storageFolder = storageFolder;
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

        Platform platform = (Platform) o;

        return shortName != null ? shortName.equals(platform.shortName) : platform.shortName == null;
    }

    @Override
    public int hashCode() {
        return shortName != null ? shortName.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Platform{" +
                "name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", storageFolder='" + storageFolder + '\'' +
                ", alternateNames=" + alternateNames +
                '}';
    }
}
