package br.com.javanei.retrocenter.gamedb.launchbox;

import java.io.Serializable;
import java.util.Objects;

public class LBoxRegion implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    public LBoxRegion() {
    }

    public LBoxRegion(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LBoxRegion lBoxRegion = (LBoxRegion) o;
        return Objects.equals(name, lBoxRegion.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "LBoxRegion{" +
                "name='" + name + '\'' +
                '}';
    }
}
