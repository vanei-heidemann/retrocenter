package br.com.javanei.retrocenter.gamedb.launchbox.parser.metadata;

import java.io.Serializable;
import java.util.Objects;

public class LBoxMetadataPlatform implements Serializable, Comparable<LBoxMetadataPlatform> {
    private static final long serialVersionUID = 1L;

    private String name;
    private Boolean emulated;
    private String releaseDate;
    private String developer;
    private String manufacturer;
    private String cpu;
    private String memory;
    private String graphics;
    private String sound;
    private String display;
    private String maxControllers;
    private String media;
    private String notes;
    private String category;
    private Boolean useMameFiles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEmulated() {
        return emulated;
    }

    public void setEmulated(String emulated) {
        if (emulated != null)
            this.emulated = Boolean.parseBoolean(emulated);
    }

    public void setEmulated(Boolean emulated) {
        this.emulated = emulated;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getGraphics() {
        return graphics;
    }

    public void setGraphics(String graphics) {
        this.graphics = graphics;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getMaxControllers() {
        return maxControllers;
    }

    public void setMaxControllers(String maxControllers) {
        this.maxControllers = maxControllers;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getUseMameFiles() {
        return useMameFiles;
    }

    public void setUseMameFiles(String useMameFiles) {
        this.useMameFiles = Boolean.valueOf(useMameFiles);
    }

    public void setUseMameFiles(Boolean useMameFiles) {
        this.useMameFiles = useMameFiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LBoxMetadataPlatform that = (LBoxMetadataPlatform) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  <Platform>\n");
        sb.append("    <Name>").append(this.name).append("</Name>\n");
        sb.append("    <Emulated>").append(this.emulated).append("</Emulated>\n");
        if (this.releaseDate != null)
            sb.append("    <ReleaseDate>").append(this.releaseDate).append("</ReleaseDate>\n");
        if (this.developer != null)
            sb.append("    <Developer>").append(this.developer).append("</Developer>\n");
        if (this.manufacturer != null)
            sb.append("    <Manufacturer>").append(this.manufacturer).append("</Manufacturer>\n");
        if (this.cpu != null)
            sb.append("    <Cpu>").append(this.cpu).append("</Cpu>\n");
        if (this.memory != null)
            sb.append("    <Memory>").append(this.memory).append("</Memory>\n");
        if (this.graphics != null)
            sb.append("    <Graphics>").append(this.graphics).append("</Graphics>\n");
        if (this.sound != null)
            sb.append("    <Sound>").append(this.sound).append("</Sound>\n");
        if (this.display != null)
            sb.append("    <Display>").append(this.display).append("</Display>\n");
        if (this.media != null)
            sb.append("    <Media>").append(this.media).append("</Media>\n");
        if (this.maxControllers != null)
            sb.append("    <MaxControllers>").append(this.maxControllers).append("</MaxControllers>\n");
        if (this.notes != null)
            sb.append("    <Notes>").append(this.notes).append("</Notes>\n");
        if (this.category != null)
            sb.append("    <Category>").append(this.category).append("</Category>\n");
        if (this.useMameFiles != null)
            sb.append("    <UseMameFiles>").append(this.useMameFiles).append("</UseMameFiles>\n");
        sb.append("  </Platform>\n");
        return sb.toString();
    }

    @Override
    public int compareTo(LBoxMetadataPlatform that) {
        if (this.name.compareTo(that.name) < 0) {
            return -1;
        } else if (this.name.compareTo(that.name) > 0) {
            return 1;
        }
        return 0;
    }
}
