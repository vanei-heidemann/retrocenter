package br.com.javanei.retrocenter.datafile.mame.mamesoftwarelist;

import br.com.javanei.retrocenter.util.StringUtil;
import br.com.javanei.retrocenter.util.ValidValuesUtil;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class MameSoftware implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String cloneof;
    private String supported; //(yes|partial|no) "yes"

    private String description;
    private String year;
    private String publisher;

    private List<MameSoftwareInfo> infos = new LinkedList<>();
    private List<MameSoftwareSharedfeat> sharedfeats = new LinkedList<>();
    private List<MameSoftwarePart> parts = new LinkedList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCloneof() {
        return cloneof;
    }

    public void setCloneof(String cloneof) {
        this.cloneof = cloneof;
    }

    public String getSupported() {
        return supported;
    }

    public void setSupported(String supported) {
        this.supported = ValidValuesUtil.validateValue(supported, ValidValuesUtil.YES_NO_PARTIAL);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<MameSoftwareInfo> getInfos() {
        return infos;
    }

    public void setInfos(List<MameSoftwareInfo> infos) {
        this.infos = infos != null ? infos : new LinkedList<>();
    }

    public void addInfo(MameSoftwareInfo info) {
        this.infos.add(info);
    }

    public List<MameSoftwareSharedfeat> getSharedfeats() {
        return sharedfeats;
    }

    public void setSharedfeats(List<MameSoftwareSharedfeat> sharedfeats) {
        this.sharedfeats = sharedfeats != null ? sharedfeats : new LinkedList<>();
    }

    public void addSharedfeat(MameSoftwareSharedfeat sharedfeat) {
        this.sharedfeats.add(sharedfeat);
    }

    public List<MameSoftwarePart> getParts() {
        return parts;
    }

    public void setParts(List<MameSoftwarePart> parts) {
        this.parts = parts != null ? parts : new LinkedList<>();
    }

    public void addPart(MameSoftwarePart part) {
        this.parts.add(part);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<software name=\"").append(this.name).append("\"");
        if (this.cloneof != null) {
            sb.append(" cloneof=\"").append(this.cloneof).append("\"");
        }
        if (this.supported != null && !this.supported.equals("yes")) {
            sb.append(" supported=\"").append(this.supported).append("\"");
        }
        sb.append(">").append(StringUtil.LINE_SEPARATOR);
        if (this.description != null) {
            sb.append("\t\t\t<description>").append(this.description).append("</description>").append(StringUtil.LINE_SEPARATOR);
        }
        if (this.year != null) {
            sb.append("\t\t\t<year>").append(this.year).append("</year>").append(StringUtil.LINE_SEPARATOR);
        }
        if (this.publisher != null) {
            sb.append("\t\t\t<publisher>").append(this.publisher).append("</publisher>").append(StringUtil.LINE_SEPARATOR);
        }

        for (MameSoftwareInfo info : this.infos) {
            sb.append(info.toString());
        }
        for (MameSoftwareSharedfeat sharedfeat : this.sharedfeats) {
            sb.append(sharedfeat.toString());
        }
        for (MameSoftwarePart part : this.parts) {
            sb.append(part.toString());
        }

        sb.append("\t\t</software>").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
