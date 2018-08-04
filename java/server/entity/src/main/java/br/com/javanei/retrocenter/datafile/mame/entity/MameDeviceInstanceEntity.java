package br.com.javanei.retrocenter.datafile.mame.entity;

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
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "MAME_DEVICEINSTANCE", indexes = {
        @Index(name = "MAME_DEVICEINSTANCE_0001", unique = true, columnList = "DEVICE_ID,NAME")
})
public class MameDeviceInstanceEntity implements Serializable, Comparable<MameDeviceInstanceEntity> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAME_DEVICEINSTANCE_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 16, nullable = false)
    private String name;

    @Column(name = "BRIEFNAME", length = 8, nullable = true)
    private String briefname;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "DEVICE_ID")
    private MameDeviceEntity device;

    public MameDeviceInstanceEntity(String name, String briefname) {
        this.name = name;
        this.briefname = briefname;
    }

    public MameDeviceInstanceEntity() {
    }

    public MameDeviceInstanceEntity(MameDeviceInstance instance) {
        this(instance.getName(), instance.getBriefname());
    }

    public MameDeviceInstance toVO() {
        return new MameDeviceInstance(this.name, this.briefname);
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

    public String getBriefname() {
        return briefname;
    }

    public void setBriefname(String briefname) {
        this.briefname = briefname;
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
        MameDeviceInstanceEntity entity = (MameDeviceInstanceEntity) o;
        return Objects.equals(name, entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(MameDeviceInstanceEntity o) {
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
