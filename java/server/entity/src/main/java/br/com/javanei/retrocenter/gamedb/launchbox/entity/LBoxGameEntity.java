package br.com.javanei.retrocenter.gamedb.launchbox.entity;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * alter table lbox_game change game_id lbox_game_id varchar(32) NOT NULL;
 * <p>
 * alter table lbox_game change platform_id lbox_platform_id bigint(20) NOT NULL;
 * <p>
 * alter table lbox_game_file_name drop foreign key FK6dnv6kskt65gbms72f1s0mngc;
 * alter table lbox_game_file_name change game_id lbox_game_id varchar(32) NOT NULL;
 * <p>
 * alter table lbox_game drop foreign key FKf0phyyr3lvp5vju21gselia20;
 * alter table lbox_game drop key FKf0phyyr3lvp5vju21gselia20;
 */
@Entity
@Table(name = "LBOX_GAME")
public class LBoxGameEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "LBOX_GAME_ID", length = 32, nullable = false, unique = true)
    private String databaseID;

    @Column(name = "NAME", length = 255, nullable = false)
    private String name;

    @Column(name = "RELEASE_DATE", length = 32, nullable = true, unique = false)
    private String releaseDate;

    @Column(name = "RELEASE_YEAR", length = 16, nullable = true, unique = false)
    private Integer releaseYear;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LBOX_PLATFORM_ID", nullable = false)
    private LBoxPlatformEntity platform;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "PUBLISHER_ID")
    private LBoxCompanyEntity publisher;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "DEVELOPER_ID")
    private LBoxCompanyEntity developer;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "LBOX_GAME_FILE_NAME",
            joinColumns = @JoinColumn(name = "LBOX_GAME_ID",
                    foreignKey = @ForeignKey(name = "FK_LBOX_GAME_FILE_NAME"),
                    unique = false)
    )
    @Column(name = "FILE_NAME", length = 255, nullable = false, unique = false)
    private Set<String> fileNames = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "game")
    private Set<LBoxGameGenreEntity> genres = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "game")
    private Set<LBoxGameRegionEntity> regions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "game")
    private Set<LBoxGameImageEntity> images = new HashSet<>();

    public LBoxGameEntity() {
    }

    public LBoxGameEntity(String databaseID) {
        this.databaseID = databaseID;
    }

    public String getDatabaseID() {
        return databaseID;
    }

    public void setDatabaseID(String databaseID) {
        this.databaseID = databaseID;
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

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public LBoxPlatformEntity getPlatform() {
        return platform;
    }

    public void setPlatform(LBoxPlatformEntity platform) {
        this.platform = platform;
    }

    public LBoxCompanyEntity getPublisher() {
        return publisher;
    }

    public void setPublisher(LBoxCompanyEntity publisher) {
        this.publisher = publisher;
    }

    public LBoxCompanyEntity getDeveloper() {
        return developer;
    }

    public void setDeveloper(LBoxCompanyEntity developer) {
        this.developer = developer;
    }

    public Set<String> getFileNames() {
        return fileNames;
    }

    public void setFileNames(Set<String> fileNames) {
        this.fileNames = fileNames;
    }

    public Set<LBoxGameGenreEntity> getGenres() {
        return genres;
    }

    public void setGenres(Set<LBoxGameGenreEntity> genres) {
        this.genres = genres;
    }

    public Set<LBoxGameRegionEntity> getRegions() {
        return regions;
    }

    public void setRegions(Set<LBoxGameRegionEntity> regions) {
        this.regions = regions;
    }

    public Set<LBoxGameImageEntity> getImages() {
        return images;
    }

    public void setImages(Set<LBoxGameImageEntity> images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LBoxGameEntity that = (LBoxGameEntity) o;

        return databaseID.equals(that.databaseID);
    }

    @Override
    public int hashCode() {
        return databaseID.hashCode();
    }

    @Override
    public String toString() {
        return "LBoxGameEntity{" +
                "databaseID='" + databaseID + '\'' +
                ", name='" + name + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", releaseYear=" + releaseYear +
                ", platform=" + platform +
                ", publisher=" + publisher +
                ", developer=" + developer +
                ", fileNames=" + fileNames +
                ", genres=" + genres +
                ", regions=" + regions +
                ", images=" + images +
                '}';
    }
}
