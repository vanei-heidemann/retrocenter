package br.com.javanei.retrocenter.gamedb.launchbox.persistence;

import br.com.javanei.retrocenter.gamedb.launchbox.entity.LBoxDatafileRegionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface LBoxDatafileRegionDAO extends JpaRepository<LBoxDatafileRegionEntity, Long> {
    Page<LBoxDatafileRegionEntity> findByDatafile_Version(@Param("version") String version, Pageable pageable);
}
