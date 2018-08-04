package br.com.javanei.retrocenter.datafile.mame.entity;

import br.com.javanei.retrocenter.datafile.mame.MameSlot;
import br.com.javanei.retrocenter.datafile.mame.MameSlotoption;

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
@Table(name = "MAME_SLOT", indexes = {
        @Index(name = "MAME_SLOT_0001", unique = true, columnList = "MACHINE_ID,NAME")
})
public class MameSlotEntity implements Serializable, Comparable<MameSlotEntity> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAME_SLOT_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 48, nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "slot")
    private Set<MameSlotoptionEntity> slotoptions = new LinkedHashSet<>();

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "MACHINE_ID")
    private MameMachineEntity machine;

    public MameSlotEntity(String name) {
        this.name = name;
    }

    public MameSlotEntity() {
    }

    public MameSlotEntity(MameSlot slot) {
        this(slot.getName());
        for (MameSlotoption slotoption : slot.getSlotoptions()) {
            MameSlotoptionEntity e = new MameSlotoptionEntity(slotoption);
            e.setSlot(this);
            this.slotoptions.add(e);
        }
    }

    public MameSlot toVO() {
        MameSlot slot = new MameSlot(this.name);
        for (MameSlotoptionEntity entity : this.slotoptions) {
            slot.addSlotoption(entity.toVO());
        }
        return slot;
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

    public Set<MameSlotoptionEntity> getSlotoptions() {
        return slotoptions;
    }

    public void setSlotoptions(Set<MameSlotoptionEntity> slotoptions) {
        this.slotoptions = slotoptions;
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
        MameSlotEntity entity = (MameSlotEntity) o;
        return Objects.equals(name, entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(MameSlotEntity o) {
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
