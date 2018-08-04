package br.com.javanei.retrocenter.datafile.logiqx.entity;

import br.com.javanei.retrocenter.datafile.logiqx.LogiqxDatafile;
import br.com.javanei.retrocenter.datafile.logiqx.LogiqxHeader;

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
@Table(name = "LOGIQX_DATAFILE", indexes = {
        @Index(name = "LOGIQX_DATAFILE_0001", unique = true, columnList = "NAME,CATALOG,VERSION")
})
@NamedQueries({
        @NamedQuery(name = "LogiqxDatafileEntity.findByUniqueFull", query = "SELECT o from LogiqxDatafileEntity o WHERE name = :name AND o.catalog = :catalog AND o.version = :version"),
        @NamedQuery(name = "LogiqxDatafileEntity.findByUnique", query = "SELECT new LogiqxDatafileEntity(id, name, catalog, version, category, description, build, debug, date, author, email, homepage, url, comment, header, forcemerging, forcenodump, forcepacking, plugin, rommode, biosmode, samplemode, lockrommode, lockbiosmode, locksamplemode) from LogiqxDatafileEntity o WHERE name = :name AND o.catalog = :catalog AND o.version = :version")
})
public class LogiqxDatafileEntity implements Serializable {
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

    @Column(name = "BUILD", length = 16, nullable = true)
    private String build;

    @Column(name = "DEBUG", length = 3, nullable = true)
    private String debug = "no";

    @Column(name = "DATE", length = 32, nullable = true)
    private String date;

    @Column(name = "AUTHOR", length = 400, nullable = true)
    private String author;

    @Column(name = "EMAIL", length = 255, nullable = true)
    private String email;

    @Column(name = "HOMEPAGE", length = 128, nullable = true)
    private String homepage;

    @Column(name = "URL", length = 128, nullable = true)
    private String url;

    @Column(name = "COMMENT", length = 255, nullable = true)
    private String comment;

    @Column(name = "HEADER", length = 64, nullable = true)
    private String header;

    @Column(name = "FORCEMERGING", length = 5, nullable = true)
    private String forcemerging;

    @Column(name = "FORCENODUMP", length = 8, nullable = true)
    private String forcenodump;

    @Column(name = "FORCEPACKING", length = 3, nullable = true)
    private String forcepacking;

    @Column(name = "PLUGIN", length = 64, nullable = true)
    private String plugin;

    @Column(name = "ROMMODE", length = 8, nullable = true)
    private String rommode;

    @Column(name = "BIOSMODE", length = 8, nullable = true)
    private String biosmode;

    @Column(name = "SAMPLEMODE", length = 8, nullable = true)
    private String samplemode;

    @Column(name = "LOCKROMMODE", length = 3, nullable = true)
    private String lockrommode;

    @Column(name = "LOCKBIOSMODE", length = 3, nullable = true)
    private String lockbiosmode;

    @Column(name = "LOCKSAMPLEMODE", length = 3, nullable = true)
    private String locksamplemode;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "datafile")
    private Set<LogiqxGameEntity> games = new HashSet<>();

    public LogiqxDatafileEntity() {
    }

    public LogiqxDatafileEntity(String name, String catalog, String version) {
        this.name = name;
        this.catalog = catalog;
        this.version = version;
    }

    public LogiqxDatafileEntity(LogiqxDatafile datafile) {
        this(datafile.getHeader().getName(), datafile.getHeader().getCatalog(), datafile.getHeader().getVersion(),
                datafile.getHeader().getCategory(),
                datafile.getHeader().getDescription(), datafile.getBuild(), datafile.getDebug(),
                datafile.getHeader().getDate(), datafile.getHeader().getAuthor(), datafile.getHeader().getEmail(),
                datafile.getHeader().getHomepage(), datafile.getHeader().getUrl(), datafile.getHeader().getComment(),
                datafile.getHeader().getHeader(), datafile.getHeader().getForcemerging(),
                datafile.getHeader().getForcenodump(), datafile.getHeader().getForcepacking(),
                datafile.getHeader().getPlugin(), datafile.getHeader().getRommode(),
                datafile.getHeader().getBiosmode(), datafile.getHeader().getSamplemode(),
                datafile.getHeader().getLockrommode(), datafile.getHeader().getLockbiosmode(),
                datafile.getHeader().getLocksamplemode());
    }

    public LogiqxDatafileEntity(String name, String catalog, String version, String category, String description, String build,
                                String debug, String date, String author, String email, String homepage, String url,
                                String comment, String header, String forcemerging, String forcenodump,
                                String forcepacking, String plugin, String rommode, String biosmode, String samplemode,
                                String lockrommode, String lockbiosmode, String locksamplemode) {
        this.name = name;
        this.catalog = catalog;
        this.version = version;
        this.category = category;
        this.description = description;
        this.build = build;
        this.debug = debug;
        this.date = date;
        this.author = author;
        this.email = email;
        this.homepage = homepage;
        this.url = url;
        this.comment = comment;
        this.header = header;
        this.forcemerging = forcemerging;
        this.forcenodump = forcenodump;
        this.forcepacking = forcepacking;
        this.plugin = plugin;
        this.rommode = rommode;
        this.biosmode = biosmode;
        this.samplemode = samplemode;
        this.lockrommode = lockrommode;
        this.lockbiosmode = lockbiosmode;
        this.locksamplemode = locksamplemode;
    }

    public LogiqxDatafileEntity(Long id, String name, String catalog, String version, String category, String description, String build,
                                String debug, String date, String author, String email, String homepage, String url,
                                String comment, String header, String forcemerging, String forcenodump,
                                String forcepacking, String plugin, String rommode, String biosmode, String samplemode,
                                String lockrommode, String lockbiosmode, String locksamplemode) {
        this.id = id;
        this.name = name;
        this.catalog = catalog;
        this.category = category;
        this.version = version;
        this.description = description;
        this.build = build;
        this.debug = debug;
        this.date = date;
        this.author = author;
        this.email = email;
        this.homepage = homepage;
        this.url = url;
        this.comment = comment;
        this.header = header;
        this.forcemerging = forcemerging;
        this.forcenodump = forcenodump;
        this.forcepacking = forcepacking;
        this.plugin = plugin;
        this.rommode = rommode;
        this.biosmode = biosmode;
        this.samplemode = samplemode;
        this.lockrommode = lockrommode;
        this.lockbiosmode = lockbiosmode;
        this.locksamplemode = locksamplemode;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getForcemerging() {
        return forcemerging;
    }

    public void setForcemerging(String forcemerging) {
        this.forcemerging = forcemerging;
    }

    public String getForcenodump() {
        return forcenodump;
    }

    public void setForcenodump(String forcenodump) {
        this.forcenodump = forcenodump;
    }

    public String getForcepacking() {
        return forcepacking;
    }

    public void setForcepacking(String forcepacking) {
        this.forcepacking = forcepacking;
    }

    public String getPlugin() {
        return plugin;
    }

    public void setPlugin(String plugin) {
        this.plugin = plugin;
    }

    public String getRommode() {
        return rommode;
    }

    public void setRommode(String rommode) {
        this.rommode = rommode;
    }

    public String getBiosmode() {
        return biosmode;
    }

    public void setBiosmode(String biosmode) {
        this.biosmode = biosmode;
    }

    public String getSamplemode() {
        return samplemode;
    }

    public void setSamplemode(String samplemode) {
        this.samplemode = samplemode;
    }

    public String getLockrommode() {
        return lockrommode;
    }

    public void setLockrommode(String lockrommode) {
        this.lockrommode = lockrommode;
    }

    public String getLockbiosmode() {
        return lockbiosmode;
    }

    public void setLockbiosmode(String lockbiosmode) {
        this.lockbiosmode = lockbiosmode;
    }

    public String getLocksamplemode() {
        return locksamplemode;
    }

    public void setLocksamplemode(String locksamplemode) {
        this.locksamplemode = locksamplemode;
    }

    public Set<LogiqxGameEntity> getGames() {
        return games;
    }

    public void setGames(Set<LogiqxGameEntity> games) {
        this.games = games;
    }

    public LogiqxDatafile toVO() {
        LogiqxDatafile datafile = new LogiqxDatafile(this.build, this.debug);
        LogiqxHeader header = new LogiqxHeader(this.name, this.catalog, this.version, this.description, this.category, this.date,
                this.author, this.email, this.homepage, this.url, this.comment, this.header, this.forcemerging,
                this.forcenodump, this.forcepacking, this.plugin, this.rommode, this.biosmode, this.samplemode,
                this.lockrommode, this.lockbiosmode, this.locksamplemode);
        datafile.setHeader(header);

        for (LogiqxGameEntity game : this.games) {
            datafile.addGame(game.toVO());
        }

        return datafile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogiqxDatafileEntity that = (LogiqxDatafileEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(catalog, that.catalog) &&
                Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, catalog, version);
    }
}
