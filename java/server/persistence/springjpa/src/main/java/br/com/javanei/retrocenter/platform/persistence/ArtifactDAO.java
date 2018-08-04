package br.com.javanei.retrocenter.platform.persistence;

import br.com.javanei.retrocenter.platform.entity.ArtifactEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtifactDAO extends JpaRepository<ArtifactEntity, Long> {
    ArtifactEntity findByCode(@Param("code") String code);

    List<ArtifactEntity> findByNameLike(@Param("name") String name);

    Page<ArtifactEntity> findByNameLike(@Param("name") String name, Pageable pageable);
}
