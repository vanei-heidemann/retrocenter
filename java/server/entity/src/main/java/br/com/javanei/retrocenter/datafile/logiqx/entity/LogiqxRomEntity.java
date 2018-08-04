package br.com.javanei.retrocenter.datafile.logiqx.entity;

import br.com.javanei.retrocenter.datafile.logiqx.LogiqxRom;

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
@Table(name = "LOGIQX_ROM", indexes = {
        @Index(name = "LOGIQX_ROM_0001", unique = true, columnList = "GAME_ID,NAME,SIZE,CRC,SHA1,MD5")
})
public class LogiqxRomEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROM_ID", nullable = false)
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

    @Column(name = "MERGE", length = 255, nullable = true)
    private String merge;

    @Column(name = "DATE", length = 32, nullable = true)
    private String date;

    @Column(name = "ROMSTATUS", length = 8, nullable = true)
    private String status = "good";

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "GAME_ID")
    private LogiqxGameEntity game;

    public LogiqxRomEntity() {
    }

    public LogiqxRomEntity(LogiqxRom rom) {
        this(rom.getName(), rom.getSize(), rom.getCrc(), rom.getSha1(), rom.getMd5(), rom.getMerge(), rom.getDate(),
                rom.getStatus());
    }

    public LogiqxRomEntity(Long id) {
        this.id = id;
    }

    public LogiqxRomEntity(String name) {
        this.name = name;
    }

    public LogiqxRomEntity(String name, Long size, String crc, String sha1, String md5) {
        this.name = name;
        this.size = size;
        this.crc = crc;
        this.sha1 = sha1;
        this.md5 = md5;
    }

    public LogiqxRomEntity(String name, Long size, String crc, String sha1, String md5, String merge, String date,
                           String status) {
        this.name = name;
        this.size = size;
        this.crc = crc;
        this.sha1 = sha1;
        this.md5 = md5;
        this.merge = merge;
        this.date = date;
        this.status = status;
    }

    public LogiqxRomEntity(Long id, String name, Long size, String crc, String sha1, String md5, String merge,
                           String date, String status) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.crc = crc;
        this.sha1 = sha1;
        this.md5 = md5;
        this.merge = merge;
        this.date = date;
        this.status = status;
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

    public String getMerge() {
        return merge;
    }

    public void setMerge(String merge) {
        this.merge = merge;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LogiqxGameEntity getGame() {
        return game;
    }

    public void setGame(LogiqxGameEntity game) {
        this.game = game;
    }

    public LogiqxRom toVO() {
        return new LogiqxRom(this.name, this.size, this.crc, this.sha1, this.md5, this.merge, this.date, this.status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogiqxRomEntity that = (LogiqxRomEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(size, that.size) &&
                Objects.equals(crc, that.crc) &&
                Objects.equals(sha1, that.sha1) &&
                Objects.equals(md5, that.md5);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size, crc, sha1, md5);
    }
}
