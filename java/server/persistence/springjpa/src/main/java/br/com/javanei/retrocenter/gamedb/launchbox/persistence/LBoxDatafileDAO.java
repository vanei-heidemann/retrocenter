package br.com.javanei.retrocenter.gamedb.launchbox.persistence;

import br.com.javanei.retrocenter.gamedb.launchbox.entity.LBoxDatafileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface LBoxDatafileDAO extends JpaRepository<LBoxDatafileEntity, Long> {
    LBoxDatafileEntity findByVersion(@Param("version") String version);
}
