package br.com.javanei.retrocenter.datafile.persistence;

import br.com.javanei.retrocenter.datafile.entity.DatafileEntity;
import br.com.javanei.retrocenter.platform.entity.PlatformEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DatafileDAO extends JpaRepository<DatafileEntity, Long> {
    DatafileEntity findByUniqueFull(@Param("name") String name, @Param("catalog") String catalog, @Param("version") String version);

    DatafileEntity findByUnique(@Param("name") String name, @Param("catalog") String catalog, @Param("version") String version);

    Page<DatafileEntity> findByCatalogAndNameLike(@Param("catalog") String catalog, @Param("name") String name, Pageable pageable);

    Page<DatafileEntity> findByCatalogAndNameLikeAndPlatform(@Param("catalog") String catalog, @Param("name") String name,
                                                             @Param("platform") PlatformEntity platform, Pageable pageable);

    Page<DatafileEntity> findByNameLike(@Param("name") String name, Pageable pageable);

    Page<DatafileEntity> findByNameLikeAndPlatform(@Param("name") String name, @Param("platform") PlatformEntity platform, Pageable pageable);

    Page<DatafileEntity> findByCatalog(@Param("catalog") String catalog, Pageable pageable);

    Page<DatafileEntity> findByCatalogAndPlatform(@Param("catalog") String catalog, @Param("platform") PlatformEntity platform, Pageable pageable);

    List<Long> findPlatformIDsByNameAndCatalog(@Param("name") String name, @Param("catalog") String catalog);

    List<DatafileEntity> findByNameAndCatalogAndNullPlatform(@Param("name") String name, @Param("catalog") String catalog);

    Page<DatafileEntity> findByPlatform(@Param("platform") PlatformEntity platform, Pageable pageable);
}
