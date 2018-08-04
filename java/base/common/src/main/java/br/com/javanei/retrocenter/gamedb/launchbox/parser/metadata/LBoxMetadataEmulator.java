package br.com.javanei.retrocenter.gamedb.launchbox.parser.metadata;

import java.io.Serializable;
import java.util.Objects;

public class LBoxMetadataEmulator implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String commandLine;
    private String applicableFileExtensions;
    private String URL;
    private String binaryFileName;
    private Boolean noQuotes;
    private Boolean noSpace;
    private Boolean hideConsole;
    private Boolean fileNameOnly;
    private Boolean autoExtract;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getBinaryFileName() {
        return binaryFileName;
    }

    public void setBinaryFileName(String binaryFileName) {
        this.binaryFileName = binaryFileName;
    }

    public Boolean getNoQuotes() {
        return noQuotes;
    }

    public void setNoQuotes(Boolean noQuotes) {
        this.noQuotes = noQuotes;
    }

    public void setNoQuotes(String noQuotes) {
        if (noQuotes != null)
            this.noQuotes = Boolean.parseBoolean(noQuotes);
    }

    public Boolean getNoSpace() {
        return noSpace;
    }

    public void setNoSpace(Boolean noSpace) {
        this.noSpace = noSpace;
    }

    public void setNoSpace(String noSpace) {
        if (noSpace != null)
            this.noSpace = Boolean.parseBoolean(noSpace);
    }

    public Boolean getHideConsole() {
        return hideConsole;
    }

    public void setHideConsole(Boolean hideConsole) {
        this.hideConsole = hideConsole;
    }

    public void setHideConsole(String hideConsole) {
        if (hideConsole != null)
            this.hideConsole = Boolean.parseBoolean(hideConsole);
    }

    public Boolean getFileNameOnly() {
        return fileNameOnly;
    }

    public void setFileNameOnly(Boolean fileNameOnly) {
        this.fileNameOnly = fileNameOnly;
    }

    public void setFileNameOnly(String fileNameOnly) {
        if (fileNameOnly != null)
            this.fileNameOnly = Boolean.parseBoolean(fileNameOnly);
    }

    public Boolean getAutoExtract() {
        return autoExtract;
    }

    public void setAutoExtract(Boolean autoExtract) {
        this.autoExtract = autoExtract;
    }

    public void setAutoExtract(String autoExtract) {
        if (autoExtract != null)
            this.autoExtract = Boolean.parseBoolean(autoExtract);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LBoxMetadataEmulator that = (LBoxMetadataEmulator) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  <Emulator>\n");
        sb.append("    <Name>").append(this.name).append("</Name>\n");
        if (this.commandLine != null) {
            sb.append("    <CommandLine>").append(this.commandLine).append("</CommandLine>\n");
        }
        if (this.applicableFileExtensions != null) {
            sb.append("    <ApplicableFileExtensions>").append(this.applicableFileExtensions).append("</ApplicableFileExtensions>\n");
        }
        if (this.URL != null) {
            sb.append("    <URL>").append(this.URL).append("</URL>\n");
        }
        if (this.binaryFileName != null) {
            sb.append("    <BinaryFileName>").append(this.binaryFileName).append("</BinaryFileName>\n");
        }
        if (this.noQuotes != null) {
            sb.append("    <NoQuotes>").append(this.noQuotes).append("</NoQuotes>\n");
        }
        if (this.noSpace != null) {
            sb.append("    <NoSpace>").append(this.noSpace).append("</NoSpace>\n");
        }
        if (this.hideConsole != null) {
            sb.append("    <HideConsole>").append(this.hideConsole).append("</HideConsole>\n");
        }
        if (this.fileNameOnly != null) {
            sb.append("    <FileNameOnly>").append(this.fileNameOnly).append("</FileNameOnly>\n");
        }
        if (this.autoExtract != null) {
            sb.append("    <AutoExtract>").append(this.autoExtract).append("</AutoExtract>\n");
        }
        sb.append("  </Emulator>\n");
        return sb.toString();
    }
}
