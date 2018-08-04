package br.com.javanei.retrocenter.gamedb.launchbox.entity;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * alter table lbox_platform change platform_id lbox_platform_id bigint(20) NOT NULL AUTO_INCREMENT;
 * alter table lbox_platform_alt_name drop foreign key FK6ixar94uke2y1jmypiwdtip5l;
 * alter table lbox_platform_alt_name change platform_id lbox_platform_id bigint(20) NOT NULL;
 */
@Entity
@Table(name = "LBOX_PLATFORM")
public class LBoxPlatformEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LBOX_PLATFORM_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 255, nullable = false, unique = true)
    private String name;

    @Column(name = "RELEASE_DATE", length = 32, nullable = true, unique = false)
    private String releaseDate;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "DEVELOPER_ID")
    private LBoxCompanyEntity developer;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "MANUFACTURER_ID")
    private LBoxCompanyEntity manufacturer;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "LBOX_PLATFORM_ALT_NAME",
            joinColumns = @JoinColumn(name = "LBOX_PLATFORM_ID",
                    foreignKey = @ForeignKey(name = "FK_LBOX_PLAT_ALT_NAME"),
                    unique = false)
    )
    @Column(name = "ALTERNATE_NAME", length = 128, nullable = false, unique = false, columnDefinition = "VARCHAR(128) COLLATE utf8_bin")
    private Set<String> alternateNames = new HashSet<>();
    //ALTER TABLE lbox_platform_alt_name CONVERT TO CHARACTER SET UTF8 COLLATE utf8_bin;

    @Column(name = "PLATFORM_ID", nullable = false)
    private Long platformId = -1l;
    //UPDATE lbox_platform SET platform_id = -1;

    public LBoxPlatformEntity() {
    }

    public LBoxPlatformEntity(String name) {
        this.name = name;
    }

    public LBoxPlatformEntity(String name, String releaseDate) {
        this.name = name;
        this.releaseDate = releaseDate;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LBoxCompanyEntity getDeveloper() {
        return developer;
    }

    public void setDeveloper(LBoxCompanyEntity developer) {
        this.developer = developer;
    }

    public LBoxCompanyEntity getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(LBoxCompanyEntity manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Set<String> getAlternateNames() {
        return alternateNames;
    }

    public void setAlternateNames(Set<String> alternateNames) {
        this.alternateNames = alternateNames;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LBoxPlatformEntity that = (LBoxPlatformEntity) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "LBoxPlatformEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", developer=" + developer +
                ", manufacturer=" + manufacturer +
                ", alternateNames=" + alternateNames +
                ", platformId=" + platformId +
                '}';
    }
}
