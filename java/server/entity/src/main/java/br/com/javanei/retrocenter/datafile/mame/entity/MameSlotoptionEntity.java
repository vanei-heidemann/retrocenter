package br.com.javanei.retrocenter.datafile.mame.entity;

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
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "MAME_SLOTOPTION", indexes = {
        @Index(name = "MAME_SLOTOPTION_0001", unique = true, columnList = "SLOT_ID,NAME")
})
public class MameSlotoptionEntity implements Serializable, Comparable<MameSlotoptionEntity> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAME_SLOTOPTION_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 16, nullable = false)
    private String name;

    @Column(name = "DEVNAME", length = 32, nullable = false)
    private String devname;

    @Column(name = "ISDEFAULT", length = 3, nullable = true)
    private String _default;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "SLOT_ID")
    private MameSlotEntity slot;

    public MameSlotoptionEntity(String name, String devname, String _default) {
        this.name = name;
        this.devname = devname;
        this._default = _default;
    }

    public MameSlotoptionEntity() {
    }

    public MameSlotoptionEntity(MameSlotoption slotoption) {
        this(slotoption.getName(), slotoption.getDevname(), slotoption.getDefault());
    }

    public MameSlotoption toVO() {
        return new MameSlotoption(this.name, this.devname, this._default);
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

    public String getDevname() {
        return devname;
    }

    public void setDevname(String devname) {
        this.devname = devname;
    }

    public String getDefault() {
        return _default;
    }

    public void setDefault(String _default) {
        this._default = _default;
    }

    public MameSlotEntity getSlot() {
        return slot;
    }

    public void setSlot(MameSlotEntity slot) {
        this.slot = slot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameSlotoptionEntity entity = (MameSlotoptionEntity) o;
        return Objects.equals(name, entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(MameSlotoptionEntity o) {
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
