package br.com.javanei.retrocenter.platform.persistence;

import br.com.javanei.retrocenter.platform.entity.PlatformArtifactFileInfoHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlatformArtifactFileInfoHistoryDAO extends JpaRepository<PlatformArtifactFileInfoHistoryEntity, Long> {
    List<PlatformArtifactFileInfoHistoryEntity> findByPlatformArtifactFileInfo_id(Long platformArtifactFileInfo);

    List<PlatformArtifactFileInfoHistoryEntity> findByPlatformArtifactFileImportHistory_id(Long platformArtifactFileImportHistory);

    PlatformArtifactFileInfoHistoryEntity findByPlatformArtifactFileInfo_idAndPlatformArtifactFileImportHistory_id(Long platformArtifactFileInfo, Long platformArtifactFileImportHistory);
}
