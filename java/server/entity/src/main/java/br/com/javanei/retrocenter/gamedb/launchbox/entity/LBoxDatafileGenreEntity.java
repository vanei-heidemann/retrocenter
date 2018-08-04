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
 * alter table lbox_datafile_genre drop foreign key FKe5x1sx83h4nbjx24gsrxyensr;
 * alter table lbox_datafile_genre drop foreign key FKj1tdul4vet3v7tj4busuw6tcr;
 * alter table lbox_datafile_genre drop key UKt0wp0y85us2r8emav24l4xlls;
 * alter table lbox_datafile_genre drop key FKe5x1sx83h4nbjx24gsrxyensr;
 * alter table lbox_datafile_genre change datafile_id lbox_datafile_id bigint(20) NOT NULL;
 * <p>
 * alter table lbox_datafile_genre drop foreign key FKj1tdul4vet3v7tj4busuw6tcr;
 * alter table lbox_datafile_genre drop foreign key FKrligatydn3c1ww0elaapdvl11;
 * alter table lbox_datafile_genre drop key UK1ujqjdo53cf7daoprfloanis0;
 * alter table lbox_datafile_genre drop key FKrligatydn3c1ww0elaapdvl11;
 * alter table lbox_datafile_genre change genre_id lbox_genre_id bigint(20) NOT NULL;
 */
@Entity
@Table(name = "LBOX_DATAFILE_GENRE",
        uniqueConstraints = @UniqueConstraint(columnNames = {"LBOX_DATAFILE_ID", "LBOX_GENRE_ID"}))
public class LBoxDatafileGenreEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DATAFILE_GENRE_ID", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LBOX_DATAFILE_ID")
    private LBoxDatafileEntity datafile;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LBOX_GENRE_ID")
    private LBoxGenreEntity genre;

    public LBoxDatafileGenreEntity() {
    }

    public LBoxDatafileGenreEntity(LBoxDatafileEntity datafile, LBoxGenreEntity genre) {
        this.datafile = datafile;
        this.genre = genre;
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

        LBoxDatafileGenreEntity that = (LBoxDatafileGenreEntity) o;

        if (datafile != null ? !datafile.equals(that.datafile) : that.datafile != null) return false;
        return genre != null ? genre.equals(that.genre) : that.genre == null;
    }

    @Override
    public int hashCode() {
        int result = datafile != null ? datafile.hashCode() : 0;
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LBoxDatafileGenreEntity{" +
                "id=" + id +
                ", genre=" + genre +
                '}';
    }
}
