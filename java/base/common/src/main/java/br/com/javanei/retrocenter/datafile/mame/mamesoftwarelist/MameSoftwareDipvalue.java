package br.com.javanei.retrocenter.datafile.mame.mamesoftwarelist;

import br.com.javanei.retrocenter.util.StringUtil;
import br.com.javanei.retrocenter.util.ValidValuesUtil;

import java.io.Serializable;

public class MameSoftwareDipvalue implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String value;
    private String _default; // (yes|no) "no";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDefault() {
        return _default;
    }

    public void setDefault(String _default) {
        this._default = ValidValuesUtil.validateValue(_default, ValidValuesUtil.YES_NO);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\t\t\t\t\t<dipvalue name=\"").append(this.name).append("\" value=\"").append(this.value).append("\"");
        if (this._default != null && !this._default.equals("no")) {
            sb.append(" default=\"").append(this._default).append("\"");
        }
        sb.append("/>").append(StringUtil.LINE_SEPARATOR);

        return sb.toString();
    }
}
