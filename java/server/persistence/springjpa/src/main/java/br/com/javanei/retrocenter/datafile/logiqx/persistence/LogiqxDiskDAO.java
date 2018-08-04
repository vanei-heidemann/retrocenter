package br.com.javanei.retrocenter.datafile.logiqx.persistence;

import br.com.javanei.retrocenter.datafile.logiqx.entity.LogiqxDiskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogiqxDiskDAO extends JpaRepository<LogiqxDiskEntity, Long> {
}
