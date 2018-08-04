package br.com.javanei.retrocenter.datafile.mame.persistence;

import br.com.javanei.retrocenter.datafile.mame.entity.MameDiskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameDiskDAO extends JpaRepository<MameDiskEntity, Long> {
}
