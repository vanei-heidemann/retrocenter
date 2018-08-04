package br.com.javanei.retrocenter.datafile.logiqx;

import br.com.javanei.retrocenter.util.StringUtil;
import br.com.javanei.retrocenter.util.ValidValuesUtil;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Objects;

/**
 * <!ELEMENT rom EMPTY>
 */
public class LogiqxRom implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * <!ATTLIST rom name CDATA #REQUIRED>
     */
    private String name;

    /**
     * <!ATTLIST rom size CDATA #REQUIRED>
     */
    private Long size;

    /**
     * <!ATTLIST rom crc CDATA #IMPLIED>
     */
    private String crc;

    /**
     * <!ATTLIST rom sha1 CDATA #IMPLIED>
     */
    private String sha1;

    /**
     * <!ATTLIST rom md5 CDATA #IMPLIED>
     */
    private String md5;

    /**
     * <!ATTLIST rom merge CDATA #IMPLIED>
     */
    private String merge;

    /**
     * <!ATTLIST rom date CDATA #IMPLIED>
     */
    private String date;

    /**
     * <!ATTLIST rom status (baddump|nodump|good|verified) "good">
     */
    private String status = "good";

    public LogiqxRom() {
    }

    public LogiqxRom(String name, Long size, String crc, String sha1, String md5, String merge, String date) {
        this.name = name;
        this.size = size;
        this.crc = crc;
        this.sha1 = sha1;
        this.md5 = md5;
        this.merge = merge;
        this.date = date;
    }

    public LogiqxRom(String name, Long size, String crc, String sha1, String md5, String merge, String date, String status) {
        this.name = name;
        this.size = size;
        this.crc = crc;
        this.sha1 = sha1;
        this.md5 = md5;
        this.merge = merge;
        this.date = date;
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

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Transient
    public void setSize(String size) {
        this.size = Long.parseLong(size);
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LogiqxRom rom = (LogiqxRom) o;
        return Objects.equals(name, rom.name) &&
                Objects.equals(size, rom.size) &&
                Objects.equals(crc, rom.crc) &&
                Objects.equals(sha1, rom.sha1) &&
                Objects.equals(md5, rom.md5);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size, crc, sha1, md5);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<rom");

        appendAttributeIfNotNull(sb, "name", this.name);
        appendAttributeIfNotNull(sb, "size", this.size);
        appendAttributeIfNotNull(sb, "crc", this.crc);
        appendAttributeIfNotNull(sb, "sha1", this.sha1);
        appendAttributeIfNotNull(sb, "md5", this.md5);
        appendAttributeIfNotNull(sb, "merge", this.merge);
        appendAttributeIfNotNull(sb, "status", this.status);
        appendAttributeIfNotNull(sb, "date", this.date);

        sb.append(" />\n");
        return sb.toString();
    }
}
