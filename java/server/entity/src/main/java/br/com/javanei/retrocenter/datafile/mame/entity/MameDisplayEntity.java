package br.com.javanei.retrocenter.datafile.mame.entity;

import br.com.javanei.retrocenter.datafile.mame.MameDisplay;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "MAME_DISPLAY", indexes = {
        @Index(name = "MAME_DISPLAY_0001", unique = true, columnList = "MACHINE_ID,TAG,TYPE")
})
public class MameDisplayEntity implements Serializable, Comparable<MameDisplayEntity> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAME_DISPLAY_ID", nullable = false)
    private Long id;

    @Column(name = "TAG", length = 128, nullable = false)
    private String tag;

    @Column(name = "TYPE", length = 8, nullable = false)
    private String type;

    @Column(name = "ROTATE", length = 3, nullable = true)
    private String rotate;

    @Column(name = "FLIPX", length = 3, nullable = true)
    private String flipx;

    @Column(name = "WIDTH", nullable = true)
    private Integer width;

    @Column(name = "HEIGHT", nullable = true)
    private Integer height;

    @Column(name = "REFRESH", length = 16, nullable = true)
    private String refresh;

    @Column(name = "PIXCLOCK", nullable = true)
    private Integer pixclock;

    @Column(name = "HTOTAL", nullable = true)
    private Integer htotal;

    @Column(name = "HBEND", nullable = true)
    private Integer hbend;

    @Column(name = "HBSTART", nullable = true)
    private Integer hbstart;

    @Column(name = "VTOTAL", nullable = true)
    private Integer vtotal;

    @Column(name = "VBEND", nullable = true)
    private Integer vbend;

    @Column(name = "VBSTART", nullable = true)
    private Integer vbstart;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "MACHINE_ID")
    private MameMachineEntity machine;

    public MameDisplayEntity() {
    }

    public MameDisplayEntity(String tag, String type, String rotate, String flipx, Integer width, Integer height,
                             String refresh, Integer pixclock, Integer htotal, Integer hbend, Integer hbstart,
                             Integer vtotal, Integer vbend, Integer vbstart) {
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

    public MameDisplayEntity(MameDisplay display) {
        this(display.getTag(), display.getType(), display.getRotate(), display.getFlipx(), display.getWidth(),
                display.getHeight(), display.getRefresh(), display.getPixclock(), display.getHtotal(),
                display.getHbend(), display.getHbstart(), display.getVtotal(), display.getVbend(), display.getVbstart());
    }

    public MameDisplay toVO() {
        return new MameDisplay(this.tag, this.type, this.rotate, this.flipx, this.width, this.height, this.refresh,
                this.pixclock, this.htotal, this.hbend, this.hbstart, this.vtotal, this.vbend, this.vbstart);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.type = type;
    }

    public String getRotate() {
        return rotate;
    }

    public void setRotate(String rotate) {
        this.rotate = rotate;
    }

    public String getFlipx() {
        return flipx;
    }

    public void setFlipx(String flipx) {
        this.flipx = flipx;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
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

    public Integer getHtotal() {
        return htotal;
    }

    public void setHtotal(Integer htotal) {
        this.htotal = htotal;
    }

    public Integer getHbend() {
        return hbend;
    }

    public void setHbend(Integer hbend) {
        this.hbend = hbend;
    }

    public Integer getHbstart() {
        return hbstart;
    }

    public void setHbstart(Integer hbstart) {
        this.hbstart = hbstart;
    }

    public Integer getVtotal() {
        return vtotal;
    }

    public void setVtotal(Integer vtotal) {
        this.vtotal = vtotal;
    }

    public Integer getVbend() {
        return vbend;
    }

    public void setVbend(Integer vbend) {
        this.vbend = vbend;
    }

    public Integer getVbstart() {
        return vbstart;
    }

    public void setVbstart(Integer vbstart) {
        this.vbstart = vbstart;
    }

    public MameMachineEntity getMachine() {
        return machine;
    }

    public void setMachine(MameMachineEntity machine) {
        this.machine = machine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameDisplayEntity entity = (MameDisplayEntity) o;
        return Objects.equals(tag, entity.tag) &&
                Objects.equals(type, entity.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag, type);
    }

    @Override
    public int compareTo(MameDisplayEntity o) {
        if (this == o)
            return 0;

        int i = 0;
        if (this.id != null && o.id == null)
            i = 1;
        else if (this.id == null && o.id != null)
            i = -1;
        else if (this.id != null)
            i = this.id.compareTo(o.id);
        if (i == 0)
            i = this.tag.compareTo(o.tag);
        if (i == 0) {
            i = this.type.compareTo(o.type);
        }
        return i;
    }
}
