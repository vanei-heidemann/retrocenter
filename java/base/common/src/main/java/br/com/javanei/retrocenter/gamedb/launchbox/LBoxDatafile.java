package br.com.javanei.retrocenter.gamedb.launchbox;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class LBoxDatafile implements Serializable {
    private String version;
    private Set<LBoxPlatform> platforms = new HashSet<>();
    private Set<LBoxGenre> genres = new HashSet<>();
    private Set<LBoxRegion> regions = new HashSet<>();
    private Set<LBoxGame> games = new HashSet<>();

    public LBoxDatafile() {
    }

    public LBoxDatafile(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Set<LBoxPlatform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(Set<LBoxPlatform> platforms) {
        this.platforms = platforms;
    }

    public void addPlatform(LBoxPlatform platform) {
        this.platforms.add(platform);
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

    public Set<LBoxRegion> getRegions() {
        return regions;
    }

    public void setRegions(Set<LBoxRegion> regions) {
        this.regions = regions;
    }

    public boolean addRegion(LBoxRegion region) {
        return this.regions.add(region);
    }

    public Set<LBoxGame> getGames() {
        return games;
    }

    public void setGames(Set<LBoxGame> games) {
        this.games = games;
    }

    public void addGame(LBoxGame game) {
        this.games.add(game);
    }

    @Override
    public String toString() {
        return "LBoxDatafile{" +
                "version='" + version + '\'' +
                ", platforms=" + platforms.size() +
                ", genres=" + genres.size() +
                ", regions=" + regions.size() +
                ", games=" + games.size() +
                '}';
    }
}
