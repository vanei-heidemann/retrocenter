package br.com.javanei.retrocenter.datafile.common;

import java.beans.Transient;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DatafileArtifactFile implements Serializable {
    /**
     * Valid values: ROM, DISK, SAMPLE
     */
    private String type;
    /**
     * logiqx.rom.name, clrmamepro.rom.name
     * logiqx.disk.name, clrmamepro.disk.name
     */
    private String name;
    /**
     * logiqx.rom.size, clrmamepro.rom.size
     * logiqx.disk.sha1, clrmamepro.disk.sha1
     */
    private String size;
    /**
     * logiqx.rom.crc, clrmamepro.rom.crc
     * CRC must have 8 characters
     */
    private String crc;
    /**
     * logiqx.rom.sha1, clrmamepro.rom.sha1
     * SHA1 must have 40 characters
     */
    private String sha1;
    /**
     * logiqx.rom.md5, clrmamepro.rom.md5
     * logiqx.disk.md5, clrmamepro.disk.md5
     * MD5 must have 32 characters
     */
    private String md5;
    /**
     * logiqx.rom.date
     */
    private String date;

    /**
     * clrmamepro.rom.region
     * logiqx.rom.merge
     * logiqx.disk.merge
     * logiqx.rom.status [(baddump|nodump|good|verified) "good"], clrmamepro.rom.flags [baddump|verified]
     * logiqx.disk.status [(baddump|nodump|good|verified) "good"]
     */
    private Map<String, String> fields = new HashMap<>();

    public DatafileArtifactFile() {
    }

    public DatafileArtifactFile(String type) {
        this.type = type;
    }

    public DatafileArtifactFile(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public DatafileArtifactFile(String type, String name, String size, String crc, String sha1, String md5, String date) {
        this.type = type;
        this.name = name;
        this.size = size;
        this.crc = crc;
        this.sha1 = sha1;
        this.md5 = md5;
        this.date = date;
    }

    public DatafileArtifactFile(String type, String name, String size, String crc, String sha1, String md5, String date,
                                Map<String, String> fields) {
        this.type = type;
        this.name = name;
        this.size = size;
        this.crc = crc;
        this.sha1 = sha1;
        this.md5 = md5;
        this.date = date;
        this.fields = fields;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
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

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }

    public void setField(String key, String value) {
        if (value != null) {
            this.fields.put(key, value);
        } else {
            this.fields.remove(key);
        }
    }

    public String getField(String key) {
        return this.fields.get(key);
    }

    @Transient
    public String getStatus() {
        return this.fields.get("status");
    }

    @Transient
    public void setStatus(String status) {
        if (status != null) {
            this.fields.put("status", status);
        } else {
            this.fields.remove("status");
        }
    }

    @Transient
    public String getMerge() {
        return this.fields.get("merge");
    }

    @Transient
    public void setMerge(String merge) {
        if (merge != null) {
            this.fields.put("merge", merge);
        } else {
            this.fields.remove("merge");
        }
    }

    @Transient
    public String getRegion() {
        return this.fields.get("region");
    }

    @Transient
    public void setRegion(String region) {
        if (region != null) {
            this.fields.put("region", region);
        } else {
            this.fields.remove("region");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DatafileArtifactFile gameFile = (DatafileArtifactFile) o;
        return Objects.equals(type, gameFile.type) &&
                Objects.equals(name, gameFile.name) &&
                Objects.equals(size, gameFile.size) &&
                Objects.equals(crc, gameFile.crc) &&
                Objects.equals(sha1, gameFile.sha1) &&
                Objects.equals(md5, gameFile.md5) &&
                Objects.equals(date, gameFile.date) &&
                Objects.equals(fields, gameFile.fields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, size, crc, sha1, md5, date, fields);
    }
}
