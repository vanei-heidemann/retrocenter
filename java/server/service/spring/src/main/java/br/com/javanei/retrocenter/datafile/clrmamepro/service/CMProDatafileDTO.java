package br.com.javanei.retrocenter.datafile.clrmamepro.service;

import br.com.javanei.retrocenter.datafile.clrmamepro.CMProDatafile;
import br.com.javanei.retrocenter.datafile.clrmamepro.CMProHeader;

import java.io.Serializable;

public class CMProDatafileDTO extends CMProDatafile implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    public CMProDatafileDTO() {
    }

    public CMProDatafileDTO(CMProHeader header, Long id) {
        super(header);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
