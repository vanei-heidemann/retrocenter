package br.com.javanei.retrocenter.datafile.mame.entity;

import br.com.javanei.retrocenter.datafile.mame.MameSound;

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
@Table(name = "MAME_SOUND")
public class MameSoundEntity implements Serializable, Comparable<MameSoundEntity> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAME_SOUND_ID", nullable = false)
    private Long id;

    @Column(name = "CHANNELS", nullable = false)
    private Integer channels;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, optional = false)
    @JoinColumn(name = "MACHINE_ID")
    private MameMachineEntity machine;

    public MameSoundEntity() {
    }

    public MameSoundEntity(Integer channels) {
        this.channels = channels;
    }

    public MameSoundEntity(MameSound sound) {
        this(sound.getChannels());
    }

    public MameSound toVO() {
        return new MameSound(this.channels);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getChannels() {
        return channels;
    }

    public void setChannels(Integer channels) {
        this.channels = channels;
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
        MameSoundEntity entity = (MameSoundEntity) o;
        return Objects.equals(channels, entity.channels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(channels);
    }

    @Override
    public int compareTo(MameSoundEntity o) {
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
            i = this.channels.compareTo(o.channels);
        return i;
    }
}
