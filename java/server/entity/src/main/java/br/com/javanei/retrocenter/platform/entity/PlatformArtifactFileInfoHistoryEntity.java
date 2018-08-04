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

@Entity
@Table(name = "PLATFORM_ARTIFACT_FILE_INFO_HISTORY", indexes = {
        @Index(name = "PLATFORM_ARTIFACT_FILE_INFO_HISTORY_0001", unique = true,
                columnList = "PLATFORM_ARTIFACT_FILE_INFO_ID,PLATFORM_ARTIFACT_FILE_IMPORT_HISTORY_ID")
})
public class PlatformArtifactFileInfoHistoryEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PLATFORM_ARTIFACT_FILE_INFO_HISTORY_ID", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "PLATFORM_ARTIFACT_FILE_INFO_ID")
    private PlatformArtifactFileInfoEntity platformArtifactFileInfo;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "PLATFORM_ARTIFACT_FILE_IMPORT_HISTORY_ID")
    private PlatformArtifactFileImportHistoryEntity platformArtifactFileImportHistory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlatformArtifactFileInfoEntity getPlatformArtifactFileInfo() {
        return platformArtifactFileInfo;
    }

    public void setPlatformArtifactFileInfo(PlatformArtifactFileInfoEntity platformArtifactFileInfo) {
        this.platformArtifactFileInfo = platformArtifactFileInfo;
    }

    public PlatformArtifactFileImportHistoryEntity getPlatformArtifactFileImportHistory() {
        return platformArtifactFileImportHistory;
    }

    public void setPlatformArtifactFileImportHistory(PlatformArtifactFileImportHistoryEntity platformArtifactFileImportHistory) {
        this.platformArtifactFileImportHistory = platformArtifactFileImportHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlatformArtifactFileInfoHistoryEntity that = (PlatformArtifactFileInfoHistoryEntity) o;

        if (platformArtifactFileInfo != null ? !platformArtifactFileInfo.equals(that.platformArtifactFileInfo) : that.platformArtifactFileInfo != null)
            return false;
        return platformArtifactFileImportHistory != null ? platformArtifactFileImportHistory.equals(that.platformArtifactFileImportHistory) : that.platformArtifactFileImportHistory == null;
    }

    @Override
    public int hashCode() {
        int result = platformArtifactFileInfo != null ? platformArtifactFileInfo.hashCode() : 0;
        result = 31 * result + (platformArtifactFileImportHistory != null ? platformArtifactFileImportHistory.hashCode() : 0);
        return result;
    }
}
