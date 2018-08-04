package br.com.javanei.retrocenter.datafile.clrmamepro.persistence;

import br.com.javanei.retrocenter.datafile.clrmamepro.entity.CMProResourceRomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CMProResourceRomDAO extends JpaRepository<CMProResourceRomEntity, Long> {
}
