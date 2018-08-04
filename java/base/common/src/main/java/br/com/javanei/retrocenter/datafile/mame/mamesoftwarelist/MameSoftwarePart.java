package br.com.javanei.retrocenter.datafile.mame.mamesoftwarelist;

import br.com.javanei.retrocenter.util.StringUtil;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class MameSoftwarePart implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String _interface; //interface

    private List<MameSoftwareFeature> features = new LinkedList<>();
    private List<MameSoftwareDataarea> dataareas = new LinkedList<>();
    private List<MameSoftwareDiskarea> diskareas = new LinkedList<>();
    private List<MameSoftwareDipswitch> dipswitches = new LinkedList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInterface() {
        return _interface;
    }

    public void setInterface(String _interface) {
        this._interface = _interface;
    }

    public List<MameSoftwareFeature> getFeatures() {
        return features;
    }

    public void setFeatures(List<MameSoftwareFeature> features) {
        this.features = features != null ? features : new LinkedList<>();
    }

    public void addFeature(MameSoftwareFeature feature) {
        this.features.add(feature);
    }

    public List<MameSoftwareDataarea> getDataareas() {
        return dataareas;
    }

    public void setDataareas(List<MameSoftwareDataarea> dataareas) {
        this.dataareas = dataareas != null ? dataareas : new LinkedList<>();
    }

    public void addDataarea(MameSoftwareDataarea dataarea) {
        this.dataareas.add(dataarea);
    }

    public List<MameSoftwareDiskarea> getDiskareas() {
        return diskareas;
    }

    public void setDiskareas(List<MameSoftwareDiskarea> diskareas) {
        this.diskareas = diskareas != null ? diskareas : new LinkedList<>();
    }

    public void addDiskarea(MameSoftwareDiskarea diskarea) {
        this.diskareas.add(diskarea);
    }

    public List<MameSoftwareDipswitch> getDipswitches() {
        return dipswitches;
    }

    public void setDipswitches(List<MameSoftwareDipswitch> dipswitches) {
        this.dipswitches = dipswitches != null ? dipswitches : new LinkedList<>();
    }

    public void addDipswitch(MameSoftwareDipswitch dipswitch) {
        this.dipswitches.add(dipswitch);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t\t<part name=\"").append(this.name).append("\" interface=\"").append(this._interface).append("\">").append(StringUtil.LINE_SEPARATOR);

        for (MameSoftwareFeature feature : this.features) {
            sb.append(feature.toString());
        }
        for (MameSoftwareDataarea dataarea : this.dataareas) {
            sb.append(dataarea);
        }
        for (MameSoftwareDiskarea diskarea : this.diskareas) {
            sb.append(diskarea);
        }
        for (MameSoftwareDipswitch dipswitch : this.dipswitches) {
            sb.append(dipswitch);
        }

        sb.append("\t\t\t</part>").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
