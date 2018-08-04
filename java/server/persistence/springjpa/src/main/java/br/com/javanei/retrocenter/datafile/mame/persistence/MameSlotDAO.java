package br.com.javanei.retrocenter.datafile.mame.persistence;

import br.com.javanei.retrocenter.datafile.mame.entity.MameSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameSlotDAO extends JpaRepository<MameSlotEntity, Long> {
}
