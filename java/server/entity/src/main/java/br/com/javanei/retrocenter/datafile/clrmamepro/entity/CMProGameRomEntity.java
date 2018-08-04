package br.com.javanei.retrocenter.datafile.clrmamepro.entity;

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
@Table(name = "CMPRO_GAMEROM", indexes = {
        @Index(name = "CMPRO_GAMEROM_0001", unique = true, columnList = "GAME_ID,NAME,SIZE,CRC,SHA1,MD5,REGION,FLAGS")
})
public class CMProGameRomEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GAMEROM_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 255, nullable = false)
    private String name;

    @Column(name = "SIZE", nullable = true)
    private Long size;

    @Column(name = "CRC", length = 8, nullable = true)
    private String crc;

    @Column(name = "SHA1", length = 40, nullable = true)
    private String sha1;

    @Column(name = "MD5", length = 32, nullable = true)
    private String md5;

    @Column(name = "REGION", length = 64, nullable = true)
    private String region;

    @Column(name = "FLAGS", length = 16, nullable = true)
    private String flags;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "GAME_ID")
    private CMProGameEntity game;

    public CMProGameRomEntity() {
    }

    public CMProGameRomEntity(String name, Long size, String crc, String sha1, String md5, String region, String flags) {
        this.name = name;
        this.size = size;
        this.crc = crc;
        this.sha1 = sha1;
        this.md5 = md5;
        this.region = region;
        this.flags = flags;
    }

    public CMProGameRomEntity(Long id) {
        this.id = id;
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

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public CMProGameEntity getGame() {
        return game;
    }

    public void setGame(CMProGameEntity game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CMProGameRomEntity that = (CMProGameRomEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(size, that.size) &&
                Objects.equals(crc, that.crc) &&
                Objects.equals(sha1, that.sha1) &&
                Objects.equals(md5, that.md5) &&
                Objects.equals(region, that.region) &&
                Objects.equals(flags, that.flags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size, crc, sha1, md5, region, flags);
    }
}
