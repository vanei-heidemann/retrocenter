package br.com.javanei.retrocenter.platform.service;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class PlatformArtifactFileImportHistoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String description;
    private Long platformID;
    private String platformName;
    private List<PlatformArtifactFileHistoryDTO> fileHistories = new LinkedList<>();

    public PlatformArtifactFileImportHistoryDTO() {
    }

    public PlatformArtifactFileImportHistoryDTO(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public PlatformArtifactFileImportHistoryDTO(Long id, String description, Long platformID, String platformName) {
        this.id = id;
        this.description = description;
        this.platformID = platformID;
        this.platformName = platformName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPlatformID() {
        return platformID;
    }

    public void setPlatformID(Long platformID) {
        this.platformID = platformID;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public List<PlatformArtifactFileHistoryDTO> getFileHistories() {
        return fileHistories;
    }

    public void setFileHistories(List<PlatformArtifactFileHistoryDTO> fileHistories) {
        this.fileHistories = fileHistories;
    }

    public void addFileHistory(PlatformArtifactFileHistoryDTO fileHistory) {
        this.fileHistories.add(fileHistory);
    }
}
