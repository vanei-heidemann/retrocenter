package br.com.javanei.retrocenter.datafile.mame.persistence;

import br.com.javanei.retrocenter.datafile.mame.entity.MameDeviceExtensionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameDeviceExtensionDAO extends JpaRepository<MameDeviceExtensionEntity, Long> {
}
