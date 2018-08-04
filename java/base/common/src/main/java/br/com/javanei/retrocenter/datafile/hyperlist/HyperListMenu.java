package br.com.javanei.retrocenter.datafile.hyperlist;

import br.com.javanei.retrocenter.common.ArtifactFileTypeEnum;
import br.com.javanei.retrocenter.common.DatafileCatalogEnum;
import br.com.javanei.retrocenter.datafile.common.Datafile;
import br.com.javanei.retrocenter.datafile.common.DatafileArtifact;
import br.com.javanei.retrocenter.datafile.common.DatafileArtifactFile;
import br.com.javanei.retrocenter.datafile.common.DatafileObject;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * https://hyperlist.hyperspin-fe.com/
 */
public class HyperListMenu implements DatafileObject, Serializable {
    private static final long serialVersionUID = 1L;

    private HyperListHeader header;
    private Set<HyperListGame> games = new HashSet<>();

    public HyperListMenu() {
    }

    public HyperListMenu(HyperListHeader header) {
        this.header = header;
    }

    private static void appendXMLAttribute(StringBuilder sb, String name, Object value) {
        sb.append(" ").append(name).append("=\"").append(value != null ? value : "").append("\"");
    }

    private static void appendXMLTag(StringBuilder sb, String name, Object value, int ident) {
        for (int i = 0; i < ident; i++) {
            sb.append("\t");
        }
        sb.append("<").append(name).append(">").append(value != null ? value : "").append("</").append(name).append(">\n");
    }

    public HyperListHeader getHeader() {
        return header;
    }

    public void setHeader(HyperListHeader header) {
        this.header = header;
    }

    public Set<HyperListGame> getGames() {
        return games;
    }

    public void setGames(Set<HyperListGame> games) {
        this.games = games;
    }

    public boolean addGame(HyperListGame game) {
        return this.games.add(game);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\"?>\n");
        sb.append("<menu>\n");
        if (this.header != null) {
            sb.append("\t<header>\n");
            appendXMLTag(sb, "listname", header.getListname(), 2);
            appendXMLTag(sb, "lastlistupdate", header.getLastlistupdate(), 2);
            appendXMLTag(sb, "listversion", header.getListversion(), 2);
            appendXMLTag(sb, "exporterversion", header.getExporterversion(), 2);
            sb.append("\t</header>\n");
        }
        if (this.games != null) {
            for (HyperListGame game : this.games) {
                sb.append("\t<game");
                appendXMLAttribute(sb, "name", game.getName());
                appendXMLAttribute(sb, "index", game.getIndex());
                appendXMLAttribute(sb, "image", game.getImage());
                sb.append(">\n");
                appendXMLTag(sb, "description", game.getDescription(), 2);
                appendXMLTag(sb, "cloneof", game.getCloneof(), 2);
                appendXMLTag(sb, "crc", game.getCrc(), 2);
                appendXMLTag(sb, "manufacturer", game.getManufacturer(), 2);
                appendXMLTag(sb, "year", game.getYear(), 2);
                appendXMLTag(sb, "genre", game.getGenre(), 2);
                appendXMLTag(sb, "rating", game.getRating(), 2);
                sb.append("\t</game>\n");
            }
        }
        sb.append("</menu>\n");
        return sb.toString();
    }

    @Override
    public Datafile toDatafile() {
        Datafile datafile = (this.header != null
                ? new Datafile(this.header.getListname(), DatafileCatalogEnum.HyperList.name(),
                this.header.getListversion(), this.header.getListname(), "HyperList", header.getLastlistupdate(),
                null, "https://hyperlist.hyperspin-fe.com/", "https://hyperlist.hyperspin-fe.com/", null)
                : new Datafile());
        for (HyperListGame game : this.games) {
            DatafileArtifact artifact = new DatafileArtifact(game.getName(), game.getDescription(), game.getYear(), null);
            artifact.setManufacturer(game.getManufacturer());
            if (game.getCloneof() != null && !game.getCloneof().isEmpty()) {
                artifact.setCloneof(game.getCloneof());
            }
            if (game.getIndex() != null && !game.getIndex().isEmpty()) {
                artifact.addField("index", game.getIndex());
            }
            if (game.getImage() != null && !game.getImage().isEmpty()) {
                artifact.addField("image", game.getImage());
            }
            if (game.getGenre() != null && !game.getGenre().isEmpty()) {
                artifact.addField("genre", game.getGenre());
            }
            if (game.getRating() != null && !game.getRating().isEmpty()) {
                artifact.addField("rating", game.getRating());
            }
            datafile.addArtifact(artifact);
            DatafileArtifactFile file = new DatafileArtifactFile(ArtifactFileTypeEnum.ROM.name(), game.getName(), null, game.getCrc(), null, null, game.getYear());
            artifact.addFile(file);
        }
        return datafile;
    }

    @Override
    public String toFile() {
        return toString();
    }
}
