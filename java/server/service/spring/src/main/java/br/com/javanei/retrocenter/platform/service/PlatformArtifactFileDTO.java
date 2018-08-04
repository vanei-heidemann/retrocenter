package br.com.javanei.retrocenter.platform.service;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class PlatformArtifactFileDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String type;
    private Long size;
    private String crc;
    private String md5;
    private String sha1;
    private List<String> info = new LinkedList<>();

    public PlatformArtifactFileDTO() {
    }

    public PlatformArtifactFileDTO(Long id, String name, String type, Long size, String crc, String md5, String sha1) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.size = size;
        this.crc = crc;
        this.md5 = md5;
        this.sha1 = sha1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public List<String> getInfo() {
        return info;
    }

    public void setInfo(List<String> info) {
        this.info = info;
    }

    public void addInfo(String info) {
        this.info.add(info);
    }

    @Override
    public String toString() {
        return "PlatformArtifactFileDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", crc='" + crc + '\'' +
                ", md5='" + md5 + '\'' +
                ", sha1='" + sha1 + '\'' +
                ", info=" + info +
                '}';
    }
}
