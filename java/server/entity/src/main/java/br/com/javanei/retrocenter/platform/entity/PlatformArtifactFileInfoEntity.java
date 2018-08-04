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
@Table(name = "PLATFORM_ARTIFACT_FILE_INFO", indexes = {
        @Index(name = "PLATFORM_ARTIFACT_FILE_INFO_0001", unique = true, columnList = "PLATFORM_ARTIFACT_FILE_ID,FILE_INFO")
})
public class PlatformArtifactFileInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PLATFORM_ARTIFACT_FILE_INFO_ID", nullable = false)
    private Long id;

    @Column(name = "FILE_INFO", nullable = false, length = 255)
    private String info;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "PLATFORM_ARTIFACT_FILE_ID")
    private PlatformArtifactFileEntity platformArtifactFile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public PlatformArtifactFileEntity getPlatformArtifactFile() {
        return platformArtifactFile;
    }

    public void setPlatformArtifactFile(PlatformArtifactFileEntity platformArtifactFile) {
        this.platformArtifactFile = platformArtifactFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlatformArtifactFileInfoEntity that = (PlatformArtifactFileInfoEntity) o;

        if (info != null ? !info.equals(that.info) : that.info != null) return false;
        return platformArtifactFile != null ? platformArtifactFile.equals(that.platformArtifactFile) : that.platformArtifactFile == null;
    }

    @Override
    public int hashCode() {
        int result = info != null ? info.hashCode() : 0;
        result = 31 * result + (platformArtifactFile != null ? platformArtifactFile.hashCode() : 0);
        return result;
    }
}
