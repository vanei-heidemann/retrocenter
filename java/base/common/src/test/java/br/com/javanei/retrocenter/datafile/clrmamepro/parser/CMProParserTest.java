package br.com.javanei.retrocenter.datafile.clrmamepro.parser;

import br.com.javanei.retrocenter.datafile.clrmamepro.CMProDatafile;
import br.com.javanei.retrocenter.datafile.clrmamepro.CMProGame;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.InputStream;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CMProParserTest {
    private static CMProDatafile psvita;

    @BeforeClass
    public static void initialize() throws Exception {
        CMProParser parser = new CMProParser();

        InputStream is = CMProParserTest.class.getClassLoader().getResourceAsStream("Sony - PlayStation Vita (20170114-224204_CM).dat");
        psvita = parser.parse(is);
    }

    @Test
    public void test010HeaderName() throws Exception {
        Assert.assertEquals("Name", "Sony - PlayStation Vita", psvita.getHeader().getName());
    }

    @Test
    public void test011HeaderDescription() {
        Assert.assertEquals("Description", "Sony - PlayStation Vita", psvita.getHeader().getDescription());
    }

    @Test
    public void test012HeaderVersion() {
        Assert.assertEquals("Version", "20170114-224204", psvita.getHeader().getVersion());
    }

    @Test
    public void test013HeaderAuthor() {
        Assert.assertEquals("Author", "Densetsu, einstein95, Gefflon, Hiccup, jimmsu, Money_114, SonGoku, xuom2, zg", psvita.getHeader().getAuthor());
    }

    @Test
    public void test014HeaderURL() {
        Assert.assertEquals("URL", "http://www.no-intro.org", psvita.getHeader().getUrl());
    }

    @Test
    public void test015HeaderHomepage() {
        Assert.assertEquals("Homepage: ", "No-Intro", psvita.getHeader().getHomepage());
    }

    @Test
    public void test020QtdGames() {
        Assert.assertEquals("Games count", 235, psvita.getGames().size());
    }

    @Test
    public void test030NoDump() {
        boolean ok = false;
        for (CMProGame game : psvita.getGames()) {
            if (game.getName().equals("Army Corps of Hell (USA)")) {
                ok = game.getRoms().size() > 0 && game.getRoms().iterator().next().isNodump();
                break;
            }
        }
        Assert.assertTrue("nodump", ok);
    }

    @Test
    public void test031FlagBaddump() {
        boolean ok = false;
        for (CMProGame game : psvita.getGames()) {
            if (game.getName().equals("Amazing Spider-Man, The (USA) [b]")) {
                ok = game.getRoms().size() > 0 && game.getRoms().iterator().next().getFlags().equals("baddump");
                break;
            }
        }
        Assert.assertTrue("baddump", ok);
    }

    @Test
    public void test032Serial() {
        String serial = null;
        for (CMProGame game : psvita.getGames()) {
            if (game.getName().equals("Amazing Spider-Man, The (USA) [b]")) {
                serial = game.getSerial();
                break;
            }
        }
        Assert.assertEquals("serial", "PCSE-00333", serial);
    }

    /*
    @Test
    public void test100ParseAll() throws Exception {
        Map<String, String> headerCustomFields = new HashMap<>();
        Set<String> flags = new HashSet<>();

        CMProParser parser = new CMProParser();
        File[] files = new File("F:\\Desenv\\Fontes\\Java\\retrocenter\\resources\\no-intro").listFiles();
        for (File f : files) {
            System.out.println("======== Processing file: " + f.getAbsolutePath());
            CMProDatafile m = parser.parse(f);
            System.out.println(m);
            headerCustomFields.putAll(m.getHeader().getCustomFields());
            for (CMProGame game : m.getArtifacts()) {
                for (CMProRom rom : game.getRoms()) {
                    if (rom.getFlags() != null) {
                        if (!flags.contains(rom.getFlags())) {
                            flags.add(rom.getFlags());
                        }
                    }
                }
            }
        }
        System.out.println(":::: Custom Fields:\n" + headerCustomFields);
        System.out.println(":::: flags:\n" + flags);
    }
    */
}
