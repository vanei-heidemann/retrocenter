package br.com.javanei.retrocenter.catalog.nointro.parser;

import org.junit.Assert;
import org.junit.Test;

public class NoIntroNameParserTest {
    private static NoIntroNameParser parser = new NoIntroNameParser();

    @Test
    public void testIsBios() throws Exception {
        Assert.assertEquals("isBios", Boolean.TRUE, parser.parseName("[BIOS] Atari Jaguar (World)").getIsBios());
    }

    @Test
    public void testNotIsBios() throws Exception {
        Assert.assertEquals("isBios", Boolean.FALSE, parser.parseName("Alien vs Predator (World)").getIsBios());
    }

    @Test
    public void testName() throws Exception {
        Assert.assertEquals("name", "[BIOS] Atari Jaguar (World)", parser.parseName("[BIOS] Atari Jaguar (World)").getName());
    }

    @Test
    public void testMainName() throws Exception {
        Assert.assertEquals("mainName", "Atari Jaguar", parser.parseName("[BIOS] Atari Jaguar (World)").getMainName());
        Assert.assertEquals("mainName", "Alien vs Predator", parser.parseName("Alien vs Predator (World)").getMainName());
    }

    @Test
    public void testRegion() throws Exception {
        Assert.assertEquals("region", "World", parser.parseName("[BIOS] Atari Jaguar (World)").getRegions().get(0));
        Assert.assertEquals("region", "USA", parser.parseName("Awesome Golf (USA, Europe)").getRegions().get(0));
        Assert.assertEquals("region", "Europe", parser.parseName("Awesome Golf (USA, Europe)").getRegions().get(1));
    }

    @Test
    public void testProto() throws Exception {
        Assert.assertEquals("proto", "Proto", parser.parseName("Alien vs Predator (USA) (Proto)").getProto());
    }

    @Test
    public void testBeta() throws Exception {
        Assert.assertEquals("beta", "Beta", parser.parseName("Klax (USA, Europe) (Beta)").getBeta());
        Assert.assertEquals("beta", "Beta", parser.parseName("[BIOS] Kickstart (USA, Europe) (v1.4 Rev 36.015) (Beta) (A500, A2000)").getBeta());
    }

    @Test
    public void testDemo() throws Exception {
        Assert.assertEquals("demo", "Demo", parser.parseName("Blue Lightning (USA) (Demo)").getDemo());
    }

    @Test
    public void testUnl() throws Exception {
        Assert.assertEquals("unl", "Unl", parser.parseName("Castle Blast (USA) (Unl)").getUnl());
    }

    @Test
    public void testLanguage() throws Exception {
        Assert.assertEquals("language", "En", parser.parseName("Adventures of Robin Hood, The (Europe) (En,Fr,De,It)").getLanguages().get(0));
        Assert.assertEquals("language", "Fr", parser.parseName("Adventures of Robin Hood, The (Europe) (En,Fr,De,It)").getLanguages().get(1));
        Assert.assertEquals("language", "De", parser.parseName("Adventures of Robin Hood, The (Europe) (En,Fr,De,It)").getLanguages().get(2));
        Assert.assertEquals("language", "It", parser.parseName("Adventures of Robin Hood, The (Europe) (En,Fr,De,It)").getLanguages().get(3));
    }

    @Test
    public void testVersion() throws Exception {
        Assert.assertEquals("version", "v2.50 4 Jan 91", parser.parseName("Backgammon Royale (Europe) (En,Fr,De) (v2.50 4 Jan 91)").getVersion());
        Assert.assertEquals("version", "v1.019", parser.parseName("Conquests of Camelot - The Search for the Grail (Europe) (v1.019)").getVersion());
        Assert.assertEquals("version", "v1.9", parser.parseName("Corruption (Europe) (v1.9)").getVersion());
        Assert.assertEquals("version", "r01.1000", parser.parseName("3D Construction Kit (Europe) (En,Fr,De,It) (r01.1000)").getVersion());
        Assert.assertEquals("version", "v1.6.6", parser.parseName("[BIOS] GamePark GP32 (Europe) (v1.6.6) (2003-05-21)").getVersion());
    }

    @Test
    public void testCompilation() throws Exception {
        Assert.assertEquals("compilation", "Compilation - TenStar Pack", parser.parseName("Chess Player 2150 (Europe) (Compilation - TenStar Pack)").getCompilation());
    }

    @Test
    public void testReleaseDate() throws Exception {
        Assert.assertEquals("releaseDate", "1991-06-04", parser.parseName("Cybercon III (Europe) (4.6.91)").getReleaseDate());
        Assert.assertEquals("releaseDate", "2003-05-21", parser.parseName("[BIOS] GamePark GP32 (Europe) (v1.6.6) (2003-05-21)").getReleaseDate());
    }

    @Test
    public void testComplement() throws Exception {
        Assert.assertEquals("complement", "Budget - 16 Blitz", parser.parseName("Double Dragon II - The Revenge (Europe) (v1.2) (Budget - 16 Blitz)").getComplements().get(0));
    }

    @Test
    public void testSystem() throws Exception {
        Assert.assertEquals("system", "Adam", parser.parseName("Donkey Kong (USA, Europe) (Adam)").getSystems().get(0));
        Assert.assertEquals("system", "A500", parser.parseName("[BIOS] Kickstart (USA, Europe) (v1.4 Rev 36.015) (Beta) (A500, A2000)").getSystems().get(0));
        Assert.assertEquals("system", "A2000", parser.parseName("[BIOS] Kickstart (USA, Europe) (v1.4 Rev 36.015) (Beta) (A500, A2000)").getSystems().get(1));
    }

    @Test
    public void testAlternate() throws Exception {
        Assert.assertEquals("alternate", "Alt 1", parser.parseName("Motocross Racer (USA) (Alt 1)").getAlternate());
        Assert.assertEquals("alternate", "Alt 2", parser.parseName("Aliens - Alien 2 (Japan) (Alt 2)").getAlternate());
    }

    @Test
    public void testBadDump() throws Exception {
        Assert.assertEquals("badDump", "BadDump", parser.parseName("Akumajou Dracula (Japan) [b]").getBadDump());
    }
}
