package br.com.javanei.retrocenter.datafile.mame;

import br.com.javanei.retrocenter.util.StringUtil;
import br.com.javanei.retrocenter.util.ValidValuesUtil;

import java.io.Serializable;
import java.util.Objects;

public class MameChip implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String tag;
    private String type;
    private String clock;

    public MameChip() {
    }

    public MameChip(String name, String tag, String type, String clock) {
        this.name = name;
        this.tag = tag;
        this.type = type;
        this.clock = clock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = ValidValuesUtil.validateValue(type, new String[]{"cpu", "audio"});
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameChip mameChip = (MameChip) o;
        return Objects.equals(name, mameChip.name) &&
                Objects.equals(tag, mameChip.tag) &&
                Objects.equals(type, mameChip.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tag, type);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<chip");
        if (this.type != null) {
            sb.append(" type=\"").append(this.type).append("\"");
        }
        if (this.tag != null) {
            sb.append(" tag=\"").append(this.tag).append("\"");
        }
        if (this.name != null) {
            sb.append(" name=\"").append(this.name).append("\"");
        }
        if (this.clock != null) {
            sb.append(" clock=\"").append(this.clock).append("\"");
        }
        sb.append("/>").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
