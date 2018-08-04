package br.com.javanei.retrocenter.datafile.mame.service;

import br.com.javanei.retrocenter.datafile.mame.Mame;

import java.io.Serializable;

public class MameDTO extends Mame implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    public MameDTO() {
    }

    public MameDTO(String build, String debug, String mameconfig, Long id) {
        super(build, debug, mameconfig);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
