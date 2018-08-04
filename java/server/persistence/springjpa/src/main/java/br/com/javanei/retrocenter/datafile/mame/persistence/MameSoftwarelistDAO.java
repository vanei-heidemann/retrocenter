package br.com.javanei.retrocenter.datafile.mame.persistence;

import br.com.javanei.retrocenter.datafile.mame.entity.MameSoftwarelistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameSoftwarelistDAO extends JpaRepository<MameSoftwarelistEntity, Long> {
}
