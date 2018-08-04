package br.com.javanei.retrocenter.datafile.mame;

import br.com.javanei.retrocenter.util.StringUtil;

import java.io.Serializable;
import java.util.Objects;

public class MameSound implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer channels;

    public MameSound() {
    }

    public MameSound(Integer channels) {
        this.channels = channels;
    }

    public Integer getChannels() {
        return channels;
    }

    public void setChannels(Integer channels) {
        this.channels = channels;
    }

    public void setChannels(String channels) {
        this.channels = new Integer(channels);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameSound mameSound = (MameSound) o;
        return Objects.equals(channels, mameSound.channels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(channels);
    }

    @Override
    public String toString() {
        return "\t\t<sound channels=\"" + this.channels + "\"/>" + StringUtil.LINE_SEPARATOR;
    }
}
