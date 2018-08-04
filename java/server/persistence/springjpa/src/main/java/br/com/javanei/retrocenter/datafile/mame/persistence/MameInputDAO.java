package br.com.javanei.retrocenter.datafile.mame.persistence;

import br.com.javanei.retrocenter.datafile.mame.entity.MameInputEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameInputDAO extends JpaRepository<MameInputEntity, Long> {
}
