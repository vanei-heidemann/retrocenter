package br.com.javanei.retrocenter.datafile.mame.entity;

import br.com.javanei.retrocenter.datafile.mame.MameDeviceref;

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
@Table(name = "MAME_DEVICEREF", indexes = {
        @Index(name = "MAME_DEVICEREF_0001", unique = false, columnList = "MACHINE_ID,NAME")
})
//WARN: Pelo xml, pode haver dois deviceref com o mesmo nome!!!! Parece ser um bug...
public class MameDevicerefEntity implements Serializable, Comparable<MameDevicerefEntity> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAME_DEVICEREF_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 32, nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "MACHINE_ID")
    private MameMachineEntity machine;

    public MameDevicerefEntity() {
    }

    public MameDevicerefEntity(String name) {
        this.name = name;
    }

    public MameDevicerefEntity(MameDeviceref deviceref) {
        this(deviceref.getName());
    }

    public MameDeviceref toVO() {
        return new MameDeviceref(this.name);
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
        MameDevicerefEntity entity = (MameDevicerefEntity) o;
        return Objects.equals(name, entity.name) &&
                Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public int compareTo(MameDevicerefEntity o) {
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
            this.name.compareTo(o.name);
        return i;
    }
}
