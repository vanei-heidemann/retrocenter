package br.com.javanei.retrocenter.platform.service;

import java.io.Serializable;

public class PlatformArtifactFileHistoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long fileInfoId;
    private Long fileId;
    private String info;
    private String name;
    private String type;
    private Long size;
    private String crc;
    private String md5;
    private String sha1;

    public PlatformArtifactFileHistoryDTO() {
    }

    public PlatformArtifactFileHistoryDTO(Long id, Long fileInfoId, Long fileId, String info, String name, String type,
                                          Long size, String crc, String md5, String sha1) {
        this.id = id;
        this.fileInfoId = fileInfoId;
        this.fileId = fileId;
        this.name = name;
        this.type = type;
        this.size = size;
        this.crc = crc;
        this.md5 = md5;
        this.sha1 = sha1;
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFileInfoId() {
        return fileInfoId;
    }

    public void setFileInfoId(Long fileInfoId) {
        this.fileInfoId = fileInfoId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
}
