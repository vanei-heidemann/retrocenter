package br.com.javanei.retrocenter.datafile.mame.persistence;

import br.com.javanei.retrocenter.datafile.mame.entity.MameRomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameRomDAO extends JpaRepository<MameRomEntity, Long> {
}
