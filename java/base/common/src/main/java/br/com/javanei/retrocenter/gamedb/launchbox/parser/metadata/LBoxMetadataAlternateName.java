package br.com.javanei.retrocenter.gamedb.launchbox.parser.metadata;

import java.io.Serializable;
import java.util.Objects;

public class LBoxMetadataAlternateName implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String alternate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlternate() {
        return alternate;
    }

    public void setAlternate(String alternate) {
        this.alternate = alternate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LBoxMetadataAlternateName that = (LBoxMetadataAlternateName) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(alternate, that.alternate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, alternate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  <PlatformAlternateName>\n");
        sb.append("    <Name>").append(this.name).append("</Name>\n");
        sb.append("    <Alternate>").append(this.alternate).append("</Alternate>\n");
        sb.append("  </PlatformAlternateName>\n");
        return sb.toString();
    }
}
