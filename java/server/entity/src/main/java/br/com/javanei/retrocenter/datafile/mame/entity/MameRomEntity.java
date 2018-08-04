package br.com.javanei.retrocenter.datafile.mame.entity;

import br.com.javanei.retrocenter.datafile.mame.MameRom;

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
@Table(name = "MAME_ROM", indexes = {
        @Index(name = "MAME_ROM_0001", unique = false, columnList = "MACHINE_ID,NAME,BIOS,REGION,OFFSET")
})
//WARN: Parece haver duplicidades no xml
public class MameRomEntity implements Serializable, Comparable<MameRomEntity> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAME_ROM_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 160, nullable = false)
    private String name;

    @Column(name = "BIOS", length = 32, nullable = true)
    private String bios;

    @Column(name = "SIZE", nullable = true)
    private Long size;

    @Column(name = "CRC", length = 8, nullable = true)
    private String crc;

    @Column(name = "SHA1", length = 40, nullable = true)
    private String sha1;

    @Column(name = "MERGE", length = 160, nullable = true)
    private String merge;

    @Column(name = "REGION", length = 40, nullable = true)
    private String region;

    @Column(name = "OFFSET", length = 8, nullable = true)
    private String offset;

    @Column(name = "STATUS", length = 8, nullable = true)
    private String status;

    @Column(name = "OPTIONAL", length = 3, nullable = true)
    private String optional;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "MACHINE_ID")
    private MameMachineEntity machine;

    public MameRomEntity() {
    }

    public MameRomEntity(String name, String bios, Long size, String crc, String sha1, String merge, String region,
                         String offset, String status, String optional) {
        this.name = name;
        this.bios = bios;
        this.size = size;
        this.crc = crc;
        this.sha1 = sha1;
        this.merge = merge;
        this.region = region;
        this.offset = offset;
        this.status = status;
        this.optional = optional;
    }

    public MameRomEntity(MameRom rom) {
        this(rom.getName(), rom.getBios(), rom.getSize(), rom.getCrc(), rom.getSha1(), rom.getMerge(), rom.getRegion(),
                rom.getOffset(), rom.getStatus(), rom.getOptional());
    }

    public MameRom toVO() {
        return new MameRom(this.name, this.bios, this.size, this.crc, this.sha1, this.merge, this.region, this.offset,
                this.status, this.optional);
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

    public String getBios() {
        return bios;
    }

    public void setBios(String bios) {
        this.bios = bios;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
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

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MameRomEntity that = (MameRomEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(bios, that.bios) &&
                Objects.equals(size, that.size) &&
                Objects.equals(crc, that.crc) &&
                Objects.equals(sha1, that.sha1) &&
                Objects.equals(merge, that.merge) &&
                Objects.equals(region, that.region) &&
                Objects.equals(offset, that.offset) &&
                Objects.equals(status, that.status) &&
                Objects.equals(optional, that.optional);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, bios, size, crc, sha1, merge, region, offset, status, optional);
    }

    @Override
    public int compareTo(MameRomEntity o) {
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
            if (this.bios != null && o.bios == null)
                i = 1;
            else if (this.bios == null && o.bios != null)
                i = -1;
            else if (this.bios != null)
                i = this.bios.compareTo(o.bios);
        }
        if (i == 0) {
            if (this.offset != null && o.offset == null)
                i = 1;
            else if (this.offset == null && o.offset != null)
                i = -1;
            else if (this.offset != null)
                i = this.offset.compareTo(o.offset);
        }
        return i;
    }
}
