package br.com.javanei.retrocenter.platform.persistence;

import br.com.javanei.retrocenter.platform.entity.PlatformArtifactFileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlatformArtifactFileDAO extends JpaRepository<PlatformArtifactFileEntity, Long> {
    List<PlatformArtifactFileEntity> findByPlatform_id(Long platformId);

    Page<PlatformArtifactFileEntity> findByPlatform_id(Long platformId, Pageable pageable);

    PlatformArtifactFileEntity findByPlatform_idAndName(Long platformId, String name);

    List<PlatformArtifactFileEntity> findByPlatform_idAndSha1(Long platformId, String sha1);

    List<PlatformArtifactFileEntity> findByPlatform_idAndMd5(Long platformId, String Md5);

    List<PlatformArtifactFileEntity> findByPlatform_idAndCrc(Long platformId, String crc);
}
