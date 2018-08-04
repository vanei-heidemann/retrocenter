package br.com.javanei.retrocenter.datafile.mame.entity;

import br.com.javanei.retrocenter.datafile.mame.MameInputControl;

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
@Table(name = "MAME_INPUTCONTROL", indexes = {
        @Index(name = "MAME_INPUTCONTROL_0001", unique = true, columnList = "INPUT_ID,TYPE,PLAYER")
})
public class MameInputControlEntity implements Serializable, Comparable<MameInputControlEntity> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAME_INPUTCONTROL_ID", nullable = false)
    private Long id;

    @Column(name = "TYPE", length = 16, nullable = false)
    private String type;

    @Column(name = "PLAYER", nullable = true)
    private Integer player;

    @Column(name = "BUTTONS", nullable = true)
    private Integer buttons;

    @Column(name = "REQBUTTONS", nullable = true)
    private Integer reqbuttons;

    @Column(name = "MINIMUM", nullable = true)
    private Integer minimum;

    @Column(name = "MAXIMUM", nullable = true)
    private Integer maximum;

    @Column(name = "SENSITIVITY", nullable = true)
    private Integer sensitivity;

    @Column(name = "KEYDELTA", nullable = true)
    private Integer keydelta;

    @Column(name = "REVERSE", length = 3, nullable = true)
    private String reverse;

    @Column(name = "WAYS", length = 16, nullable = true)
    private String ways;

    @Column(name = "WAYS2", length = 16, nullable = true)
    private String ways2;

    @Column(name = "WAYS3", length = 16, nullable = true)
    private String ways3;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "INPUT_ID")
    private MameInputEntity input;

    public MameInputControlEntity() {
    }

    public MameInputControlEntity(String type, Integer player, Integer buttons, Integer reqbuttons, Integer minimum,
                                  Integer maximum, Integer sensitivity, Integer keydelta, String reverse, String ways,
                                  String ways2, String ways3) {
        this.type = type;
        this.player = player;
        this.buttons = buttons;
        this.reqbuttons = reqbuttons;
        this.minimum = minimum;
        this.maximum = maximum;
        this.sensitivity = sensitivity;
        this.keydelta = keydelta;
        this.reverse = reverse;
        this.ways = ways;
        this.ways2 = ways2;
        this.ways3 = ways3;
    }

    public MameInputControlEntity(MameInputControl control) {
        this(control.getType(), control.getPlayer(), control.getButtons(), control.getReqbuttons(),
                control.getMinimum(), control.getMaximum(), control.getSensitivity(), control.getKeydelta(),
                control.getReverse(), control.getWays(), control.getWays2(), control.getWays3());
    }

    public MameInputControl toVO() {
        return new MameInputControl(this.type, this.player, this.buttons, this.reqbuttons, this.minimum, this.maximum,
                this.sensitivity, this.keydelta, this.reverse, this.ways, this.ways2, this.ways3);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPlayer() {
        return player;
    }

    public void setPlayer(Integer player) {
        this.player = player;
    }

    public Integer getButtons() {
        return buttons;
    }

    public void setButtons(Integer buttons) {
        this.buttons = buttons;
    }

    public Integer getReqbuttons() {
        return reqbuttons;
    }

    public void setReqbuttons(Integer reqbuttons) {
        this.reqbuttons = reqbuttons;
    }

    public Integer getMinimum() {
        return minimum;
    }

    public void setMinimum(Integer minimum) {
        this.minimum = minimum;
    }

    public Integer getMaximum() {
        return maximum;
    }

    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
    }

    public Integer getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(Integer sensitivity) {
        this.sensitivity = sensitivity;
    }

    public Integer getKeydelta() {
        return keydelta;
    }

    public void setKeydelta(Integer keydelta) {
        this.keydelta = keydelta;
    }

    public String getReverse() {
        return reverse;
    }

    public void setReverse(String reverse) {
        this.reverse = reverse;
    }

    public String getWays() {
        return ways;
    }

    public void setWays(String ways) {
        this.ways = ways;
    }

    public String getWays2() {
        return ways2;
    }

    public void setWays2(String ways2) {
        this.ways2 = ways2;
    }

    public String getWays3() {
        return ways3;
    }

    public void setWays3(String ways3) {
        this.ways3 = ways3;
    }

    public MameInputEntity getInput() {
        return input;
    }

    public void setInput(MameInputEntity input) {
        this.input = input;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameInputControlEntity that = (MameInputControlEntity) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, player);
    }

    @Override
    public int compareTo(MameInputControlEntity o) {
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
            i = this.type.compareTo(o.type);
        if (i == 0) {
            i = this.player.compareTo(o.player);
        }
        return i;
    }
}
