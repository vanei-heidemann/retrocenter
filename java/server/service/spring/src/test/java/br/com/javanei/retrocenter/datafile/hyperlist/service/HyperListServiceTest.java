package br.com.javanei.retrocenter.datafile.hyperlist.service;

import br.com.javanei.retrocenter.datafile.hyperlist.HyperListGame;
import br.com.javanei.retrocenter.datafile.hyperlist.HyperListHeader;
import br.com.javanei.retrocenter.datafile.hyperlist.HyperListMenu;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HyperListServiceConfiguration.class})
public class HyperListServiceTest {
    private static HyperListMenu datafile;
    private static HyperListGame game;
    @Autowired
    private HyperListService hyperListService;

    @BeforeClass
    public static void initialize() throws Exception {
        datafile = new HyperListMenu(
                new HyperListHeader("Atari 2600", "03/06/2016", "1.1",
                        "HyperList XML Exporter Version 1.3 Copywrite (c) 2009-2011 William Strong")
        );
        game = new HyperListGame(
                "2005 Minigame Multicart (USA) (Unl)", "true", "3", "2005 Minigame Multicart (USA) (Unl)", "",
                "401F769B", "AtariAge - Zach Matley, Bob Montgomery, Fred Quimbey & Chris Walton", "2005",
                "Mini-Games", "Other - NR (Not Rated)"
        );
        datafile.addGame(game);
    }

    @Test
    public void createHeader() {
        HyperListMenu d = hyperListService.create(datafile);
        HyperListHeader h = d.getHeader();

        Assert.assertEquals("name", "Atari 2600", h.getListname());
        Assert.assertEquals("lastlistupdate", "03/06/2016", h.getLastlistupdate());
        Assert.assertEquals("version", "1.1", h.getListversion());
        Assert.assertEquals("exporterversion", "HyperList XML Exporter Version 1.3 Copywrite (c) 2009-2011 William Strong", h.getExporterversion());
    }

    @Test
    public void createGame() {
        HyperListMenu d = hyperListService.create(datafile);
        Assert.assertEquals("games", 1, d.getGames().size());
        HyperListGame g = d.getGames().iterator().next();

        Assert.assertEquals("game name", "2005 Minigame Multicart (USA) (Unl)", g.getName());
        Assert.assertEquals("game description", "2005 Minigame Multicart (USA) (Unl)", g.getDescription());
        Assert.assertEquals("game cloneof", "", g.getCloneof());
        Assert.assertEquals("game crc", "401F769B", g.getCrc());
        Assert.assertEquals("game manufacturer", "AtariAge - Zach Matley, Bob Montgomery, Fred Quimbey & Chris Walton", g.getManufacturer());
        Assert.assertEquals("game year", "2005", g.getYear());
        Assert.assertEquals("game genre", "Mini-Games", g.getGenre());
        Assert.assertEquals("game rating", "Other - NR (Not Rated)", g.getRating());
        Assert.assertEquals("game index", "true", g.getIndex());
        Assert.assertEquals("game image", "3", g.getImage());
    }
}
