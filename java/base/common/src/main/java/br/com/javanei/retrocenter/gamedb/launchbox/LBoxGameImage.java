package br.com.javanei.retrocenter.gamedb.launchbox;

import java.io.Serializable;

public class LBoxGameImage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fileName;
    private String type;
    private String region;
    private String crc32;

    public LBoxGameImage() {
    }

    public LBoxGameImage(String fileName, String type, String region, String crc32) {
        this.fileName = fileName;
        this.type = type;
        this.region = region;
        this.crc32 = crc32;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCrc32() {
        return crc32;
    }

    public void setCrc32(String crc32) {
        this.crc32 = crc32;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LBoxGameImage that = (LBoxGameImage) o;

        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        return crc32 != null ? crc32.equals(that.crc32) : that.crc32 == null;
    }

    @Override
    public int hashCode() {
        int result = fileName != null ? fileName.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (crc32 != null ? crc32.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LBoxGameImage{" +
                ", fileName='" + fileName + '\'' +
                ", type='" + type + '\'' +
                ", region='" + region + '\'' +
                ", crc32='" + crc32 + '\'' +
                '}';
    }
}
