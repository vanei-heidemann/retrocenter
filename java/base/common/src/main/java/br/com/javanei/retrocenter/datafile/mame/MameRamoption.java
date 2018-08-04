package br.com.javanei.retrocenter.datafile.mame;

import br.com.javanei.retrocenter.util.StringUtil;

import java.io.Serializable;
import java.util.Objects;

public class MameRamoption implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer _default;
    private Long content;

    public MameRamoption(Integer _default, Long content) {
        this._default = _default;
        this.content = content;
    }

    public MameRamoption() {
    }

    public Integer getDefault() {
        return _default;
    }

    public void setDefault(Integer _default) {
        this._default = _default;
    }

    public void setDefault(String _default) {
        if (_default != null)
            this._default = new Integer(_default);
    }

    public Long getContent() {
        return content;
    }

    public void setContent(Long content) {
        this.content = content;
    }

    public void setContent(String content) {
        if (content != null)
            this.content = new Long(content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameRamoption that = (MameRamoption) o;
        return Objects.equals(_default, that._default) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_default, content);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<ramoption");
        if (this._default != null) {
            sb.append(" default=\"").append(this._default).append("\"");
        }
        sb.append(">");
        sb.append(this.content);
        sb.append("</ramoption>").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
