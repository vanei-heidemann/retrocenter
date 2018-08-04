package br.com.javanei.retrocenter.gamedb.launchbox.parser.metadata;

import java.io.Serializable;
import java.util.Objects;

public class LBoxMetadataGameImage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String databaseID;
    private String fileName;
    private String type;
    private String region;
    private String crc32;

    public String getDatabaseID() {
        return databaseID;
    }

    public void setDatabaseID(String databaseID) {
        this.databaseID = databaseID;
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
        LBoxMetadataGameImage that = (LBoxMetadataGameImage) o;
        return Objects.equals(databaseID, that.databaseID) &&
                Objects.equals(fileName, that.fileName) &&
                Objects.equals(type, that.type) &&
                Objects.equals(region, that.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(databaseID, fileName, type, region);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  <GameImage>\n");
        sb.append("    <DatabaseID>").append(this.databaseID).append("</DatabaseID>\n");
        sb.append("    <FileName>").append(this.fileName).append("</FileName>\n");
        sb.append("    <Type>").append(this.type).append("</Type>\n");
        if (this.region != null) {
            sb.append("    <Region>").append(this.region).append("</Region>\n");
        }
        if (this.crc32 != null) {
            sb.append("    <CRC32>").append(this.crc32).append("</CRC32>\n");
        }
        sb.append("  </GameImage>\n");
        return sb.toString();
    }
}
