package br.com.javanei.retrocenter.datafile.logiqx.persistence;

import br.com.javanei.retrocenter.datafile.logiqx.entity.LogiqxReleaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogiqxReleaseDAO extends JpaRepository<LogiqxReleaseEntity, Long> {
}
