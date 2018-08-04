package br.com.javanei.retrocenter.datafile.mame.parser;

import br.com.javanei.retrocenter.datafile.mame.Mame;
import org.junit.Assert;

import java.io.InputStream;

public class MameParserTest {
    private static Mame mame;

    //@BeforeClass
    public static void initialize() throws Exception {
        MameParser parser = new MameParser();

        InputStream is = MameParserTest.class.getClassLoader().getResourceAsStream("mame0186.xml");
        mame = parser.parse(is);
        //System.out.println(mame);
    }

    //@Test
    public void test() {
        //System.out.println(mame);
        Assert.assertTrue(true);
    }
}
