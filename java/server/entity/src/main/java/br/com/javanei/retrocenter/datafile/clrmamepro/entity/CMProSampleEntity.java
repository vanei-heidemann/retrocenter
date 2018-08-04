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
@Table(name = "CMPRO_SAMPLE", indexes = {
        @Index(name = "CMPRO_SAMPLE_0001", unique = true, columnList = "GAME_ID,SAMPLE")
})
public class CMProSampleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SAMPLE_ID", nullable = false)
    private Long id;

    @Column(name = "SAMPLE", length = 255, nullable = false)
    private String sample;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "GAME_ID")
    private CMProGameEntity game;

    public CMProSampleEntity() {
    }

    public CMProSampleEntity(String sample) {
        this.sample = sample;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
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
        CMProSampleEntity that = (CMProSampleEntity) o;
        return Objects.equals(sample, that.sample);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sample);
    }
}
