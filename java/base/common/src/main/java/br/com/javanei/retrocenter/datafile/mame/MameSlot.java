package br.com.javanei.retrocenter.datafile.mame;

import br.com.javanei.retrocenter.util.StringUtil;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MameSlot implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Set<MameSlotoption> slotoptions = new HashSet<>();

    public MameSlot(String name) {
        this.name = name;
    }

    public MameSlot() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MameSlotoption> getSlotoptions() {
        return slotoptions;
    }

    public void setSlotoptions(Set<MameSlotoption> slotoptions) {
        this.slotoptions = slotoptions;
    }

    public boolean addSlotoption(MameSlotoption slotoption) {
        return this.slotoptions.add(slotoption);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameSlot mameSlot = (MameSlot) o;
        return Objects.equals(name, mameSlot.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<slot name=\"").append(this.name).append("\">").append(StringUtil.LINE_SEPARATOR);

        for (MameSlotoption slotoption : this.slotoptions) {
            sb.append(slotoption.toString());
        }

        sb.append("\t\t</slot>").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
