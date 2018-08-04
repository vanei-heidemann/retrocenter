package br.com.javanei.retrocenter.datafile.mame.persistence;

import br.com.javanei.retrocenter.datafile.mame.entity.MameBiossetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameBiossetDAO extends JpaRepository<MameBiossetEntity, Long> {
}
