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
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "DATAFILE_ARTIFACTFILE", indexes = {
        @Index(name = "DATAFILE_ARTIFACTFILE_0001", unique = false, columnList = "ARTIFACT_ID,FILE_TYPE,NAME,CRC,SHA1,MD5")
})
public class DatafileArtifactFileEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ARTIFACTFILE_ID", nullable = false)
    private Long id;

    @Column(name = "FILE_TYPE", length = 16, nullable = false)
    private String type;

    @Column(name = "NAME", length = 255, nullable = false)
    private String name;

    @Column(name = "SIZE", length = 16, nullable = true)
    private String size;

    @Column(name = "CRC", length = 8, nullable = true)
    private String crc;

    @Column(name = "SHA1", length = 40, nullable = true)
    private String sha1;

    @Column(name = "MD5", length = 32, nullable = true)
    private String md5;

    @Column(name = "DATE", length = 32, nullable = true)
    private String date;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ARTIFACT_ID")
    private DatafileArtifactEntity artifact;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "DATAFILE_ARTIFACTFILE_FIELD")
    @MapKeyColumn(name = "FIELD_KEY", length = 128)
    @Column(name = "FIELD_VALUE", length = 255, nullable = true)
    private Map<String, String> fields = new HashMap<>();

    public DatafileArtifactFileEntity() {
    }

    public DatafileArtifactFileEntity(Long id) {
        this.id = id;
    }

    public DatafileArtifactFileEntity(String type, String name, String size, String crc, String sha1, String md5, String date) {
        this.type = type;
        this.name = name;
        this.size = size;
        this.crc = crc;
        this.sha1 = sha1;
        this.md5 = md5;
        this.date = date;
    }

    public DatafileArtifactFileEntity(Long id, String type, String name, String size, String crc, String sha1, String md5,
                                      String date) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.size = size;
        this.crc = crc;
        this.sha1 = sha1;
        this.md5 = md5;
        this.date = date;
    }

    public DatafileArtifactFileEntity(String type, String name, String size, String crc, String sha1, String md5, String date,
                                      Map<String, String> fields) {
        this.type = type;
        this.name = name;
        this.size = size;
        this.crc = crc;
        this.sha1 = sha1;
        this.md5 = md5;
        this.date = date;
        this.fields = fields;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DatafileArtifactEntity getArtifact() {
        return artifact;
    }

    public void setArtifact(DatafileArtifactEntity artifact) {
        this.artifact = artifact;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }

    @Transient
    public String getStatus() {
        return this.fields.get("status");
    }

    @Transient
    public void setStatus(String status) {
        if (status != null) {
            this.fields.put("status", status);
        } else {
            this.fields.remove("status");
        }
    }

    @Transient
    public String getMerge() {
        return this.fields.get("merge");
    }

    @Transient
    public void setMerge(String merge) {
        if (merge != null) {
            this.fields.put("merge", merge);
        } else {
            this.fields.remove("merge");
        }
    }

    @Transient
    public String getRegion() {
        return this.fields.get("region");
    }

    @Transient
    public void setRegion(String region) {
        if (region != null) {
            this.fields.put("region", region);
        } else {
            this.fields.remove("region");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatafileArtifactFileEntity that = (DatafileArtifactFileEntity) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name);
    }
}
