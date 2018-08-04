package br.com.javanei.retrocenter.catalog.tosec.parser;

import br.com.javanei.retrocenter.catalog.tosec.TOSECGame;
import org.junit.Assert;
import org.junit.Test;

public class TOSECNameParserTest {
    private static TOSECNameParser parser = new TOSECNameParser();

    @Test
    public void testGameName() {
        Assert.assertEquals("name", "Legend of TOSEC, The (1986)(Devstudio)(Disk 3 of 3)(Character Disk)",
                parser.parseName("Legend of TOSEC, The (1986)(Devstudio)(Disk 3 of 3)(Character Disk)").getName());
    }

    @Test
    public void testGameMainName() {
        Assert.assertEquals("mainName", "Legend of TOSEC, The",
                parser.parseName("Legend of TOSEC, The (1986)(Devstudio)(Disk 3 of 3)(Character Disk)").getMainName());
    }

    @Test
    public void testGameDemoStatus() {
        Assert.assertEquals("demoStatus", "demo",
                parser.parseName("Legend of TOSEC, The (demo) (1986)(Devstudio)").getDemoStatus());
    }

    @Test
    public void testReleaseDate() {
        Assert.assertEquals("releaseDate", "1986",
                parser.parseName("Legend of TOSEC, The (1986)(Devstudio)(Disk 3 of 3)(Character Disk)").getReleaseDate());
    }

    @Test
    public void testReleaseDate19xx() {
        Assert.assertEquals("releaseDate", "19xx",
                parser.parseName("Legend of TOSEC, The (19xx)").getReleaseDate());
    }

    @Test
    public void testReleaseDate199x() {
        Assert.assertEquals("releaseDate", "199x",
                parser.parseName("Legend of TOSEC, The (199x)").getReleaseDate());
    }

    @Test
    public void testReleaseDate2001_01() {
        Assert.assertEquals("releaseDate", "2001-01",
                parser.parseName("Legend of TOSEC, The (2001-01)").getReleaseDate());
    }

    @Test
    public void testReleaseDate1986_06_21() {
        Assert.assertEquals("releaseDate", "1986-06-21",
                parser.parseName("Legend of TOSEC, The (1986-06-21)").getReleaseDate());
    }

    @Test
    public void testReleaseDate19xx_12() {
        Assert.assertEquals("releaseDate", "19xx-12",
                parser.parseName("Legend of TOSEC, The (19xx-12)").getReleaseDate());
    }

    @Test
    public void testReleaseDate19xx_12_25() {
        Assert.assertEquals("releaseDate", "19xx-12-25",
                parser.parseName("Legend of TOSEC, The (19xx-12-25)").getReleaseDate());
    }

    @Test
    public void testReleaseDate19xx_12_2x() {
        Assert.assertEquals("releaseDate", "19xx-12-2x",
                parser.parseName("Legend of TOSEC, The (19xx-12-2x)").getReleaseDate());
    }

    @Test
    public void testVersion() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v1.0 (1986)(Devstudio)(Disk 3 of 3)(Character Disk)");
        Assert.assertEquals("version", "v1.0", game.getVersion());
        Assert.assertEquals("mainName", "Legend of TOSEC, The", game.getMainName());
    }

    /*
    @Test
    public void testVersion_v1_03b() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v1.03b (1986)(Devstudio)(Disk 3 of 3)(Character Disk)");
        Assert.assertEquals("version", "v1.03b", game.getVersion());
        Assert.assertEquals("mainName", "Legend of TOSEC, The", game.getMainName());
    }
    */

    @Test
    public void testVersion_Rev1() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The Rev 1 (1986)(Devstudio)(Disk 3 of 3)(Character Disk)");
        Assert.assertEquals("version", "Rev 1", game.getVersion());
        Assert.assertEquals("mainName", "Legend of TOSEC, The", game.getMainName());
    }

    @Test
    public void testVersion_v20000101() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(Devstudio)(Disk 3 of 3)(Character Disk)");
        Assert.assertEquals("version", "v20000101", game.getVersion());
        Assert.assertEquals("mainName", "Legend of TOSEC, The", game.getMainName());
    }

    @Test
    public void testVersion1_00() {
        TOSECGame game = parser.parseName("Acorn Archimedes RISC OS Application Suite v1.00 (19xx)(Acorn)(Disk 1 of 2)[a][Req RISC OS]");
        Assert.assertEquals("version", "v1.00", game.getVersion());
        Assert.assertEquals("mainName", "Acorn Archimedes RISC OS Application Suite", game.getMainName());
    }

    @Test
    public void testVersion1_0() {
        TOSECGame game = parser.parseName("Acorn Archimedes RISC OS Application Suite v1.0 (19xx)(Acorn)(Disk 1 of 2)[a][Req RISC OS]");
        Assert.assertEquals("version", "v1.0", game.getVersion());
        Assert.assertEquals("mainName", "Acorn Archimedes RISC OS Application Suite", game.getMainName());
    }

    @Test
    public void testPublisher() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(Devstudio)(Disk 3 of 3)(Character Disk)");
        Assert.assertEquals("publishers", 1, game.getPublishers().size());
        Assert.assertEquals("publisher", "Devstudio", game.getPublishers().get(0));
    }

    @Test
    public void testPublishers() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(Devstudio - Javanei)");
        Assert.assertEquals("publishers", 2, game.getPublishers().size());
        Assert.assertEquals("publisher", "Devstudio", game.getPublishers().get(0));
        Assert.assertEquals("publisher", "Javanei", game.getPublishers().get(1));
    }

    @Test
    public void testNoPublisher() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(-)");
        Assert.assertEquals("publishers", 0, game.getPublishers().size());
    }

    @Test
    public void testCracked() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(-) [cr]");
        Assert.assertEquals("cracked", "cr", game.getCracked());
    }

    @Test
    public void testCracked2() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(-) [cr2 Javanei]");
        Assert.assertEquals("cracked", "cr2 Javanei", game.getCracked());
    }

    @Test
    public void testFixed() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(-)[f]");
        Assert.assertEquals("fixed", "f", game.getFixed());
    }

    @Test
    public void testFixed3() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(-)[f3]");
        Assert.assertEquals("fixed", "f3", game.getFixed());
    }

    @Test
    public void testHacked() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(-)[f][h]");
        Assert.assertEquals("hacked", "h", game.getHacked());
    }

    @Test
    public void testModified() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(-)[f][h][m]");
        Assert.assertEquals("modified", "m", game.getModified());
    }

    @Test
    public void testPirated() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(-)[f][h][p Javanei]");
        Assert.assertEquals("pirated", "p Javanei", game.getPirated());
    }

    @Test
    public void testTranslated() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(-)[f][h][p Javanei][tr]");
        Assert.assertEquals("translated", "tr", game.getTranslated());
    }

    @Test
    public void testTrained() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(-)[f][h][p Javanei][tr][t]");
        Assert.assertEquals("trained", "t", game.getTrained());
    }

    @Test
    public void testOverDump() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(-)[f][h][p Javanei][tr][t][o3]");
        Assert.assertEquals("overdump", "o3", game.getOverDump());
    }

    @Test
    public void testUnderDump() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(-)[f][h][p Javanei][tr][t][o3][u]");
        Assert.assertEquals("underdump", "u", game.getUnderDump());
    }

    @Test
    public void testVirus() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(-)[f][h][p Javanei][tr][t][o3][u][v]");
        Assert.assertEquals("virus", "v", game.getVirus());
    }

    @Test
    public void testBadDump() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(-)[f][h][p Javanei][tr][t][o3][u][v][b2]");
        Assert.assertEquals("baddump", "b2", game.getBadDump());
    }

    @Test
    public void testAlternate() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(-)[f][h][tr][t][o3][u][v][b2][a Mario]");
        Assert.assertEquals("alternate", "a Mario", game.getAlternate());
    }

    @Test
    public void testVerifiedGoodDump() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(-)[!]");
        Assert.assertEquals("verifiedgooddump", "!", game.getVerifiedGoodDump());
    }

    @Test
    public void testSystem() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(-)(A500)[!]");
        Assert.assertEquals("system", "A500", game.getSystem());
    }

    @Test
    public void testVideo() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(-)(A500)(PAL-NTSC)[!]");
        Assert.assertEquals("video", "PAL-NTSC", game.getVideo());
    }

    @Test
    public void testRegion() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(-)(DE)(A500)(PAL-NTSC)[!]");
        Assert.assertEquals("regions", 1, game.getRegions().size());
        Assert.assertEquals("region", "DE", game.getRegions().get(0));
    }

    @Test
    public void testRegions() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(-)(DE-FR)(A500)(PAL-NTSC)[!]");
        Assert.assertEquals("regions", 2, game.getRegions().size());
        Assert.assertEquals("region", "FR", game.getRegions().get(1));
    }

    @Test
    public void testLanguage() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(-)(DE)(de)(A500)(PAL-NTSC)[!]");
        Assert.assertEquals("languages", 1, game.getLanguages().size());
        Assert.assertEquals("language", "de", game.getLanguages().get(0));
    }

    @Test
    public void testLanguages() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(-)(DE-FR)(de-fr)(A500)(PAL-NTSC)[!]");
        Assert.assertEquals("languages", 2, game.getLanguages().size());
        Assert.assertEquals("language", "fr", game.getLanguages().get(1));
    }

    @Test
    public void testCopyright() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(-)(DE)(de)(CW-R)");
        Assert.assertEquals("copyright", "CW-R", game.getCopyright());
    }

    @Test
    public void testDevStatus() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(-)(DE)(de)(CW-R)(preview)");
        Assert.assertEquals("devstatus", "preview", game.getDevStatus());
    }

    @Test
    public void testMediaType() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(-)(DE)(de)(CW-R)(preview)(Disc 1 of 6)");
        Assert.assertEquals("mediatype", "Disc 1 of 6", game.getMediaType());
    }

    @Test
    public void testMediaLabel() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(-)(DE)(de)(CW-R)(preview)(Disc 1 of 6)(Data)");
        Assert.assertEquals("medialabel", "Data", game.getMediaLabel());
    }

    @Test
    public void testMediaLabelNoMediaType() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(-)(DE)(de)(CW-R)(preview)(Program)");
        Assert.assertEquals("medialabel", "Program", game.getMediaLabel());
    }

    @Test
    public void testComplement() {
        TOSECGame game = parser.parseName("Legend of TOSEC, The v20000101 (1986)(-)(DE)(de)(CW-R)(preview)(Teste)(Program)");
        Assert.assertEquals("complements", 1, game.getComplements().size());
        Assert.assertEquals("complement", "Teste", game.getComplements().get(0));
    }
}
