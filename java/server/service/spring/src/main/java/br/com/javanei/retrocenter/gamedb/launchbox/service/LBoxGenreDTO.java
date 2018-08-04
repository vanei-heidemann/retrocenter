package br.com.javanei.retrocenter.gamedb.launchbox.service;

import br.com.javanei.retrocenter.gamedb.launchbox.LBoxGenre;

import java.io.Serializable;

public class LBoxGenreDTO extends LBoxGenre implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    public LBoxGenreDTO() {
    }

    public LBoxGenreDTO(String name, Long id) {
        super(name);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
