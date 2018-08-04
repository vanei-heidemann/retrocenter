package br.com.javanei.retrocenter.datafile.mame.entity;

import br.com.javanei.retrocenter.datafile.mame.MameDipswitch;
import br.com.javanei.retrocenter.datafile.mame.MameDipvalue;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "MAME_DIPSWITCH", indexes = {
        @Index(name = "MAME_DIPSWITCH_0001", unique = false, columnList = "MACHINE_ID,NAME,MASK,TAG")
})
//WARN: Pelo xml, pode haver dois dipswitch com os mesmos valores!!!! Parece ser um bug...
public class MameDipswitchEntity implements Serializable, Comparable<MameDipswitchEntity> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAME_DIPSWITCH_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 64, nullable = false)
    private String name;

    @Column(name = "TAG", length = 128, nullable = true)
    private String tag;

    @Column(name = "MASK", nullable = true)
    private Long mask;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "dipswitch")
    private List<MameDipvalueEntity> dipvalues = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "MACHINE_ID")
    private MameMachineEntity machine;

    public MameDipswitchEntity() {
    }

    public MameDipswitchEntity(String name, String tag, Long mask) {
        this.name = name;
        this.tag = tag;
        this.mask = mask;
    }

    public MameDipswitchEntity(MameDipswitch dipswitch) {
        this(dipswitch.getName(), dipswitch.getTag(), dipswitch.getMask());
        for (MameDipvalue dipvalue : dipswitch.getDipvalues()) {
            MameDipvalueEntity e = new MameDipvalueEntity(dipvalue);
            e.setDipswitch(this);
            this.dipvalues.add(e);
        }
    }

    public MameDipswitch toVO() {
        MameDipswitch dipswitch = new MameDipswitch(this.name, this.tag, this.mask);
        for (MameDipvalueEntity dipvalueEntity : this.dipvalues) {
            dipswitch.addDipvalue(dipvalueEntity.toVO());
        }
        return dipswitch;
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

    public Long getMask() {
        return mask;
    }

    public void setMask(Long mask) {
        this.mask = mask;
    }

    public List<MameDipvalueEntity> getDipvalues() {
        return dipvalues;
    }

    public void setDipvalues(List<MameDipvalueEntity> dipvalues) {
        this.dipvalues = dipvalues;
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
        MameDipswitchEntity entity = (MameDipswitchEntity) o;
        return Objects.equals(name, entity.name) &&
                Objects.equals(tag, entity.tag) &&
                Objects.equals(mask, entity.mask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tag, mask);
    }

    @Override
    public int compareTo(MameDipswitchEntity o) {
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
        if (i == 0) {
            if (this.tag != null && o.tag == null)
                i = 1;
            else if (this.tag == null && o.tag != null)
                i = -1;
            else if (this.tag != null)
                i = this.tag.compareTo(o.tag);
        }
        if (i == 0) {
            if (this.mask != null && o.mask == null)
                i = 1;
            else if (this.mask == null && o.mask != null)
                i = -1;
            else if (this.mask != null)
                i = this.mask.compareTo(o.mask);
        }
        return i;
    }
}
