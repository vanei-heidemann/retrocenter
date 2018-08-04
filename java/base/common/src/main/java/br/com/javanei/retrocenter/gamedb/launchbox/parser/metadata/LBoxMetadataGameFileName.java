package br.com.javanei.retrocenter.gamedb.launchbox.parser.metadata;

import java.io.Serializable;
import java.util.Objects;

public class LBoxMetadataGameFileName implements Serializable {
    private static final long serialVersionUID = 1L;

    private String platform;
    private String fileName;
    private String gameName;

    public LBoxMetadataGameFileName() {
    }

    public LBoxMetadataGameFileName(String platform, String fileName, String gameName) {
        this.platform = platform;
        this.fileName = fileName;
        this.gameName = gameName;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LBoxMetadataGameFileName that = (LBoxMetadataGameFileName) o;
        return Objects.equals(platform, that.platform) &&
                Objects.equals(fileName, that.fileName) &&
                Objects.equals(gameName, that.gameName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(platform, fileName, gameName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  <File>\n");
        sb.append("    <Platform>").append(this.platform).append("</Platform>\n");
        sb.append("    <FileName>").append(this.fileName).append("</FileName>\n");
        sb.append("    <GameName>").append(this.gameName).append("</GameName>\n");
        sb.append("  </File>\n");
        return sb.toString();
    }
}
