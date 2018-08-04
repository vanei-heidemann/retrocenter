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
@Table(name = "PLATFORM_ARTIFACT_FILE_IMPORT_HISTORY", indexes = {
        @Index(name = "PLATFORM_ARTIFACT_FILE_IMPORT_HISTORY_0001", unique = true, columnList = "PLATFORM_ID,IMPORT_DESCRIPTION")
})
public class PlatformArtifactFileImportHistoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PLATFORM_ARTIFACT_FILE_IMPORT_HISTORY_ID", nullable = false)
    private Long id;

    @Column(name = "IMPORT_DESCRIPTION", nullable = false, length = 128)
    private String description;

    @Column(name = "LAST_IMPORT_DATE", nullable = false, length = 32)
    private String lastDate;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "PLATFORM_ID")
    private PlatformEntity platform;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
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

        PlatformArtifactFileImportHistoryEntity that = (PlatformArtifactFileImportHistoryEntity) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return platform != null ? platform.equals(that.platform) : that.platform == null;
    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (platform != null ? platform.hashCode() : 0);
        return result;
    }
}
