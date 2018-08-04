package br.com.javanei.retrocenter.gamedb.launchbox.service;

import java.io.Serializable;

public class LBoxDatafileDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String version;

    public LBoxDatafileDTO() {
    }

    public LBoxDatafileDTO(Long id, String version) {
        this.id = id;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LBoxDatafileDTO that = (LBoxDatafileDTO) o;

        return version != null ? version.equals(that.version) : that.version == null;
    }

    @Override
    public int hashCode() {
        return version != null ? version.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "LBoxDatafileDTO{" +
                "id=" + id +
                ", version='" + version + '\'' +
                '}';
    }
}
