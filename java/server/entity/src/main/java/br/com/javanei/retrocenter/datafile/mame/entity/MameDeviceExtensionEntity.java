package br.com.javanei.retrocenter.datafile.mame.entity;

import br.com.javanei.retrocenter.datafile.mame.MameDeviceExtension;

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
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "MAME_DEVICEEXTENSION", indexes = {
        @Index(name = "MAME_DEVICEEXTENSION_0001", unique = true, columnList = "DEVICE_ID,NAME")
})
public class MameDeviceExtensionEntity implements Serializable, Comparable<MameDeviceExtensionEntity> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAME_DEVICEEXTENSION_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 8, nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "DEVICE_ID")
    private MameDeviceEntity device;

    public MameDeviceExtensionEntity(String name) {
        this.name = name;
    }

    public MameDeviceExtensionEntity() {
    }

    public MameDeviceExtensionEntity(MameDeviceExtension extension) {
        this(extension.getName());
    }

    public MameDeviceExtension toVO() {
        return new MameDeviceExtension(this.name);
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

    public MameDeviceEntity getDevice() {
        return device;
    }

    public void setDevice(MameDeviceEntity device) {
        this.device = device;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameDeviceExtensionEntity entity = (MameDeviceExtensionEntity) o;
        return Objects.equals(name, entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(MameDeviceExtensionEntity o) {
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
