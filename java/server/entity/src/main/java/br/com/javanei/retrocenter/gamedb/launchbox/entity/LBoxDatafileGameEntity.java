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
 * alter table lbox_datafile_game drop foreign key FKg9rayoxfwobyx05xlvcpyayxt;
 * alter table lbox_datafile_game drop foreign key FKpxnlmardyd9kuin4aqbdalcer;
 * alter table lbox_datafile_game drop key UKo8144p7k6r4e93xs3ml032tel;
 * alter table lbox_datafile_game drop key FKg9rayoxfwobyx05xlvcpyayxt;
 * alter table lbox_datafile_game change datafile_id lbox_datafile_id bigint(20) NOT NULL;
 * <p>
 * alter table lbox_datafile_game drop foreign key FK73skp62i81mxs4x55nrkgpyku;
 * alter table lbox_datafile_game drop foreign key FKpxnlmardyd9kuin4aqbdalcer;
 * alter table lbox_datafile_game drop key UKeue1iock9h43onf1han07jcpt;
 * alter table lbox_datafile_game drop key FK73skp62i81mxs4x55nrkgpyku;
 * alter table lbox_datafile_game change game_id lbox_game_id varchar(32) NOT NULL;
 */
@Entity
@Table(name = "LBOX_DATAFILE_GAME",
        uniqueConstraints = @UniqueConstraint(columnNames = {"LBOX_DATAFILE_ID", "LBOX_GAME_ID"}))
public class LBoxDatafileGameEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DATAFILE_GAME_ID", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LBOX_DATAFILE_ID")
    private LBoxDatafileEntity datafile;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LBOX_GAME_ID")
    private LBoxGameEntity game;

    public LBoxDatafileGameEntity() {
    }

    public LBoxDatafileGameEntity(LBoxDatafileEntity datafile, LBoxGameEntity game) {
        this.datafile = datafile;
        this.game = game;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LBoxDatafileEntity getDatafile() {
        return datafile;
    }

    public void setDatafile(LBoxDatafileEntity datafile) {
        this.datafile = datafile;
    }

    public LBoxGameEntity getGame() {
        return game;
    }

    public void setGame(LBoxGameEntity game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LBoxDatafileGameEntity that = (LBoxDatafileGameEntity) o;

        if (datafile != null ? !datafile.equals(that.datafile) : that.datafile != null) return false;
        return game != null ? game.equals(that.game) : that.game == null;
    }

    @Override
    public int hashCode() {
        int result = datafile != null ? datafile.hashCode() : 0;
        result = 31 * result + (game != null ? game.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LBoxDatafileGameEntity{" +
                "id=" + id +
                ", game=" + game +
                '}';
    }
}
