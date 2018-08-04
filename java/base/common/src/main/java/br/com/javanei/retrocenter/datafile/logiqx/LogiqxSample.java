package br.com.javanei.retrocenter.datafile.logiqx;

import br.com.javanei.retrocenter.util.StringUtil;

import java.io.Serializable;
import java.util.Objects;

/**
 * <!ELEMENT sample EMPTY>
 */
public class LogiqxSample implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * <!ATTLIST sample name CDATA #REQUIRED>
     */
    private String name;

    public LogiqxSample() {
    }

    public LogiqxSample(String name) {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogiqxSample sample = (LogiqxSample) o;
        return Objects.equals(name, sample.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<sample name=\"").append(StringUtil.escapeXMLEntities(this.name)).append(" />\n");
        return sb.toString();
    }
}
