package br.com.javanei.retrocenter.datafile.mame;

import br.com.javanei.retrocenter.util.StringUtil;
import br.com.javanei.retrocenter.util.ValidValuesUtil;

import java.io.Serializable;
import java.util.Objects;

public class MameSlotoption implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String devname;
    private String _default; // (yes|no) "no"

    public MameSlotoption(String name, String devname, String _default) {
        this.name = name;
        this.devname = devname;
        this._default = _default;
    }

    public MameSlotoption() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDevname() {
        return devname;
    }

    public void setDevname(String devname) {
        this.devname = devname;
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
        MameSlotoption that = (MameSlotoption) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t\t<slotoption");
        if (this.name != null) {
            sb.append(" name=\"").append(this.name).append("\"");
        }
        if (this.devname != null) {
            sb.append(" devname=\"").append(this.devname).append("\"");
        }
        if (this._default != null && !this._default.equals("no")) {
            sb.append(" default=\"").append(this._default).append("\"");
        }
        sb.append("/>").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
