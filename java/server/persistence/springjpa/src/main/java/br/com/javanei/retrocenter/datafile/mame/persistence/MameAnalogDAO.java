package br.com.javanei.retrocenter.datafile.mame.persistence;

import br.com.javanei.retrocenter.datafile.mame.entity.MameAnalogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameAnalogDAO extends JpaRepository<MameAnalogEntity, Long> {
}
