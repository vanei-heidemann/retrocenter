package br.com.javanei.retrocenter.datafile.clrmamepro;

import java.io.Serializable;
import java.util.Objects;

public class CMProDisk implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    /**
     * SHA1 must have 40 characters
     */
    private String sha1;
    /**
     * MD5 must have 32 characters
     */
    private String md5;

    public CMProDisk() {
    }

    public CMProDisk(String name) {
        this.name = name;
    }

    public CMProDisk(String name, String sha1, String md5) {
        this.name = name;
        this.sha1 = sha1;
        this.md5 = md5;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CMProDisk disk = (CMProDisk) o;
        return Objects.equals(name, disk.name) &&
                Objects.equals(sha1, disk.sha1) &&
                Objects.equals(md5, disk.md5);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sha1, md5);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\tdisk ( name \"").append(this.name).append("\"");
        if (this.sha1 != null) sb.append(" sha1 ").append(this.sha1);
        if (this.md5 != null) sb.append(" md5 ").append(this.md5);
        sb.append(" )\n");
        return sb.toString();
    }
}
