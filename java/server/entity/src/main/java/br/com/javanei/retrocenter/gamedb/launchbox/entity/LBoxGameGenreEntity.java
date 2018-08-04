package br.com.javanei.retrocenter.gamedb.launchbox.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

/**
 * alter table lbox_game_genre drop foreign key FK5udm1hke3i3t788llbx8wx1hj;
 * alter table lbox_game_genre drop foreign key FKjxjoynlocljavwbo1n8jgrtyw;
 * alter table lbox_game_genre drop key UK72a7uyqk5x7ms9nef7jficbjy;
 * alter table lbox_game_genre drop key FKjxjoynlocljavwbo1n8jgrtyw;
 * alter table lbox_game_genre change game_id lbox_game_id varchar(32) NOT NULL;
 * <p>
 * alter table lbox_game_genre drop foreign key FK5udm1hke3i3t788llbx8wx1hj;
 * alter table lbox_game_genre drop foreign key FKqjskbaak7gymkioh9hwxgfj4h;
 * alter table lbox_game_genre drop key UKp0yqwwhujh8it2o0prv0lahou;
 * alter table lbox_game_genre drop key FKqjskbaak7gymkioh9hwxgfj4h;
 * alter table lbox_game_genre change genre_id lbox_genre_id bigint(20) NOT NULL;
 */
@Entity
@Table(name = "LBOX_GAME_GENRE",
        uniqueConstraints = @UniqueConstraint(columnNames = {"LBOX_GAME_ID", "LBOX_GENRE_ID"}))
public class LBoxGameGenreEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GAME_GENRE_ID", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LBOX_GAME_ID")
    private LBoxGameEntity game;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LBOX_GENRE_ID")
    private LBoxGenreEntity genre;

    public LBoxGameGenreEntity() {
    }

    public LBoxGameGenreEntity(LBoxGameEntity game, LBoxGenreEntity genre) {
        this.game = game;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LBoxGameEntity getGame() {
        return game;
    }

    public void setGame(LBoxGameEntity game) {
        this.game = game;
    }

    public LBoxGenreEntity getGenre() {
        return genre;
    }

    public void setGenre(LBoxGenreEntity genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LBoxGameGenreEntity that = (LBoxGameGenreEntity) o;

        if (game != null ? !game.equals(that.game) : that.game != null) return false;
        return genre != null ? genre.equals(that.genre) : that.genre == null;
    }

    @Override
    public int hashCode() {
        int result = game != null ? game.hashCode() : 0;
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LBoxGameGenreEntity{" +
                "id=" + id +
                ", genre=" + genre +
                '}';
    }
}
