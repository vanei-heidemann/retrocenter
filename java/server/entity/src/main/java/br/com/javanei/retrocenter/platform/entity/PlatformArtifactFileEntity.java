package br.com.javanei.retrocenter.platform.entity;

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

@Entity
@Table(name = "PLATFORM_ARTIFACT_FILE", indexes = {
        @Index(name = "PLATFORM_ARTIFACT_FILE_0001", unique = true, columnList = "PLATFORM_ID,FILE_NAME"),
        @Index(name = "PLATFORM_ARTIFACT_FILE_0002", unique = false, columnList = "PLATFORM_ID,FILE_CRC"),
        @Index(name = "PLATFORM_ARTIFACT_FILE_0003", unique = false, columnList = "PLATFORM_ID,FILE_MD5"),
        @Index(name = "PLATFORM_ARTIFACT_FILE_0004", unique = false, columnList = "PLATFORM_ID,FILE_SHA1")
})
public class PlatformArtifactFileEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PLATFORM_ARTIFACT_FILE_ID", nullable = false)
    private Long id;

    @Column(name = "FILE_NAME", nullable = false, length = 80)
    private String name;

    @Column(name = "FILE_TYPE", nullable = false, length = 16)
    private String type;

    @Column(name = "FILE_SIZE", nullable = false)
    private Long size;

    @Column(name = "FILE_CRC", nullable = false, length = 8)
    private String crc;

    @Column(name = "FILE_MD5", nullable = false, length = 32)
    private String md5;

    @Column(name = "FILE_SHA1", nullable = false, length = 40)
    private String sha1;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "PLATFORM_ID")
    private PlatformEntity platform;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public PlatformEntity getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformEntity platform) {
        this.platform = platform;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlatformArtifactFileEntity that = (PlatformArtifactFileEntity) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return platform != null ? platform.equals(that.platform) : that.platform == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (platform != null ? platform.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PlatformArtifactFileEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", crc='" + crc + '\'' +
                ", md5='" + md5 + '\'' +
                ", sha1='" + sha1 + '\'' +
                '}';
    }
}
