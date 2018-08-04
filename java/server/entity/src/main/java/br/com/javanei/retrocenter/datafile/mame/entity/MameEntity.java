package br.com.javanei.retrocenter.datafile.mame.entity;

import br.com.javanei.retrocenter.datafile.mame.Mame;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "MAME", indexes = {
        @Index(name = "MAME_0001", unique = true, columnList = "BUILD")
})
@NamedQueries({
        @NamedQuery(name = "MameEntity.findByBuildFull", query = "SELECT o from MameEntity o WHERE build = :build"),
        @NamedQuery(name = "MameEntity.findByBuild", query = "SELECT new MameEntity(o.id, o.build, o.debug, o.mameconfig) from MameEntity o WHERE build = :build")
})
public class MameEntity implements Serializable, Comparable<MameEntity> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAME_ID", nullable = false)
    private Long id;

    @Column(name = "BUILD", length = 16, nullable = false)
    private String build;

    @Column(name = "DEBUG", length = 3, nullable = true)
    private String debug;

    @Column(name = "MAMECONFIG", length = 16, nullable = true)
    private String mameconfig;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "mame")
    private Set<MameMachineEntity> machines = new LinkedHashSet<>();

    public MameEntity() {
    }

    public MameEntity(Long id) {
        this.id = id;
    }

    public MameEntity(String build) {
        this.build = build;
    }

    public MameEntity(Long id, String build, String debug, String mameconfig) {
        this.id = id;
        this.build = build;
        this.debug = debug;
        this.mameconfig = mameconfig;
    }

    public MameEntity(String build, String debug, String mameconfig) {
        this.build = build;
        this.debug = debug;
        this.mameconfig = mameconfig;
    }

    public MameEntity(Mame mame) {
        this(mame.getBuild(), mame.getDebug(), mame.getMameconfig());
    }

    public Mame toVO() {
        Mame mame = new Mame(this.build, this.debug, this.mameconfig);
        for (MameMachineEntity machineEntity : this.machines) {
            mame.addMachine(machineEntity.toVO());
        }

        return mame;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }

    public String getMameconfig() {
        return mameconfig;
    }

    public void setMameconfig(String mameconfig) {
        this.mameconfig = mameconfig;
    }

    public Set<MameMachineEntity> getMachines() {
        return machines;
    }

    public void setMachines(Set<MameMachineEntity> machines) {
        this.machines = machines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameEntity that = (MameEntity) o;
        return Objects.equals(build, that.build);
    }

    @Override
    public int hashCode() {
        return Objects.hash(build);
    }

    @Override
    public int compareTo(MameEntity o) {
        if (this == o)
            return 0;

        return this.build.compareTo(o.build);
    }
}
