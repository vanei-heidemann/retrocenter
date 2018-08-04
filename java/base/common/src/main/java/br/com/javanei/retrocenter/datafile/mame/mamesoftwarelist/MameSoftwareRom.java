package br.com.javanei.retrocenter.datafile.mame.mamesoftwarelist;

import br.com.javanei.retrocenter.util.StringUtil;
import br.com.javanei.retrocenter.util.ValidValuesUtil;

import java.io.Serializable;

public class MameSoftwareRom implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Integer size;
    private String length;
    private String crc;
    private String sha1;
    private String offset;
    private String status; //(baddump|nodump|good) "good"
    private String loadflag; // (load16_byte|load16_word|load16_word_swap|load32_byte|load32_word|load32_word_swap|load32_dword|load64_word|load64_word_swap|reload|fill|continue|reload_plain) #IMPLIED

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = ValidValuesUtil.validateValue(status, ValidValuesUtil.BADDUMP_NODUMP_GOOD);
    }

    public String getLoadflag() {
        return loadflag;
    }

    public void setLoadflag(String loadflag) {
        this.loadflag = ValidValuesUtil.validateValue(loadflag, new String[]{"load16_byte", "load16_word",
                "load16_word_swap", "load32_byte", "load32_word", "load32_word_swap", "load32_dword", "load64_word",
                "load64_word_swap", "reload", "fill", "continue", "reload_plain"});
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\t\t\t\t\t<rom");
        if (this.name != null) {
            sb.append(" name=\"").append(this.name).append("\"");
        }
        if (this.size != null) {
            sb.append(" size=\"").append(this.size).append("\"");
        }
        if (this.length != null) {
            sb.append(" length=\"").append(this.length).append("\"");
        }
        if (this.crc != null) {
            sb.append(" crc=\"").append(this.crc).append("\"");
        }
        if (this.sha1 != null) {
            sb.append(" sha1=\"").append(this.sha1).append("\"");
        }
        if (this.offset != null) {
            sb.append(" offset=\"").append(this.offset).append("\"");
        }
        if (this.status != null && !this.status.equals("good")) {
            sb.append(" status=\"").append(this.status).append("\"");
        }
        if (this.loadflag != null) {
            sb.append(" loadflag=\"").append(this.loadflag).append("\"");
        }
        if (this.offset != null) sb.append(" ");
        sb.append("/>").append(StringUtil.LINE_SEPARATOR);

        return sb.toString();
    }
}
