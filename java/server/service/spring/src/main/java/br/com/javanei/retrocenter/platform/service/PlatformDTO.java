package br.com.javanei.retrocenter.platform.service;

import br.com.javanei.retrocenter.platform.Platform;

import java.io.Serializable;
import java.util.Set;

public class PlatformDTO extends Platform implements Serializable {
    private Long id;

    public PlatformDTO() {
    }

    public PlatformDTO(String name, String shortName, String storageFolder, Set<String> alternateNames, Long id) {
        super(name, shortName, storageFolder, alternateNames);
        this.id = id;
    }

    public PlatformDTO(String name, String shortName, String storageFolder, Long id) {
        super(name, shortName, storageFolder);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PlatformDTO{" +
                "id=" + id +
                "} " + super.toString();
    }
}
