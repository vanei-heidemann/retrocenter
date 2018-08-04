package br.com.javanei.retrocenter.datafile.clrmamepro.persistence;

import br.com.javanei.retrocenter.datafile.clrmamepro.entity.CMProDatafileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CMProDatafileDAO extends JpaRepository<CMProDatafileEntity, Long> {
    CMProDatafileEntity findByUniqueFull(@Param("name") String name, @Param("catalog") String catalog, @Param("version") String version);

    CMProDatafileEntity findByUnique(@Param("name") String name, @Param("catalog") String catalog, @Param("version") String version);

    Page<CMProDatafileEntity> findByNameLike(@Param("name") String name, Pageable pageable);
}
