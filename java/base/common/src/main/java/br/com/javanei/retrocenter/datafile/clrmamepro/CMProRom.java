package br.com.javanei.retrocenter.datafile.clrmamepro;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Objects;

public class CMProRom implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Long size;
    /**
     * CRC must have 8 characters
     */
    private String crc;
    /**
     * SHA1 must have 40 characters
     */
    private String sha1;
    /**
     * MD5 must have 32 characters
     */
    private String md5;
    private String region;
    /**
     * Common values:
     * - baddump
     * - verified
     */
    private String flags;

    public CMProRom() {
    }

    public CMProRom(String name, Long size, String crc, String sha1, String md5, String region, String flags) {
        this.name = name;
        this.size = size;
        this.crc = crc;
        this.sha1 = sha1;
        this.md5 = md5;
        this.region = region;
        this.flags = flags;
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

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
        if (this.crc != null) {
            while (this.crc.length() < 8) {
                this.crc = "0" + this.crc;
            }
        }
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
        if (this.sha1 != null) {
            while (this.sha1.length() < 40) {
                this.sha1 = "0" + this.sha1;
            }
        }
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
        if (this.md5 != null) {
            while (this.md5.length() < 32) {
                this.md5 = "0" + this.md5;
            }
        }
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    @Transient
    public boolean isNodump() {
        return this.crc == null && this.md5 == null && this.sha1 == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CMProRom rom = (CMProRom) o;
        return Objects.equals(name, rom.name) &&
                Objects.equals(size, rom.size) &&
                Objects.equals(crc, rom.crc) &&
                Objects.equals(sha1, rom.sha1) &&
                Objects.equals(md5, rom.md5) &&
                Objects.equals(region, rom.region) &&
                Objects.equals(flags, rom.flags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size, crc, sha1, md5, region, flags);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\trom ( name \"").append(this.name).append("\"");
        if (this.size != null) sb.append(" size ").append(this.size);
        if (this.crc != null) sb.append(" crc ").append(this.crc);
        if (this.sha1 != null) sb.append(" sha1 ").append(this.sha1);
        if (this.md5 != null) sb.append(" md5 ").append(this.md5);
        if (this.region != null) sb.append(" region ").append(this.region);
        if (this.flags != null) sb.append(" flags ").append(this.flags);
        if (this.isNodump()) sb.append(" nodump");
        sb.append(" )\n");
        return sb.toString();
    }
}
