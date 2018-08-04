package br.com.javanei.retrocenter.datafile.hyperlist.entity;

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
@Table(name = "HYPERLIST_DATAFILE", indexes = {
        @Index(name = "HYPERLIST_DATAFILE_0001", unique = true, columnList = "NAME,LAST_UPDATE,VERSION")
})
@NamedQueries({
        @NamedQuery(name = "HyperListEntity.findByUniqueFull", query = "SELECT o from HyperListEntity o WHERE o.name = :name AND o.lastUpdate = :lastUpdate AND o.version = :version"),
        @NamedQuery(name = "HyperListEntity.findByUnique", query = "SELECT new HyperListEntity(id, name, lastUpdate, version, exporterVersion) from HyperListEntity o WHERE o.name = :name AND o.lastUpdate = :lastUpdate AND o.version = :version")
})
public class HyperListEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "NAME", length = 64, nullable = false)
    public String name;
    @Column(name = "LAST_UPDATE", length = 16, nullable = false)
    public String lastUpdate;
    @Column(name = "VERSION", length = 64, nullable = false)
    public String version;
    @Column(name = "EXPORTER_VERSION", length = 255, nullable = true)
    public String exporterVersion;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DATAFILE_ID", nullable = false)
    private Long id;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "datafile")
    private Set<HyperListGameEntity> games = new HashSet<>();

    public HyperListEntity() {
    }

    public HyperListEntity(Long id) {
        this.id = id;
    }

    public HyperListEntity(String name, String lastUpdate, String version, String exporterVersion) {
        this.name = name;
        this.lastUpdate = lastUpdate;
        this.version = version;
        this.exporterVersion = exporterVersion;
    }

    public HyperListEntity(Long id, String name, String lastUpdate, String version, String exporterVersion) {
        this.id = id;
        this.name = name;
        this.lastUpdate = lastUpdate;
        this.version = version;
        this.exporterVersion = exporterVersion;
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

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getExporterVersion() {
        return exporterVersion;
    }

    public void setExporterVersion(String exporterVersion) {
        this.exporterVersion = exporterVersion;
    }

    public Set<HyperListGameEntity> getGames() {
        return games;
    }

    public void setGames(Set<HyperListGameEntity> games) {
        this.games = games;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HyperListEntity that = (HyperListEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastUpdate, version);
    }

    @Override
    public String toString() {
        return "HyperListEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                ", version='" + version + '\'' +
                ", exporterVersion='" + exporterVersion + '\'' +
                '}';
    }
}
