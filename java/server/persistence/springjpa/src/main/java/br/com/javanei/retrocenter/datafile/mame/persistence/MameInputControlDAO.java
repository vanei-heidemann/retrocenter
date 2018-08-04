package br.com.javanei.retrocenter.datafile.mame.persistence;

import br.com.javanei.retrocenter.datafile.mame.entity.MameInputControlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameInputControlDAO extends JpaRepository<MameInputControlEntity, Long> {
}
