package br.com.javanei.retrocenter.datafile.mame;

import br.com.javanei.retrocenter.util.StringUtil;

import java.io.Serializable;
import java.util.Objects;

public class MameDeviceExtension implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    public MameDeviceExtension(String name) {
        this.name = name;
    }

    public MameDeviceExtension() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameDeviceExtension that = (MameDeviceExtension) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "\t\t\t<extension name=\"" + this.name + "\"/>" + StringUtil.LINE_SEPARATOR;
    }
}
