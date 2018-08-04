package br.com.javanei.retrocenter.gamedb.launchbox;

import java.io.Serializable;
import java.util.Objects;

public class LBoxGenre implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    public LBoxGenre() {
    }

    public LBoxGenre(String name) {
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
        LBoxGenre lBoxGenre = (LBoxGenre) o;
        return Objects.equals(name, lBoxGenre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "LBoxGenre{" +
                "name='" + name + '\'' +
                '}';
    }
}
