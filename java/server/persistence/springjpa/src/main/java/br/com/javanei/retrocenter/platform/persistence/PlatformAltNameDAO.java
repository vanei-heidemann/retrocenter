package br.com.javanei.retrocenter.platform.persistence;

import br.com.javanei.retrocenter.platform.entity.PlatformAltNameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface PlatformAltNameDAO extends JpaRepository<PlatformAltNameEntity, Long> {
    PlatformAltNameEntity findByAlternateName(@Param("alternateName") String alternateName);
}
