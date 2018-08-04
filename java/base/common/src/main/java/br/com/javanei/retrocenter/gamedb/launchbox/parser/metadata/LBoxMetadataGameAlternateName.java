package br.com.javanei.retrocenter.gamedb.launchbox.parser.metadata;

import java.io.Serializable;
import java.util.Objects;

public class LBoxMetadataGameAlternateName implements Serializable {
    private static final long serialVersionUID = 1L;

    private String databaseID;
    private String alternateName;
    private String region;

    public LBoxMetadataGameAlternateName() {
    }

    public LBoxMetadataGameAlternateName(String databaseID, String alternateName, String region) {
        this.databaseID = databaseID;
        this.alternateName = alternateName;
        this.region = region;
    }

    public String getDatabaseID() {
        return databaseID;
    }

    public void setDatabaseID(String databaseID) {
        this.databaseID = databaseID;
    }

    public String getAlternateName() {
        return alternateName;
    }

    public void setAlternateName(String alternateName) {
        this.alternateName = alternateName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LBoxMetadataGameAlternateName that = (LBoxMetadataGameAlternateName) o;
        return Objects.equals(databaseID, that.databaseID) &&
                Objects.equals(alternateName, that.alternateName) &&
                Objects.equals(region, that.region);
    }

    @Override
    public int hashCode() {

        return Objects.hash(databaseID, alternateName, region);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  <GameAlternateName>\n");
        if (this.alternateName != null) {
            sb.append("    <AlternateName>").append(this.alternateName).append("</AlternateName>\n");
        }
        if (this.databaseID != null) {
            sb.append("    <DatabaseID>").append(this.databaseID).append("</DatabaseID>\n");
        }
        if (this.region != null) {
            sb.append("    <Region>").append(this.region).append("</Region>\n");
        }
        sb.append("  </GameAlternateName>\n");
        return sb.toString();
    }
}
