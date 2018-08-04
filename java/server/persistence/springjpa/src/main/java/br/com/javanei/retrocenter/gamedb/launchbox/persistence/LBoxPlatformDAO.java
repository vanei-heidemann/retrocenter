package br.com.javanei.retrocenter.gamedb.launchbox.persistence;

import br.com.javanei.retrocenter.gamedb.launchbox.entity.LBoxPlatformEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LBoxPlatformDAO extends JpaRepository<LBoxPlatformEntity, Long> {
    LBoxPlatformEntity findByName(@Param("name") String name);

    List<LBoxPlatformEntity> findByDeveloper_Id(Long id);

    List<LBoxPlatformEntity> findByManufacturer_Id(Long id);

    Page<LBoxPlatformEntity> findByPlatformId(@Param("platformId") Long platformId, Pageable pageable);
}
