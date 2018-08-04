package br.com.javanei.retrocenter.datafile.mame.persistence;

import br.com.javanei.retrocenter.datafile.mame.entity.MameConfsettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameConfsettingDAO extends JpaRepository<MameConfsettingEntity, Long> {
}
