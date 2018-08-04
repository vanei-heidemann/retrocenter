package br.com.javanei.retrocenter.datafile.persistence;

import br.com.javanei.retrocenter.datafile.entity.DatafileReleaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatafileReleaseDAO extends JpaRepository<DatafileReleaseEntity, Long> {
}
