package br.com.javanei.retrocenter.datafile.mame;

import br.com.javanei.retrocenter.common.ArtifactFileTypeEnum;
import br.com.javanei.retrocenter.datafile.common.Datafile;
import br.com.javanei.retrocenter.datafile.common.DatafileArtifact;
import br.com.javanei.retrocenter.datafile.common.DatafileArtifactFile;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Iterator;

public class ToDatafileTest {
    private static Mame mame;
    private static Datafile datafile;

    @BeforeClass
    public static void initialize() throws Exception {
        mame = new Mame("0.186", "no", "10");
        MameMachine machine = new MameMachine("machine 01", null, "no", "no", "no", "yes", "cloneof teste", "romof teste",
                "sampleof teste", "machine 01", "2017", "javanei");
        mame.addMachine(machine);

        MameRom rom = new MameRom("rom 01", "false", 1l, "12345678",
                "1234567890123456789012345678901234567890", "no", "region 01", "offset 01",
                "good", "true");
        machine.addRom(rom);

        MameDisk disk = new MameDisk("disk 01", "1234567890123456789012345678901234567890", "yes", "region 01", 1, "no",
                "baddump", "yes");
        machine.addDisk(disk);

        MameSample sample = new MameSample("sample 01");
        machine.addSample(sample);

        datafile = mame.toDatafile();
    }

    @Test
    public void testName() {
        Assert.assertEquals("MAME", datafile.getName());
    }

    @Test
    public void testVersion() {
        Assert.assertEquals("0.186", datafile.getVersion());
    }

    @Test
    public void testCategory() {
        Assert.assertEquals("MAME", datafile.getCatalog());
    }

    @Test
    public void testMachine() {
        Assert.assertEquals("Machines", 1, datafile.getArtifacts().size());

        DatafileArtifact game = datafile.getArtifacts().iterator().next();
        Assert.assertEquals("Artifact : Name", "machine 01", game.getName());
        Assert.assertEquals("Artifact : cloneof", "cloneof teste", game.getCloneof());
        Assert.assertEquals("Artifact : description", "machine 01", game.getDescription());
        Assert.assertEquals("Artifact : isbios", "no", game.getIsbios());
        Assert.assertEquals("Artifact : manufacturer", "javanei", game.getManufacturer());
        Assert.assertEquals("Artifact : romof", "romof teste", game.getRomof());
        Assert.assertEquals("Artifact : sampleof", "sampleof teste", game.getSampleof());
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
        Assert.assertEquals("Name", "rom 01", gameFile.getName());
        Assert.assertEquals("Size", "1", gameFile.getSize());
        Assert.assertEquals("CRC", "12345678", gameFile.getCrc());
        Assert.assertEquals("SHA1", "1234567890123456789012345678901234567890", gameFile.getSha1());
        Assert.assertNull("MD5", gameFile.getMd5());
        Assert.assertEquals("Status", "good", gameFile.getStatus());
        Assert.assertNull("Date", gameFile.getDate());
        Assert.assertEquals("Merge", "no", gameFile.getMerge());
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
        Assert.assertEquals("Name", "disk 01", gameFile.getName());
        Assert.assertNull("Size", gameFile.getSize());
        Assert.assertNull("CRC", gameFile.getCrc());
        Assert.assertEquals("SHA1", "1234567890123456789012345678901234567890", gameFile.getSha1());
        Assert.assertNull("MD5", gameFile.getMd5());
        Assert.assertEquals("Merge", "yes", gameFile.getMerge());
        Assert.assertEquals("Status", "baddump", gameFile.getStatus());
        Assert.assertNull("Date", gameFile.getDate());
        Assert.assertEquals("Region", "region 01", gameFile.getRegion());
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
