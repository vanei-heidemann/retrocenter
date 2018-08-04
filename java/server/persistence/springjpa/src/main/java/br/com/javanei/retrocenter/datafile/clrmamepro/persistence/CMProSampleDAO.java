package br.com.javanei.retrocenter.datafile.clrmamepro.persistence;

import br.com.javanei.retrocenter.datafile.clrmamepro.entity.CMProSampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CMProSampleDAO extends JpaRepository<CMProSampleEntity, Long> {
}
