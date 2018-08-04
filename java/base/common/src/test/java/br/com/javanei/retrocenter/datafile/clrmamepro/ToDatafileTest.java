package br.com.javanei.retrocenter.datafile.clrmamepro;

import br.com.javanei.retrocenter.common.ArtifactFileTypeEnum;
import br.com.javanei.retrocenter.common.DatafileCatalogEnum;
import br.com.javanei.retrocenter.datafile.common.Datafile;
import br.com.javanei.retrocenter.datafile.common.DatafileArtifact;
import br.com.javanei.retrocenter.datafile.common.DatafileArtifactFile;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Iterator;

public class ToDatafileTest {
    private static CMProDatafile cmpro;
    private static Datafile datafile;

    @BeforeClass
    public static void initialize() throws Exception {
        cmpro = new CMProDatafile(new CMProHeader("name 01", "no-intro", "1.00", "description 01", "no-intro",
                "javanei", "javanei.com.br", "www.javanei.com.br",
                "split", "yes"));
        CMProGame game = new CMProGame("game name 01", "game description 01", "2017",
                "manufacturer 01", "cloneof 01", "romof 01", null);
        cmpro.addGame(game);

        CMProRom rom = new CMProRom("rom name 01", Long.valueOf(1l), "12345678", "1234567890123456789012345678901234567890",
                "12345678901234567890123456789012", "region 01", "verified");
        game.addRom(rom);

        CMProDisk disk = new CMProDisk("disk name 01", "1234567890123456789012345678901234567890",
                "12345678901234567890123456789012");
        game.addDisk(disk);

        game.addSample("sample 01");
        game.addSampleOf("sampleof 01");

        datafile = cmpro.toDatafile();
    }

    @Test
    public void testName() {
        Assert.assertEquals("Name", "name 01", datafile.getName());
    }

    @Test
    public void testVersion() {
        Assert.assertEquals("Version", "1.00", datafile.getVersion());
    }

    @Test
    public void testCatalog() {
        Assert.assertEquals("Catalog", DatafileCatalogEnum.NoIntro.name(), datafile.getCatalog());
    }

    @Test
    public void testGame() {
        Assert.assertEquals("Artifact", 1, datafile.getArtifacts().size());

        DatafileArtifact game = datafile.getArtifacts().iterator().next();
        Assert.assertEquals("Artifact : Name", "game name 01", game.getName());
        Assert.assertEquals("Artifact : cloneof", "cloneof 01", game.getCloneof());
        Assert.assertEquals("Artifact : description", "game description 01", game.getDescription());
        Assert.assertNull("Artifact : isbios", game.getIsbios());
        Assert.assertEquals("Artifact : manufacturer", "manufacturer 01", game.getManufacturer());
        Assert.assertEquals("Artifact : romof", "romof 01", game.getRomof());
        Assert.assertEquals("Artifact : sampleof", "sampleof 01", game.getSampleof());
        Assert.assertEquals("Artifact : year", "2017", game.getYear());
    }

    @Test
    public void testRom() {
        DatafileArtifact game = datafile.getArtifacts().iterator().next();
        Iterator<DatafileArtifactFile> it = game.getFiles().iterator();
        DatafileArtifactFile gameFile = it.next();
        while (gameFile != null && !gameFile.getType().equals(ArtifactFileTypeEnum.ROM.name()) && it.hasNext()) {
            gameFile = it.next();
        }

        Assert.assertNotNull("ROM", gameFile);
        Assert.assertEquals("Name", "rom name 01", gameFile.getName());
        Assert.assertEquals("Size", "1", gameFile.getSize());
        Assert.assertEquals("CRC", "12345678", gameFile.getCrc());
        Assert.assertEquals("SHA1", "1234567890123456789012345678901234567890", gameFile.getSha1());
        Assert.assertEquals("MD5", "12345678901234567890123456789012", gameFile.getMd5());
        Assert.assertEquals("Status", "verified", gameFile.getStatus());
        Assert.assertNull("Date", gameFile.getDate());
        Assert.assertNull("Merge", gameFile.getMerge());
        Assert.assertEquals("Region", "region 01", gameFile.getRegion());
    }

    @Test
    public void testDisk() {
        DatafileArtifact game = datafile.getArtifacts().iterator().next();
        Iterator<DatafileArtifactFile> it = game.getFiles().iterator();
        DatafileArtifactFile gameFile = it.next();
        while (gameFile != null && !gameFile.getType().equals(ArtifactFileTypeEnum.DISK.name()) && it.hasNext()) {
            gameFile = it.next();
        }

        Assert.assertNotNull("Disk", gameFile);
        Assert.assertEquals("Name", "disk name 01", gameFile.getName());
        Assert.assertNull("Size", gameFile.getSize());
        Assert.assertNull("CRC", gameFile.getCrc());
        Assert.assertEquals("SHA1", "1234567890123456789012345678901234567890", gameFile.getSha1());
        Assert.assertEquals("MD5", "12345678901234567890123456789012", gameFile.getMd5());
        Assert.assertNull("Merge", gameFile.getMerge());
        Assert.assertNull("Status", gameFile.getStatus());
        Assert.assertNull("Date", gameFile.getDate());
        Assert.assertNull("Region", gameFile.getRegion());
    }

    @Test
    public void testSample() {
        DatafileArtifact game = datafile.getArtifacts().iterator().next();
        Iterator<DatafileArtifactFile> it = game.getFiles().iterator();
        DatafileArtifactFile gameFile = it.next();
        while (gameFile != null && !gameFile.getType().equals(ArtifactFileTypeEnum.SAMPLE.name()) && it.hasNext()) {
            gameFile = it.next();
        }

        Assert.assertNotNull("Sample", gameFile);
        Assert.assertEquals("Name", "sample 01", gameFile.getName());
        Assert.assertNull("Size", gameFile.getSize());
        Assert.assertNull("CRC", gameFile.getCrc());
        Assert.assertNull("SHA1", gameFile.getSha1());
        Assert.assertNull("MD5", gameFile.getMd5());
        Assert.assertNull("Merge", gameFile.getMerge());
        Assert.assertNull("Status", gameFile.getStatus());
        Assert.assertNull("Date", gameFile.getDate());
        Assert.assertNull("Region", gameFile.getRegion());
    }
}
