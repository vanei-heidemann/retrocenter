package br.com.javanei.retrocenter.gamedb.launchbox.persistence;

import br.com.javanei.retrocenter.gamedb.launchbox.entity.LBoxDatafilePlatformEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface LBoxDatafilePlatformDAO extends JpaRepository<LBoxDatafilePlatformEntity, Long> {
    Page<LBoxDatafilePlatformEntity> findByDatafile_Version(@Param("version") String version, Pageable pageable);

    Page<LBoxDatafilePlatformEntity> findByDatafile_VersionAndPlatform_PlatformId(@Param("version") String version,
                                                                                  @Param("platformId") Long platformId,
                                                                                  Pageable pageable);
}
