package br.com.javanei.retrocenter.platform.persistence;

import br.com.javanei.retrocenter.platform.entity.PlatformArtifactFileImportHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlatformArtifactFileImportHistoryDAO extends JpaRepository<PlatformArtifactFileImportHistoryEntity, Long> {
    List<PlatformArtifactFileImportHistoryEntity> findByPlatform_id(Long platformId);

    Page<PlatformArtifactFileImportHistoryEntity> findByPlatform_id(Long platformId, Pageable pageable);

    PlatformArtifactFileImportHistoryEntity findByPlatform_idAndDescription(Long platformId, String description);
}
