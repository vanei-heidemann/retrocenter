package br.com.javanei.retrocenter.datafile.logiqx.persistence;

import br.com.javanei.retrocenter.datafile.logiqx.entity.LogiqxDatafileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface LogiqxDatafileDAO extends JpaRepository<LogiqxDatafileEntity, Long> {
    LogiqxDatafileEntity findByUniqueFull(@Param("name") String name, @Param("catalog") String catalog, @Param("version") String version);

    LogiqxDatafileEntity findByUnique(@Param("name") String name, @Param("catalog") String catalog, @Param("version") String version);

    Page<LogiqxDatafileEntity> findByNameLike(@Param("name") String name, Pageable pageable);
}
