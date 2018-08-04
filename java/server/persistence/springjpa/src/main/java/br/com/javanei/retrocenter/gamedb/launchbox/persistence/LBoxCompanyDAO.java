package br.com.javanei.retrocenter.gamedb.launchbox.persistence;

import br.com.javanei.retrocenter.gamedb.launchbox.entity.LBoxCompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface LBoxCompanyDAO extends JpaRepository<LBoxCompanyEntity, Long> {
    LBoxCompanyEntity findByName(@Param("name") String name);
}
