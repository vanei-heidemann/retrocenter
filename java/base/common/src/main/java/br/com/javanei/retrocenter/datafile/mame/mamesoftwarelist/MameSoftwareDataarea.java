package br.com.javanei.retrocenter.datafile.mame.mamesoftwarelist;

import br.com.javanei.retrocenter.util.StringUtil;
import br.com.javanei.retrocenter.util.ValidValuesUtil;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class MameSoftwareDataarea implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String size;
    private String databits; // (8|16|32|64) "8"
    private String endian; // (big|little) "little"
    private List<MameSoftwareRom> roms = new LinkedList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDatabits() {
        return databits;
    }

    public void setDatabits(String databits) {
        this.databits = ValidValuesUtil.validateValue(databits, new String[]{"8", "16", "32", "64"});
    }

    public String getEndian() {
        return endian;
    }

    public void setEndian(String endian) {
        this.endian = ValidValuesUtil.validateValue(endian, new String[]{"big", "little"});
    }

    public List<MameSoftwareRom> getRoms() {
        return roms;
    }

    public void setRoms(List<MameSoftwareRom> roms) {
        this.roms = roms != null ? roms : new LinkedList<>();
    }

    public void addRom(MameSoftwareRom rom) {
        this.roms.add(rom);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t\t\t<dataarea name=\"").append(this.name).append("\"");
        if (this.size != null) {
            sb.append(" size=\"").append(this.size).append("\"");
        }
        if (this.databits != null && !this.databits.equals("8")) {
            sb.append(" databits=\"").append(this.databits).append("\"");
        }
        if (this.endian != null && !this.endian.equals("little")) {
            sb.append(" endian=\"").append(this.endian).append("\"");
        }
        sb.append(">").append(StringUtil.LINE_SEPARATOR);

        for (MameSoftwareRom rom : this.roms) {
            sb.append(rom);
        }
        sb.append("\t\t\t\t</dataarea>").append(StringUtil.LINE_SEPARATOR);

        return sb.toString();
    }
}
