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
 * alter table lbox_game_image drop foreign key FK19d4l74taesk6la9vxt9rj3k8;
 * alter table lbox_game_image drop foreign key FKol9r11w1sytdc01seawkrr3sm;
 * alter table lbox_game_image drop key UK6bvd6aimbv52dyt3atjlvfv24;
 * alter table lbox_game_image drop key FKol9r11w1sytdc01seawkrr3sm;
 * alter table lbox_game_image change game_id lbox_game_id varchar(32) NOT NULL;
 * <p>
 * alter table lbox_game_image drop foreign key FK19d4l74taesk6la9vxt9rj3k8;
 * alter table lbox_game_image drop foreign key FK2ryjt6o067blm7mbo18v6gfax;
 * alter table lbox_game_image drop key UKim7sun92mve9pbxspvp6c4y3s;
 * alter table lbox_game_image drop key FK2ryjt6o067blm7mbo18v6gfax;
 * alter table lbox_game_image change region_id lbox_region_id bigint(20) DEFAULT NULL;
 */
@Entity
@Table(name = "LBOX_GAME_IMAGE", uniqueConstraints =
        {@UniqueConstraint(columnNames =
                {"LBOX_GAME_ID", "IMAGE_TYPE", "FILE_NAME", "CRC32", "LBOX_REGION_ID"})
        })
public class LBoxGameImageEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GAME_IMAGE_ID", nullable = false)
    private Long id;

    @Column(name = "FILE_NAME", length = 255, nullable = false)
    private String fileName;

    @Column(name = "IMAGE_TYPE", length = 64, nullable = false)
    private String type;

    @Column(name = "CRC32", length = 10, nullable = true)
    private String crc32;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "LBOX_REGION_ID")
    private LBoxRegionEntity region;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LBOX_GAME_ID")
    private LBoxGameEntity game;

    public LBoxGameImageEntity() {
    }

    public LBoxGameImageEntity(LBoxGameEntity game) {
        this.game = game;
    }

    public LBoxGameImageEntity(LBoxGameEntity game, String fileName, String type, String crc32) {
        this.fileName = fileName;
        this.type = type;
        this.crc32 = crc32;
        this.region = region;
        this.game = game;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCrc32() {
        return crc32;
    }

    public void setCrc32(String crc32) {
        this.crc32 = crc32;
    }

    public LBoxRegionEntity getRegion() {
        return region;
    }

    public void setRegion(LBoxRegionEntity region) {
        this.region = region;
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

        LBoxGameImageEntity that = (LBoxGameImageEntity) o;

        if (!fileName.equals(that.fileName)) return false;
        if (!type.equals(that.type)) return false;
        if (crc32 != null ? !crc32.equals(that.crc32) : that.crc32 != null) return false;
        return region != null ? region.equals(that.region) : that.region == null;
    }

    @Override
    public int hashCode() {
        int result = fileName.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + (crc32 != null ? crc32.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LBoxGameImageEntity{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", type='" + type + '\'' +
                ", crc32='" + crc32 + '\'' +
                ", region=" + region +
                '}';
    }
}
