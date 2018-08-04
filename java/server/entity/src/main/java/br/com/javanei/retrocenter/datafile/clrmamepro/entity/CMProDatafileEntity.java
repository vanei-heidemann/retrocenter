package br.com.javanei.retrocenter.datafile.clrmamepro.entity;

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
import javax.persistence.MapKeyColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "CMPRO_DATAFILE", indexes = {
        @Index(name = "CMPRO_DATAFILE_0001", unique = true, columnList = "NAME,CATALOG,VERSION")
})
@NamedQueries({
        @NamedQuery(name = "CMProDatafileEntity.findByUniqueFull", query = "SELECT o from CMProDatafileEntity o WHERE o.name = :name AND o.catalog = :catalog AND o.version = :version"),
        @NamedQuery(name = "CMProDatafileEntity.findByUnique", query = "SELECT new CMProDatafileEntity(id, name, catalog, version, category, description, author, homepage, url, forcemerging, forcezipping) from CMProDatafileEntity o WHERE o.name = :name AND o.catalog = :catalog AND o.version = :version")
})
public class CMProDatafileEntity implements Serializable {
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

    @Column(name = "CATEGORY", length = 128, nullable = true)
    private String category;

    @Column(name = "DESCRIPTION", length = 255, nullable = true)
    private String description;

    @Column(name = "AUTHOR", length = 400, nullable = true)
    private String author;

    @Column(name = "HOMEPAGE", length = 128, nullable = true)
    private String homepage;

    @Column(name = "URL", length = 128, nullable = true)
    private String url;

    @Column(name = "FORCEMERGING", length = 5, nullable = true)
    private String forcemerging;

    @Column(name = "FORCEZIPPING", length = 3, nullable = true)
    private String forcezipping;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "CMPRO_CUSTOMFIELD")
    @MapKeyColumn(name = "CUSTOM_KEY", length = 160)
    @Column(name = "CUSTOM_VALUE", length = 255, nullable = false)
    private Map<String, String> customFields = new HashMap<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "datafile")
    private Set<CMProGameEntity> games = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "datafile")
    private Set<CMProResourceEntity> resources = new HashSet<>();

    public CMProDatafileEntity() {
    }

    public CMProDatafileEntity(String name, String catalog, String version, String category, String description, String author,
                               String homepage, String url, String forcemerging, String forcezipping) {
        this.name = name;
        this.catalog = catalog;
        this.category = category;
        this.version = version;
        this.description = description;
        this.author = author;
        this.homepage = homepage;
        this.url = url;
        this.forcemerging = forcemerging;
        this.forcezipping = forcezipping;
    }

    public CMProDatafileEntity(Long id, String name, String catalog, String version, String category, String description,
                               String author, String homepage, String url, String forcemerging, String forcezipping) {
        this.id = id;
        this.name = name;
        this.catalog = catalog;
        this.category = category;
        this.version = version;
        this.description = description;
        this.author = author;
        this.homepage = homepage;
        this.url = url;
        this.forcemerging = forcemerging;
        this.forcezipping = forcezipping;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String getForcemerging() {
        return forcemerging;
    }

    public void setForcemerging(String forcemerging) {
        this.forcemerging = forcemerging;
    }

    public String getForcezipping() {
        return forcezipping;
    }

    public void setForcezipping(String forcezipping) {
        this.forcezipping = forcezipping;
    }

    public Map<String, String> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(Map<String, String> customFields) {
        this.customFields = customFields;
    }

    public void addCustomField(String key, String value) {
        if (value != null) {
            this.customFields.put(key, value);
        } else {
            this.customFields.remove(key);
        }
    }

    @Transient
    public String getCustomField(String key) {
        return this.customFields.get(key);
    }

    public Set<CMProGameEntity> getGames() {
        return games;
    }

    public void setGames(Set<CMProGameEntity> games) {
        this.games = games;
    }

    public Set<CMProResourceEntity> getResources() {
        return resources;
    }

    public void setResources(Set<CMProResourceEntity> resources) {
        this.resources = resources;
    }
}
