package br.com.javanei.retrocenter.datafile.logiqx.entity;

import br.com.javanei.retrocenter.datafile.logiqx.LogiqxGame;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "LOGIQX_GAME", indexes = {
        @Index(name = "LOGIQX_GAME_0001", unique = true, columnList = "DATAFILE_ID,NAME")
})
@NamedQueries({
        @NamedQuery(name = "LogiqxGameEntity.findByDatafileAndName",
                query = "SELECT o from LogiqxGameEntity o WHERE o.datafile.name = :datafileName AND o.datafile.catalog = :catalog AND o.datafile.version = :version AND o.name = :name")
})
public class LogiqxGameEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GAME_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 255, nullable = false)
    private String name;

    @Column(name = "SOURCEFILE", length = 48, nullable = true)
    private String sourcefile;

    @Column(name = "ISBIOS", length = 3, nullable = true)
    private String isbios = "no";

    @Column(name = "CLONEOF", length = 16, nullable = true)
    private String cloneof;

    @Column(name = "ROMOF", length = 16, nullable = true)
    private String romof;

    @Column(name = "SAMPLEOF", length = 32, nullable = true)
    private String sampleof;

    @Column(name = "BOARD", length = 64, nullable = true)
    private String board;

    @Column(name = "REBUILDTO", length = 64, nullable = true)
    private String rebuildto;

    @Column(name = "COMMENT", length = 255, nullable = true)
    private String comment;

    @Column(name = "DESCRIPTION", length = 255, nullable = true)
    private String description;

    @Column(name = "YEAR", length = 8, nullable = true)
    private String year;

    @Column(name = "MANUFACTURER", length = 80, nullable = true)
    private String manufacturer;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "DATAFILE_ID")
    private LogiqxDatafileEntity datafile;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "game")
    private Set<LogiqxReleaseEntity> releases = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "game")
    private Set<LogiqxBiossetEntity> biossets = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "game")
    private Set<LogiqxRomEntity> roms = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "game")
    private Set<LogiqxDiskEntity> disks = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "game")
    private Set<LogiqxSampleEntity> samples = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "game")
    private Set<LogiqxArchiveEntity> archives = new HashSet<>();

    public LogiqxGameEntity() {
    }

    public LogiqxGameEntity(LogiqxGame game) {
        this(game.getName(), game.getSourcefile(), game.getIsbios(), game.getCloneof(), game.getRomof(),
                game.getSampleof(), game.getBoard(), game.getRebuildto(), game.getComment(), game.getDescription(),
                game.getYear(), game.getManufacturer());
    }

    public LogiqxGameEntity(Long id) {
        this.id = id;
    }

    public LogiqxGameEntity(String name) {
        this.name = name;
    }

    public LogiqxGameEntity(String name, String sourcefile, String isbios, String cloneof, String romof,
                            String sampleof, String board, String rebuildto, String comment, String description,
                            String year, String manufacturer) {
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

    public LogiqxGameEntity(Long id, String name, String sourcefile, String isbios, String cloneof, String romof,
                            String sampleof, String board, String rebuildto, String comment, String description,
                            String year, String manufacturer) {
        this.id = id;
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
        this.isbios = isbios;
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

    public LogiqxDatafileEntity getDatafile() {
        return datafile;
    }

    public void setDatafile(LogiqxDatafileEntity datafile) {
        this.datafile = datafile;
    }

    public Set<LogiqxReleaseEntity> getReleases() {
        return releases;
    }

    public void setReleases(Set<LogiqxReleaseEntity> releases) {
        this.releases = releases;
    }

    public Set<LogiqxBiossetEntity> getBiossets() {
        return biossets;
    }

    public void setBiossets(Set<LogiqxBiossetEntity> biossets) {
        this.biossets = biossets;
    }

    public Set<LogiqxRomEntity> getRoms() {
        return roms;
    }

    public void setRoms(Set<LogiqxRomEntity> roms) {
        this.roms = roms;
    }

    public Set<LogiqxDiskEntity> getDisks() {
        return disks;
    }

    public void setDisks(Set<LogiqxDiskEntity> disks) {
        this.disks = disks;
    }

    public Set<LogiqxSampleEntity> getSamples() {
        return samples;
    }

    public void setSamples(Set<LogiqxSampleEntity> samples) {
        this.samples = samples;
    }

    public Set<LogiqxArchiveEntity> getArchives() {
        return archives;
    }

    public void setArchives(Set<LogiqxArchiveEntity> archives) {
        this.archives = archives;
    }

    public LogiqxGame toVO() {
        LogiqxGame game = new LogiqxGame(this.name, this.sourcefile, this.isbios, this.cloneof, this.romof, this.sampleof,
                this.board, this.rebuildto, this.comment, this.description, this.year, this.manufacturer);

        for (LogiqxReleaseEntity release : this.releases) {
            game.addRelease(release.toVO());
        }

        for (LogiqxBiossetEntity biosset : this.biossets) {
            game.addBiosset(biosset.toVO());
        }

        for (LogiqxRomEntity rom : this.roms) {
            game.addRom(rom.toVO());
        }

        for (LogiqxDiskEntity disk : this.disks) {
            game.addDisk(disk.toVO());
        }

        for (LogiqxSampleEntity sample : this.samples) {
            game.addSample(sample.toVO());
        }

        for (LogiqxArchiveEntity archive : this.archives) {
            game.addArchive(archive.toVO());
        }

        return game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogiqxGameEntity that = (LogiqxGameEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
