package br.com.javanei.retrocenter.datafile.mame.persistence;

import br.com.javanei.retrocenter.datafile.mame.entity.MameDevicerefEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MameDevicerefDAO extends JpaRepository<MameDevicerefEntity, Long> {
}
