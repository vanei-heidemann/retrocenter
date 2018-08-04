package br.com.javanei.retrocenter.gamedb.launchbox.persistence;

import br.com.javanei.retrocenter.gamedb.launchbox.entity.LBoxRegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface LBoxRegionDAO extends JpaRepository<LBoxRegionEntity, Long> {
    LBoxRegionEntity findByName(@Param("name") String name);
}
