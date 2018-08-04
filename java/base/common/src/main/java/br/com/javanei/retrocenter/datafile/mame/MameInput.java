package br.com.javanei.retrocenter.datafile.mame;

import br.com.javanei.retrocenter.util.StringUtil;
import br.com.javanei.retrocenter.util.ValidValuesUtil;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MameInput implements Serializable {
    private static final long serialVersionUID = 1L;

    private String service; // (yes|no) "no"
    private String tilt; // (yes|no) "no"
    private Integer players;
    private Integer coins;
    private Set<MameInputControl> controls = new HashSet<>();

    public MameInput() {
    }

    public MameInput(String service, String tilt, Integer players, Integer coins) {
        this.service = service;
        this.tilt = tilt;
        this.players = players;
        this.coins = coins;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = ValidValuesUtil.validateValue(service, ValidValuesUtil.YES_NO);
    }

    public String getTilt() {
        return tilt;
    }

    public void setTilt(String tilt) {
        this.tilt = ValidValuesUtil.validateValue(tilt, ValidValuesUtil.YES_NO);
    }

    public Integer getPlayers() {
        return players;
    }

    public void setPlayers(Integer players) {
        this.players = players;
    }

    public void setPlayers(String players) {
        this.players = new Integer(players);
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public void setCoins(String coins) {
        this.coins = new Integer(coins);
    }

    public Set<MameInputControl> getControls() {
        return controls;
    }

    public void setControls(Set<MameInputControl> controls) {
        this.controls = controls;
    }

    public boolean addControl(MameInputControl control) {
        return this.controls.add(control);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameInput mameInput = (MameInput) o;
        return Objects.equals(players, mameInput.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<input");
        //<input players="1" coins="2" service="yes" tilt="yes">
        if (this.players != null) {
            sb.append(" players=\"").append(this.players).append("\"");
        }
        if (this.coins != null) {
            sb.append(" coins=\"").append(this.coins).append("\"");
        }
        if (this.service != null && !this.service.equals("no")) {
            sb.append(" service=\"").append(this.service).append("\"");
        }
        if (this.tilt != null && !this.tilt.equals("no")) {
            sb.append(" tilt=\"").append(this.tilt).append("\"");
        }
        sb.append(">").append(StringUtil.LINE_SEPARATOR);

        for (MameInputControl control : this.controls) {
            sb.append(control.toString());
        }

        sb.append("\t\t</input>").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
