package br.com.javanei.retrocenter.util;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Map;

import static org.junit.Assert.*;

public class ZipUtilTest {
    private File baseDir;
    private File zipFile;

    @Before
    public void setUp() throws Exception {
        baseDir = new File("src/test/resources");
        zipFile = new File(baseDir, "test.zip");
    }

    @Test
    public void extractToByteArray_Byte() throws IOException {
        byte[] file = FileUtil.readFile(zipFile);

        Map<String, byte[]> map = ZipUtil.extractToByteArray(file);

        assertEquals(2, map.size());
    }

    @Test
    public void extractToByteArray_File() throws IOException {
        Map<String, byte[]> map = ZipUtil.extractToByteArray(zipFile);

        assertEquals(2, map.size());
    }

    @Test
    public void getCRCFromFirstFile() throws Exception {
        assertEquals(2665963749l, ZipUtil.getCRCFromFirstFile(zipFile));
    }

    @Test
    public void getComment() throws IOException {
        assertEquals("comment", ZipUtil.getComment(zipFile));
    }

    @Test(expected = FileAlreadyExistsException.class)
    public void validateExistFileInZip() throws Exception {
        ZipUtil.validateExistFileInZip(zipFile, "file1.txt");
    }

    @Test
    public void validateNotExistFileInZip() throws Exception {
        ZipUtil.validateExistFileInZip(zipFile, "abc.txt");
    }

    @Test
    public void isZipFile() {
        assertTrue(ZipUtil.isZipFile(zipFile));
    }

    @Test
    public void isValidZip() {
        assertTrue(ZipUtil.isValidZip(zipFile));
    }

    @Test
    public void notIsValidZip1() {
        assertFalse(ZipUtil.isValidZip(new File(baseDir, "text.txt")));
    }

    @Test
    public void notIsValidZip2() {
        assertFalse(ZipUtil.isValidZip(new File(baseDir, "abc.txt")));
    }

    @Test
    public void isZip() throws IOException {
        byte[] file = FileUtil.readFile(zipFile);
        assertTrue(ZipUtil.isZip(file));
    }

    @Test
    public void notIsZip() throws IOException {
        byte[] file = FileUtil.readFile(new File(baseDir, "text.txt"));
        assertFalse(ZipUtil.isZip(file));
    }
}