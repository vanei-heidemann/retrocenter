package br.com.javanei.retrocenter.datafile.mame.entity;

import br.com.javanei.retrocenter.datafile.mame.MameAnalog;

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
@Table(name = "MAME_ANALOG", indexes = {
        @Index(name = "MAME_ANALOG_0001", unique = false, columnList = "PORT_ID,MASK")
})
//WARN: Está duplicando!
public class MameAnalogEntity implements Serializable, Comparable<MameAnalogEntity> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAME_ANALOG_ID", nullable = false)
    private Long id;

    @Column(name = "MASK", nullable = false)
    private Integer mask;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "PORT_ID")
    private MamePortEntity port;

    public MameAnalogEntity() {
    }

    public MameAnalogEntity(Integer mask) {
        this.mask = mask;
    }

    public MameAnalogEntity(MameAnalog analog) {
        this(analog.getMask());
    }

    public MameAnalog toVO() {
        return new MameAnalog(this.mask);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMask() {
        return mask;
    }

    public void setMask(Integer mask) {
        this.mask = mask;
    }

    public MamePortEntity getPort() {
        return port;
    }

    public void setPort(MamePortEntity port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameAnalogEntity that = (MameAnalogEntity) o;
        return Objects.equals(mask, that.mask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mask);
    }

    @Override
    public int compareTo(MameAnalogEntity o) {
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
            i = this.mask.compareTo(o.mask);
        return i;
    }
}
