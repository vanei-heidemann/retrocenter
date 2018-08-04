package br.com.javanei.retrocenter.datafile.mame.entity;

import br.com.javanei.retrocenter.datafile.mame.MameConfsetting;

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
@Table(name = "MAME_CONFSETTING", indexes = {
        @Index(name = "MAME_CONFSETTING_0001", unique = true, columnList = "CONFIGURATION_ID,NAME,VALUE")
})
public class MameConfsettingEntity implements Serializable, Comparable<MameConfsettingEntity> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAME_CONFSETTING_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 64, nullable = false)
    private String name;

    @Column(name = "VALUE", nullable = false)
    private Integer value;

    @Column(name = "ISDEFAULT", nullable = true)
    private String _default; // (yes|no) "no";

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CONFIGURATION_ID")
    private MameConfigurationEntity configuration;

    public MameConfsettingEntity() {
    }

    public MameConfsettingEntity(String name, Integer value, String _default) {
        this.name = name;
        this.value = value;
        this._default = _default;
    }

    public MameConfsettingEntity(MameConfsetting confsetting) {
        this(confsetting.getName(), confsetting.getValue(), confsetting.getDefault());
    }

    public MameConfsetting toVO() {
        return new MameConfsetting(this.name, this.value, this._default);
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDefault() {
        return _default;
    }

    public void setDefault(String _default) {
        this._default = _default;
    }

    public MameConfigurationEntity getConfiguration() {
        return configuration;
    }

    public void setConfiguration(MameConfigurationEntity configuration) {
        this.configuration = configuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameConfsettingEntity entity = (MameConfsettingEntity) o;
        return Objects.equals(name, entity.name) &&
                Objects.equals(value, entity.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    @Override
    public int compareTo(MameConfsettingEntity o) {
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
        if (i == 0)
            i = this.value.compareTo(o.value);
        return i;
    }
}
