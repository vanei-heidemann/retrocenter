package br.com.javanei.retrocenter.datafile.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "DATAFILE", indexes = {
        @Index(name = "DATAFILE_0001", unique = true, columnList = "NAME,CATALOG,VERSION"),
        @Index(name = "DATAFILE_0002", unique = false, columnList = "CATALOG,VERSION")
})
@NamedQueries({
        @NamedQuery(name = "DatafileEntity.findByUniqueFull", query = "SELECT o from DatafileEntity o WHERE name = :name AND o.catalog = :catalog AND o.version = :version"),
        @NamedQuery(name = "DatafileEntity.findByUnique", query = "SELECT new DatafileEntity(id, name, catalog, version, description, author, date, email, homepage, url, comment) from DatafileEntity o WHERE name = :name AND o.catalog = :catalog AND o.version = :version")
})
public class DatafileEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DATAFILE_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 160, nullable = false)
    private String name;

    @Column(name = "CATALOG", length = 32, nullable = false)
    private String catalog;

    @Column(name = "VERSION", length = 64, nullable = false)
    private String version;

    @Column(name = "DESCRIPTION", length = 255, nullable = true)
    private String description;

    @Column(name = "AUTHOR", length = 400, nullable = true)
    private String author;

    @Column(name = "DATE", length = 32, nullable = true)
    private String date;

    @Column(name = "EMAIL", length = 255, nullable = true)
    private String email;

    @Column(name = "HOMEPAGE", length = 128, nullable = true)
    private String homepage;

    @Column(name = "URL", length = 128, nullable = true)
    private String url;

    @Column(name = "COMMENT", length = 255, nullable = true)
    private String comment;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "datafile")
    private Set<DatafileArtifactEntity> artifacts = new HashSet<>();

    public DatafileEntity() {
    }

    public DatafileEntity(Long id) {
        this.id = id;
    }

    public DatafileEntity(String name, String catalog, String version, String description, String author, String date, String email, String homepage, String url, String comment) {
        this.name = name;
        this.catalog = catalog;
        this.version = version;
        this.description = description;
        this.author = author;
        this.date = date;
        this.email = email;
        this.homepage = homepage;
        this.url = url;
        this.comment = comment;
    }

    public DatafileEntity(Long id, String name, String catalog, String version, String description, String author, String date, String email, String homepage, String url, String comment) {
        this.id = id;
        this.name = name;
        this.catalog = catalog;
        this.version = version;
        this.description = description;
        this.author = author;
        this.date = date;
        this.email = email;
        this.homepage = homepage;
        this.url = url;
        this.comment = comment;
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

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Set<DatafileArtifactEntity> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(Set<DatafileArtifactEntity> artifacts) {
        this.artifacts = artifacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatafileEntity that = (DatafileEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(catalog, that.catalog) &&
                Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, catalog, version);
    }
}
