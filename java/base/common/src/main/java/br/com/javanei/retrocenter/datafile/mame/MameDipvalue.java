package br.com.javanei.retrocenter.datafile.mame;

import br.com.javanei.retrocenter.util.StringUtil;
import br.com.javanei.retrocenter.util.ValidValuesUtil;

import java.io.Serializable;
import java.util.Objects;

public class MameDipvalue implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Long value;
    private String _default; // (yes|no) "no"

    public MameDipvalue() {
    }

    public MameDipvalue(String name, Long value, String _default) {
        this.name = name;
        this.value = value;
        this._default = _default;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = new Long(value);
    }

    public String getDefault() {
        return _default;
    }

    public void setDefault(String _default) {
        this._default = ValidValuesUtil.validateValue(_default, ValidValuesUtil.YES_NO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameDipvalue that = (MameDipvalue) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(value, that.value) &&
                Objects.equals(_default, that._default);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value, _default);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t\t<dipvalue");
        if (this.name != null) {
            sb.append(" name=\"").append(StringUtil.escapeXMLEntities(this.name)).append("\"");
        }
        if (this.value != null) {
            sb.append(" value=\"").append(this.value).append("\"");
        }
        if (this._default != null && !this._default.equals("no")) {
            sb.append(" default=\"").append(this._default).append("\"");
        }
        sb.append("/>").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
