package br.com.javanei.retrocenter.gamedb.launchbox.persistence;

import br.com.javanei.retrocenter.gamedb.launchbox.entity.LBoxGenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface LBoxGenreDAO extends JpaRepository<LBoxGenreEntity, Long> {
    LBoxGenreEntity findByName(@Param("name") String name);
}
