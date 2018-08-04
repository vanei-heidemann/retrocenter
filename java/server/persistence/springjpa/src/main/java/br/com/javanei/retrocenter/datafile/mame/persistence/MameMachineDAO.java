package br.com.javanei.retrocenter.datafile.mame.persistence;

import br.com.javanei.retrocenter.datafile.mame.entity.MameMachineEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MameMachineDAO extends JpaRepository<MameMachineEntity, Long> {
    List<MameMachineEntity> findByBuild(@Param("build") String build);

    MameMachineEntity findByBuildAndName(@Param("build") String build, @Param("name") String name);

    Page<MameMachineEntity> findByMame_Id(Long id, Pageable pageable);
}
