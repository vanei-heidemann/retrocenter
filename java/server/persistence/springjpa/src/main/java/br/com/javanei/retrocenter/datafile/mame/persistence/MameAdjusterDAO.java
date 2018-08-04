package br.com.javanei.retrocenter.datafile.mame.persistence;

import br.com.javanei.retrocenter.datafile.mame.entity.MameAdjusterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameAdjusterDAO extends JpaRepository<MameAdjusterEntity, Long> {
}
