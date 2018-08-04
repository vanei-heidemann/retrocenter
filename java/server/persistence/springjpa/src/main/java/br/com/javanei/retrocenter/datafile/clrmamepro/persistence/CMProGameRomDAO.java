package br.com.javanei.retrocenter.datafile.clrmamepro.persistence;

import br.com.javanei.retrocenter.datafile.clrmamepro.entity.CMProGameRomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CMProGameRomDAO extends JpaRepository<CMProGameRomEntity, Long> {
}
