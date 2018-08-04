package br.com.javanei.retrocenter.datafile.mame.persistence;

import br.com.javanei.retrocenter.datafile.mame.entity.MameDriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameDriverDAO extends JpaRepository<MameDriverEntity, Long> {
}
