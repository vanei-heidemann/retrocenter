package br.com.javanei.retrocenter.datafile.clrmamepro.service;

import br.com.javanei.retrocenter.common.DatafileCatalogEnum;
import br.com.javanei.retrocenter.datafile.clrmamepro.CMProDatafile;
import br.com.javanei.retrocenter.datafile.clrmamepro.CMProDisk;
import br.com.javanei.retrocenter.datafile.clrmamepro.CMProGame;
import br.com.javanei.retrocenter.datafile.clrmamepro.CMProHeader;
import br.com.javanei.retrocenter.datafile.clrmamepro.CMProResource;
import br.com.javanei.retrocenter.datafile.clrmamepro.CMProRom;
import br.com.javanei.retrocenter.datafile.clrmamepro.parser.CMProParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CMProServiceConfiguration.class})
public class CMProServiceTest {

    private static CMProDatafile datafile;
    private static CMProHeader header;
    private static CMProGame game;
    private static CMProResource resource;
    private static CMProDisk disk;
    private static CMProRom rom;

    @Autowired
    private CMProService cmProService;

    @BeforeClass
    public static void initialize() throws Exception {
        CMProParser parser = new CMProParser();

        InputStream is = CMProServiceTest.class.getClassLoader().getResourceAsStream("cmpro-test.dat");
        datafile = parser.parse(is);
        header = datafile.getHeader();
        game = datafile.getGames().iterator().next();
        resource = datafile.getResources().iterator().next();
    }

    @Test
    public void createHeader() {
        CMProDatafile d = cmProService.create(datafile);
        CMProHeader h = d.getHeader();

        Assert.assertEquals("name", "Sony - PlayStation Vita", h.getName());
        Assert.assertEquals("description", "Sony - PlayStation Vita", h.getDescription());
        Assert.assertEquals("version", "20170114-224204", h.getVersion());
        //Assert.assertEquals("category", DatafileCatalogEnum.NoIntro.name(), h.getCategory());
        Assert.assertEquals("catalog", DatafileCatalogEnum.NoIntro.name(), h.getCatalog());
        Assert.assertEquals("author", "Densetsu, einstein95, Gefflon, Hiccup, jimmsu, Money_114, SonGoku, xuom2, zg", h.getAuthor());
        Assert.assertEquals("homepage", "No-Intro", h.getHomepage());
        Assert.assertEquals("url", "http://www.no-intro.org", h.getUrl());
        Assert.assertNull("category", h.getCategory());
        Assert.assertNull("forcemerging", h.getForcemerging());
        Assert.assertNull("forcezipping", h.getForcezipping());
    }

    @Test
    public void createGame() {
        CMProDatafile d = cmProService.create(datafile);
        CMProGame g = d.getGames().iterator().next();

        Assert.assertEquals("name", "Aegis of Earth - Protonovus Assault (USA)", g.getName());
        Assert.assertEquals("description", "Aegis of Earth - Protonovus Assault (USA)", g.getDescription());
        Assert.assertEquals("year", "2016", g.getYear());
        Assert.assertEquals("manufacturer", "Manufacturer 01", g.getManufacturer());
        Assert.assertEquals("cloneof", "cloneof 01", g.getCloneof());
        Assert.assertEquals("romof", "romof 01", g.getRomof());
        Assert.assertEquals("serial", "PCSE-00844", g.getSerial());
    }

    @Test
    public void createRom() {
        CMProDatafile d = cmProService.create(datafile);
        CMProGame g = d.getGames().iterator().next();
        CMProRom r = g.getRoms().iterator().next();

        Assert.assertEquals("name", "Aegis of Earth - Protonovus Assault (USA).vpk", r.getName());
        Assert.assertEquals("size", Long.valueOf(825418380), r.getSize());
        Assert.assertEquals("crc", "BBA209A4", r.getCrc());
        Assert.assertEquals("sha1", "9E58A114387A4DAF2033063C836EFEFDFF4EEE58", r.getSha1());
        Assert.assertEquals("md5", "1293854EC3B83DABA1595249CA637725", r.getMd5());
        Assert.assertEquals("region", "Brazil", r.getRegion());
        Assert.assertEquals("flags", "verified", r.getFlags());
    }

    @Test
    public void createDisk() {
        CMProDatafile dat = cmProService.create(datafile);
        CMProGame g = dat.getGames().iterator().next();
        CMProDisk d = g.getDisks().iterator().next();

        Assert.assertEquals("name", "disk 01", d.getName());
        Assert.assertEquals("sha1", "9E58A114387A4DAF2033063C836EFEFDFF4EEE58", d.getSha1());
        Assert.assertEquals("md5", "1293854EC3B83DABA1595249CA637725", d.getMd5());
    }

    @Test
    public void createSamplesof() {
        CMProDatafile dat = cmProService.create(datafile);
        CMProGame g = dat.getGames().iterator().next();
        String s = g.getSampleof().iterator().next();

        Assert.assertEquals("sampleof", "sampleof 01", s);
    }

    @Test
    public void createSamples() {
        CMProDatafile dat = cmProService.create(datafile);
        CMProGame g = dat.getGames().iterator().next();
        String s = g.getSamples().iterator().next();

        Assert.assertEquals("sample", "sample 01", s);
    }

    @Test
    public void createResource() {
        CMProDatafile dat = cmProService.create(datafile);
        CMProResource r = dat.getResources().iterator().next();

        Assert.assertEquals("name", "Aegis of Earth - Protonovus Assault (JP)", r.getName());
        Assert.assertEquals("description", "Aegis of Earth - Protonovus Assault (JP)", r.getDescription());
        Assert.assertEquals("year", "2016", r.getYear());
        Assert.assertEquals("manufacturer", "Manufacturer 01", r.getManufacturer());
    }

    @Test
    public void createResourceRom() {
        CMProDatafile dat = cmProService.create(datafile);
        CMProResource rr = dat.getResources().iterator().next();
        CMProRom r = rr.getRoms().iterator().next();

        Assert.assertEquals("name", "Aegis of Earth - Protonovus Assault (JP).vpk", r.getName());
        Assert.assertEquals("size", Long.valueOf(825418380), r.getSize());
        Assert.assertEquals("crc", "BBA209A4", r.getCrc());
        Assert.assertEquals("sha1", "9E58A114387A4DAF2033063C836EFEFDFF4EEE58", r.getSha1());
        Assert.assertEquals("md5", "1293854EC3B83DABA1595249CA637725", r.getMd5());
        Assert.assertEquals("region", "Brazil", r.getRegion());
        Assert.assertEquals("flags", "verified", r.getFlags());
    }
}
