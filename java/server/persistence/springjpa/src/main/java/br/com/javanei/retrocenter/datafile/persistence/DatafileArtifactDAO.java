package br.com.javanei.retrocenter.datafile.persistence;

import br.com.javanei.retrocenter.datafile.entity.DatafileArtifactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface DatafileArtifactDAO extends JpaRepository<DatafileArtifactEntity, Long> {
    DatafileArtifactEntity findByDatafileAndName(@Param("datafileName") String datafileName, @Param("catalog") String catalog,
                                                 @Param("version") String version, @Param("name") String name);
}
