package br.com.javanei.retrocenter.datafile.mame.persistence;

import br.com.javanei.retrocenter.datafile.mame.entity.MameDeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameDeviceDAO extends JpaRepository<MameDeviceEntity, Long> {
}
