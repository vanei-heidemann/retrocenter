package br.com.javanei.retrocenter.datafile.hyperlist;

import java.io.Serializable;
import java.util.Objects;

public class HyperListGame implements Serializable {
    private static final long serialVersionUID = 1L;

    public String name;
    public String index;
    public String image;

    public String description;
    public String cloneof;
    public String crc;
    public String manufacturer;
    public String year;
    public String genre;
    public String rating;

    public HyperListGame() {
    }

    public HyperListGame(String name, String index, String image) {
        this.name = name;
        this.index = index;
        this.image = image;
    }

    public HyperListGame(String name, String index, String image, String description, String cloneof, String crc,
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

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

    public String getCloneof() {
        return cloneof;
    }

    public void setCloneof(String cloneof) {
        this.cloneof = cloneof;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HyperListGame that = (HyperListGame) o;
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
        return "HyperListGame{" +
                "name='" + name + '\'' +
                ", index='" + index + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", crc='" + crc + '\'' +
                ", cloneof='" + cloneof + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", year='" + year + '\'' +
                ", genre='" + genre + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
