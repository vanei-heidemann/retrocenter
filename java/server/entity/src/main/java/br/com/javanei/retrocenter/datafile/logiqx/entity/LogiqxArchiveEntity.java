package br.com.javanei.retrocenter.datafile.logiqx.entity;

import br.com.javanei.retrocenter.datafile.logiqx.LogiqxArchive;

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
@Table(name = "LOGIQX_ARCHIVE", indexes = {
        @Index(name = "LOGIQX_ARCHIVE_0001", unique = true, columnList = "GAME_ID,NAME")
})
public class LogiqxArchiveEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ARCHIVE_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 255, nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "GAME_ID")
    private LogiqxGameEntity game;

    public LogiqxArchiveEntity() {
    }

    public LogiqxArchiveEntity(LogiqxArchive archive) {
        this(archive.getName());
    }

    public LogiqxArchiveEntity(Long id) {
        this.id = id;
    }

    public LogiqxArchiveEntity(String name) {
        this.name = name;
    }

    public LogiqxArchiveEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LogiqxGameEntity getGame() {
        return game;
    }

    public void setGame(LogiqxGameEntity game) {
        this.game = game;
    }

    public LogiqxArchive toVO() {
        return new LogiqxArchive(this.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogiqxArchiveEntity that = (LogiqxArchiveEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
