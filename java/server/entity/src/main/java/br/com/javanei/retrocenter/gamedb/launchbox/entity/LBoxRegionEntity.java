package br.com.javanei.retrocenter.gamedb.launchbox.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * alter table lbox_region change region_id lbox_region_id bigint(20) NOT NULL AUTO_INCREMENT;
 */
@Entity
@Table(name = "LBOX_REGION")
public class LBoxRegionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LBOX_REGION_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 255, nullable = false, unique = true)
    private String name;

    public LBoxRegionEntity() {
    }

    public LBoxRegionEntity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LBoxRegionEntity that = (LBoxRegionEntity) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "LBoxRegionEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
