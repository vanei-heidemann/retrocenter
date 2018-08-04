package br.com.javanei.retrocenter.datafile.mame.entity;

import br.com.javanei.retrocenter.datafile.mame.MameDriver;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "MAME_DRIVER")
public class MameDriverEntity implements Serializable, Comparable<MameDriverEntity> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAME_DRIVER_ID", nullable = false)
    private Long id;

    @Column(name = "STATUS", length = 12, nullable = false)
    private String status;

    @Column(name = "EMULATION", length = 12, nullable = false)
    private String emulation;

    @Column(name = "COLOR", length = 12, nullable = true)
    private String color;

    @Column(name = "SOUND", length = 12, nullable = true)
    private String sound;

    @Column(name = "GRAPHIC", length = 12, nullable = true)
    private String graphic;

    @Column(name = "COCKTAIL", length = 12, nullable = true)
    private String cocktail;

    @Column(name = "PROTECTION", length = 12, nullable = true)
    private String protection;

    @Column(name = "SAVESTATE", length = 12, nullable = true)
    private String savestate;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, optional = false)
    @JoinColumn(name = "MACHINE_ID")
    private MameMachineEntity machine;

    public MameDriverEntity() {
    }

    public MameDriverEntity(String status, String emulation, String color, String sound, String graphic,
                            String cocktail, String protection, String savestate) {
        this.id = id;
        this.status = status;
        this.emulation = emulation;
        this.color = color;
        this.sound = sound;
        this.graphic = graphic;
        this.cocktail = cocktail;
        this.protection = protection;
        this.savestate = savestate;
    }

    public MameDriverEntity(MameDriver driver) {
        this(driver.getStatus(), driver.getEmulation(), driver.getColor(), driver.getSound(), driver.getGraphic(),
                driver.getCocktail(), driver.getProtection(), driver.getSavestate());
    }

    public MameDriver toVO() {
        return new MameDriver(this.status, this.emulation, this.color, this.sound, this.graphic, this.cocktail,
                this.protection, this.savestate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmulation() {
        return emulation;
    }

    public void setEmulation(String emulation) {
        this.emulation = emulation;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getGraphic() {
        return graphic;
    }

    public void setGraphic(String graphic) {
        this.graphic = graphic;
    }

    public String getCocktail() {
        return cocktail;
    }

    public void setCocktail(String cocktail) {
        this.cocktail = cocktail;
    }

    public String getProtection() {
        return protection;
    }

    public void setProtection(String protection) {
        this.protection = protection;
    }

    public String getSavestate() {
        return savestate;
    }

    public void setSavestate(String savestate) {
        this.savestate = savestate;
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
        MameDriverEntity entity = (MameDriverEntity) o;
        return Objects.equals(status, entity.status) &&
                Objects.equals(emulation, entity.emulation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, emulation);
    }

    @Override
    public int compareTo(MameDriverEntity o) {
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
            i = this.status.compareTo(o.status);
        if (i == 0) {
            i = this.emulation.compareTo(o.emulation);
        }
        return i;
    }
}
