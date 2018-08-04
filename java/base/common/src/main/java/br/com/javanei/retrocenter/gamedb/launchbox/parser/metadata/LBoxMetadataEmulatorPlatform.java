package br.com.javanei.retrocenter.gamedb.launchbox.parser.metadata;

import java.io.Serializable;
import java.util.Objects;

public class LBoxMetadataEmulatorPlatform implements Serializable {
    private static final long serialVersionUID = 1L;

    private String emulator;
    private String platform;
    private String commandLine;
    private String applicableFileExtensions;
    private Boolean recommended;

    public String getEmulator() {
        return emulator;
    }

    public void setEmulator(String emulator) {
        this.emulator = emulator;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getCommandLine() {
        return commandLine;
    }

    public void setCommandLine(String commandLine) {
        this.commandLine = commandLine;
    }

    public String getApplicableFileExtensions() {
        return applicableFileExtensions;
    }

    public void setApplicableFileExtensions(String applicableFileExtensions) {
        this.applicableFileExtensions = applicableFileExtensions;
    }

    public Boolean getRecommended() {
        return recommended;
    }

    public void setRecommended(Boolean recommended) {
        this.recommended = recommended;
    }

    public void setRecommended(String recommended) {
        if (recommended != null)
            this.recommended = recommended.trim().equalsIgnoreCase("true") || recommended.trim().equalsIgnoreCase("yes");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LBoxMetadataEmulatorPlatform that = (LBoxMetadataEmulatorPlatform) o;
        return Objects.equals(emulator, that.emulator) &&
                Objects.equals(platform, that.platform);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emulator, platform);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  <EmulatorPlatform>\n");
        sb.append("    <Emulator>").append(this.emulator).append("</Emulator>\n");
        sb.append("    <Platform>").append(this.platform).append("</Platform>\n");
        if (this.commandLine != null) {
            sb.append("    <CommandLine>").append(this.commandLine).append("</CommandLine>\n");
        }
        if (this.applicableFileExtensions != null) {
            sb.append("    <ApplicableFileExtensions>").append(this.applicableFileExtensions).append("</ApplicableFileExtensions>\n");
        }
        if (this.recommended != null) {
            sb.append("    <Recommended>").append(this.recommended).append("</Recommended>\n");
        }
        sb.append("  </EmulatorPlatform>\n");
        return sb.toString();
    }
}
