package br.com.javanei.retrocenter.datafile.clrmamepro;

import br.com.javanei.retrocenter.common.DatafileCatalogEnum;

import java.beans.Transient;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CMProHeader implements Serializable {
    private static final long serialVersionUID = 1L;

    // Standard fields
    private String name; // Name of the emulator without a version number.
    private String description; // Name of the emulator with a version number.
    private String catalog;
    private String category; // General comment about the emulator (e.g. the systems or game types it supports).
    private String version; // Vsersion number of the data file. I would recommend using something like a date encoded version number (YYYYMMDD is preferable to DDMMYYYY as it can be sorted and is unambiguous).
    private String author; // Your name and e-mail/web address.
    private String homepage;
    private String url;
    private String forcemerging; // To force CMProDatafile to use a particular merging format (none/split/full).
    private String forcezipping; // To force CMProDatafile to use ZIPs (yes) or directories (no).
    //
    /**
     * Custom fields, used in some managers
     * Common values:
     * - header
     * - comment
     * - url
     * - homepage
     * - forcenodump
     */
    private Map<String, String> customFields = new HashMap<>();

    public CMProHeader() {
    }

    public CMProHeader(String name, String catalog, String version, String description, String category, String author, String homepage,
                       String url, String forcemerging, String forcezipping) {
        this.name = name;
        this.setCatalog(catalog);
        this.description = description;
        this.category = category;
        this.version = version;
        this.author = author;
        this.homepage = homepage;
        this.url = url;
        this.forcemerging = forcemerging;
        this.forcezipping = forcezipping;
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
        if (this.catalog == null && DatafileCatalogEnum.isValid(description)) {
            this.setCatalog(DatafileCatalogEnum.fromName(description));
        }
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.setCatalog(DatafileCatalogEnum.fromName(catalog));
    }

    @Transient
    public void setCatalog(DatafileCatalogEnum catalog) {
        this.catalog = catalog.name();
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

    public Map<String, String> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(Map<String, String> customFields) {
        this.customFields = customFields;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
        if (this.catalog == null && DatafileCatalogEnum.isValid(homepage)) {
            this.setCatalog(DatafileCatalogEnum.fromName(homepage));
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        if (this.catalog == null && DatafileCatalogEnum.isValid(url)) {
            this.setCatalog(DatafileCatalogEnum.fromName(url));
        }
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

    public void setComment(String comment) {
        this.addCustomField("comment", comment);
        if (this.catalog == null && DatafileCatalogEnum.isValid(comment)) {
            this.setCatalog(DatafileCatalogEnum.fromName(comment));
        }
    }

    public boolean addCustomField(String key, String value) {
        if (!this.customFields.containsKey(key)) {
            this.customFields.put(key, value);
            return true;
        }
        return false;
    }

    public String getCustomField(String key) {
        return this.customFields.get(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CMProHeader header = (CMProHeader) o;
        return Objects.equals(name, header.name) &&
                Objects.equals(description, header.description) &&
                Objects.equals(catalog, header.catalog) &&
                Objects.equals(version, header.version) &&
                Objects.equals(author, header.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, catalog, version, author);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("clrmamepro (\n");
        if (this.name != null)
            sb.append("\t").append("name \"").append(this.name).append("\"\n");
        if (this.description != null)
            sb.append("\t").append("description \"").append(this.description).append("\"\n");
        if (this.category != null)
            sb.append("\t").append("category \"").append(this.category).append("\"\n");
        if (this.version != null)
            sb.append("\t").append("version \"").append(this.version).append("\"\n");
        if (this.author != null)
            sb.append("\t").append("author \"").append(this.author).append("\"\n");
        if (this.homepage != null)
            sb.append("\t").append("homepage \"").append(this.homepage).append("\"\n");
        if (this.url != null)
            sb.append("\t").append("url \"").append(this.url).append("\"\n");
        if (this.forcemerging != null)
            sb.append("\t").append("forcemerging \"").append(this.forcemerging).append("\"\n");
        if (this.forcezipping != null)
            sb.append("\t").append("forcezipping \"").append(this.forcezipping).append("\"\n");
        if (this.customFields != null && !this.customFields.isEmpty()) {
            for (String key : this.customFields.keySet()) {
                sb.append("\t").append(key).append(" \"").append(this.customFields.get(key)).append("\"\n");
            }
        }
        sb.append(")\n");
        sb.append("\n");
        return sb.toString();
    }
}
