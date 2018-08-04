package br.com.javanei.retrocenter.datafile.mame.persistence;

import br.com.javanei.retrocenter.datafile.mame.entity.MameDipswitchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameDipswitchDAO extends JpaRepository<MameDipswitchEntity, Long> {
}
