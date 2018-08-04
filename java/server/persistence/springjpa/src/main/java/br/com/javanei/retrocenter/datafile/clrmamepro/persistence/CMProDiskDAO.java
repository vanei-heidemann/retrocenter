package br.com.javanei.retrocenter.datafile.clrmamepro.persistence;

import br.com.javanei.retrocenter.datafile.clrmamepro.entity.CMProDiskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CMProDiskDAO extends JpaRepository<CMProDiskEntity, Long> {
}
