package br.com.javanei.retrocenter.platform.persistence;

import br.com.javanei.retrocenter.platform.entity.PlatformEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface PlatformDAO extends JpaRepository<PlatformEntity, Long> {
    PlatformEntity findByName(@Param("name") String name);
}
