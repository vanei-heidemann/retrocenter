package br.com.javanei.retrocenter.gamedb.launchbox.parser.metadata;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class LBoxMetadata implements Serializable {
    private Set<LBoxMetadataPlatform> platforms = new HashSet<>();
    private Set<LBoxMetadataAlternateName> alternateNames = new HashSet<>();
    private Set<LBoxMetadataEmulator> emulators = new HashSet<>();
    private Set<LBoxMetadataEmulatorPlatform> emulatorPlatforms = new HashSet<>();
    private Set<LBoxMetadataGameImage> gameImages = new HashSet<>();
    private Set<LBoxMetadataGame> games = new HashSet<>();
    private Set<LBoxMetadataGameFileName> gameFiles = new HashSet<>();
    private Set<LBoxMetadataGameAlternateName> gameAlternateNames = new HashSet<>();
    private Set<String> regions = new HashSet<>();

    public Set<LBoxMetadataPlatform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(Set<LBoxMetadataPlatform> platforms) {
        this.platforms = platforms;
    }

    public boolean addPlatform(LBoxMetadataPlatform platform) {
        return this.platforms.add(platform);
    }

    public Set<LBoxMetadataAlternateName> getAlternateNames() {
        return alternateNames;
    }

    public void setAlternateNames(Set<LBoxMetadataAlternateName> alternateNames) {
        this.alternateNames = alternateNames;
    }

    public boolean addAlternateNames(LBoxMetadataAlternateName alternateName) {
        return this.alternateNames.add(alternateName);
    }

    public Set<LBoxMetadataEmulator> getEmulators() {
        return emulators;
    }

    public void setEmulators(Set<LBoxMetadataEmulator> emulators) {
        this.emulators = emulators;
    }

    public boolean addEmulator(LBoxMetadataEmulator emulator) {
        return this.emulators.add(emulator);
    }

    public Set<LBoxMetadataEmulatorPlatform> getEmulatorPlatforms() {
        return emulatorPlatforms;
    }

    public void setEmulatorPlatforms(Set<LBoxMetadataEmulatorPlatform> emulatorPlatforms) {
        this.emulatorPlatforms = emulatorPlatforms;
    }

    public boolean addEmulatorPlatform(LBoxMetadataEmulatorPlatform emulatorPlatform) {
        return this.emulatorPlatforms.add(emulatorPlatform);
    }

    public Set<LBoxMetadataGameImage> getGameImages() {
        return gameImages;
    }

    public void setGameImages(Set<LBoxMetadataGameImage> gameImages) {
        this.gameImages = gameImages;
    }

    public boolean addGameImage(LBoxMetadataGameImage gameImage) {
        return this.gameImages.add(gameImage);
    }

    public Set<LBoxMetadataGame> getGames() {
        return games;
    }

    public void setGames(Set<LBoxMetadataGame> game) {
        this.games = games;
    }

    public boolean addGame(LBoxMetadataGame game) {
        return this.games.add(game);
    }

    public Set<LBoxMetadataGameFileName> getGameFiles() {
        return gameFiles;
    }

    public void setGameFiles(Set<LBoxMetadataGameFileName> gameFiles) {
        this.gameFiles = gameFiles;
    }

    public boolean addGameFile(LBoxMetadataGameFileName gameFile) {
        return this.gameFiles.add(gameFile);
    }

    public Set<LBoxMetadataGameAlternateName> getGameAlternateNames() {
        return gameAlternateNames;
    }

    public void setGameAlternateNames(Set<LBoxMetadataGameAlternateName> gameAlternateNames) {
        this.gameAlternateNames = gameAlternateNames;
    }

    public boolean addGameAlternateName(LBoxMetadataGameAlternateName gameAlternateName) {
        return this.gameAlternateNames.add(gameAlternateName);
    }

    public Set<String> getRegions() {
        return regions;
    }

    public void setRegions(Set<String> regions) {
        this.regions = regions;
    }

    public boolean addRegion(String region) {
        return this.regions.add(region);
    }
}
