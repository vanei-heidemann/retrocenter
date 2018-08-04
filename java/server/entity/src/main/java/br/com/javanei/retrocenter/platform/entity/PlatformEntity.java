package br.com.javanei.retrocenter.platform.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PLATFORM")
public class PlatformEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PLATFORM_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 255, nullable = false, unique = true)
    private String name;

    @Column(name = "SHORT_NAME", length = 128, nullable = false)
    private String shortName;

    @Column(name = "STORAGE_FOLDER", length = 128, nullable = false)
    private String storageFolder;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "platform")
    private Set<PlatformAltNameEntity> alternateNames = new HashSet<>();

    public PlatformEntity() {
    }

    public PlatformEntity(String name, String shortName, String storageFolder) {
        this.name = name;
        this.shortName = shortName;
        this.storageFolder = storageFolder;
    }

    public PlatformEntity(Long id, String name, String shortName, String storageFolder) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.storageFolder = storageFolder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<PlatformAltNameEntity> getAlternateNames() {
        return alternateNames;
    }

    public void setAlternateNames(Set<PlatformAltNameEntity> alternateNames) {
        this.alternateNames = alternateNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlatformEntity that = (PlatformEntity) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
