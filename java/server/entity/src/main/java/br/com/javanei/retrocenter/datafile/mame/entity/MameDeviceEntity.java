package br.com.javanei.retrocenter.datafile.mame.entity;

import br.com.javanei.retrocenter.datafile.mame.MameDevice;
import br.com.javanei.retrocenter.datafile.mame.MameDeviceExtension;
import br.com.javanei.retrocenter.datafile.mame.MameDeviceInstance;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "MAME_DEVICE", indexes = {
        @Index(name = "MAME_DEVICE_0001", unique = true, columnList = "MACHINE_ID,TYPE,TAG")
})
public class MameDeviceEntity implements Serializable, Comparable<MameDeviceEntity> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAME_DEVICE_ID", nullable = false)
    private Long id;

    @Column(name = "TYPE", length = 16, nullable = false)
    private String type;

    @Column(name = "TAG", length = 128, nullable = false)
    private String tag;

    @Column(name = "FIXED_IMAGE", nullable = true)
    private Integer fixed_image;

    @Column(name = "MANDATORY", nullable = true)
    private Integer mandatory;

    @Column(name = "INTERFACE", length = 20, nullable = true)
    private String _interface;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "device")
    private Set<MameDeviceInstanceEntity> instances = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "device")
    private Set<MameDeviceExtensionEntity> extensions = new LinkedHashSet<>();

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "MACHINE_ID")
    private MameMachineEntity machine;

    public MameDeviceEntity(String type, String tag, Integer fixed_image, Integer mandatory, String _interface) {
        this.type = type;
        this.tag = tag;
        this.fixed_image = fixed_image;
        this.mandatory = mandatory;
        this._interface = _interface;
    }

    public MameDeviceEntity() {
    }

    public MameDeviceEntity(MameDevice device) {
        this(device.getType(), device.getTag(), device.getFixed_image(), device.getMandatory(), device.getInterface());
        for (MameDeviceInstance instance : device.getInstances()) {
            MameDeviceInstanceEntity e = new MameDeviceInstanceEntity(instance);
            e.setDevice(this);
            this.instances.add(e);
        }
        for (MameDeviceExtension extension : device.getExtensions()) {
            MameDeviceExtensionEntity e = new MameDeviceExtensionEntity(extension);
            e.setDevice(this);
            this.extensions.add(e);
        }
    }

    public MameDevice toVO() {
        MameDevice device = new MameDevice(this.type, this.tag, this.fixed_image, this.mandatory, this._interface);
        for (MameDeviceInstanceEntity instanceEntity : this.instances) {
            device.addInstance(instanceEntity.toVO());
        }
        for (MameDeviceExtensionEntity extensionEntity : this.extensions) {
            device.addExtension(extensionEntity.toVO());
        }
        return device;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getFixed_image() {
        return fixed_image;
    }

    public void setFixed_image(Integer fixed_image) {
        this.fixed_image = fixed_image;
    }

    public Integer getMandatory() {
        return mandatory;
    }

    public void setMandatory(Integer mandatory) {
        this.mandatory = mandatory;
    }

    public String getInterface() {
        return _interface;
    }

    public void setInterface(String _interface) {
        this._interface = _interface;
    }

    public Set<MameDeviceInstanceEntity> getInstances() {
        return instances;
    }

    public void setInstances(Set<MameDeviceInstanceEntity> instances) {
        this.instances = instances;
    }

    public Set<MameDeviceExtensionEntity> getExtensions() {
        return extensions;
    }

    public void setExtensions(Set<MameDeviceExtensionEntity> extensions) {
        this.extensions = extensions;
    }

    public MameMachineEntity getMachine() {
        return machine;
    }

    public void setMachine(MameMachineEntity machine) {
        this.machine = machine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameDeviceEntity entity = (MameDeviceEntity) o;
        return Objects.equals(type, entity.type) &&
                Objects.equals(tag, entity.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, tag);
    }

    @Override
    public int compareTo(MameDeviceEntity o) {
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
            i = this.type.compareTo(o.type);
        if (i == 0) {
            i = this.tag.compareTo(o.tag);
        }
        return i;
    }
}
