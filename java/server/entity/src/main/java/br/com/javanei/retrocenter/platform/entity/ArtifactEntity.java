package br.com.javanei.retrocenter.platform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "ARTIFACT")
public class ArtifactEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PLATFORM_ID", nullable = false)
    private Long id;

    @Column(name = "CODE", length = 255, nullable = false, unique = true)
    private String code;

    @Column(name = "NAME", length = 255, nullable = false, unique = false)
    private String name;

    public ArtifactEntity() {
    }

    public ArtifactEntity(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

        ArtifactEntity that = (ArtifactEntity) o;

        return code != null ? code.equals(that.code) : that.code == null;
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }
}
