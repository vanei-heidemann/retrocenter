package br.com.javanei.retrocenter.datafile.service;

import br.com.javanei.retrocenter.datafile.common.Datafile;

import java.io.Serializable;

public class DatafileDTO extends Datafile implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    public DatafileDTO() {
    }

    public DatafileDTO(String name) {
        super(name);
    }

    public DatafileDTO(String name, String catalog, String version, String description, String author, String date,
                       String email, String homepage, String url, String comment, Long id) {
        super(name, catalog, version, description, author, date, email, homepage, url, comment);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
