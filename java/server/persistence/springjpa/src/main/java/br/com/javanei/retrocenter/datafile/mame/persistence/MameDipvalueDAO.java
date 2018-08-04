package br.com.javanei.retrocenter.datafile.mame.persistence;

import br.com.javanei.retrocenter.datafile.mame.entity.MameDipvalueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameDipvalueDAO extends JpaRepository<MameDipvalueEntity, Long> {
}
