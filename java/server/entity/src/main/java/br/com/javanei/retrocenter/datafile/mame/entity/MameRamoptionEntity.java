package br.com.javanei.retrocenter.datafile.mame.entity;

import br.com.javanei.retrocenter.datafile.mame.MameRamoption;

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
@Table(name = "MAME_RAMOPTION", indexes = {
        @Index(name = "MAME_RAMOPTION_0001", unique = true, columnList = "MACHINE_ID,CONTENT,ISDEFAULT")
})
public class MameRamoptionEntity implements Serializable, Comparable<MameRamoptionEntity> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAME_RAMOPTION_ID", nullable = false)
    private Long id;

    @Column(name = "ISDEFAULT", nullable = true)
    private Integer _default;

    @Column(name = "CONTENT", nullable = true)
    private Long content;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "MACHINE_ID")
    private MameMachineEntity machine;

    public MameRamoptionEntity(Integer _default, Long content) {
        this._default = _default;
        this.content = content;
    }

    public MameRamoptionEntity() {
    }

    public MameRamoptionEntity(MameRamoption ramoption) {
        this(ramoption.getDefault(), ramoption.getContent());
    }

    public MameRamoption toVO() {
        return new MameRamoption(this._default, this.content);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDefault() {
        return _default;
    }

    public void setDefault(Integer _default) {
        this._default = _default;
    }

    public Long getContent() {
        return content;
    }

    public void setContent(Long content) {
        this.content = content;
    }

    public MameMachineEntity getMachine() {
        return machine;
    }

    public void setMachine(MameMachineEntity machine) {
        this.machine = machine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MameRamoptionEntity that = (MameRamoptionEntity) o;
        return Objects.equals(_default, that._default) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_default, content);
    }

    @Override
    public int compareTo(MameRamoptionEntity o) {
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
            i = this.content.compareTo(o.content);
        if (i == 0) {
            if (this._default != null && o._default == null)
                i = 1;
            else if (this._default == null && o._default != null)
                i = -1;
            else if (this._default != null)
                i = this._default.compareTo(o._default);
        }
        return i;
    }
}
