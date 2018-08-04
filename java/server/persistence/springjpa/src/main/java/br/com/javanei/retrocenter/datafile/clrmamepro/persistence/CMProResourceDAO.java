package br.com.javanei.retrocenter.datafile.clrmamepro.persistence;

import br.com.javanei.retrocenter.datafile.clrmamepro.entity.CMProResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CMProResourceDAO extends JpaRepository<CMProResourceEntity, Long> {
    CMProResourceEntity findByDatafile_NameAndDatafile_CatalogAndDatafile_VersionAndName(@Param("datafileName") String datafileName,
                                                                                         @Param("catalog") String catalog,
                                                                                         @Param("version") String version,
                                                                                         @Param("name") String name);
}
