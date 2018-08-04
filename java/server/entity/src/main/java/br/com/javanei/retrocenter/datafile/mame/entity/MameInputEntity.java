package br.com.javanei.retrocenter.datafile.mame.entity;

import br.com.javanei.retrocenter.datafile.mame.MameInput;
import br.com.javanei.retrocenter.datafile.mame.MameInputControl;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "MAME_INPUT")
public class MameInputEntity implements Serializable, Comparable<MameInputEntity> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAME_INPUT_ID", nullable = false)
    private Long id;

    @Column(name = "SERVICE", length = 3, nullable = true)
    private String service;

    @Column(name = "TILT", length = 3, nullable = true)
    private String tilt;

    @Column(name = "PLAYERS", nullable = false)
    private Integer players;

    @Column(name = "COINS", nullable = true)
    private Integer coins;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "input")
    private Set<MameInputControlEntity> controls = new LinkedHashSet<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, optional = false)
    @JoinColumn(name = "MACHINE_ID")
    private MameMachineEntity machine;

    public MameInputEntity() {
    }

    public MameInputEntity(String service, String tilt, Integer players, Integer coins) {
        this.service = service;
        this.tilt = tilt;
        this.players = players;
        this.coins = coins;
    }

    public MameInputEntity(MameInput input) {
        this(input.getService(), input.getTilt(), input.getPlayers(), input.getCoins());
        for (MameInputControl control : input.getControls()) {
            MameInputControlEntity e = new MameInputControlEntity(control);
            e.setInput(this);
            this.controls.add(e);
        }
    }

    public MameInput toVO() {
        MameInput input = new MameInput(this.service, this.tilt, this.players, this.coins);
        for (MameInputControlEntity controlEntity : this.controls) {
            input.addControl(controlEntity.toVO());
        }
        return input;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getTilt() {
        return tilt;
    }

    public void setTilt(String tilt) {
        this.tilt = tilt;
    }

    public Integer getPlayers() {
        return players;
    }

    public void setPlayers(Integer players) {
        this.players = players;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public Set<MameInputControlEntity> getControls() {
        return controls;
    }

    public void setControls(Set<MameInputControlEntity> controls) {
        this.controls = controls;
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
        MameInputEntity entity = (MameInputEntity) o;
        return Objects.equals(players, entity.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players);
    }

    @Override
    public int compareTo(MameInputEntity o) {
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
            i = this.players.compareTo(o.players);
        return i;
    }
}
