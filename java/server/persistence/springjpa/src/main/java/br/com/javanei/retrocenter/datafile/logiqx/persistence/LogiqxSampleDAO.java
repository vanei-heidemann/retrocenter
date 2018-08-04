package br.com.javanei.retrocenter.datafile.logiqx.persistence;

import br.com.javanei.retrocenter.datafile.logiqx.entity.LogiqxSampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogiqxSampleDAO extends JpaRepository<LogiqxSampleEntity, Long> {
}
