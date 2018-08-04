package br.com.javanei.retrocenter.datafile.mame.entity;

import br.com.javanei.retrocenter.datafile.mame.MameAdjuster;

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
@Table(name = "MAME_ADJUSTER", indexes = {
        @Index(name = "MAME_ADJUSTER_0001", unique = true, columnList = "MACHINE_ID,NAME")
})
public class MameAdjusterEntity implements Serializable, Comparable<MameAdjusterEntity> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAME_ADJUSTER_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 32, nullable = false)
    private String name;

    @Column(name = "DEFAULT_VALUE", nullable = true)
    private Integer _default;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "MACHINE_ID")
    private MameMachineEntity machine;

    public MameAdjusterEntity(String name, Integer _default) {
        this.name = name;
        this._default = _default;
    }

    public MameAdjusterEntity() {
    }

    public MameAdjusterEntity(MameAdjuster adjuster) {
        this(adjuster.getName(), adjuster.getDefault());
    }

    public MameAdjuster toVO() {
        return new MameAdjuster(this.name, this.getDefault());
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

    public Integer getDefault() {
        return _default;
    }

    public void setDefault(Integer _default) {
        this._default = _default;
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
        MameAdjusterEntity entity = (MameAdjusterEntity) o;
        return Objects.equals(name, entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(MameAdjusterEntity o) {
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
