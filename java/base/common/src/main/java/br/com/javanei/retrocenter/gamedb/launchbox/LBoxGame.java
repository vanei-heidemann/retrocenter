package br.com.javanei.retrocenter.gamedb.launchbox;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class LBoxGame implements Serializable {
    private static final long serialVersionUID = 1L;

    private String databaseID;
    private String name;
    private String releaseDate;
    private Integer releaseYear;
    private LBoxPlatform platform;
    private LBoxCompany publisher;
    private LBoxCompany developer;
    private Set<String> fileNames = new HashSet<>();
    private Set<LBoxGenre> genres = new HashSet<>();
    private Set<LBoxGameImage> images = new HashSet<>();
    private Set<LBoxRegion> regions = new HashSet<>();
    private Set<LBoxGameAlternateName> alternateNames = new HashSet<>();

    public LBoxGame() {
    }

    public LBoxGame(String name) {
        this.name = name;
    }

    public LBoxGame(String databaseID, String name) {
        this.databaseID = databaseID;
        this.name = name;
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

    public LBoxPlatform getPlatform() {
        return platform;
    }

    public void setPlatform(LBoxPlatform platform) {
        this.platform = platform;
    }

    public LBoxCompany getPublisher() {
        return publisher;
    }

    public void setPublisher(LBoxCompany publisher) {
        this.publisher = publisher;
    }

    public LBoxCompany getDeveloper() {
        return developer;
    }

    public void setDeveloper(LBoxCompany developer) {
        this.developer = developer;
    }

    public Set<String> getFileNames() {
        return fileNames;
    }

    public void setFileNames(Set<String> fileNames) {
        this.fileNames = fileNames;
    }

    public boolean addFileName(String fileName) {
        return this.fileNames.add(fileName);
    }

    public Set<LBoxGenre> getGenres() {
        return genres;
    }

    public void setGenres(Set<LBoxGenre> genres) {
        this.genres = genres;
    }

    public boolean addGenre(LBoxGenre genre) {
        return this.genres.add(genre);
    }

    public Set<LBoxGameImage> getImages() {
        return images;
    }

    public void setImages(Set<LBoxGameImage> images) {
        this.images = images;
    }

    public void addImage(LBoxGameImage image) {
        this.images.add(image);
    }

    public Set<LBoxRegion> getRegions() {
        return regions;
    }

    public void setRegions(Set<LBoxRegion> regions) {
        this.regions = regions;
    }

    public boolean addRegion(LBoxRegion region) {
        return this.regions.add(region);
    }

    public Set<LBoxGameAlternateName> getAlternateNames() {
        return alternateNames;
    }

    public void setAlternateNames(Set<LBoxGameAlternateName> alternateNames) {
        this.alternateNames = alternateNames;
    }

    public boolean addAlternateName(LBoxGameAlternateName alternateName) {
        return this.alternateNames.add(alternateName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LBoxGame lBoxGame = (LBoxGame) o;

        return databaseID.equals(lBoxGame.databaseID);
    }

    @Override
    public int hashCode() {
        return databaseID.hashCode();
    }

    @Override
    public String toString() {
        return "LBoxGame{" +
                "databaseID='" + databaseID + '\'' +
                ", name='" + name + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", releaseYear=" + releaseYear +
                ", fileNames=" + fileNames +
                ", platform=" + platform +
                ", publisher=" + publisher +
                ", developer=" + developer +
                ", genres=" + genres +
                ", images=" + images +
                ", regions=" + regions +
                ", alternateNames=" + alternateNames +
                '}';
    }
}
