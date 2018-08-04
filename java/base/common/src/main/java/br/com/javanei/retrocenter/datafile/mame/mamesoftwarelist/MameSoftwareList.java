package br.com.javanei.retrocenter.datafile.mame.mamesoftwarelist;

import br.com.javanei.retrocenter.util.StringUtil;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class MameSoftwareList implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;

    private List<MameSoftware> softwares = new LinkedList<>();

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

    public List<MameSoftware> getSoftwares() {
        return softwares;
    }

    public void setSoftwares(List<MameSoftware> softwares) {
        this.softwares = softwares != null ? softwares : new LinkedList<>();
    }

    public void addSoftware(MameSoftware software) {
        this.softwares.add(software);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t<softwarelist name=\"").append(this.name).append("\"");
        if (this.description != null) {
            sb.append(" description=\"").append(this.description).append("\"");
        }
        sb.append(">").append(StringUtil.LINE_SEPARATOR);
        for (MameSoftware software : this.softwares) {
            sb.append(software.toString());
        }
        sb.append("\t").append("</softwarelist>").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
