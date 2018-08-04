package br.com.javanei.retrocenter.datafile.mame.persistence;

import br.com.javanei.retrocenter.datafile.mame.entity.MameDeviceInstanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameDeviceInstanceDAO extends JpaRepository<MameDeviceInstanceEntity, Long> {
}
