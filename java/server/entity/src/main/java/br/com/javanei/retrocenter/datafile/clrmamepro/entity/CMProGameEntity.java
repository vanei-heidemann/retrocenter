package br.com.javanei.retrocenter.datafile.clrmamepro.entity;

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
@Table(name = "CMPRO_GAME", indexes = {
        @Index(name = "CMPRO_GAME_0001", unique = true, columnList = "DATAFILE_ID,NAME")
})
@NamedQueries({
        @NamedQuery(name = "CMProGameEntity.findByDatafileAndName",
                query = "SELECT o from CMProGameEntity o WHERE o.datafile.name = :datafileName AND o.datafile.catalog = :catalog AND o.datafile.version = :version AND o.name = :name")
})
public class CMProGameEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GAME_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 255, nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", length = 255, nullable = true)
    private String description;

    @Column(name = "YEAR", length = 32, nullable = true)
    private String year;

    @Column(name = "MANUFACTURER", length = 255, nullable = true)
    private String manufacturer;

    @Column(name = "CLONEOF", length = 255, nullable = true)
    private String cloneof;

    @Column(name = "ROMOF", length = 255, nullable = true)
    private String romof;

    @Column(name = "SERIAL", length = 64, nullable = true)
    private String serial;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "game")
    private Set<CMProGameRomEntity> roms = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "game")
    private Set<CMProDiskEntity> disks = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "game")
    private Set<CMProSampleofEntity> sampleof = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "game")
    private Set<CMProSampleEntity> samples = new HashSet<>();

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "DATAFILE_ID")
    private CMProDatafileEntity datafile;

    public CMProGameEntity() {
    }

    public CMProGameEntity(String name, String description, String year, String manufacturer, String cloneof,
                           String romof, String serial) {
        this.name = name;
        this.description = description;
        this.year = year;
        this.manufacturer = manufacturer;
        this.cloneof = cloneof;
        this.romof = romof;
        this.serial = serial;
    }

    public CMProGameEntity(Long id) {
        this.id = id;
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

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Set<CMProGameRomEntity> getRoms() {
        return roms;
    }

    public void setRoms(Set<CMProGameRomEntity> roms) {
        this.roms = roms;
    }

    public Set<CMProDiskEntity> getDisks() {
        return disks;
    }

    public void setDisks(Set<CMProDiskEntity> disks) {
        this.disks = disks;
    }

    public Set<CMProSampleofEntity> getSampleof() {
        return sampleof;
    }

    public void setSampleof(Set<CMProSampleofEntity> sampleof) {
        this.sampleof = sampleof;
    }

    public Set<CMProSampleEntity> getSamples() {
        return samples;
    }

    public void setSamples(Set<CMProSampleEntity> samples) {
        this.samples = samples;
    }

    public CMProDatafileEntity getDatafile() {
        return datafile;
    }

    public void setDatafile(CMProDatafileEntity datafile) {
        this.datafile = datafile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CMProGameEntity that = (CMProGameEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
