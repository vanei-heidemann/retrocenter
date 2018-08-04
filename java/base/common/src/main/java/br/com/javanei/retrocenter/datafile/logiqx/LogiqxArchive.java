package br.com.javanei.retrocenter.datafile.logiqx;

import br.com.javanei.retrocenter.util.StringUtil;

import java.io.Serializable;
import java.util.Objects;

/**
 * <!ELEMENT archive EMPTY>
 */
public class LogiqxArchive implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * <!ATTLIST archive name CDATA #REQUIRED>
     */
    private String name;

    public LogiqxArchive() {
    }

    public LogiqxArchive(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LogiqxArchive archive = (LogiqxArchive) o;
        return Objects.equals(name, archive.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<archive name=\"").append(StringUtil.escapeXMLEntities(this.name)).append(" />\n");
        return sb.toString();
    }
}
