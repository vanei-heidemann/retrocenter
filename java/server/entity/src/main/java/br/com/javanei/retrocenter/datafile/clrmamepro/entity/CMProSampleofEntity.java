package br.com.javanei.retrocenter.datafile.clrmamepro.entity;

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
@Table(name = "CMPRO_SAMPLEOF", indexes = {
        @Index(name = "CMPRO_SAMPLEOF_0001", unique = true, columnList = "GAME_ID,SAMPLEOF")
})
public class CMProSampleofEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SAMPLEOF_ID", nullable = false)
    private Long id;

    @Column(name = "SAMPLEOF", length = 255, nullable = false)
    private String sampleof;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "GAME_ID")
    private CMProGameEntity game;

    public CMProSampleofEntity() {
    }

    public CMProSampleofEntity(String sampleof) {
        this.sampleof = sampleof;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSampleof() {
        return sampleof;
    }

    public void setSampleof(String sampleof) {
        this.sampleof = sampleof;
    }

    public CMProGameEntity getGame() {
        return game;
    }

    public void setGame(CMProGameEntity game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CMProSampleofEntity that = (CMProSampleofEntity) o;
        return Objects.equals(sampleof, that.sampleof);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sampleof);
    }
}
