package br.com.javanei.retrocenter.datafile.logiqx.parser;

import br.com.javanei.retrocenter.datafile.logiqx.LogiqxDatafile;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.InputStream;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LogiqxParserTest {
    private static LogiqxDatafile n64;

    @BeforeClass
    public static void initialize() throws Exception {
        LogiqxParser parser = new LogiqxParser();

        InputStream is = LogiqxParserTest.class.getClassLoader().getResourceAsStream("Nintendo 64 - Games (TOSEC-v2015-05-27_CM).dat");
        n64 = parser.parse(is);
    }

    @Test
    public void test010HeaderName() throws Exception {
        Assert.assertTrue("Name", n64.getHeader().getName().equals("Nintendo 64 - Games"));
    }

    @Test
    public void test011HeaderDescription() throws Exception {
        Assert.assertTrue("Description", n64.getHeader().getDescription().equals("Nintendo 64 - Games (TOSEC-v2015-05-27)"));
    }

    @Test
    public void test012HeaderCategory() throws Exception {
        Assert.assertTrue("Category", n64.getHeader().getCategory().equals("TOSEC"));
    }

    @Test
    public void test013HeaderVersion() throws Exception {
        Assert.assertTrue("Version", n64.getHeader().getVersion().equals("2015-05-27"));
    }

    @Test
    public void test014HeaderAuthor() throws Exception {
        Assert.assertTrue("Author", n64.getHeader().getAuthor().equals("Cassiel - Yori Yoshizuki"));
    }

    @Test
    public void test015HeaderEmail() throws Exception {
        Assert.assertTrue("Email", n64.getHeader().getEmail().equals("contact@tosecdev.org"));
    }

    @Test
    public void test016HeaderHomepage() throws Exception {
        Assert.assertTrue("Homepage", n64.getHeader().getHomepage().equals("TOSEC"));
    }

    @Test
    public void test017HeaderUrl() throws Exception {
        Assert.assertTrue("URL", n64.getHeader().getUrl().equals("http://www.tosecdev.org/"));
    }
}
