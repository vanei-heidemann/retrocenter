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
 * alter table lbox_game_region drop foreign key FKbm21nvmtysc9bp2spd8434xxp;
 * alter table lbox_game_region drop foreign key FKpa147bb52tqn7ktxvpnxymfo3;
 * alter table lbox_game_region drop key UKirxr0yhb7ply2t09e3558jn06;
 * alter table lbox_game_region drop key FKpa147bb52tqn7ktxvpnxymfo3;
 * alter table lbox_game_region change game_id lbox_game_id varchar(32) NOT NULL;
 * <p>
 * alter table lbox_game_region drop foreign key FKbc1vp1qn2j56997mpuf280uuc;
 * alter table lbox_game_region drop foreign key FKbm21nvmtysc9bp2spd8434xxp;
 * alter table lbox_game_region drop key UKi8mwsncfig2ebdonefohfvvmo;
 * alter table lbox_game_region drop key FKbc1vp1qn2j56997mpuf280uuc;
 * alter table lbox_game_region change region_id lbox_region_id bigint(20) NOT NULL;
 */
@Entity
@Table(name = "LBOX_GAME_REGION",
        uniqueConstraints = @UniqueConstraint(columnNames = {"LBOX_GAME_ID", "LBOX_REGION_ID"}))
public class LBoxGameRegionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GAME_REGION_ID", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LBOX_GAME_ID")
    private LBoxGameEntity game;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LBOX_REGION_ID")
    private LBoxRegionEntity region;

    public LBoxGameRegionEntity() {
    }

    public LBoxGameRegionEntity(LBoxGameEntity game, LBoxRegionEntity region) {
        this.game = game;
        this.region = region;
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

    public LBoxRegionEntity getRegion() {
        return region;
    }

    public void setRegion(LBoxRegionEntity region) {
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LBoxGameRegionEntity that = (LBoxGameRegionEntity) o;

        if (game != null ? !game.equals(that.game) : that.game != null) return false;
        return region != null ? region.equals(that.region) : that.region == null;
    }

    @Override
    public int hashCode() {
        int result = game != null ? game.hashCode() : 0;
        result = 31 * result + (region != null ? region.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LBoxGameRegionEntity{" +
                "id=" + id +
                ", region=" + region +
                '}';
    }
}
