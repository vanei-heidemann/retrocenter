package br.com.javanei.retrocenter.datafile.mame.entity;

import br.com.javanei.retrocenter.datafile.mame.MameConfiguration;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "MAME_CONFIGURATION", indexes = {
        @Index(name = "MAME_CONFIGURATION_0001", unique = true, columnList = "MACHINE_ID,NAME,MASK,TAG")
})
public class MameConfigurationEntity implements Serializable, Comparable<MameConfigurationEntity> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAME_CONFIGURATION_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 64, nullable = false)
    private String name;

    @Column(name = "TAG", length = 128, nullable = false)
    private String tag;

    @Column(name = "MASK", nullable = false)
    private Integer mask;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "configuration")
    private Set<MameConfsettingEntity> confsettings = new LinkedHashSet<>();

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "MACHINE_ID")
    private MameMachineEntity machine;

    public MameConfigurationEntity() {
    }

    public MameConfigurationEntity(String name, String tag, Integer mask) {
        this.name = name;
        this.tag = tag;
        this.mask = mask;
    }

    public MameConfigurationEntity(MameConfiguration configuration) {
        this(configuration.getName(), configuration.getTag(), configuration.getMask());
        for (MameConfsetting confsetting : configuration.getConfsettings()) {
            MameConfsettingEntity e = new MameConfsettingEntity(confsetting);
            e.setConfiguration(this);
            this.confsettings.add(e);
        }
    }

    public MameConfiguration toVO() {
        MameConfiguration configuration = new MameConfiguration(this.name, this.tag, this.mask);
        for (MameConfsettingEntity confsettingEntity : this.confsettings) {
            configuration.addConfsetting(confsettingEntity.toVO());
        }
        return configuration;
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

    public Integer getMask() {
        return mask;
    }

    public void setMask(Integer mask) {
        this.mask = mask;
    }

    public Set<MameConfsettingEntity> getConfsettings() {
        return confsettings;
    }

    public void setConfsettings(Set<MameConfsettingEntity> confsettings) {
        this.confsettings = confsettings;
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
        MameConfigurationEntity entity = (MameConfigurationEntity) o;
        return Objects.equals(name, entity.name) &&
                Objects.equals(tag, entity.tag) &&
                Objects.equals(mask, entity.mask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tag, mask);
    }

    @Override
    public int compareTo(MameConfigurationEntity o) {
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
            if (this.mask != null && o.mask == null)
                i = 1;
            else if (this.mask == null && o.mask != null)
                i = -1;
            else if (this.mask != null)
                i = this.mask.compareTo(o.mask);
        }
        if (i == 0) {
            if (this.tag != null && o.tag == null)
                i = 1;
            else if (this.tag == null && o.tag != null)
                i = -1;
            else if (this.tag != null)
                i = this.tag.compareTo(o.tag);
        }
        return i;
    }
}
