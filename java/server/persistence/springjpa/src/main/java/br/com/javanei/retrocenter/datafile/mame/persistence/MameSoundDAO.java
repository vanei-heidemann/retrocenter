package br.com.javanei.retrocenter.datafile.mame.persistence;

import br.com.javanei.retrocenter.datafile.mame.entity.MameSoundEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameSoundDAO extends JpaRepository<MameSoundEntity, Long> {
}
