package br.com.javanei.retrocenter.datafile.logiqx.persistence;

import br.com.javanei.retrocenter.datafile.logiqx.entity.LogiqxArchiveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogiqxArchiveDAO extends JpaRepository<LogiqxArchiveEntity, Long> {
}
