package br.com.javanei.retrocenter.datafile.hyperlist.entity;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "HYPERLIST_GAME", indexes = {
        @Index(name = "HYPERLIST_GAME_0001", unique = true, columnList = "GAME_ID,NAME,GAME_INDEX,GAME_IMAGE")
})
@NamedQueries({
        @NamedQuery(name = "HyperListGameEntity.findByDatafileAndUnique",
                query = "SELECT o from HyperListGameEntity o WHERE o.datafile.name = :datafileName AND o.datafile.lastUpdate = :lastUpdate AND o.datafile.version = :version AND o.name = :name AND o.index = :index AND o.image = :image")
})
public class HyperListGameEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "NAME", length = 255, nullable = false)
    public String name;
    @Column(name = "GAME_INDEX", length = 16, nullable = true)
    public String index;
    @Column(name = "GAME_IMAGE", length = 16, nullable = true)
    public String image;
    @Column(name = "DESCRIPTION", length = 255, nullable = true)
    public String description;
    @Column(name = "CLONEOF", length = 255, nullable = true)
    public String cloneof;
    @Column(name = "CRC", length = 8, nullable = true)
    public String crc;
    @Column(name = "MANUFACTURER", length = 255, nullable = true)
    public String manufacturer;
    @Column(name = "YEAR", length = 10, nullable = true)
    public String year;
    @Column(name = "GENRE", length = 128, nullable = true)
    public String genre;
    @Column(name = "RATING", length = 255, nullable = true)
    public String rating;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GAME_ID", nullable = false)
    private Long id;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "DATAFILE_ID")
    private HyperListEntity datafile;

    public HyperListGameEntity() {
    }

    public HyperListGameEntity(Long id) {
        this.id = id;
    }

    public HyperListGameEntity(String name, String index, String image, String description, String cloneof, String crc,
                               String manufacturer, String year, String genre, String rating) {
        this.name = name;
        this.index = index;
        this.image = image;
        this.description = description;
        this.cloneof = cloneof;
        this.crc = crc;
        this.manufacturer = manufacturer;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
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

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCloneof() {
        return cloneof;
    }

    public void setCloneof(String cloneof) {
        this.cloneof = cloneof;
    }

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public HyperListEntity getDatafile() {
        return datafile;
    }

    public void setDatafile(HyperListEntity datafile) {
        this.datafile = datafile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HyperListGameEntity that = (HyperListGameEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(index, that.index) &&
                Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, index, image);
    }

    @Override
    public String toString() {
        return "HyperListGameEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", index='" + index + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", cloneof='" + cloneof + '\'' +
                ", crc='" + crc + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", year='" + year + '\'' +
                ", genre='" + genre + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
