package br.com.javanei.retrocenter.datafile.mame.mamesoftwarelist;

import br.com.javanei.retrocenter.util.StringUtil;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class MameSoftwareDipswitch implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String tag;
    private String mask;

    private List<MameSoftwareDipvalue> dipvalues = new LinkedList<>();

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

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public List<MameSoftwareDipvalue> getDipvalues() {
        return dipvalues;
    }

    public void setDipvalues(List<MameSoftwareDipvalue> dipvalues) {
        this.dipvalues = dipvalues != null ? dipvalues : new LinkedList<>();
    }

    public void addDipvalue(MameSoftwareDipvalue dipvalue) {
        this.dipvalues.add(dipvalue);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\t\t\t\t<dipswitch name=\"").append(this.name).append("\" tag=\"").append(this.tag).append("\" mask=\"").append(this.mask).append("\">").append(StringUtil.LINE_SEPARATOR);

        for (MameSoftwareDipvalue dipvalue : this.dipvalues) {
            sb.append(dipvalue);
        }

        sb.append("\t\t\t\t</dipswitch>").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
