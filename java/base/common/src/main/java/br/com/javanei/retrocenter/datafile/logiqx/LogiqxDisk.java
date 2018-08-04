package br.com.javanei.retrocenter.datafile.logiqx;

import br.com.javanei.retrocenter.util.StringUtil;
import br.com.javanei.retrocenter.util.ValidValuesUtil;

import java.io.Serializable;
import java.util.Objects;

/**
 * <!ELEMENT disk EMPTY>
 */
public class LogiqxDisk implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * <!ATTLIST disk name CDATA #REQUIRED>
     */
    private String name;

    /**
     * <!ATTLIST disk sha1 CDATA #IMPLIED>
     */
    private String sha1;

    /**
     * <!ATTLIST disk md5 CDATA #IMPLIED>
     */
    private String md5;

    /**
     * <!ATTLIST disk merge CDATA #IMPLIED>
     */
    private String merge;

    /**
     * <!ATTLIST disk status (baddump|nodump|good|verified) "good">
     */
    private String status = "good";

    public LogiqxDisk() {
    }

    public LogiqxDisk(String name) {
        this.name = name;
    }

    public LogiqxDisk(String name, String sha1, String md5, String merge) {
        this.name = name;
        this.sha1 = sha1;
        this.md5 = md5;
        this.merge = merge;
    }

    public LogiqxDisk(String name, String sha1, String md5, String merge, String status) {
        this.name = name;
        this.sha1 = sha1;
        this.md5 = md5;
        this.merge = merge;
        this.status = status;
    }

    private static void appendAttributeIfNotNull(StringBuilder sb, String name, Object value) {
        if (value != null)
            sb.append(" ").append(name).append("=\"").append(StringUtil.escapeXMLEntities(value.toString())).append("\"");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getMerge() {
        return merge;
    }

    public void setMerge(String merge) {
        this.merge = merge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = ValidValuesUtil.validateValue(status, ValidValuesUtil.BADDUMP_NODUMP_GOOD_VERIFIED);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LogiqxDisk disk = (LogiqxDisk) o;
        return Objects.equals(name, disk.name) &&
                Objects.equals(sha1, disk.sha1) &&
                Objects.equals(md5, disk.md5);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sha1, md5);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<disk");

        appendAttributeIfNotNull(sb, "name", this.name);
        appendAttributeIfNotNull(sb, "sha1", this.sha1);
        appendAttributeIfNotNull(sb, "md5", this.md5);
        appendAttributeIfNotNull(sb, "merge", this.merge);
        appendAttributeIfNotNull(sb, "status", this.status);

        sb.append(" />\n");
        return sb.toString();
    }
}
