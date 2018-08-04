package br.com.javanei.retrocenter.datafile.mame;

import br.com.javanei.retrocenter.util.StringUtil;
import br.com.javanei.retrocenter.util.ValidValuesUtil;

import java.io.Serializable;
import java.util.Objects;

public class MameDriver implements Serializable {
    private static final long serialVersionUID = 1L;

    private String status; // (good|imperfect|preliminary)
    private String emulation; // (good|imperfect|preliminary)
    private String color; // (good|imperfect|preliminary)
    private String sound; // (good|imperfect|preliminary)
    private String graphic; // (good|imperfect|preliminary)
    private String cocktail; // (good|imperfect|preliminary)
    private String protection; // (good|imperfect|preliminary)
    private String savestate; // (supported|unsupported)

    public MameDriver() {
    }

    public MameDriver(String status, String emulation, String color, String sound, String graphic, String cocktail,
                      String protection, String savestate) {
        this.status = status;
        this.emulation = emulation;
        this.color = color;
        this.sound = sound;
        this.graphic = graphic;
        this.cocktail = cocktail;
        this.protection = protection;
        this.savestate = savestate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = ValidValuesUtil.validateValue(status, ValidValuesUtil.GOOD_IMPERFECT_PRELIMINARY);
    }

    public String getEmulation() {
        return emulation;
    }

    public void setEmulation(String emulation) {
        this.emulation = ValidValuesUtil.validateValue(emulation, ValidValuesUtil.GOOD_IMPERFECT_PRELIMINARY);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = ValidValuesUtil.validateValue(color, ValidValuesUtil.GOOD_IMPERFECT_PRELIMINARY);
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = ValidValuesUtil.validateValue(sound, ValidValuesUtil.GOOD_IMPERFECT_PRELIMINARY);
    }

    public String getGraphic() {
        return graphic;
    }

    public void setGraphic(String graphic) {
        this.graphic = ValidValuesUtil.validateValue(graphic, ValidValuesUtil.GOOD_IMPERFECT_PRELIMINARY);
    }

    public String getCocktail() {
        return cocktail;
    }

    public void setCocktail(String cocktail) {
        this.cocktail = ValidValuesUtil.validateValue(cocktail, ValidValuesUtil.GOOD_IMPERFECT_PRELIMINARY);
    }

    public String getProtection() {
        return protection;
    }

    public void setProtection(String protection) {
        this.protection = ValidValuesUtil.validateValue(protection, ValidValuesUtil.GOOD_IMPERFECT_PRELIMINARY);
    }

    public String getSavestate() {
        return savestate;
    }

    public void setSavestate(String savestate) {
        this.savestate = ValidValuesUtil.validateValue(savestate, ValidValuesUtil.SUPPORTED_UNSUPPORTED);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameDriver that = (MameDriver) o;
        return Objects.equals(status, that.status) &&
                Objects.equals(emulation, that.emulation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, emulation);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<driver");
        if (this.status != null) {
            sb.append(" status=\"").append(this.status).append("\"");
        }
        if (this.emulation != null) {
            sb.append(" emulation=\"").append(this.emulation).append("\"");
        }
        if (this.color != null) {
            sb.append(" color=\"").append(this.color).append("\"");
        }
        if (this.sound != null) {
            sb.append(" sound=\"").append(this.sound).append("\"");
        }
        if (this.graphic != null) {
            sb.append(" graphic=\"").append(this.graphic).append("\"");
        }
        if (this.cocktail != null) {
            sb.append(" cocktail=\"").append(this.cocktail).append("\"");
        }
        if (this.protection != null) {
            sb.append(" protection=\"").append(this.protection).append("\"");
        }
        if (this.savestate != null) {
            sb.append(" savestate=\"").append(this.savestate).append("\"");
        }
        sb.append("/>").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
