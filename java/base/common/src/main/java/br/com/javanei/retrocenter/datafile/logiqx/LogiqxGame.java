package br.com.javanei.retrocenter.datafile.logiqx;

import br.com.javanei.retrocenter.common.DuplicatedItemException;
import br.com.javanei.retrocenter.util.StringUtil;
import br.com.javanei.retrocenter.util.ValidValuesUtil;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * <!ELEMENT game (comment*, description, year?, manufacturer?, release*, biosset*, rom*, disk*, sample*, archive*)>
 */
public class LogiqxGame implements Serializable {
    private static final long serialVersionUID = 1L;

    // ////////// ATTLIST

    /**
     * <!ATTLIST game name CDATA #REQUIRED>
     */
    private String name;

    /**
     * <!ATTLIST game sourcefile CDATA #IMPLIED>
     */
    private String sourcefile;

    /**
     * <!ATTLIST game isbios (yes|no) "no">
     */
    private String isbios = "no";

    /**
     * <!ATTLIST game cloneof CDATA #IMPLIED>
     */
    private String cloneof;

    /**
     * <!ATTLIST game romof CDATA #IMPLIED>
     */
    private String romof;

    /**
     * <!ATTLIST game sampleof CDATA #IMPLIED>
     */
    private String sampleof;

    /**
     * <!ATTLIST game board CDATA #IMPLIED>
     */
    private String board;

    /**
     * <!ATTLIST game rebuildto CDATA #IMPLIED>
     */
    private String rebuildto;

    // ////////// ELEMENT

    /**
     * <!ELEMENT comment (#PCDATA)>
     */
    private String comment;

    /**
     * <!ELEMENT description (#PCDATA)>
     */
    private String description;

    /**
     * <!ELEMENT year (#PCDATA)>
     */
    private String year;

    /**
     * <!ELEMENT manufacturer (#PCDATA)>
     */
    private String manufacturer;

    //

    private Set<LogiqxRelease> releases = new HashSet<>();

    private Set<LogiqxBiosset> biossets = new HashSet<>();

    private Set<LogiqxRom> roms = new HashSet<>();

    private Set<LogiqxDisk> disks = new HashSet<>();

    private Set<LogiqxSample> samples = new HashSet<>();

    private Set<LogiqxArchive> archives = new HashSet<>();

    public LogiqxGame() {
    }

    public LogiqxGame(String name, String sourcefile, String isbios, String cloneof, String romof, String sampleof,
                      String board, String rebuildto, String comment, String description, String year, String manufacturer) {
        this.name = name;
        this.sourcefile = sourcefile;
        this.isbios = isbios;
        this.cloneof = cloneof;
        this.romof = romof;
        this.sampleof = sampleof;
        this.board = board;
        this.rebuildto = rebuildto;
        this.comment = comment;
        this.description = description;
        this.year = year;
        this.manufacturer = manufacturer;
    }

    private static void appendTagIfNotNull(StringBuilder sb, String name, Object value) {
        if (value != null)
            sb.append("\t\t<").append(name).append(">").append(StringUtil.escapeXMLEntities(value.toString())).append("</").append(name).append(">\n");
    }

    private static void appendAttributeIfNotNull(StringBuilder sb, String name, Object value) {
        if (value != null)
            sb.append(" ").append(name).append("=\"").append(StringUtil.escapeXMLEntities(value.toString())).append("\"");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSourcefile() {
        return sourcefile;
    }

    public void setSourcefile(String sourcefile) {
        this.sourcefile = sourcefile;
    }

    public String getIsbios() {
        return isbios;
    }

    public void setIsbios(String isbios) {
        this.isbios = ValidValuesUtil.validateValue(isbios, ValidValuesUtil.YES_NO);
    }

    public String getCloneof() {
        return cloneof;
    }

    public void setCloneof(String cloneof) {
        this.cloneof = cloneof;
    }

    public String getRomof() {
        return romof;
    }

    public void setRomof(String romof) {
        this.romof = romof;
    }

    public String getSampleof() {
        return sampleof;
    }

    public void setSampleof(String sampleof) {
        this.sampleof = sampleof;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getRebuildto() {
        return rebuildto;
    }

    public void setRebuildto(String rebuildto) {
        this.rebuildto = rebuildto;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Set<LogiqxRelease> getReleases() {
        return releases;
    }

    public void setReleases(Set<LogiqxRelease> releases) {
        this.releases = releases;
    }

    public void addRelease(LogiqxRelease release) {
        this.releases.add(release);
    }

    public Set<LogiqxBiosset> getBiossets() {
        return biossets;
    }

    public void setBiossets(Set<LogiqxBiosset> biossets) {
        this.biossets = biossets;
    }

    public void addBiosset(LogiqxBiosset biosset) {
        this.biossets.add(biosset);
    }

    public Set<LogiqxRom> getRoms() {
        return roms;
    }

    public void setRoms(Set<LogiqxRom> roms) {
        this.roms = roms;
    }

    public void addRom(LogiqxRom rom) {
        if (this.roms.contains(rom)) {
            throw new DuplicatedItemException("rom (" + rom.getName() + ")");
        }
        this.roms.add(rom);
    }

    public Set<LogiqxDisk> getDisks() {
        return disks;
    }

    public void setDisks(Set<LogiqxDisk> disks) {
        this.disks = disks;
    }

    public void addDisk(LogiqxDisk disk) {
        this.disks.add(disk);
    }

    public Set<LogiqxSample> getSamples() {
        return samples;
    }

    public void setSamples(Set<LogiqxSample> samples) {
        this.samples = samples;
    }

    public void addSample(LogiqxSample sample) {
        this.samples.add(sample);
    }

    public Set<LogiqxArchive> getArchives() {
        return archives;
    }

    public void setArchives(Set<LogiqxArchive> archives) {
        this.archives = archives;
    }

    public void addArchive(LogiqxArchive archive) {
        this.archives.add(archive);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LogiqxGame game = (LogiqxGame) o;
        return Objects.equals(name, game.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t<game");
        appendAttributeIfNotNull(sb, "name", this.name);
        appendAttributeIfNotNull(sb, "sourcefile", this.sourcefile);
        appendAttributeIfNotNull(sb, "isbios", this.isbios);
        appendAttributeIfNotNull(sb, "cloneof", this.cloneof);
        appendAttributeIfNotNull(sb, "romof", this.romof);
        appendAttributeIfNotNull(sb, "sampleof", this.sampleof);
        appendAttributeIfNotNull(sb, "board", this.board);
        appendAttributeIfNotNull(sb, "rebuildto", this.rebuildto);
        sb.append(">\n");
        appendTagIfNotNull(sb, "comment", comment);
        appendTagIfNotNull(sb, "description", description);
        appendTagIfNotNull(sb, "year", year);
        appendTagIfNotNull(sb, "manufacturer", manufacturer);
        for (LogiqxRelease release : this.releases) {
            sb.append(release.toString());
        }
        for (LogiqxBiosset biosset : this.biossets) {
            sb.append(biosset.toString());
        }
        for (LogiqxRom rom : this.roms) {
            sb.append(rom.toString());
        }
        for (LogiqxDisk disk : this.disks) {
            sb.append(disk.toString());
        }
        for (LogiqxSample sample : this.samples) {
            sb.append(sample.toString());
        }
        for (LogiqxArchive archive : this.archives) {
            sb.append(archive.toString());
        }
        sb.append("\t</game>\n");
        return sb.toString();
    }
}
