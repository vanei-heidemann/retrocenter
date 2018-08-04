package br.com.javanei.retrocenter.datafile.mame.entity;

import br.com.javanei.retrocenter.datafile.mame.MameSoftwarelist;

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
@Table(name = "MAME_SOFTWARELIST", indexes = {
        @Index(name = "MAME_SOFTWARELIST_0001", unique = true, columnList = "MACHINE_ID,NAME")
})
public class MameSoftwarelistEntity implements Serializable, Comparable<MameSoftwarelistEntity> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAME_SOFTWARELIST_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 16, nullable = false)
    private String name;

    @Column(name = "STATUS", length = 10, nullable = false)
    private String status;

    @Column(name = "FILTER", length = 8, nullable = true)
    private String filter;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "MACHINE_ID")
    private MameMachineEntity machine;

    public MameSoftwarelistEntity(String name, String status, String filter) {
        this.name = name;
        this.status = status;
        this.filter = filter;
    }

    public MameSoftwarelistEntity() {
    }

    public MameSoftwarelistEntity(MameSoftwarelist softwarelist) {
        this(softwarelist.getName(), softwarelist.getStatus(), softwarelist.getFilter());
    }

    public MameSoftwarelist toVO() {
        return new MameSoftwarelist(this.name, this.status, this.filter);
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
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
        MameSoftwarelistEntity entity = (MameSoftwarelistEntity) o;
        return Objects.equals(name, entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(MameSoftwarelistEntity o) {
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
