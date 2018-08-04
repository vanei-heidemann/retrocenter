package br.com.javanei.retrocenter.gamedb.launchbox;

import java.io.Serializable;
import java.util.Objects;

public class LBoxGameAlternateName implements Serializable {
    private static final long serialVersionUID = 1L;

    private String alternateName;
    private String region;

    public LBoxGameAlternateName() {
    }

    public LBoxGameAlternateName(String alternateName, String region) {
        this.alternateName = alternateName;
        this.region = region;
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
        LBoxGameAlternateName that = (LBoxGameAlternateName) o;
        return Objects.equals(alternateName, that.alternateName) &&
                Objects.equals(region, that.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alternateName, region);
    }

    @Override
    public String toString() {
        return "LBoxGameAlternateName{" +
                "alternateName='" + alternateName + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
