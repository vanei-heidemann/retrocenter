package br.com.javanei.retrocenter.datafile.hyperlist.parser;

import br.com.javanei.retrocenter.datafile.hyperlist.HyperListGame;
import br.com.javanei.retrocenter.datafile.hyperlist.HyperListMenu;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;

public class HyperListParserTest {
    private static HyperListMenu atari2600;
    private static HyperListGame game2005;
    private static HyperListGame gameAdventure;

    @BeforeClass
    public static void initialize() throws Exception {
        HyperListParser parser = new HyperListParser();

        InputStream is = HyperListParser.class.getClassLoader().getResourceAsStream("Atari 2600.xml");
        atari2600 = parser.parse(is);
        for (HyperListGame g : atari2600.getGames()) {
            if (g.getName().equals("Adventure (USA)")) {
                gameAdventure = g;
            } else if (g.getName().equals("2005 Minigame Multicart (USA) (Unl)")) {
                game2005 = g;
            }
        }
    }

    @Test
    public void testHeaderName() {
        Assert.assertEquals("listname", "Atari 2600", atari2600.getHeader().getListname());
    }

    @Test
    public void testHeaderUpdate() {
        Assert.assertEquals("lastlistupdate", "03/06/2016", atari2600.getHeader().getLastlistupdate());
    }

    @Test
    public void testHeaderVersion() {
        Assert.assertEquals("listversion", "1.1", atari2600.getHeader().getListversion());
    }

    @Test
    public void testHeaderExporter() {
        Assert.assertEquals("exporterversion",
                "HyperList XML Exporter Version 1.3 Copywrite (c) 2009-2011 William Strong",
                atari2600.getHeader().getExporterversion());
    }

    @Test
    public void testGameName() {
        Assert.assertEquals("name", "Adventure (USA)", gameAdventure.getName());
    }

    @Test
    public void testGameDescription() {
        Assert.assertEquals("description", "Adventure (USA)", gameAdventure.getDescription());
    }

    @Test
    public void testGameCloneofIsEmpty() {
        Assert.assertEquals("cloneof", "", gameAdventure.getCloneof());
    }

    @Test
    public void testGameCrc() {
        Assert.assertEquals("crc", "A6DB4B3A", gameAdventure.getCrc());
    }

    @Test
    public void testGameManufacturer() {
        Assert.assertEquals("manufacturer", "Atari", gameAdventure.getManufacturer());
    }

    @Test
    public void testGameYear() {
        Assert.assertEquals("year", "1980", gameAdventure.getYear());
    }

    @Test
    public void testGameGenre() {
        Assert.assertEquals("genre", "Adventure", gameAdventure.getGenre());
    }

    @Test
    public void testGameRating() {
        Assert.assertEquals("rating", "Other - NR (Not Rated)", gameAdventure.getRating());
    }

    @Test
    public void testGameIndexfIsEmpty() {
        Assert.assertEquals("index", "", gameAdventure.getIndex());
    }

    @Test
    public void testGameImageIsEmpty() {
        Assert.assertEquals("image", "", gameAdventure.getImage());
    }

    @Test
    public void testGameIndexf() {
        Assert.assertEquals("index", "true", game2005.getIndex());
    }

    @Test
    public void testGameImage() {
        Assert.assertEquals("image", "2", game2005.getImage());
    }
}
