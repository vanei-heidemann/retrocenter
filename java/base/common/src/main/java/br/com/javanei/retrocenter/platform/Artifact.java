package br.com.javanei.retrocenter.platform;

import java.io.Serializable;

public class Artifact implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;
    private String name;

    public Artifact() {
    }

    public Artifact(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Artifact{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
