package br.com.javanei.retrocenter.datafile.mame;

import br.com.javanei.retrocenter.util.StringUtil;
import br.com.javanei.retrocenter.util.ValidValuesUtil;

import java.io.Serializable;
import java.util.Objects;

public class MameSoftwarelist implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String status; // (original|compatible)
    private String filter;

    public MameSoftwarelist(String name, String status, String filter) {
        this.name = name;
        this.status = status;
        this.filter = filter;
    }

    public MameSoftwarelist() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = ValidValuesUtil.validateValue(status, ValidValuesUtil.ORIGINAL_COMPATIBLE);
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameSoftwarelist that = (MameSoftwarelist) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<softwarelist");
        if (this.name != null) {
            sb.append(" name=\"").append(this.name).append("\"");
        }
        if (this.status != null) {
            sb.append(" status=\"").append(this.status).append("\"");
        }
        if (this.filter != null) {
            sb.append(" filter=\"").append(this.filter).append("\"");
        }
        sb.append(" />").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
