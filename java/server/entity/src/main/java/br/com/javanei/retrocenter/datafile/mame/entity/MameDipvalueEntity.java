package br.com.javanei.retrocenter.datafile.mame.entity;

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
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "MAME_DIPVALUE", indexes = {
        @Index(name = "MAME_DIPVALUE_0001", unique = false, columnList = "DIPSWITCH_ID,NAME,VALUE,ISDEFAULT")
})
public class MameDipvalueEntity implements Serializable, Comparable<MameDipvalueEntity> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAME_DIPVALUE_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 96, nullable = false)
    private String name;

    @Column(name = "VALUE", nullable = false)
    private Long value;

    @Column(name = "ISDEFAULT", length = 3, nullable = true)
    private String _default;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "DIPSWITCH_ID")
    private MameDipswitchEntity dipswitch;

    public MameDipvalueEntity() {
    }

    public MameDipvalueEntity(String name, Long value, String _default) {
        this.name = name;
        this.value = value;
        this._default = _default;
    }

    public MameDipvalueEntity(MameDipvalue dipvalue) {
        this(dipvalue.getName(), dipvalue.getValue(), dipvalue.getDefault());
    }

    public MameDipvalue toVO() {
        return new MameDipvalue(this.name, this.value, this._default);
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

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getDefault() {
        return _default;
    }

    public void setDefault(String _default) {
        this._default = _default;
    }

    public MameDipswitchEntity getDipswitch() {
        return dipswitch;
    }

    public void setDipswitch(MameDipswitchEntity dipswitch) {
        this.dipswitch = dipswitch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameDipvalueEntity entity = (MameDipvalueEntity) o;
        return Objects.equals(name, entity.name) &&
                Objects.equals(value, entity.value) &&
                Objects.equals(_default, entity._default);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value, _default);
    }

    @Override
    public int compareTo(MameDipvalueEntity o) {
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
            if (this.value != null && o.value == null)
                i = 1;
            else if (this.value == null && o.value != null)
                i = -1;
            else if (this.value != null)
                i = this.value.compareTo(o.value);
        }
        if (i == 0) {
            if (this._default != null && o._default == null)
                i = 1;
            else if (this._default == null && o._default != null)
                i = -1;
            else if (this._default != null)
                i = this._default.compareTo(o._default);
        }
        return i;
    }
}
