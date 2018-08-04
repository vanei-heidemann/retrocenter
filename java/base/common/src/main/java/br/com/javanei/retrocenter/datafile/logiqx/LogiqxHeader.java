package br.com.javanei.retrocenter.datafile.logiqx;

import br.com.javanei.retrocenter.common.DatafileCatalogEnum;
import br.com.javanei.retrocenter.util.ValidValuesUtil;

import java.beans.Transient;
import java.io.Serializable;

/**
 * <!ELEMENT header (name, description, category?, version, date?, author, email?, homepage?, url?, comment?, clrmamepro?, romcenter?)>
 */
public class LogiqxHeader implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * <!ELEMENT name (#PCDATA)>
     */
    private String name;
    private String catalog;
    /**
     * <!ELEMENT version (#PCDATA)>
     */
    private String version;
    /**
     * <!ELEMENT description (#PCDATA)>
     */
    private String description;
    /**
     * <!ELEMENT category (#PCDATA)>
     */
    private String category;
    /**
     * <!ELEMENT date (#PCDATA)>
     */
    private String date;
    /**
     * <!ELEMENT author (#PCDATA)>
     */
    private String author;
    /**
     * <!ELEMENT email (#PCDATA)>
     */
    private String email;
    /**
     * <!ELEMENT homepage (#PCDATA)>
     */
    private String homepage;
    /**
     * <!ELEMENT url (#PCDATA)>
     */
    private String url;
    /**
     * <!ELEMENT comment (#PCDATA)>
     */
    private String comment;

    // ///////////// clrmamepro properties

    /**
     * <!ATTLIST clrmamepro header CDATA #IMPLIED>
     */
    private String header;

    /**
     * <!ATTLIST clrmamepro forcemerging (none|split|full) "split">
     */
    private String forcemerging;

    /**
     * <!ATTLIST clrmamepro forcenodump (obsolete|required|ignore) "obsolete">
     */
    private String forcenodump;

    /**
     * <!ATTLIST clrmamepro forcepacking (zip|unzip) "zip">
     */
    private String forcepacking;

    // ///////////// romcenter properties

    /**
     * <!ATTLIST romcenter plugin CDATA #IMPLIED>
     */
    private String plugin;

    /**
     * <!ATTLIST romcenter rommode (merged|split|unmerged) "split">
     */
    private String rommode;

    /**
     * <!ATTLIST romcenter biosmode (merged|split|unmerged) "split">
     */
    private String biosmode;

    /**
     * <!ATTLIST romcenter samplemode (merged|unmerged) "merged">
     */
    private String samplemode;

    /**
     * <!ATTLIST romcenter lockrommode (yes|no) "no">
     */
    private String lockrommode;

    /**
     * <!ATTLIST romcenter lockbiosmode (yes|no) "no">
     */
    private String lockbiosmode;

    /**
     * <!ATTLIST romcenter locksamplemode (yes|no) "no">
     */
    private String locksamplemode;

    public LogiqxHeader() {
    }

    public LogiqxHeader(String name, String catalog, String version, String description, String category, String date, String author,
                        String email, String homepage, String url, String comment) {
        this.name = name;
        this.setCatalog(catalog);
        this.description = description;
        this.category = category;
        this.version = version;
        this.date = date;
        this.author = author;
        this.email = email;
        this.homepage = homepage;
        this.url = url;
        this.comment = comment;
    }

    public LogiqxHeader(String name, String catalog, String version, String description, String category, String date, String author,
                        String email, String homepage, String url, String comment, String header, String forcemerging,
                        String forcenodump, String forcepacking, String plugin, String rommode, String biosmode, String samplemode,
                        String lockrommode, String lockbiosmode, String locksamplemode) {
        this.name = name;
        this.setCatalog(catalog);
        this.description = description;
        this.category = category;
        this.version = version;
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

    private static void appendTagIfNotNull(StringBuilder sb, String name, Object value) {
        if (value != null)
            sb.append("\t\t<").append(name).append(">").append(value).append("</").append(name).append(">\n");
    }

    private static void appendAttributeIfNotNull(StringBuilder sb, String name, Object value) {
        if (value != null)
            sb.append(" ").append(name).append("=\"").append(value).append("\"");
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
        if (this.catalog == null && DatafileCatalogEnum.isValid(description)) {
            this.setCatalog(DatafileCatalogEnum.fromName(description));
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
        if (this.catalog == null && DatafileCatalogEnum.isValid(category)) {
            this.setCatalog(DatafileCatalogEnum.fromName(category));
        }
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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
        if (this.catalog == null && DatafileCatalogEnum.isValid(email)) {
            this.setCatalog(DatafileCatalogEnum.fromName(email));
        }
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
        if (this.catalog == null && DatafileCatalogEnum.isValid(comment)) {
            this.setCatalog(DatafileCatalogEnum.fromName(comment));
        }
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getForcemerging() {
        return isClrmamepro() && forcemerging == null ? "split" : this.forcemerging;
    }

    public void setForcemerging(String forcemerging) {
        this.forcemerging = ValidValuesUtil.validateValue(forcemerging, ValidValuesUtil.NONE_SPLIT_FULL);
    }

    public String getForcenodump() {
        return isClrmamepro() && forcenodump == null ? "obsolete" : this.forcenodump;
    }

    public void setForcenodump(String forcenodump) {
        this.forcenodump = ValidValuesUtil.validateValue(forcenodump, ValidValuesUtil.OBSOLETE_REQUIRED_IGNORE);
    }

    public String getForcepacking() {
        return isClrmamepro() && forcepacking == null ? "zip" : this.forcepacking;
    }

    public void setForcepacking(String forcepacking) {
        this.forcepacking = ValidValuesUtil.validateValue(forcepacking, ValidValuesUtil.ZIP_UNZIP);
    }

    public String getPlugin() {
        return plugin;
    }

    public void setPlugin(String plugin) {
        this.plugin = plugin;
    }

    public String getRommode() {
        return isRomcenter() && rommode == null ? "split" : this.rommode;
    }

    public void setRommode(String rommode) {
        this.rommode = ValidValuesUtil.validateValue(rommode, ValidValuesUtil.MERGED_SPLIT_UNMERGED);
    }

    public String getBiosmode() {
        return isRomcenter() && biosmode == null ? "split" : this.biosmode;
    }

    public void setBiosmode(String biosmode) {
        this.biosmode = ValidValuesUtil.validateValue(biosmode, ValidValuesUtil.MERGED_SPLIT_UNMERGED);
    }

    public String getSamplemode() {
        return isRomcenter() && samplemode == null ? "merged" : this.samplemode;
    }

    public void setSamplemode(String samplemode) {
        this.samplemode = ValidValuesUtil.validateValue(samplemode, ValidValuesUtil.MERGED_UNMERGED);
    }

    public String getLockrommode() {
        return isRomcenter() && lockrommode == null ? "no" : this.lockrommode;
    }

    public void setLockrommode(String lockrommode) {
        this.lockrommode = ValidValuesUtil.validateValue(lockrommode, ValidValuesUtil.YES_NO);
    }

    public String getLockbiosmode() {
        return isRomcenter() && lockbiosmode == null ? "no" : this.lockbiosmode;
    }

    public void setLockbiosmode(String lockbiosmode) {
        this.lockbiosmode = ValidValuesUtil.validateValue(lockbiosmode, ValidValuesUtil.YES_NO);
    }

    public String getLocksamplemode() {
        return isRomcenter() && locksamplemode == null ? "no" : this.locksamplemode;
    }

    public void setLocksamplemode(String locksamplemode) {
        this.locksamplemode = ValidValuesUtil.validateValue(locksamplemode, ValidValuesUtil.YES_NO);
    }

    @Transient
    public boolean isClrmamepro() {
        return this.header != null || this.forcemerging != null || this.forcenodump != null || this.forcepacking != null;
    }

    @Transient
    public boolean isRomcenter() {
        return this.plugin != null || this.rommode != null || this.biosmode != null || this.samplemode != null
                || this.lockrommode != null || this.lockbiosmode != null || this.locksamplemode != null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t<header>\n");
        appendTagIfNotNull(sb, "name", this.name);
        appendTagIfNotNull(sb, "description", this.description);
        appendTagIfNotNull(sb, "category", this.category);
        appendTagIfNotNull(sb, "version", this.version);
        appendTagIfNotNull(sb, "date", this.date);
        appendTagIfNotNull(sb, "author", this.author);
        appendTagIfNotNull(sb, "email", this.email);
        appendTagIfNotNull(sb, "homepage", this.homepage);
        appendTagIfNotNull(sb, "url", this.url);
        appendTagIfNotNull(sb, "comment", this.comment);

        if (isClrmamepro()) {
            sb.append("\t\t<clrmamepro");
            appendAttributeIfNotNull(sb, "header", this.header);
            appendAttributeIfNotNull(sb, "forcemerging", this.forcemerging);
            appendAttributeIfNotNull(sb, "forcenodump", this.forcenodump);
            appendAttributeIfNotNull(sb, "forcepacking", this.forcepacking);
            sb.append(" />\n");
        }

        if (isRomcenter()) {
            sb.append("\t\t<romcenter");
            appendAttributeIfNotNull(sb, "plugin", this.plugin);
            appendAttributeIfNotNull(sb, "rommode", this.rommode);
            appendAttributeIfNotNull(sb, "biosmode", this.biosmode);
            appendAttributeIfNotNull(sb, "samplemode", this.samplemode);
            appendAttributeIfNotNull(sb, "lockrommode", this.lockrommode);
            appendAttributeIfNotNull(sb, "lockbiosmode", this.lockbiosmode);
            appendAttributeIfNotNull(sb, "locksamplemode", this.locksamplemode);
            sb.append(" />\n");
        }

        sb.append("\t</header>\n");
        return sb.toString();
    }
}
