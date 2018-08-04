package br.com.javanei.retrocenter.datafile.logiqx.entity;

import br.com.javanei.retrocenter.datafile.logiqx.LogiqxDisk;

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
@Table(name = "LOGIQX_DISK", indexes = {
        @Index(name = "LOGIQX_DISK_0001", unique = true, columnList = "GAME_ID,NAME,SHA1,MD5")
})
public class LogiqxDiskEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DISK_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 255, nullable = false)
    private String name;

    @Column(name = "SHA1", length = 40, nullable = true)
    private String sha1;

    @Column(name = "MD5", length = 32, nullable = true)
    private String md5;

    @Column(name = "MERGE", length = 255, nullable = true)
    private String merge;

    @Column(name = "DISKSTATUS", length = 8, nullable = true)
    private String status = "good";

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "GAME_ID")
    private LogiqxGameEntity game;

    public LogiqxDiskEntity() {
    }

    public LogiqxDiskEntity(LogiqxDisk disk) {
        this(disk.getName(), disk.getSha1(), disk.getMd5(), disk.getMerge(), disk.getStatus());
    }

    public LogiqxDiskEntity(Long id) {
        this.id = id;
    }

    public LogiqxDiskEntity(String name) {
        this.name = name;
    }

    public LogiqxDiskEntity(String name, String sha1, String md5) {
        this.name = name;
        this.sha1 = sha1;
        this.md5 = md5;
    }

    public LogiqxDiskEntity(String name, String sha1, String md5, String merge, String status) {
        this.name = name;
        this.sha1 = sha1;
        this.md5 = md5;
        this.merge = merge;
        this.status = status;
    }

    public LogiqxDiskEntity(Long id, String name, String sha1, String md5, String merge, String status) {
        this.id = id;
        this.name = name;
        this.sha1 = sha1;
        this.md5 = md5;
        this.merge = merge;
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

    public LogiqxDisk toVO() {
        return new LogiqxDisk(this.name, this.sha1, this.md5, this.merge, this.status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogiqxDiskEntity that = (LogiqxDiskEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(sha1, that.sha1) &&
                Objects.equals(md5, that.md5);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sha1, md5);
    }
}
