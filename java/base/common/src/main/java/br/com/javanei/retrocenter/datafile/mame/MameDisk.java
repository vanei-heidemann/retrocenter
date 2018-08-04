package br.com.javanei.retrocenter.datafile.mame;

import br.com.javanei.retrocenter.util.StringUtil;
import br.com.javanei.retrocenter.util.ValidValuesUtil;

import java.io.Serializable;
import java.util.Objects;

public class MameDisk implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String sha1;
    private String merge;
    private String region;
    private Integer index;
    private String writable; // (yes|no) "no"
    private String status; // (baddump|nodump|good) "good"
    private String optional; // (yes|no) "no"

    public MameDisk() {
    }

    public MameDisk(String name, String sha1, String merge, String region, Integer index, String writable,
                    String status, String optional) {
        this.name = name;
        this.sha1 = sha1;
        this.merge = merge;
        this.region = region;
        this.index = index;
        this.writable = writable;
        this.status = status;
        this.optional = optional;
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

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public void setIndex(String index) {
        if (index != null)
            this.index = new Integer(index);
    }

    public String getWritable() {
        return writable;
    }

    public void setWritable(String writable) {
        this.writable = ValidValuesUtil.validateValue(writable, ValidValuesUtil.YES_NO);
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
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameDisk mameDisk = (MameDisk) o;
        return Objects.equals(name, mameDisk.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<disk");
        if (this.name != null) {
            sb.append(" name=\"").append(this.name).append("\"");
        }
        if (this.merge != null) {
            sb.append(" merge=\"").append(this.merge).append("\"");
        }
        if (this.sha1 != null) {
            sb.append(" sha1=\"").append(this.sha1).append("\"");
        }
        if (this.status != null && !this.status.equals("good")) {
            sb.append(" status=\"").append(this.status).append("\"");
        }
        if (this.region != null) {
            sb.append(" region=\"").append(this.region).append("\"");
        }
        if (this.index != null) {
            sb.append(" index=\"").append(this.index).append("\"");
        }
        if (this.writable != null && !this.writable.equals("no")) {
            sb.append(" writable=\"").append(this.writable).append("\"");
        }
        if (this.optional != null && !this.optional.equals("no")) {
            sb.append(" optional=\"").append(this.optional).append("\"");
        }
        sb.append("/>").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
