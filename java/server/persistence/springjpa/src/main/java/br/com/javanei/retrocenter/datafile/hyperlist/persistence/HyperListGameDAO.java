package br.com.javanei.retrocenter.datafile.hyperlist.persistence;

import br.com.javanei.retrocenter.datafile.hyperlist.entity.HyperListGameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface HyperListGameDAO extends JpaRepository<HyperListGameEntity, Long> {
    HyperListGameEntity findByDatafileAndUnique(@Param("datafileName") String datafileName,
                                                @Param("lastUpdate") String lastUpdate,
                                                @Param("version") String version,
                                                @Param("name") String name,
                                                @Param("index") String index,
                                                @Param("image") String image);
}
