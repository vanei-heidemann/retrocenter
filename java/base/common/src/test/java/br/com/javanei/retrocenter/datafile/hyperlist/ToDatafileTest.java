package br.com.javanei.retrocenter.datafile.hyperlist;

import br.com.javanei.retrocenter.common.ArtifactFileTypeEnum;
import br.com.javanei.retrocenter.common.DatafileCatalogEnum;
import br.com.javanei.retrocenter.datafile.common.Datafile;
import br.com.javanei.retrocenter.datafile.common.DatafileArtifact;
import br.com.javanei.retrocenter.datafile.common.DatafileArtifactFile;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ToDatafileTest {
    private static HyperListMenu hyperList;
    private static Datafile datafile;
    private static DatafileArtifact artifact;
    private static DatafileArtifactFile file;

    @BeforeClass
    public static void initialize() throws Exception {
        hyperList = new HyperListMenu(
                new HyperListHeader("Atari 2600", "03/06/2016", "1.1",
                        "HyperList XML Exporter Version 1.3 Copywrite (c) 2009-2011 William Strong")
        );
        hyperList.addGame(new HyperListGame(
                "2005 Minigame Multicart (USA) (Unl)", "true", "3", "2005 Minigame Multicart (USA) (Unl)", "",
                "401F769B", "AtariAge - Zach Matley, Bob Montgomery, Fred Quimbey & Chris Walton", "2005",
                "Mini-Games", "Other - NR (Not Rated)"
        ));

        datafile = hyperList.toDatafile();
        artifact = datafile.getArtifacts().iterator().next();
        file = artifact.getFiles().iterator().next();
    }

    @Test
    public void testHeaderName() {
        Assert.assertEquals("name", "Atari 2600", datafile.getName());
    }

    @Test
    public void testHeaderCatalog() {
        Assert.assertEquals("catalog", DatafileCatalogEnum.HyperList.name(), datafile.getCatalog());
    }

    @Test
    public void testHeaderVersion() {
        Assert.assertEquals("version", "1.1", datafile.getVersion());
    }

    @Test
    public void testHeaderDescription() {
        Assert.assertEquals("description", "Atari 2600", datafile.getDescription());
    }

    @Test
    public void testHeaderAuthor() {
        Assert.assertEquals("author", "HyperList", datafile.getAuthor());
    }

    @Test
    public void testHeaderDate() {
        Assert.assertEquals("date", "03/06/2016", datafile.getDate());
    }

    @Test
    public void testHeaderEmail() {
        Assert.assertNull("mail", datafile.getEmail());
    }

    @Test
    public void testHeaderHomepage() {
        Assert.assertEquals("homepage", "https://hyperlist.hyperspin-fe.com/", datafile.getHomepage());
    }

    @Test
    public void testHeaderUrl() {
        Assert.assertEquals("url", "https://hyperlist.hyperspin-fe.com/", datafile.getUrl());
    }

    @Test
    public void testHeaderComment() {
        Assert.assertNull("comment", datafile.getComment());
    }

    @Test
    public void testArtifactName() {
        Assert.assertEquals("artifact name", "2005 Minigame Multicart (USA) (Unl)", artifact.getName());
    }

    @Test
    public void testArtifactDescription() {
        Assert.assertEquals("artifact description", "2005 Minigame Multicart (USA) (Unl)", artifact.getDescription());
    }

    @Test
    public void testArtifactYear() {
        Assert.assertEquals("artifact year", "2005", artifact.getYear());
    }

    @Test
    public void testArtifactComment() {
        Assert.assertNull("artifact comment", artifact.getComment());
    }

    @Test
    public void testArtifactFileType() {
        Assert.assertEquals("file type", ArtifactFileTypeEnum.ROM.name(), file.getType());
    }

    @Test
    public void testArtifactFileName() {
        Assert.assertEquals("file name", "2005 Minigame Multicart (USA) (Unl)", file.getName());
    }

    @Test
    public void testArtifactFileSize() {
        Assert.assertNull("file size", file.getSize());
    }

    @Test
    public void testArtifactFileCrc() {
        Assert.assertEquals("file crc", "401F769B", file.getCrc());
    }

    @Test
    public void testArtifactFileSha1() {
        Assert.assertNull("file sha1", file.getSha1());
    }

    @Test
    public void testArtifactFileMD5() {
        Assert.assertNull("file MD5", file.getMd5());
    }

    @Test
    public void testArtifactFileDate() {
        Assert.assertEquals("file date", "2005", file.getDate());
    }
}
