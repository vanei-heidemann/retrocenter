package br.com.javanei.retrocenter.gamedb.launchbox.entity;

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

/**
 * alter table lbox_datafile change datafile_id lbox_datafile_id bigint(20) NOT NULL AUTO_INCREMENT;
 */
@Entity
@Table(name = "LBOX_DATAFILE")
public class LBoxDatafileEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LBOX_DATAFILE_ID", nullable = false)
    private Long id;

    @Column(name = "VERSION", length = 16, nullable = false, unique = true)
    private String version;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "datafile")
    private Set<LBoxDatafilePlatformEntity> platforms = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "datafile")
    private Set<LBoxDatafileGenreEntity> genres = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "datafile")
    private Set<LBoxDatafileRegionEntity> regions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "datafile")
    private Set<LBoxDatafileGameEntity> games = new HashSet<>();

    public LBoxDatafileEntity() {
    }

    public LBoxDatafileEntity(String version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Set<LBoxDatafilePlatformEntity> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(Set<LBoxDatafilePlatformEntity> platforms) {
        this.platforms = platforms;
    }

    public Set<LBoxDatafileGenreEntity> getGenres() {
        return genres;
    }

    public void setGenres(Set<LBoxDatafileGenreEntity> genres) {
        this.genres = genres;
    }

    public Set<LBoxDatafileRegionEntity> getRegions() {
        return regions;
    }

    public void setRegions(Set<LBoxDatafileRegionEntity> regions) {
        this.regions = regions;
    }

    public Set<LBoxDatafileGameEntity> getGames() {
        return games;
    }

    public void setGames(Set<LBoxDatafileGameEntity> games) {
        this.games = games;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LBoxDatafileEntity that = (LBoxDatafileEntity) o;

        return version.equals(that.version);
    }

    @Override
    public int hashCode() {
        return version.hashCode();
    }

    @Override
    public String toString() {
        return "LBoxDatafileEntity{" +
                "id=" + id +
                ", version='" + version + '\'' +
                '}';
    }
}
