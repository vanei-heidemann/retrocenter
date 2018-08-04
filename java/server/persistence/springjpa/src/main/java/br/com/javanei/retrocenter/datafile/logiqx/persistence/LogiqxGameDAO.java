package br.com.javanei.retrocenter.datafile.logiqx.persistence;

import br.com.javanei.retrocenter.datafile.logiqx.entity.LogiqxGameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface LogiqxGameDAO extends JpaRepository<LogiqxGameEntity, Long> {
    LogiqxGameEntity findByDatafileAndName(@Param("datafileName") String datafileName, @Param("catalog") String catalog, @Param("version") String version, @Param("name") String name);
}
