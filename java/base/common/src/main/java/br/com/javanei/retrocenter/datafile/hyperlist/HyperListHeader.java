package br.com.javanei.retrocenter.datafile.hyperlist;

import java.io.Serializable;

public class HyperListHeader implements Serializable {
    private static final long serialVersionUID = 1L;

    public String listname;
    public String lastlistupdate;
    public String listversion;
    public String exporterversion;

    public HyperListHeader() {
    }

    public HyperListHeader(String listname, String lastlistupdate, String listversion, String exporterversion) {
        this.listname = listname;
        this.lastlistupdate = lastlistupdate;
        this.listversion = listversion;
        this.exporterversion = exporterversion;
    }

    public String getListname() {
        return listname;
    }

    public void setListname(String listname) {
        this.listname = listname;
    }

    public String getLastlistupdate() {
        return lastlistupdate;
    }

    public void setLastlistupdate(String lastlistupdate) {
        this.lastlistupdate = lastlistupdate;
    }

    public String getListversion() {
        return listversion;
    }

    public void setListversion(String listversion) {
        this.listversion = listversion;
    }

    public String getExporterversion() {
        return exporterversion;
    }

    public void setExporterversion(String exporterversion) {
        this.exporterversion = exporterversion;
    }

    @Override
    public String toString() {
        return "HyperListHeader{" +
                "listname='" + listname + '\'' +
                ", lastlistupdate='" + lastlistupdate + '\'' +
                ", listversion='" + listversion + '\'' +
                ", exporterversion='" + exporterversion + '\'' +
                '}';
    }
}
