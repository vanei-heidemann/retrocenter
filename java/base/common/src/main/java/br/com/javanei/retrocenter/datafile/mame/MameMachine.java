package br.com.javanei.retrocenter.datafile.mame;

import br.com.javanei.retrocenter.util.StringUtil;
import br.com.javanei.retrocenter.util.ValidValuesUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class MameMachine implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String sourcefile;
    private String isbios; // (yes|no) "no"
    private String isdevice; // (yes|no) "no"
    private String ismechanical; // (yes|no) "no"
    private String runnable; // (yes|no) "yes"
    private String cloneof;
    private String romof;
    private String sampleof;

    private String description;
    private String year;
    private String manufacturer;

    private Set<MameBiosset> biossets = new HashSet<>();
    private List<MameRom> roms = new LinkedList<>();
    private Set<MameDisk> disks = new HashSet<>();
    // Há duplicados
    private List<MameDeviceref> devicerefs = new LinkedList<>();
    // Há duplicados
    private List<MameSample> samples = new LinkedList<>();
    private Set<MameChip> chips = new HashSet<>();
    private Set<MameDisplay> displays = new HashSet<>();
    private MameSound sound;
    private MameInput input;
    // Há duplicado.
    private List<MameDipswitch> dipswitches = new ArrayList<>();
    private Set<MameConfiguration> configurations = new HashSet<>();
    // Há duplicado!
    private List<MamePort> ports = new ArrayList<>();
    private Set<MameAdjuster> adjusters = new HashSet<>();
    private MameDriver driver;
    private Set<MameDevice> devices = new HashSet<>();
    private Set<MameSlot> slots = new HashSet<>();
    private Set<MameSoftwarelist> softwarelists = new HashSet<>();
    private Set<MameRamoption> ramoptions = new HashSet<>();

    public MameMachine() {
    }

    public MameMachine(String name, String sourcefile, String isbios, String isdevice, String ismechanical,
                       String runnable, String cloneof, String romof, String sampleof, String description, String year,
                       String manufacturer) {
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

    public String getIsdevice() {
        return isdevice;
    }

    public void setIsdevice(String isdevice) {
        this.isdevice = ValidValuesUtil.validateValue(isdevice, ValidValuesUtil.YES_NO);
    }

    public String getIsmechanical() {
        return ismechanical;
    }

    public void setIsmechanical(String ismechanical) {
        this.ismechanical = ValidValuesUtil.validateValue(ismechanical, ValidValuesUtil.YES_NO);
    }

    public String getRunnable() {
        return runnable;
    }

    public void setRunnable(String runnable) {
        this.runnable = ValidValuesUtil.validateValue(runnable, ValidValuesUtil.YES_NO);
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

    public Set<MameBiosset> getBiossets() {
        return biossets;
    }

    public void setBiossets(Set<MameBiosset> biossets) {
        this.biossets = biossets;
    }

    public boolean addBiosset(MameBiosset biosset) {
        return this.biossets.add(biosset);
    }

    public List<MameRom> getRoms() {
        return roms;
    }

    public void setRoms(List<MameRom> roms) {
        this.roms = roms;
    }

    public void addRom(MameRom rom) {
        this.roms.add(rom);
    }

    public Set<MameDisk> getDisks() {
        return disks;
    }

    public void setDisks(Set<MameDisk> disks) {
        this.disks = disks;
    }

    public boolean addDisk(MameDisk disk) {
        return this.disks.add(disk);
    }

    public List<MameDeviceref> getDevicerefs() {
        return devicerefs;
    }

    public void setDevicerefs(List<MameDeviceref> devicerefs) {
        this.devicerefs = devicerefs;
    }

    public void addDeviceref(MameDeviceref deviceref) {
        this.devicerefs.add(deviceref);
    }

    public List<MameSample> getSamples() {
        return samples;
    }

    public void setSamples(List<MameSample> samples) {
        this.samples = samples;
    }

    public void addSample(MameSample sample) {
        this.samples.add(sample);
    }

    public Set<MameChip> getChips() {
        return chips;
    }

    public void setChips(Set<MameChip> chips) {
        this.chips = chips;
    }

    public boolean addChip(MameChip chip) {
        return this.chips.add(chip);
    }

    public Set<MameDisplay> getDisplays() {
        return displays;
    }

    public void setDisplays(Set<MameDisplay> displays) {
        this.displays = displays;
    }

    public boolean addDisplay(MameDisplay display) {
        return this.displays.add(display);
    }

    public MameSound getSound() {
        return sound;
    }

    public void setSound(MameSound sound) {
        this.sound = sound;
    }

    public MameInput getInput() {
        return input;
    }

    public void setInput(MameInput input) {
        this.input = input;
    }

    public List<MameDipswitch> getDipswitches() {
        return dipswitches;
    }

    public void setDipswitches(List<MameDipswitch> dipswitches) {
        this.dipswitches = dipswitches;
    }

    public void addDipswitch(MameDipswitch dipswitch) {
        this.dipswitches.add(dipswitch);
    }

    public Set<MameConfiguration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(Set<MameConfiguration> configurations) {
        this.configurations = configurations;
    }

    public boolean addConfiguration(MameConfiguration configuration) {
        return this.configurations.add(configuration);
    }

    public List<MamePort> getPorts() {
        return ports;
    }

    public void setPorts(List<MamePort> ports) {
        this.ports = ports;
    }

    public void addPort(MamePort port) {
        this.ports.add(port);
    }

    public Set<MameAdjuster> getAdjusters() {
        return adjusters;
    }

    public void setAdjusters(Set<MameAdjuster> adjusters) {
        this.adjusters = adjusters;
    }

    public boolean addAdjuster(MameAdjuster adjuster) {
        return this.adjusters.add(adjuster);
    }

    public MameDriver getDriver() {
        return driver;
    }

    public void setDriver(MameDriver driver) {
        this.driver = driver;
    }

    public Set<MameDevice> getDevices() {
        return devices;
    }

    public void setDevices(Set<MameDevice> devices) {
        this.devices = devices;
    }

    public boolean addDevice(MameDevice device) {
        return this.devices.add(device);
    }

    public Set<MameSlot> getSlots() {
        return slots;
    }

    public void setSlots(Set<MameSlot> slots) {
        this.slots = slots;
    }

    public boolean addSlot(MameSlot slot) {
        return this.slots.add(slot);
    }

    public Set<MameSoftwarelist> getSoftwarelists() {
        return softwarelists;
    }

    public void setSoftwarelists(Set<MameSoftwarelist> softwarelists) {
        this.softwarelists = softwarelists;
    }

    public boolean addSoftwarelist(MameSoftwarelist softwarelist) {
        return this.softwarelists.add(softwarelist);
    }

    public Set<MameRamoption> getRamoptions() {
        return ramoptions;
    }

    public void setRamoptions(Set<MameRamoption> ramoptions) {
        this.ramoptions = ramoptions;
    }

    public boolean addRamoption(MameRamoption ramoption) {
        return this.ramoptions.add(ramoption);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameMachine that = (MameMachine) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t<machine");
        if (this.name != null) {
            sb.append(" name=\"").append(this.name).append("\"");
        }
        if (this.sourcefile != null) {
            sb.append(" sourcefile=\"").append(this.sourcefile).append("\"");
        }
        if (this.isbios != null && !this.isbios.equals("no")) {
            sb.append(" isbios=\"").append(this.isbios).append("\"");
        }
        if (this.isdevice != null && !this.isdevice.equals("no")) {
            sb.append(" isdevice=\"").append(this.isdevice).append("\"");
        }
        if (this.ismechanical != null && !this.ismechanical.equals("no")) {
            sb.append(" ismechanical=\"").append(this.ismechanical).append("\"");
        }
        if (this.runnable != null && !this.runnable.equals("yes")) {
            sb.append(" runnable=\"").append(this.runnable).append("\"");
        }
        if (this.cloneof != null) {
            sb.append(" cloneof=\"").append(this.cloneof).append("\"");
        }
        if (this.romof != null) {
            sb.append(" romof=\"").append(this.romof).append("\"");
        }
        if (this.sampleof != null) {
            sb.append(" sampleof=\"").append(this.sampleof).append("\"");
        }
        sb.append(">").append(StringUtil.LINE_SEPARATOR);
        if (this.description != null) {
            sb.append("\t\t<description>").append(StringUtil.escapeXMLEntities(this.description)).append("</description>").append(StringUtil.LINE_SEPARATOR);
        }
        if (this.year != null) {
            sb.append("\t\t<year>").append(this.year).append("</year>").append(StringUtil.LINE_SEPARATOR);
        }
        if (this.manufacturer != null) {
            sb.append("\t\t<manufacturer>").append(StringUtil.escapeXMLEntities(this.manufacturer)).append("</manufacturer>").append(StringUtil.LINE_SEPARATOR);
        }

        for (MameBiosset biosset : this.biossets) {
            sb.append(biosset.toString());
        }
        for (MameRom rom : this.roms) {
            sb.append(rom.toString());
        }
        for (MameDisk disk : disks) {
            sb.append(disk.toString());
        }
        for (MameDeviceref deviceref : this.devicerefs) {
            sb.append(deviceref.toString());
        }
        for (MameSample sample : this.samples) {
            sb.append(sample.toString());
        }
        for (MameChip chip : this.chips) {
            sb.append(chip.toString());
        }
        for (MameDisplay display : this.displays) {
            sb.append(display.toString());
        }
        if (sound != null) {
            sb.append(sound.toString());
        }
        if (input != null) {
            sb.append(input.toString());
        }
        for (MameDipswitch dipswitch : this.dipswitches) {
            sb.append(dipswitch.toString());
        }
        for (MameConfiguration configuration : this.configurations) {
            sb.append(configuration.toString());
        }
        for (MamePort port : this.ports) {
            sb.append(port.toString());
        }
        for (MameAdjuster adjuster : this.adjusters) {
            sb.append(adjuster.toString());
        }
        if (driver != null) {
            sb.append(driver.toString());
        }
        for (MameDevice device : this.devices) {
            sb.append(device.toString());
        }
        for (MameSlot slot : this.slots) {
            sb.append(slot.toString());
        }
        for (MameSoftwarelist softwarelist : this.softwarelists) {
            sb.append(softwarelist.toString());
        }
        for (MameRamoption ramoption : this.ramoptions) {
            sb.append(ramoption.toString());
        }
        sb.append("\t</machine>").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
