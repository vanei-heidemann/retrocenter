package br.com.javanei.retrocenter.datafile.mame.persistence;

import br.com.javanei.retrocenter.datafile.mame.entity.MameDisplayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameDisplayDAO extends JpaRepository<MameDisplayEntity, Long> {
}
