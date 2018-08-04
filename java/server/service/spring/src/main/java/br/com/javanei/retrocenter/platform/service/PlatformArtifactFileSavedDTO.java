package br.com.javanei.retrocenter.platform.service;

import java.io.Serializable;

public class PlatformArtifactFileSavedDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long platformId;
    private String platformName;
    private String importInfo;
    private String fileName;
    private String type;

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getImportInfo() {
        return importInfo;
    }

    public void setImportInfo(String importInfo) {
        this.importInfo = importInfo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PlatformArtifactFileSavedDTO{" +
                "platformId=" + platformId +
                ", platformName='" + platformName + '\'' +
                ", importInfo='" + importInfo + '\'' +
                ", fileName='" + fileName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
