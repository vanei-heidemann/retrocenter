package br.com.javanei.retrocenter.datafile.parser;

import br.com.javanei.retrocenter.common.ArtifactFileTypeEnum;
import br.com.javanei.retrocenter.common.DatafileCatalogEnum;
import br.com.javanei.retrocenter.datafile.common.Datafile;
import br.com.javanei.retrocenter.datafile.common.DatafileArtifact;
import br.com.javanei.retrocenter.datafile.common.DatafileArtifactFile;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;

public class RetrocenterDatafileParserTest {
    private static Datafile datafile;

    @BeforeClass
    public static void initialize() throws Exception {
        RetrocenterDatafileParser parser = new RetrocenterDatafileParser();
        InputStream is = DatafileParserTest.class.getClassLoader().getResourceAsStream("retrocenter.xml");

        datafile = parser.parse(is);
    }

    @Test
    public void testHeader() {
        Assert.assertEquals("name", "Nintendo 64 - Games", datafile.getName());
        Assert.assertEquals("catalog", DatafileCatalogEnum.TOSEC.name(), datafile.getCatalog());
        Assert.assertEquals("version", "2015-05-27", datafile.getVersion());
        Assert.assertEquals("description", "Nintendo 64 - Games (TOSEC-v2015-05-27)", datafile.getDescription());
        Assert.assertEquals("author", "Cassiel - Yori Yoshizuki", datafile.getAuthor());
        Assert.assertEquals("email", "contact@tosecdev.org", datafile.getEmail());
        Assert.assertEquals("homepage", "TOSEC", datafile.getHomepage());
        Assert.assertEquals("url", "http://www.tosecdev.org/", datafile.getUrl());
        Assert.assertEquals("date", "1998", datafile.getDate());
        Assert.assertEquals("comment", "Comment", datafile.getComment());
    }

    @Test
    public void testArtifact() {
        Assert.assertEquals("artifacts", 1, datafile.getArtifacts().size());

        DatafileArtifact g = datafile.getArtifacts().iterator().next();
        Assert.assertEquals("name", "1080 Snowboarding (1998)(Nintendo)(EU)(M4)", g.getName());
        Assert.assertEquals("description", "1080 Snowboarding (1998)(Nintendo)(EU)(M4)", g.getDescription());
        Assert.assertEquals("year", "1998", g.getYear());
        Assert.assertEquals("isbios", "no", g.getIsbios());
        Assert.assertEquals("manufacturer", "Nintendo", g.getManufacturer());
        Assert.assertEquals("cloneof", "1080 Snowboarding (1998)(Nintendo)", g.getCloneof());
        Assert.assertEquals("romof", "1080 Snowboarding", g.getRomof());
        Assert.assertEquals("sampleof", "1080 Snowboarding sample", g.getSampleof());
        Assert.assertEquals("comment", "No Comment", g.getComment());
    }

    @Test
    public void testRom() {
        DatafileArtifactFile r = null;
        for (DatafileArtifactFile af : datafile.getArtifacts().iterator().next().getFiles().toArray(new DatafileArtifactFile[0])) {
            if (af.getType().equals(ArtifactFileTypeEnum.ROM.name())) {
                r = af;
                break;
            }
        }

        Assert.assertNotNull(r);
        Assert.assertEquals("name", "1080 Snowboarding (1998)(Nintendo)(EU)(M4).bin", r.getName());
        Assert.assertEquals("size", "16777216", r.getSize());
        Assert.assertEquals("crc", "75a21679", r.getCrc());
        Assert.assertEquals("sha1", "637d92b08dbfe7c2f9d5e338835b1fce5f4a87d0", r.getSha1());
        Assert.assertEquals("md5", "632c98cf281cda776e66685b278a4fa6", r.getMd5());
        Assert.assertEquals("status", "good", r.getStatus());
        Assert.assertEquals("region", "Brazil", r.getRegion());
        Assert.assertEquals("merge", "merge 01", r.getMerge());
        Assert.assertEquals("date", "1998", r.getDate());
    }

    @Test
    public void testSample() {
        DatafileArtifactFile r = null;
        for (DatafileArtifactFile af : datafile.getArtifacts().iterator().next().getFiles().toArray(new DatafileArtifactFile[0])) {
            if (af.getType().equals(ArtifactFileTypeEnum.SAMPLE.name())) {
                r = af;
                break;
            }
        }

        Assert.assertNotNull(r);
        Assert.assertEquals("name", "1080 sample", r.getName());
    }

    @Test
    public void testDisk() {
        DatafileArtifactFile r = null;
        for (DatafileArtifactFile af : datafile.getArtifacts().iterator().next().getFiles().toArray(new DatafileArtifactFile[0])) {
            if (af.getType().equals(ArtifactFileTypeEnum.DISK.name())) {
                r = af;
                break;
            }
        }

        Assert.assertNotNull(r);
        Assert.assertEquals("name", "1080 Snowboarding (1998)(Nintendo)(EU)(M4).dsk", r.getName());
        Assert.assertEquals("sha1", "637d92b08dbfe7c2f9d5e338835b1fce5f4a87d0", r.getSha1());
        Assert.assertEquals("md5", "632c98cf281cda776e66685b278a4fa6", r.getMd5());
        Assert.assertEquals("status", "good", r.getStatus());
    }
}
