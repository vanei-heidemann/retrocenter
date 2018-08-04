package br.com.javanei.retrocenter.datafile.mame.persistence;

import br.com.javanei.retrocenter.datafile.mame.entity.MameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface MameDAO extends JpaRepository<MameEntity, Long> {
    MameEntity findByBuild(@Param("build") String build);

    MameEntity findByBuildFull(@Param("build") String build);
}
