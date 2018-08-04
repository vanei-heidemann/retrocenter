package br.com.javanei.retrocenter.datafile.mame.parser;

import br.com.javanei.retrocenter.datafile.mame.Mame;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;

public class MameTest {
    private static Mame mame;

    @BeforeClass
    public static void initialize() throws Exception {
        MameParser parser = new MameParser();

        InputStream is = MameParserTest.class.getClassLoader().getResourceAsStream("mame-info.xml");
        mame = parser.parse(is);
    }

    @Test
    public void debugNo() throws Exception {
        MameParser parser = new MameParser();

        InputStream is = MameParserTest.class.getClassLoader().getResourceAsStream("mame-debug-no.xml");
        Mame mame = parser.parse(is);
        Assert.assertEquals("no", mame.getDebug());
    }

    @Test
    public void debugYes() throws Exception {
        MameParser parser = new MameParser();

        InputStream is = MameParserTest.class.getClassLoader().getResourceAsStream("mame-debug-yes.xml");
        Mame mame = parser.parse(is);
        Assert.assertEquals("yes", mame.getDebug());
    }

    @Test
    public void debugNoInfo() throws Exception {
        MameParser parser = new MameParser();

        InputStream is = MameParserTest.class.getClassLoader().getResourceAsStream("mame-debug-noinfo.xml");
        Mame mame = parser.parse(is);
        Assert.assertEquals("no", mame.getDebug());
    }

    @Test
    public void mameBuild() throws Exception {
        Assert.assertEquals("build", "0.186 (mame0186)", mame.getBuild());
    }

    @Test
    public void mameConfig() throws Exception {
        Assert.assertEquals("mameconfig", "10", mame.getMameconfig());
    }

    @Test
    public void mameMachines() throws Exception {
        Assert.assertEquals("machines", 1, mame.getMachines().size());
    }
}
