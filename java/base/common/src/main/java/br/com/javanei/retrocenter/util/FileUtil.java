package br.com.javanei.retrocenter.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.CRC32;

public final class FileUtil {
    public static final List<File> listZipFilesRecursive(File srcDir) throws Exception {
        List<File> result = new LinkedList<>();
        listZipFilesRecursive(srcDir, result);
        return result;
    }

    private static void listZipFilesRecursive(File srcDir, List<File> result) throws Exception {
        File[] files = srcDir.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                listZipFilesRecursive(f, result);
            } else if (f.isFile() && f.getName().toLowerCase().endsWith(".zip")) {
                result.add(f);
            }
        }
    }

    public static final String getFileExtension(String fileName) {
        if (fileName.indexOf(".") > 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return "";
    }

    public static final long getCRC(File f) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        FileInputStream fis = new FileInputStream(f);
        byte[] b = new byte[4096];
        int len = fis.read(b);
        while (len > 0) {
            out.write(b, 0, len);
            len = fis.read(b);
        }
        out.close();
        return getCRC(out.toByteArray());
    }

    public static long getCRC(byte[] b) {
        CRC32 crc = new CRC32();
        crc.update(b);
        return crc.getValue();
    }

    public static byte[] getMD5(byte[] b) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.reset();
        md5.update(b);
        return md5.digest();
    }

    public static byte[] getSHA1(byte[] b) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("SHA1");
        md5.reset();
        md5.update(b);
        return md5.digest();
    }

    public static void moveFile(File srcFile, File dstFile) throws IOException {
        if (!srcFile.renameTo(dstFile)) {
            Path src = srcFile.toPath();
            Path dst = dstFile.toPath();
            Files.move(src, dst, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public static void copyFile(File srcFile, File dstFile) throws IOException {
        if (!srcFile.renameTo(dstFile)) {
            Path src = srcFile.toPath();
            Path dst = dstFile.toPath();
            Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public static byte[] readFile(File srcFile) throws IOException {
        byte[] result;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            try (FileInputStream fis = new FileInputStream(srcFile)) {
                byte[] b = new byte[4096];
                int len = fis.read(b);
                while (len > 0) {
                    out.write(b, 0, len);
                    len = fis.read(b);
                }
            }
            result = out.toByteArray();
        }
        return result;
    }

    public static void writeFile(File destFile, byte[] bytes) throws IOException {
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }
        try (FileOutputStream out = new FileOutputStream(destFile)) {
            out.write(bytes);
        }
    }

    public static List<String> readFileAsLines(File scrFile) throws Exception {
        List<String> result = new LinkedList<>();

        try (FileReader fileReader = new FileReader(scrFile)) {
            try (BufferedReader reader = new BufferedReader(fileReader)) {
                String line = reader.readLine();
                while (line != null) {
                    result.add(line);
                    line = reader.readLine();
                }
            }
        }

        return result;
    }
}
