package br.com.javanei.retrocenter.datafile.mame.entity;

import br.com.javanei.retrocenter.datafile.mame.MameDisk;

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
@Table(name = "MAME_DISK", indexes = {
        @Index(name = "MAME_DISK_0001", unique = true, columnList = "MACHINE_ID,NAME")
})
public class MameDiskEntity implements Serializable, Comparable<MameDiskEntity> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAME_DISK_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 64, nullable = false)
    private String name;

    @Column(name = "SHA1", length = 40, nullable = true)
    private String sha1;

    @Column(name = "MERGE", length = 64, nullable = true)
    private String merge;

    @Column(name = "REGION", length = 32, nullable = true)
    private String region;

    @Column(name = "DISK_INDEX", nullable = true)
    private Integer index;

    @Column(name = "WRITABLE", length = 3, nullable = true)
    private String writable;

    @Column(name = "STATUS", length = 8, nullable = true)
    private String status;

    @Column(name = "OPTIONAL", length = 3, nullable = true)
    private String optional;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "MACHINE_ID")
    private MameMachineEntity machine;

    public MameDiskEntity() {
    }

    public MameDiskEntity(String name, String sha1, String merge, String region, Integer index, String writable,
                          String status, String optional) {
        this.name = name;
        this.sha1 = sha1;
        this.merge = merge;
        this.region = region;
        this.index = index;
        this.writable = writable;
        this.status = status;
        this.optional = optional;
    }

    public MameDiskEntity(MameDisk disk) {
        this(disk.getName(), disk.getSha1(), disk.getMerge(), disk.getRegion(), disk.getIndex(), disk.getWritable(),
                disk.getStatus(), disk.getOptional());
    }

    public MameDisk toVO() {
        return new MameDisk(this.name, this.sha1, this.merge, this.region, this.index, this.writable, this.status,
                this.optional);
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

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public String getMerge() {
        return merge;
    }

    public void setMerge(String merge) {
        this.merge = merge;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getWritable() {
        return writable;
    }

    public void setWritable(String writable) {
        this.writable = writable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOptional() {
        return optional;
    }

    public void setOptional(String optional) {
        this.optional = optional;
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
        MameDiskEntity entity = (MameDiskEntity) o;
        return Objects.equals(name, entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(MameDiskEntity o) {
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
