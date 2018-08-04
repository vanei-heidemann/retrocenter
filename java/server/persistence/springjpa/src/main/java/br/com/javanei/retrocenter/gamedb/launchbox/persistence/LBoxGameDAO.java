package br.com.javanei.retrocenter.gamedb.launchbox.persistence;

import br.com.javanei.retrocenter.gamedb.launchbox.entity.LBoxGameEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LBoxGameDAO extends JpaRepository<LBoxGameEntity, String> {
    List<LBoxGameEntity> findByNameLike(@Param("name") String name);

    Page<LBoxGameEntity> findByNameLike(@Param("name") String name, Pageable pageable);

    List<LBoxGameEntity> findByPlatform_Id(Long id);

    List<LBoxGameEntity> findByPublisher_Id(Long id);

    List<LBoxGameEntity> findByDeveloper_Id(Long id);
}
