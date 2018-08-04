package br.com.javanei.retrocenter.datafile.hyperlist.persistence;

import br.com.javanei.retrocenter.datafile.hyperlist.entity.HyperListEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface HyperListDAO extends JpaRepository<HyperListEntity, Long> {
    HyperListEntity findByUniqueFull(@Param("name") String name, @Param("lastUpdate") String lastUpdate, @Param("version") String version);

    HyperListEntity findByUnique(@Param("name") String name, @Param("lastUpdate") String lastUpdate, @Param("version") String version);

    Page<HyperListEntity> findByNameLike(@Param("name") String name, Pageable pageable);
}
