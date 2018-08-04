package br.com.javanei.retrocenter.gamedb.launchbox.service;

import br.com.javanei.retrocenter.gamedb.launchbox.LBoxCompany;

import java.io.Serializable;

public class LBoxCompanyDTO extends LBoxCompany implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    public LBoxCompanyDTO() {
    }

    public LBoxCompanyDTO(String name, Long id) {
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
