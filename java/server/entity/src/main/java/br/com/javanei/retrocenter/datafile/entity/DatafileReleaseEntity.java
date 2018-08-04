package br.com.javanei.retrocenter.datafile.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "DATAFILE_RELEASE", indexes = {
        @Index(name = "DATAFILE_RELEASE_0001", unique = true, columnList = "ARTIFACT_ID,NAME,REGION,LANGUAGE,DATE,ISDEFAULT")
})
public class DatafileReleaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RELEASE_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 255, nullable = false)
    private String name;

    @Column(name = "REGION", length = 128, nullable = true)
    private String region;

    @Column(name = "LANGUAGE", length = 128, nullable = true)
    private String language;

    @Column(name = "DATE", length = 32, nullable = true)
    private String date;

    @Column(name = "ISDEFAULT", length = 3, nullable = true)
    private String _default;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ARTIFACT_ID")
    private DatafileArtifactEntity artifact;

    public DatafileReleaseEntity() {
    }

    public DatafileReleaseEntity(Long id) {
        this.id = id;
    }

    public DatafileReleaseEntity(String name, String region, String language, String date, String _default) {
        this.name = name;
        this.region = region;
        this.language = language;
        this.date = date;
        this._default = _default;
    }

    public DatafileReleaseEntity(Long id, String name, String region, String language, String date, String _default) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.language = language;
        this.date = date;
        this._default = _default;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDefault() {
        return _default;
    }

    public void setDefault(String _default) {
        this._default = _default;
    }

    public DatafileArtifactEntity getArtifact() {
        return artifact;
    }

    public void setArtifact(DatafileArtifactEntity artifact) {
        this.artifact = artifact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatafileReleaseEntity that = (DatafileReleaseEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(region, that.region) &&
                Objects.equals(language, that.language) &&
                Objects.equals(date, that.date) &&
                Objects.equals(_default, that._default);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, region, language, date, _default);
    }
}
