package br.com.javanei.retrocenter.datafile.mame.entity;

import br.com.javanei.retrocenter.datafile.mame.MameAdjuster;
import br.com.javanei.retrocenter.datafile.mame.MameBiosset;
import br.com.javanei.retrocenter.datafile.mame.MameChip;
import br.com.javanei.retrocenter.datafile.mame.MameConfiguration;
import br.com.javanei.retrocenter.datafile.mame.MameDevice;
import br.com.javanei.retrocenter.datafile.mame.MameDeviceref;
import br.com.javanei.retrocenter.datafile.mame.MameDipswitch;
import br.com.javanei.retrocenter.datafile.mame.MameDisk;
import br.com.javanei.retrocenter.datafile.mame.MameDisplay;
import br.com.javanei.retrocenter.datafile.mame.MameMachine;
import br.com.javanei.retrocenter.datafile.mame.MamePort;
import br.com.javanei.retrocenter.datafile.mame.MameRamoption;
import br.com.javanei.retrocenter.datafile.mame.MameRom;
import br.com.javanei.retrocenter.datafile.mame.MameSample;
import br.com.javanei.retrocenter.datafile.mame.MameSlot;
import br.com.javanei.retrocenter.datafile.mame.MameSoftwarelist;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "MAME_MACHINE", indexes = {
        @Index(name = "MAME_MACHINE_0001", unique = true, columnList = "MAME_ID,NAME")
})
@NamedQueries({
        @NamedQuery(name = "MameMachineEntity.findByBuild", query = "SELECT o from MameMachineEntity o WHERE o.mame.build = :build"),
        @NamedQuery(name = "MameMachineEntity.findByBuildAndName", query = "SELECT o from MameMachineEntity o WHERE o.mame.build = :build AND o.name = :name")
})
public class MameMachineEntity implements Serializable, Comparable<MameMachineEntity> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAME_MACHINE_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 64, nullable = false)
    private String name;

    @Column(name = "SOURCEFILE", length = 48, nullable = false)
    private String sourcefile;

    @Column(name = "ISBIOS", length = 3, nullable = true)
    private String isbios;

    @Column(name = "ISDEVICE", length = 3, nullable = true)
    private String isdevice;

    @Column(name = "ISMECHANICAL", length = 3, nullable = true)
    private String ismechanical;

    @Column(name = "RUNNABLE", length = 3, nullable = true)
    private String runnable;

    @Column(name = "CLONEOF", length = 16, nullable = true)
    private String cloneof;

    @Column(name = "ROMOF", length = 16, nullable = true)
    private String romof;

    @Column(name = "SAMPLEOF", length = 32, nullable = true)
    private String sampleof;

    @Column(name = "DESCRIPTION", length = 160, nullable = true)
    private String description;

    @Column(name = "YEAR", length = 8, nullable = true)
    private String year;

    @Column(name = "MANUFACTURER", length = 80, nullable = true)
    private String manufacturer;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "machine")
    private Set<MameBiossetEntity> biossets = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "machine")
    private List<MameRomEntity> roms = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "machine")
    private Set<MameDiskEntity> disks = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "machine")
    private List<MameDevicerefEntity> devicerefs = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "machine")
    private List<MameSampleEntity> samples = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "machine")
    private Set<MameChipEntity> chips = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "machine")
    private Set<MameDisplayEntity> displays = new LinkedHashSet<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "machine", optional = true)
    private MameSoundEntity sound;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "machine", optional = true)
    private MameInputEntity input;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "machine")
    private List<MameDipswitchEntity> dipswitches = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "machine")
    private Set<MameConfigurationEntity> configurations = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "machine")
    private List<MamePortEntity> ports = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "machine")
    private Set<MameAdjusterEntity> adjusters = new LinkedHashSet<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "machine", optional = true)
    private MameDriverEntity driver;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "machine")
    private Set<MameDeviceEntity> devices = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "machine")
    private Set<MameSlotEntity> slots = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "machine")
    private Set<MameSoftwarelistEntity> softwarelists = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "machine")
    private Set<MameRamoptionEntity> ramoptions = new LinkedHashSet<>();

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "MAME_ID")
    private MameEntity mame;

    public MameMachineEntity() {
    }

    public MameMachineEntity(String name, String sourcefile, String isbios, String isdevice,
                             String ismechanical, String runnable, String cloneof, String romof, String sampleof,
                             String description, String year, String manufacturer) {
        this.name = name;
        this.sourcefile = sourcefile;
        this.isbios = isbios;
        this.isdevice = isdevice;
        this.ismechanical = ismechanical;
        this.runnable = runnable;
        this.cloneof = cloneof;
        this.romof = romof;
        this.sampleof = sampleof;
        this.description = description;
        this.year = year;
        this.manufacturer = manufacturer;
    }

    public MameMachineEntity(MameMachine machine) {
        this(machine.getName(), machine.getSourcefile(), machine.getIsbios(), machine.getIsdevice(),
                machine.getIsmechanical(), machine.getRunnable(), machine.getCloneof(), machine.getRomof(),
                machine.getSampleof(), machine.getDescription(), machine.getYear(), machine.getManufacturer());
        if (machine.getSound() != null) {
            this.sound = new MameSoundEntity(machine.getSound());
            this.sound.setMachine(this);
        }
        if (machine.getInput() != null) {
            this.input = new MameInputEntity(machine.getInput());
            this.input.setMachine(this);
        }
        if (machine.getDriver() != null) {
            this.driver = new MameDriverEntity(machine.getDriver());
            this.driver.setMachine(this);
        }
        for (MameBiosset biosset : machine.getBiossets()) {
            MameBiossetEntity e = new MameBiossetEntity(biosset);
            e.setMachine(this);
            this.biossets.add(e);
        }
        for (MameRom rom : machine.getRoms()) {
            MameRomEntity e = new MameRomEntity(rom);
            this.roms.add(e);
            e.setMachine(this);
        }
        for (MameDisk disk : machine.getDisks()) {
            MameDiskEntity e = new MameDiskEntity(disk);
            e.setMachine(this);
            this.disks.add(e);
        }
        for (MameDeviceref deviceref : machine.getDevicerefs()) {
            MameDevicerefEntity e = new MameDevicerefEntity(deviceref);
            e.setMachine(this);
            this.devicerefs.add(e);
        }
        for (MameSample sample : machine.getSamples()) {
            MameSampleEntity e = new MameSampleEntity(sample.getName());
            e.setMachine(this);
            this.samples.add(e);
        }
        for (MameChip chip : machine.getChips()) {
            MameChipEntity e = new MameChipEntity(chip);
            e.setMachine(this);
            this.chips.add(e);
        }
        for (MameDisplay display : machine.getDisplays()) {
            MameDisplayEntity e = new MameDisplayEntity(display);
            e.setMachine(this);
            this.displays.add(e);
        }
        for (MameDipswitch dipswitch : machine.getDipswitches()) {
            MameDipswitchEntity e = new MameDipswitchEntity(dipswitch);
            e.setMachine(this);
            this.dipswitches.add(e);
        }
        for (MameConfiguration configuration : machine.getConfigurations()) {
            MameConfigurationEntity e = new MameConfigurationEntity(configuration);
            e.setMachine(this);
            this.configurations.add(e);
        }
        for (MamePort port : machine.getPorts()) {
            MamePortEntity e = new MamePortEntity(port);
            e.setMachine(this);
            this.ports.add(e);
        }
        for (MameAdjuster adjuster : machine.getAdjusters()) {
            MameAdjusterEntity e = new MameAdjusterEntity(adjuster);
            e.setMachine(this);
            this.adjusters.add(e);
        }
        for (MameDevice device : machine.getDevices()) {
            MameDeviceEntity e = new MameDeviceEntity(device);
            e.setMachine(this);
            this.devices.add(e);
        }
        for (MameSlot slot : machine.getSlots()) {
            MameSlotEntity e = new MameSlotEntity(slot);
            e.setMachine(this);
            this.slots.add(e);
        }
        for (MameSoftwarelist softwarelist : machine.getSoftwarelists()) {
            MameSoftwarelistEntity e = new MameSoftwarelistEntity(softwarelist);
            e.setMachine(this);
            this.softwarelists.add(e);
        }
        for (MameRamoption ramoption : machine.getRamoptions()) {
            MameRamoptionEntity e = new MameRamoptionEntity(ramoption);
            e.setMachine(this);
            this.ramoptions.add(e);
        }
    }

    public MameMachine toVO() {
        MameMachine machine = new MameMachine(this.name, this.sourcefile, this.isbios, this.isdevice, this.ismechanical,
                this.runnable, this.cloneof, this.romof, this.sampleof, this.description, this.year, this.manufacturer);
        if (this.sound != null) {
            machine.setSound(this.sound.toVO());
        }
        if (this.input != null) {
            machine.setInput(this.input.toVO());
        }
        if (this.driver != null) {
            machine.setDriver(this.driver.toVO());
        }
        for (MameBiossetEntity biossetEntity : this.biossets) {
            machine.addBiosset(biossetEntity.toVO());
        }
        for (MameRomEntity romEntity : this.roms) {
            machine.addRom(romEntity.toVO());
        }
        for (MameDiskEntity diskEntity : this.disks) {
            machine.addDisk(diskEntity.toVO());
        }
        for (MameDevicerefEntity devicerefEntity : this.devicerefs) {
            machine.addDeviceref(devicerefEntity.toVO());
        }
        for (MameSampleEntity sampleEntity : this.samples) {
            machine.addSample(sampleEntity.toVO());
        }
        for (MameChipEntity chipEntity : this.chips) {
            machine.addChip(chipEntity.toVO());
        }
        for (MameDisplayEntity displayEntity : this.displays) {
            machine.addDisplay(displayEntity.toVO());
        }
        for (MameDipswitchEntity dipswitchEntity : this.dipswitches) {
            machine.addDipswitch(dipswitchEntity.toVO());
        }
        for (MameConfigurationEntity configurationEntity : this.configurations) {
            machine.addConfiguration(configurationEntity.toVO());
        }
        for (MamePortEntity entity : this.ports) {
            machine.addPort(entity.toVO());
        }
        for (MameAdjusterEntity adjusterEntity : this.adjusters) {
            machine.addAdjuster(adjusterEntity.toVO());
        }
        for (MameDeviceEntity deviceEntity : this.devices) {
            machine.addDevice(deviceEntity.toVO());
        }
        for (MameSlotEntity entity : this.slots) {
            machine.addSlot(entity.toVO());
        }
        for (MameSoftwarelistEntity entity : this.softwarelists) {
            machine.addSoftwarelist(entity.toVO());
        }
        for (MameRamoptionEntity entity : this.ramoptions) {
            machine.addRamoption(entity.toVO());
        }

        return machine;
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

    public String getIsdevice() {
        return isdevice;
    }

    public void setIsdevice(String isdevice) {
        this.isdevice = isdevice;
    }

    public String getIsmechanical() {
        return ismechanical;
    }

    public void setIsmechanical(String ismechanical) {
        this.ismechanical = ismechanical;
    }

    public String getRunnable() {
        return runnable;
    }

    public void setRunnable(String runnable) {
        this.runnable = runnable;
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

    public Set<MameBiossetEntity> getBiossets() {
        return biossets;
    }

    public void setBiossets(Set<MameBiossetEntity> biossets) {
        this.biossets = biossets;
    }

    public List<MameRomEntity> getRoms() {
        return roms;
    }

    public void setRoms(List<MameRomEntity> roms) {
        this.roms = roms;
    }

    public Set<MameDiskEntity> getDisks() {
        return disks;
    }

    public void setDisks(Set<MameDiskEntity> disks) {
        this.disks = disks;
    }

    public List<MameDevicerefEntity> getDevicerefs() {
        return devicerefs;
    }

    public void setDevicerefs(List<MameDevicerefEntity> devicerefs) {
        this.devicerefs = devicerefs;
    }

    public List<MameSampleEntity> getSamples() {
        return samples;
    }

    public void setSamples(List<MameSampleEntity> samples) {
        this.samples = samples;
    }

    public Set<MameChipEntity> getChips() {
        return chips;
    }

    public void setChips(Set<MameChipEntity> chips) {
        this.chips = chips;
    }

    public Set<MameDisplayEntity> getDisplays() {
        return displays;
    }

    public void setDisplays(Set<MameDisplayEntity> displays) {
        this.displays = displays;
    }

    public MameSoundEntity getSound() {
        return sound;
    }

    public void setSound(MameSoundEntity sound) {
        this.sound = sound;
    }

    public MameInputEntity getInput() {
        return input;
    }

    public void setInput(MameInputEntity input) {
        this.input = input;
    }

    public List<MameDipswitchEntity> getDipswitches() {
        return dipswitches;
    }

    public void setDipswitches(List<MameDipswitchEntity> dipswitches) {
        this.dipswitches = dipswitches;
    }

    public Set<MameConfigurationEntity> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(Set<MameConfigurationEntity> configurations) {
        this.configurations = configurations;
    }

    public List<MamePortEntity> getPorts() {
        return ports;
    }

    public void setPorts(List<MamePortEntity> ports) {
        this.ports = ports;
    }

    public Set<MameAdjusterEntity> getAdjusters() {
        return adjusters;
    }

    public void setAdjusters(Set<MameAdjusterEntity> adjusters) {
        this.adjusters = adjusters;
    }

    public MameDriverEntity getDriver() {
        return driver;
    }

    public void setDriver(MameDriverEntity driver) {
        this.driver = driver;
    }

    public Set<MameDeviceEntity> getDevices() {
        return devices;
    }

    public void setDevices(Set<MameDeviceEntity> devices) {
        this.devices = devices;
    }

    public Set<MameSlotEntity> getSlots() {
        return slots;
    }

    public void setSlots(Set<MameSlotEntity> slots) {
        this.slots = slots;
    }

    public Set<MameSoftwarelistEntity> getSoftwarelists() {
        return softwarelists;
    }

    public void setSoftwarelists(Set<MameSoftwarelistEntity> softwarelists) {
        this.softwarelists = softwarelists;
    }

    public Set<MameRamoptionEntity> getRamoptions() {
        return ramoptions;
    }

    public void setRamoptions(Set<MameRamoptionEntity> ramoptions) {
        this.ramoptions = ramoptions;
    }

    public MameEntity getMame() {
        return mame;
    }

    public void setMame(MameEntity mame) {
        this.mame = mame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameMachineEntity that = (MameMachineEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(MameMachineEntity o) {
        if (this == o)
            return 0;

        int i = 0;
        if (this.id != null && o.id == null)
            i = 1;
        else if (this.id == null && o.id != null)
            i = -1;
        else if (this.id != null)
            i = this.id.compareTo(o.id);
        if (i == 0)
            i = this.name.compareTo(o.name);
        return i;
    }
}
