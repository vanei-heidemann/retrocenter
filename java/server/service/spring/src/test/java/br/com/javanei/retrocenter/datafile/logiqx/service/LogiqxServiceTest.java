package br.com.javanei.retrocenter.datafile.logiqx.service;

import br.com.javanei.retrocenter.datafile.logiqx.LogiqxArchive;
import br.com.javanei.retrocenter.datafile.logiqx.LogiqxBiosset;
import br.com.javanei.retrocenter.datafile.logiqx.LogiqxDatafile;
import br.com.javanei.retrocenter.datafile.logiqx.LogiqxDisk;
import br.com.javanei.retrocenter.datafile.logiqx.LogiqxGame;
import br.com.javanei.retrocenter.datafile.logiqx.LogiqxHeader;
import br.com.javanei.retrocenter.datafile.logiqx.LogiqxRelease;
import br.com.javanei.retrocenter.datafile.logiqx.LogiqxRom;
import br.com.javanei.retrocenter.datafile.logiqx.LogiqxSample;
import br.com.javanei.retrocenter.datafile.logiqx.parser.LogiqxParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {LogiqxServiceConfiguration.class})
public class LogiqxServiceTest {
    private static LogiqxDatafile datafile;
    private static LogiqxHeader header;
    private static LogiqxGame game;
    private static LogiqxRelease release;
    private static LogiqxBiosset biosset;
    private static LogiqxRom rom;
    private static LogiqxDisk disk;
    private static LogiqxSample sample;
    private static LogiqxArchive archive;

    @Autowired
    private LogiqxService logiqxService;

    @BeforeClass
    public static void initialize() throws Exception {
        LogiqxParser parser = new LogiqxParser();

        InputStream is = LogiqxServiceTest.class.getClassLoader().getResourceAsStream("logiqx-test.xml");
        datafile = parser.parse(is);
        header = datafile.getHeader();
        game = datafile.getGames().iterator().next();
        release = game.getReleases().iterator().next();
        biosset = game.getBiossets().iterator().next();
        rom = game.getRoms().iterator().next();
        disk = game.getDisks().iterator().next();
        sample = game.getSamples().iterator().next();
        archive = game.getArchives().iterator().next();
    }

    @Test
    public void create() {
        LogiqxDatafile d = logiqxService.create(datafile);

        Assert.assertEquals("debug", "no", d.getDebug());
    }

    @Test
    public void createHeader() {
        LogiqxDatafile d = logiqxService.create(datafile);
        LogiqxHeader h = d.getHeader();

        Assert.assertEquals("name", "Nintendo 64 - Games", h.getName());
        Assert.assertEquals("description", "Nintendo 64 - Games (TOSEC-v2015-05-27)", h.getDescription());
        Assert.assertEquals("category", "TOSEC", h.getCategory());
        Assert.assertEquals("version", "2015-05-27", h.getVersion());
        Assert.assertEquals("date", header.getDate(), h.getDate());
        Assert.assertEquals("author", "Cassiel - Yori Yoshizuki", h.getAuthor());
        Assert.assertEquals("email", "contact@tosecdev.org", h.getEmail());
        Assert.assertEquals("homepage", header.getHomepage(), h.getHomepage());
        Assert.assertEquals("url", "http://www.tosecdev.org/", h.getUrl());
        Assert.assertEquals("comment", header.getComment(), h.getComment());
        Assert.assertEquals("header", header.getHeader(), h.getHeader());
        Assert.assertEquals("forcemerging", header.getForcemerging(), h.getForcemerging());
        Assert.assertEquals("forcenodump", header.getForcenodump(), h.getForcenodump());
        Assert.assertEquals("forcepacking", header.getForcepacking(), h.getForcepacking());
        Assert.assertEquals("plugin", header.getPlugin(), h.getPlugin());
        Assert.assertEquals("rommode", header.getRommode(), h.getRommode());
        Assert.assertEquals("biosmode", header.getBiosmode(), h.getBiosmode());
        Assert.assertEquals("samplemode", header.getSamplemode(), h.getSamplemode());
        Assert.assertEquals("lockrommode", header.getLockrommode(), h.getLockrommode());
        Assert.assertEquals("lockbiosmode", header.getLockbiosmode(), h.getLockbiosmode());
        Assert.assertEquals("locksamplemode", header.getLocksamplemode(), h.getLocksamplemode());
    }

    @Test
    public void createGame() {
        LogiqxDatafile d = logiqxService.create(datafile);
        Assert.assertEquals("games", 1, d.getGames().size());
        LogiqxGame g = d.getGames().iterator().next();

        Assert.assertEquals("name", "1080 Snowboarding (1998)(Nintendo)(EU)(M4)", g.getName());
        Assert.assertEquals("sourcefile", g.getSourcefile(), g.getSourcefile());
        Assert.assertEquals("isbios", "no", g.getIsbios());
        Assert.assertEquals("cloneof", "1080 Snowboarding (1998)(Nintendo)", g.getCloneof());
        Assert.assertEquals("romof", "1080 Snowboarding", g.getRomof());
        Assert.assertEquals("sampleof", "1080 Snowboarding sample", g.getSampleof());
        Assert.assertEquals("board", game.getBoard(), g.getBoard());
        Assert.assertEquals("rebuildto", game.getRebuildto(), g.getRebuildto());
        Assert.assertEquals("comment", "No Comment", g.getComment());
        Assert.assertEquals("description", "1080 Snowboarding (1998)(Nintendo)(EU)(M4)", g.getDescription());
        Assert.assertEquals("year", "1998", g.getYear());
        Assert.assertEquals("manufacturer", "Nintendo", g.getManufacturer());
    }

    @Test
    public void createRom() {
        LogiqxDatafile d = logiqxService.create(datafile);
        LogiqxGame g = d.getGames().iterator().next();
        Assert.assertEquals("roms", 1, g.getRoms().size());
        LogiqxRom r = g.getRoms().iterator().next();

        Assert.assertEquals("name", "1080 Snowboarding (1998)(Nintendo)(EU)(M4).bin", r.getName());
        Assert.assertEquals("size", Long.valueOf(16777216), r.getSize());
        Assert.assertEquals("crc", "75a21679", r.getCrc());
        Assert.assertEquals("sha1", "637d92b08dbfe7c2f9d5e338835b1fce5f4a87d0", r.getSha1());
        Assert.assertEquals("md5", "632c98cf281cda776e66685b278a4fa6", r.getMd5());
        Assert.assertEquals("merge", rom.getMerge(), r.getMerge());
        Assert.assertEquals("date", rom.getDate(), r.getDate());
        Assert.assertEquals("status", rom.getStatus(), r.getStatus());
    }

    @Test
    public void createRelease() {
        LogiqxDatafile d = logiqxService.create(datafile);
        LogiqxGame g = d.getGames().iterator().next();
        Assert.assertEquals("releases", 1, g.getReleases().size());
        LogiqxRelease r = g.getReleases().iterator().next();

        Assert.assertEquals("name", "release 1", r.getName());
        Assert.assertEquals("region", "world", r.getRegion());
        Assert.assertEquals("language", "en", r.getLanguage());
        Assert.assertEquals("date", "1998", r.getDate());
        Assert.assertEquals("default", "yes", r.getDefault());
    }

    @Test
    public void createBiosset() {
        LogiqxDatafile d = logiqxService.create(datafile);
        LogiqxGame g = d.getGames().iterator().next();
        Assert.assertEquals("biossets", 1, g.getBiossets().size());
        LogiqxBiosset b = g.getBiossets().iterator().next();

        Assert.assertEquals("name", "biosset 1", b.getName());
        Assert.assertEquals("description", "biosset 01", b.getDescription());
        Assert.assertEquals("default", "yes", b.getDefault());
    }

    @Test
    public void createDisk() {
        LogiqxDatafile d = logiqxService.create(datafile);
        LogiqxGame g = d.getGames().iterator().next();
        Assert.assertEquals("disks", 1, g.getDisks().size());
        LogiqxDisk dsk = g.getDisks().iterator().next();

        Assert.assertEquals("name", "1080 Snowboarding (1998)(Nintendo)(EU)(M4).dsk", dsk.getName());
        Assert.assertEquals("sha1", "637d92b08dbfe7c2f9d5e338835b1fce5f4a87d0", dsk.getSha1());
        Assert.assertEquals("md5", "632c98cf281cda776e66685b278a4fa6", dsk.getMd5());
        Assert.assertEquals("merge", disk.getMerge(), dsk.getMerge());
        Assert.assertEquals("status", disk.getStatus(), dsk.getStatus());
    }

    @Test
    public void createSample() {
        LogiqxDatafile d = logiqxService.create(datafile);
        LogiqxGame g = d.getGames().iterator().next();
        Assert.assertEquals("samples", 1, g.getSamples().size());
        LogiqxSample s = g.getSamples().iterator().next();

        Assert.assertEquals("name", "1080 sample", s.getName());
    }

    @Test
    public void createArchive() {
        LogiqxDatafile d = logiqxService.create(datafile);
        LogiqxGame g = d.getGames().iterator().next();
        Assert.assertEquals("archives", 1, g.getArchives().size());
        LogiqxArchive a = g.getArchives().iterator().next();

        Assert.assertEquals("name", "1080 archive", a.getName());
    }
}
