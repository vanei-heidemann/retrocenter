package br.com.javanei.retrocenter.datafile.mame.persistence;

import br.com.javanei.retrocenter.datafile.mame.entity.MameChipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameChipDAO extends JpaRepository<MameChipEntity, Long> {
}
