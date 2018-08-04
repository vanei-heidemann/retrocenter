package br.com.javanei.retrocenter.datafile.entity;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "DATAFILE_ARTIFACT", indexes = {
        @Index(name = "DATAFILE_ARTIFACT_0001", unique = true, columnList = "DATAFILE_ID,NAME")
})
@NamedQueries({
        @NamedQuery(name = "DatafileArtifactEntity.findByDatafileAndName",
                query = "SELECT o from DatafileArtifactEntity o WHERE o.datafile.name = :datafileName AND o.datafile.catalog = :catalog AND o.datafile.version = :version AND o.name = :name")
})
public class DatafileArtifactEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ARTIFACT_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 255, nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", length = 255, nullable = true)
    private String description;

    @Column(name = "YEAR", length = 32, nullable = true)
    private String year;

    @Column(name = "COMMENT", length = 255, nullable = true)
    private String comment;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "DATAFILE_ARTIFACT_FIELD")
    @MapKeyColumn(name = "FIELD_KEY", length = 128)
    @Column(name = "FIELD_VALUE", length = 255, nullable = true)
    private Map<String, String> fields = new HashMap<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "artifact")
    private List<DatafileArtifactFileEntity> files = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "artifact")
    private Set<DatafileReleaseEntity> releases = new HashSet<>();

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "DATAFILE_ID")
    private DatafileEntity datafile;

    public DatafileArtifactEntity() {
    }

    public DatafileArtifactEntity(Long id) {
        this.id = id;
    }

    public DatafileArtifactEntity(String name, String description, String year, String comment) {
        this.name = name;
        this.description = description;
        this.year = year;
        this.comment = comment;
    }

    public DatafileArtifactEntity(String name, String description, String year, String comment, Map<String, String> fields) {
        this.name = name;
        this.description = description;
        this.year = year;
        this.comment = comment;
        this.fields = fields;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }

    public void addField(String key, String value) {
        if (this.fields == null) {
            this.fields = new HashMap<>();
        }
        this.fields.put(key, value);
    }

    public List<DatafileArtifactFileEntity> getFiles() {
        return files;
    }

    public void setFiles(List<DatafileArtifactFileEntity> files) {
        this.files = files;
    }

    public Set<DatafileReleaseEntity> getReleases() {
        return releases;
    }

    public void setReleases(Set<DatafileReleaseEntity> releases) {
        this.releases = releases;
    }

    public DatafileEntity getDatafile() {
        return datafile;
    }

    public void setDatafile(DatafileEntity datafile) {
        this.datafile = datafile;
    }

    @Transient
    public String getIsbios() {
        return this.fields.get("isbios");
    }

    @Transient
    public void setIsbios(String isbios) {
        if (isbios != null) {
            this.fields.put("isbios", isbios);
        } else {
            this.fields.remove(isbios);
        }
    }

    @Transient
    public String getManufacturer() {
        return this.fields.get("manufacturer");
    }

    @Transient
    public void setManufacturer(String manufacturer) {
        if (manufacturer != null) {
            this.fields.put("manufacturer", manufacturer);
        } else {
            this.fields.remove("manufacturer");
        }
    }

    @Transient
    public String getCloneof() {
        return this.fields.get("cloneof");
    }

    @Transient
    public void setCloneof(String cloneof) {
        if (cloneof != null) {
            this.fields.put("cloneof", cloneof);
        } else {
            this.fields.remove("cloneof");
        }
    }

    @Transient
    public String getRomof() {
        return this.fields.get("romof");
    }

    @Transient
    public void setRomof(String romof) {
        if (romof != null) {
            this.fields.put("romof", romof);
        } else {
            this.fields.remove("romof");
        }
    }

    @Transient
    public String getSampleof() {
        return this.fields.get("sampleof");
    }

    @Transient
    public void setSampleof(String sampleof) {
        if (sampleof != null) {
            this.fields.put("sampleof", sampleof);
        } else {
            this.fields.remove("sampleof");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatafileArtifactEntity that = (DatafileArtifactEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
