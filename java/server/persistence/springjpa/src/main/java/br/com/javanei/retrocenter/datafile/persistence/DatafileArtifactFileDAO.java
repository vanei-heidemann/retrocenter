package br.com.javanei.retrocenter.datafile.persistence;

import br.com.javanei.retrocenter.datafile.entity.DatafileArtifactFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatafileArtifactFileDAO extends JpaRepository<DatafileArtifactFileEntity, Long> {
}
