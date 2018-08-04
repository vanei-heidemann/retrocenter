package br.com.javanei.retrocenter.datafile.mame;

import br.com.javanei.retrocenter.util.StringUtil;
import br.com.javanei.retrocenter.util.ValidValuesUtil;

import java.io.Serializable;
import java.util.Objects;

public class MameRom implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String bios;
    private Long size;
    private String crc;
    private String sha1;
    private String merge;
    private String region;
    private String offset;
    private String status; // (baddump|nodump|good) "good"
    private String optional; // (yes|no) "no"

    public MameRom() {
    }

    public MameRom(String name, String bios, Long size, String crc, String sha1, String merge, String region,
                   String offset, String status, String optional) {
        this.name = name;
        this.bios = bios;
        this.size = size;
        this.crc = crc;
        this.sha1 = sha1;
        this.merge = merge;
        this.region = region;
        this.offset = offset;
        this.status = status;
        this.optional = optional;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBios() {
        return bios;
    }

    public void setBios(String bios) {
        this.bios = bios;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = new Long(size);
    }

    public void setSize(Long size) {
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

    public String getMerge() {
        return merge;
    }

    public void setMerge(String merge) {
        this.merge = merge;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = ValidValuesUtil.validateValue(status, ValidValuesUtil.BADDUMP_NODUMP_GOOD);
    }

    public String getOptional() {
        return optional;
    }

    public void setOptional(String optional) {
        this.optional = ValidValuesUtil.validateValue(optional, ValidValuesUtil.YES_NO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MameRom rom = (MameRom) o;
        return Objects.equals(name, rom.name) &&
                Objects.equals(bios, rom.bios) &&
                Objects.equals(size, rom.size) &&
                Objects.equals(crc, rom.crc) &&
                Objects.equals(sha1, rom.sha1) &&
                Objects.equals(merge, rom.merge) &&
                Objects.equals(region, rom.region) &&
                Objects.equals(offset, rom.offset) &&
                Objects.equals(status, rom.status) &&
                Objects.equals(optional, rom.optional);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, bios, size, crc, sha1, merge, region, offset, status, optional);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<rom");
        if (this.name != null) {
            sb.append(" name=\"").append(StringUtil.escapeXMLEntities(this.name)).append("\"");
        }
        if (this.merge != null) {
            sb.append(" merge=\"").append(StringUtil.escapeXMLEntities(this.merge)).append("\"");
        }
        if (this.bios != null) {
            sb.append(" bios=\"").append(this.bios).append("\"");
        }
        if (this.size != null) {
            sb.append(" size=\"").append(this.size).append("\"");
        }
        if (this.crc != null) {
            sb.append(" crc=\"").append(this.crc).append("\"");
        }
        if (this.sha1 != null) {
            sb.append(" sha1=\"").append(this.sha1).append("\"");
        }
        if (this.status != null && !status.equals("good")) {
            sb.append(" status=\"").append(this.status).append("\"");
        }
        if (this.region != null) {
            sb.append(" region=\"").append(this.region).append("\"");
        }
        if (this.offset != null) {
            sb.append(" offset=\"").append(this.offset).append("\"");
        }
        if (this.optional != null && !this.optional.equals("no")) {
            sb.append(" optional=\"").append(this.optional).append("\"");
        }
        sb.append("/>").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
