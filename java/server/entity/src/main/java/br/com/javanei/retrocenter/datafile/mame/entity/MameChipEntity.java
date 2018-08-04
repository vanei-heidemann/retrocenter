package br.com.javanei.retrocenter.datafile.mame.entity;

import br.com.javanei.retrocenter.datafile.mame.MameChip;

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
@Table(name = "MAME_CHIP", indexes = {
        @Index(name = "MAME_CHIP_0001", unique = true, columnList = "MACHINE_ID,TAG,TYPE,NAME"),
        @Index(name = "MAME_CHIP_0002", unique = false, columnList = "NAME,MACHINE_ID")
})
public class MameChipEntity implements Serializable, Comparable<MameChipEntity> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAME_CHIP_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 48, nullable = false)
    private String name;

    @Column(name = "TAG", length = 128, nullable = false)
    private String tag;

    @Column(name = "TYPE", length = 5, nullable = false)
    private String type; // (cpu|audio);

    @Column(name = "CLOCK", length = 16, nullable = true)
    private String clock;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "MACHINE_ID")
    private MameMachineEntity machine;

    public MameChipEntity() {
    }

    public MameChipEntity(String name, String tag, String type, String clock) {
        this.name = name;
        this.tag = tag;
        this.type = type;
        this.clock = clock;
    }

    public MameChipEntity(MameChip chip) {
        this(chip.getName(), chip.getTag(), chip.getType(), chip.getClock());
    }

    public MameChip toVO() {
        return new MameChip(this.name, this.tag, this.type, this.clock);
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
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
        MameChipEntity entity = (MameChipEntity) o;
        return Objects.equals(name, entity.name) &&
                Objects.equals(tag, entity.tag) &&
                Objects.equals(type, entity.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tag, type);
    }

    @Override
    public int compareTo(MameChipEntity o) {
        if (this == o)
            return 0;

        int result = 0;
        if (this.id != null && o.id == null)
            result = 1;
        else if (this.id == null && o.id != null)
            result = -1;
        else if (this.id != null)
            result = this.id.compareTo(o.id);
        if (result == 0)
            result = this.type.compareTo(o.type);
        if (result == 0) {
            result = this.tag.compareTo(o.tag);
        }
        if (result == 0) {
            if (this.name != null && o.name == null)
                result = 1;
            else if (this.name == null && o.name != null)
                result = -1;
            else if (this.name != null)
                result = this.name.compareTo(o.name);
        }
        return result;
    }
}
