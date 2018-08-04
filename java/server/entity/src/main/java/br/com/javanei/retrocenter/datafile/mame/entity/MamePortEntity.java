package br.com.javanei.retrocenter.datafile.mame.entity;

import br.com.javanei.retrocenter.datafile.mame.MameAnalog;
import br.com.javanei.retrocenter.datafile.mame.MamePort;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "MAME_PORT", indexes = {
        @Index(name = "MAME_PORT_0001", unique = false, columnList = "MACHINE_ID,TAG")
})
public class MamePortEntity implements Serializable, Comparable<MamePortEntity> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAME_PORT_ID", nullable = false)
    private Long id;

    @Column(name = "TAG", length = 128, nullable = false)
    private String tag;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "port")
    private List<MameAnalogEntity> analogs = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "MACHINE_ID")
    private MameMachineEntity machine;

    public MamePortEntity() {
    }

    public MamePortEntity(String tag) {
        this.tag = tag;
    }

    public MamePortEntity(MamePort port) {
        this(port.getTag());
        for (MameAnalog analog : port.getAnalogs()) {
            MameAnalogEntity e = new MameAnalogEntity(analog);
            e.setPort(this);
            this.analogs.add(e);
        }
    }

    public MamePort toVO() {
        MamePort port = new MamePort(this.tag);
        for (MameAnalogEntity analogEntity : this.analogs) {
            port.addAnalog(analogEntity.toVO());
        }
        return port;
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

    public List<MameAnalogEntity> getAnalogs() {
        return analogs;
    }

    public void setAnalogs(List<MameAnalogEntity> analogs) {
        this.analogs = analogs;
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
        MamePortEntity entity = (MamePortEntity) o;
        return Objects.equals(tag, entity.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag);
    }

    @Override
    public int compareTo(MamePortEntity o) {
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
        return i;
    }
}
