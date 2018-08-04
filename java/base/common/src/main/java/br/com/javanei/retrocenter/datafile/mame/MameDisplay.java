package br.com.javanei.retrocenter.datafile.mame;

import br.com.javanei.retrocenter.util.StringUtil;
import br.com.javanei.retrocenter.util.ValidValuesUtil;

import java.io.Serializable;
import java.util.Objects;

public class MameDisplay implements Serializable {
    private static final long serialVersionUID = 1L;

    private String tag;
    private String type; // (raster|vector|lcd|unknown)
    private String rotate; // (0|90|180|270)
    private String flipx; // (yes|no) "no"
    private Integer width;
    private Integer height;
    private String refresh;
    private Integer pixclock;
    private Integer htotal;
    private Integer hbend;
    private Integer hbstart;
    private Integer vtotal;
    private Integer vbend;
    private Integer vbstart;

    public MameDisplay() {
    }

    public MameDisplay(String tag, String type, String rotate, String flipx, Integer width, Integer height,
                       String refresh, Integer pixclock, Integer htotal, Integer hbend, Integer hbstart, Integer vtotal,
                       Integer vbend, Integer vbstart) {
        this.tag = tag;
        this.type = type;
        this.rotate = rotate;
        this.flipx = flipx;
        this.width = width;
        this.height = height;
        this.refresh = refresh;
        this.pixclock = pixclock;
        this.htotal = htotal;
        this.hbend = hbend;
        this.hbstart = hbstart;
        this.vtotal = vtotal;
        this.vbend = vbend;
        this.vbstart = vbstart;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = ValidValuesUtil.validateValue(type, new String[]{"raster", "vector", "lcd", "unknown"});
    }

    public String getRotate() {
        return rotate;
    }

    public void setRotate(String rotate) {
        this.rotate = ValidValuesUtil.validateValue(rotate, new String[]{"0", "90", "180", "270"});
    }

    public String getFlipx() {
        return flipx;
    }

    public void setFlipx(String flipx) {
        this.flipx = ValidValuesUtil.validateValue(flipx, ValidValuesUtil.YES_NO);
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public void setWidth(String width) {
        this.width = new Integer(width);
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setHeight(String height) {
        this.height = new Integer(height);
    }

    public String getRefresh() {
        return refresh;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }

    public Integer getPixclock() {
        return pixclock;
    }

    public void setPixclock(Integer pixclock) {
        this.pixclock = pixclock;
    }

    public void setPixclock(String pixclock) {
        this.pixclock = new Integer(pixclock);
    }

    public Integer getHtotal() {
        return htotal;
    }

    public void setHtotal(Integer htotal) {
        this.htotal = htotal;
    }

    public void setHtotal(String htotal) {
        this.htotal = new Integer(htotal);
    }

    public Integer getHbend() {
        return hbend;
    }

    public void setHbend(Integer hbend) {
        this.hbend = hbend;
    }

    public void setHbend(String hbend) {
        this.hbend = new Integer(hbend);
    }

    public Integer getHbstart() {
        return hbstart;
    }

    public void setHbstart(Integer hbstart) {
        this.hbstart = hbstart;
    }

    public void setHbstart(String hbstart) {
        this.hbstart = new Integer(hbstart);
    }

    public Integer getVtotal() {
        return vtotal;
    }

    public void setVtotal(Integer vtotal) {
        this.vtotal = vtotal;
    }

    public void setVtotal(String vtotal) {
        this.vtotal = new Integer(vtotal);
    }

    public Integer getVbend() {
        return vbend;
    }

    public void setVbend(Integer vbend) {
        this.vbend = vbend;
    }

    public void setVbend(String vbend) {
        this.vbend = new Integer(vbend);
    }

    public Integer getVbstart() {
        return vbstart;
    }

    public void setVbstart(Integer vbstart) {
        this.vbstart = vbstart;
    }

    public void setVbstart(String vbstart) {
        this.vbstart = new Integer(vbstart);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameDisplay that = (MameDisplay) o;
        return Objects.equals(tag, that.tag) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag, type);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<display");
        if (this.tag != null) {
            sb.append(" tag=\"").append(this.tag).append("\"");
        }
        if (this.type != null) {
            sb.append(" type=\"").append(this.type).append("\"");
        }
        if (this.rotate != null) {
            sb.append(" rotate=\"").append(this.rotate).append("\"");
        }
        if (this.flipx != null && !this.flipx.equals("no")) {
            sb.append(" flipx=\"").append(this.flipx).append("\"");
        }
        if (this.width != null) {
            sb.append(" width=\"").append(this.width).append("\"");
        }
        if (this.height != null) {
            sb.append(" height=\"").append(this.height).append("\"");
        }
        if (this.refresh != null) {
            sb.append(" refresh=\"").append(this.refresh).append("\"");
        }
        if (this.pixclock != null) {
            sb.append(" pixclock=\"").append(this.pixclock).append("\"");
        }
        if (this.htotal != null) {
            sb.append(" htotal=\"").append(this.htotal).append("\"");
        }
        if (this.hbend != null) {
            sb.append(" hbend=\"").append(this.hbend).append("\"");
        }
        if (this.hbstart != null) {
            sb.append(" hbstart=\"").append(this.hbstart).append("\"");
        }
        if (this.vtotal != null) {
            sb.append(" vtotal=\"").append(this.vtotal).append("\"");
        }
        if (this.vbend != null) {
            sb.append(" vbend=\"").append(this.vbend).append("\"");
        }
        if (this.vbstart != null) {
            sb.append(" vbstart=\"").append(this.vbstart).append("\"");
        }
        sb.append(" />").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
