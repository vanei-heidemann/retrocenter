package br.com.javanei.retrocenter.datafile.common;

import java.beans.Transient;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class DatafileArtifact implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * logiqx.game.name, clrmamepro.game.name
     */
    private String name;
    /**
     * logiqx.game.description, clrmamepro.game.description
     */
    private String description;
    /**
     * logiqx.game.year, clrmamepro.game.year
     */
    private String year;
    /**
     * logiqx.game.comment
     */
    private String comment;

    /**
     * logiqx.game.isbios
     * logiqx.game.manufacturer, clrmamepro.game.manufacturer
     * logiqx.game.cloneof, clrmamepro.game.cloneof
     * logiqx.game.romof, clrmamepro.game.romof
     * logiqx.game.sampleof
     */
    private Map<String, String> fields = new HashMap<>();

    private Set<DatafileArtifactFile> files = new HashSet<>();

    private Set<Release> releases = new HashSet<>();

    public DatafileArtifact() {
    }

    public DatafileArtifact(String name, String description, String year, String comment) {
        this.name = name;
        this.description = description;
        this.year = year;
        this.comment = comment;
    }

    public DatafileArtifact(String name, String description, String year, String comment, Map<String, String> fields) {
        this.name = name;
        this.description = description;
        this.year = year;
        this.comment = comment;
        this.fields = fields;
    }

    public boolean addFile(DatafileArtifactFile file) {
        return this.files.add(file);
    }

    public boolean addRelease(Release release) {
        return this.releases.add(release);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }

    public void addField(String key, String value) {
        if (this.fields == null) {
            this.fields = new HashMap<>();
        }
        this.fields.put(key, value);
    }

    @Transient
    public String getField(String key) {
        return this.fields.get(key);
    }

    public Set<DatafileArtifactFile> getFiles() {
        return files;
    }

    public void setFiles(Set<DatafileArtifactFile> files) {
        this.files = files;
    }

    public Set<Release> getReleases() {
        return releases;
    }

    public void setReleases(Set<Release> releases) {
        this.releases = releases;
    }

    @Transient
    public String getIsbios() {
        return this.fields.get("isbios");
    }

    @Transient
    public void setIsbios(String isbios) {
        if (isbios != null) {
            this.fields.put("isbios", isbios);
        } else {
            this.fields.remove("isbios");
        }
    }

    @Transient
    public String getManufacturer() {
        return this.fields.get("manufacturer");
    }

    @Transient
    public void setManufacturer(String manufacturer) {
        if (manufacturer != null) {
            this.fields.put("manufacturer", manufacturer);
        } else {
            this.fields.remove("manufacturer");
        }
    }

    @Transient
    public String getCloneof() {
        return this.fields.get("cloneof");
    }

    @Transient
    public void setCloneof(String cloneof) {
        if (cloneof != null) {
            this.fields.put("cloneof", cloneof);
        } else {
            this.fields.remove("cloneof");
        }
    }

    @Transient
    public String getRomof() {
        return this.fields.get("romof");
    }

    @Transient
    public void setRomof(String romof) {
        if (romof != null) {
            this.fields.put("romof", romof);
        } else {
            this.fields.remove("romof");
        }
    }

    @Transient
    public String getSampleof() {
        return this.fields.get("sampleof");
    }

    @Transient
    public void setSampleof(String sampleof) {
        if (sampleof != null) {
            this.fields.put("sampleof", sampleof);
        } else {
            this.fields.remove("sampleof");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DatafileArtifact game = (DatafileArtifact) o;
        return Objects.equals(name, game.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "DatafileArtifact{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", year='" + year + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
