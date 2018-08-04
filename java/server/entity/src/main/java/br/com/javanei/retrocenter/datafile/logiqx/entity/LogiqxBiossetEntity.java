package br.com.javanei.retrocenter.datafile.logiqx.entity;

import br.com.javanei.retrocenter.datafile.logiqx.LogiqxBiosset;

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
@Table(name = "LOGIQX_BIOSSET", indexes = {
        @Index(name = "LOGIQX_BIOSSET_0001", unique = true, columnList = "GAME_ID,NAME")
})
public class LogiqxBiossetEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BIOSSET_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 255, nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", length = 255, nullable = true)
    private String description;

    @Column(name = "ISDEFAULT", length = 3, nullable = true)
    private String _default;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "GAME_ID")
    private LogiqxGameEntity game;

    public LogiqxBiossetEntity() {
    }

    public LogiqxBiossetEntity(LogiqxBiosset biosset) {
        this(biosset.getName(), biosset.getDescription(), biosset.getDefault());
    }

    public LogiqxBiossetEntity(Long id) {
        this.id = id;
    }

    public LogiqxBiossetEntity(String name) {
        this.name = name;
    }

    public LogiqxBiossetEntity(String name, String description, String _default) {
        this.name = name;
        this.description = description;
        this._default = _default;
    }

    public LogiqxBiossetEntity(Long id, String name, String description, String _default) {
        this.id = id;
        this.name = name;
        this.description = description;
        this._default = _default;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefault() {
        return _default;
    }

    public void setDefault(String _default) {
        this._default = _default;
    }

    public LogiqxGameEntity getGame() {
        return game;
    }

    public void setGame(LogiqxGameEntity game) {
        this.game = game;
    }

    public LogiqxBiosset toVO() {
        return new LogiqxBiosset(this.name, this.description, this._default);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogiqxBiossetEntity that = (LogiqxBiossetEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
