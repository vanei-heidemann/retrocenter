package br.com.javanei.retrocenter.datafile.common;

import br.com.javanei.retrocenter.common.DatafileCatalogEnum;

import java.beans.Transient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Datafile implements DatafileObject, Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * clrmamepro.header.name, logiqx.header.name
     */
    private String name;
    private String catalog;
    /**
     * clrmamepro.header.version, logiqx.header.version
     */
    private String version;
    /**
     * clrmamepro.header.description, logiqx.header.description
     */
    private String description;
    /**
     * clrmamepro.header.author, logiqx.header.author
     */
    private String author;
    /**
     * logiqx.header.date
     */
    private String date;
    /**
     * logiqx.header.email
     */
    private String email;
    /**
     * clrmamepro.header.homepage, logiqx.header.homepage
     */
    private String homepage;
    /**
     * clrmamepro.header.url, logiqx.header.url
     */
    private String url;
    /**
     * logiqx.header.comment
     */
    private String comment;

    private Set<DatafileArtifact> artifacts = new HashSet<>();

    private String platformName;

    public Datafile() {
    }

    public Datafile(String name) {
        this.name = name;
    }

    public Datafile(String name, String catalog, String version, String description, String author, String date,
                    String email, String homepage, String url, String comment) {
        this.name = name;
        this.setCatalog(catalog);
        this.version = version;
        this.description = description;
        this.author = author;
        this.date = date;
        this.email = email;
        this.homepage = homepage;
        this.url = url;
        this.comment = comment;
    }

    private static void appendXMLAttributeIfNotNull(StringBuilder sb, String name, Object value) {
        if (value != null) {
            sb.append(" ").append(name).append("=\"").append(value).append("\"");
        }
    }

    private static void appendXMLTagIfNotNull(StringBuilder sb, String name, Object value, int ident) {
        if (value != null) {
            for (int i = 0; i < ident; i++) {
                sb.append("\t");
            }
            sb.append("<").append(name).append(">").append(value).append("</").append(name).append(">\n");
        }
    }

    public boolean addArtifact(DatafileArtifact artifact) {
        return this.artifacts.add(artifact);
    }

    public Set<DatafileArtifact> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(Set<DatafileArtifact> artifacts) {
        this.artifacts = artifacts;
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

    @Transient
    public void setCatalog(DatafileCatalogEnum catalog) {
        this.catalog = catalog.name();
    }

    public void setCatalog(String catalog) {
        this.setCatalog(DatafileCatalogEnum.fromName(catalog));
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Datafile datafile = (Datafile) o;
        return Objects.equals(name, datafile.name) &&
                Objects.equals(catalog, datafile.catalog) &&
                Objects.equals(version, datafile.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, catalog, version);
    }

    @Override
    public Datafile toDatafile() {
        return this;
    }

    @Override
    public String toFile() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\"?>\n");
        sb.append("<retrocenter");
        appendXMLAttributeIfNotNull(sb, "name", name);
        appendXMLAttributeIfNotNull(sb, "catalog", catalog);
        appendXMLAttributeIfNotNull(sb, "version", version);
        sb.append(">\n");
        appendXMLTagIfNotNull(sb, "description", description, 1);
        appendXMLTagIfNotNull(sb, "author", author, 1);
        appendXMLTagIfNotNull(sb, "date", date, 1);
        appendXMLTagIfNotNull(sb, "email", email, 1);
        appendXMLTagIfNotNull(sb, "homepage", homepage, 1);
        appendXMLTagIfNotNull(sb, "url", url, 1);
        appendXMLTagIfNotNull(sb, "comment", comment, 1);

        for (DatafileArtifact game : this.artifacts) {
            sb.append("\t<artifact");
            appendXMLAttributeIfNotNull(sb, "name", game.getName());
            appendXMLAttributeIfNotNull(sb, "description", game.getDescription());
            appendXMLAttributeIfNotNull(sb, "year", game.getYear());
            sb.append(">\n");
            for (String key : game.getFields().keySet()) {
                appendXMLTagIfNotNull(sb, key, game.getField(key), 2);
            }

            for (DatafileArtifactFile file : game.getFiles()) {
                sb.append("\t\t<file");
                appendXMLAttributeIfNotNull(sb, "type", file.getType());
                appendXMLAttributeIfNotNull(sb, "name", file.getName());
                appendXMLAttributeIfNotNull(sb, "size", file.getSize());
                appendXMLAttributeIfNotNull(sb, "crc", file.getCrc());
                appendXMLAttributeIfNotNull(sb, "sha1", file.getSha1());
                appendXMLAttributeIfNotNull(sb, "md5", file.getMd5());
                appendXMLAttributeIfNotNull(sb, "date", file.getDate());
                if (game.getFields().size() > 0) {
                    sb.append(">\n");
                    for (String key : game.getFields().keySet()) {
                        appendXMLTagIfNotNull(sb, key, file.getField(key), 3);
                    }
                    sb.append("\t\t</file>\n");
                } else {
                    sb.append("/>\n");
                }
            }

            for (Release release : game.getReleases()) {
                sb.append("\t\t<release");
                appendXMLAttributeIfNotNull(sb, "name", release.getName());
                appendXMLAttributeIfNotNull(sb, "region", release.getRegion());
                appendXMLAttributeIfNotNull(sb, "language", release.getLanguage());
                appendXMLAttributeIfNotNull(sb, "date", release.getDate());
                appendXMLAttributeIfNotNull(sb, "default", release.getDefault());
                sb.append("/>\n");
            }

            sb.append("\t</artifact>\n");
        }

        sb.append("</retrocenter>\n");
        return sb.toString();
    }
}
