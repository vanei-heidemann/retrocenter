package br.com.javanei.retrocenter.platform.persistence;

import br.com.javanei.retrocenter.platform.entity.PlatformArtifactFileInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlatformArtifactFileInfoDAO extends JpaRepository<PlatformArtifactFileInfoEntity, Long> {
    List<PlatformArtifactFileInfoEntity> findByPlatformArtifactFile_id(Long platformArtifactFile);

    PlatformArtifactFileInfoEntity findByPlatformArtifactFile_idAndInfo(Long platformArtifactFile, String info);
}
