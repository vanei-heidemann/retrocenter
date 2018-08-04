package br.com.javanei.retrocenter.datafile.mame.mamesoftwarelist;

import br.com.javanei.retrocenter.util.StringUtil;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class MameSoftwareDiskarea implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private List<MameSoftwareDisk> disks = new LinkedList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MameSoftwareDisk> getDisks() {
        return disks;
    }

    public void setDisks(List<MameSoftwareDisk> disks) {
        this.disks = disks != null ? disks : new LinkedList<>();
    }

    public void addDisk(MameSoftwareDisk disk) {
        this.disks.add(disk);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\t\t\t\t<diskarea name=\"").append(this.name).append("\">").append(StringUtil.LINE_SEPARATOR);

        for (MameSoftwareDisk disk : this.disks) {
            sb.append(disk.toString());
        }

        sb.append("\t\t\t\t</diskarea>").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
