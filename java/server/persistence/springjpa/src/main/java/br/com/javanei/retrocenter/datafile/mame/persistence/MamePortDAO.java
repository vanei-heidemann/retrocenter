package br.com.javanei.retrocenter.datafile.mame.persistence;

import br.com.javanei.retrocenter.datafile.mame.entity.MamePortEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MamePortDAO extends JpaRepository<MamePortEntity, Long> {
}
