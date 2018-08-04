package br.com.javanei.retrocenter.datafile.logiqx;

import br.com.javanei.retrocenter.util.ValidValuesUtil;

import java.io.Serializable;
import java.util.Objects;

/**
 * <!ELEMENT biosset EMPTY>
 */
public class LogiqxBiosset implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * <!ATTLIST biosset name CDATA #REQUIRED>
     */
    private String name;

    /**
     * <!ATTLIST biosset description CDATA #REQUIRED>
     */
    private String description;

    /**
     * <!ATTLIST biosset default (yes|no) "no">
     */
    private String _default = "no";

    public LogiqxBiosset() {
    }

    public LogiqxBiosset(String name, String description, String _default) {
        this.name = name;
        this.description = description;
        this._default = _default;
    }

    public LogiqxBiosset(String name, String description) {
        this.name = name;
        this.description = description;
    }

    private static void appendAttributeIfNotNull(StringBuilder sb, String name, Object value) {
        if (value != null)
            sb.append(" ").append(name).append("=\"").append(value).append("\"");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefault() {
        return _default;
    }

    public void setDefault(String _default) {
        this._default = ValidValuesUtil.validateValue(_default, ValidValuesUtil.YES_NO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogiqxBiosset biosset = (LogiqxBiosset) o;
        return Objects.equals(name, biosset.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<biosset");
        appendAttributeIfNotNull(sb, "name", this.name);
        appendAttributeIfNotNull(sb, "description", this.description);
        appendAttributeIfNotNull(sb, "default", this._default);
        sb.append(" />\n");
        return sb.toString();
    }
}
