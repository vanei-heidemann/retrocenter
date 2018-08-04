package br.com.javanei.retrocenter.datafile.logiqx.persistence;

import br.com.javanei.retrocenter.datafile.logiqx.entity.LogiqxRomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogiqxRomDAO extends JpaRepository<LogiqxRomEntity, Long> {
}
