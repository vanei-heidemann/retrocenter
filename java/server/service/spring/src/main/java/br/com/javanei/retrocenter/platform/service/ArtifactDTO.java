package br.com.javanei.retrocenter.platform.service;

import br.com.javanei.retrocenter.platform.Artifact;

import java.io.Serializable;

public class ArtifactDTO extends Artifact implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    public ArtifactDTO() {
    }

    public ArtifactDTO(String code, String name, Long id) {
        super(code, name);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
