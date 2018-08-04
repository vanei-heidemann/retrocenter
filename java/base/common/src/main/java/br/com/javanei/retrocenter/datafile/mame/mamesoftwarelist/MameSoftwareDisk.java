package br.com.javanei.retrocenter.datafile.mame.mamesoftwarelist;

import br.com.javanei.retrocenter.util.StringUtil;
import br.com.javanei.retrocenter.util.ValidValuesUtil;

import java.io.Serializable;

public class MameSoftwareDisk implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String sha1;
    private String status; // (baddump|nodump|good) "good"
    private String writeable; //(yes|no) "no";

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = ValidValuesUtil.validateValue(status, ValidValuesUtil.BADDUMP_NODUMP_GOOD);
    }

    public String getWriteable() {
        return writeable;
    }

    public void setWriteable(String writeable) {
        this.writeable = ValidValuesUtil.validateValue(writeable, ValidValuesUtil.YES_NO);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\t\t\t\t\t<disk name=\"").append(this.name).append("\"");
        if (this.sha1 != null) {
            sb.append(" sha1=\"").append(this.sha1).append("\"");
        }
        if (this.status != null && !this.status.equals("good")) {
            sb.append(" status=\"").append(this.status).append("\"");
        }
        if (this.writeable != null && !this.writeable.equals("no")) {
            sb.append(" writeable=\"").append(this.writeable).append("\"");
        }
        sb.append("/>").append(StringUtil.LINE_SEPARATOR);

        return sb.toString();
    }
}
