package br.com.javanei.retrocenter.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
    public static Map<String, byte[]> extractToByteArray(byte[] zipContent) throws IOException {
        Map<String, byte[]> result = new LinkedHashMap<>();

        ZipInputStream zipStream = new ZipInputStream(new ByteArrayInputStream(zipContent));
        ZipEntry entry = null;
        while ((entry = zipStream.getNextEntry()) != null) {
            String entryName = entry.getName();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            byte[] byteBuff = new byte[4096];
            int bytesRead = 0;
            while ((bytesRead = zipStream.read(byteBuff)) != -1) {
                out.write(byteBuff, 0, bytesRead);
            }
            if (!entry.isDirectory()) {
                result.put(entryName, out.toByteArray());
            }

            out.close();
            zipStream.closeEntry();
        }
        zipStream.close();
        return result;
    }

    public static Map<String, byte[]> extractToByteArray(File zipFile) throws IOException {
        Map<String, byte[]> result = new LinkedHashMap<>();

        ZipFile zip = new ZipFile(zipFile);
        Enumeration entries = zip.entries();
        while (entries.hasMoreElements()) {
            ZipEntry ze = (ZipEntry) entries.nextElement();
            if (!ze.isDirectory()) {
                String name = ze.getName();
                InputStream is = zip.getInputStream(ze);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] b = new byte[4096];
                int size = is.read(b);
                while (size > 0) {
                    out.write(b, 0, size);
                    size = is.read(b);
                }
                result.put(name, out.toByteArray());
                out.close();
            }
        }
        zip.close();
        return result;
    }

    public static void extract(File zipFile, File destDir) throws Exception {
        ZipFile zip = new ZipFile(zipFile);
        Enumeration entries = zip.entries();
        while (entries.hasMoreElements()) {
            ZipEntry ze = (ZipEntry) entries.nextElement();
            System.out.println(ze.getName() + " : " + ze.getCrc());
            File dest = new File(destDir, ze.getName());
            if (ze.isDirectory()) {
                dest.mkdir();
            } else {
                InputStream is = zip.getInputStream(ze);
                FileOutputStream fos = new FileOutputStream(dest);
                byte[] b = new byte[4096];
                int size = is.read(b);
                while (size > 0) {
                    fos.write(b, 0, size);
                    size = is.read(b);
                }
                fos.close();
            }
        }
        zip.close();
    }

    public static void extractByCRC(File zipFile, File destFile, String crc) throws Exception {
        ZipFile zip = new ZipFile(zipFile);
        Enumeration entries = zip.entries();
        while (entries.hasMoreElements()) {
            ZipEntry ze = (ZipEntry) entries.nextElement();
            if (StringUtil.toStringCRC(ze.getCrc()).equals(crc)) {
                InputStream is = zip.getInputStream(ze);
                FileOutputStream fos = new FileOutputStream(destFile);
                byte[] b = new byte[4096];
                int size = is.read(b);
                while (size > 0) {
                    fos.write(b, 0, size);
                    size = is.read(b);
                }
                fos.close();
            }
        }
        zip.close();
    }

    public static long getCRCFromFirstFile(File zipFile) throws Exception {
        long r = 0;
        ZipFile zip = new ZipFile(zipFile);
        Enumeration entries = zip.entries();
        if (entries.hasMoreElements()) {
            ZipEntry ze = (ZipEntry) entries.nextElement();
            r = ze.getCrc();
        }
        zip.close();
        return r;
    }

    public static void createZipFile(File zip, File file) throws Exception {
        // out put file
        try ( // input file
              FileInputStream in = new FileInputStream(file); // out put file
              ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zip))) {
            // name the file inside the zip  file
            out.putNextEntry(new ZipEntry(file.getName()));
            // buffer size
            byte[] b = new byte[1024];
            int count;
            while ((count = in.read(b)) > 0) {
                out.write(b, 0, count);
            }
            out.closeEntry();
        }
    }

    public static void createZipFile(File zipFile, File[] files) throws IOException {
        createZipFile(zipFile, Arrays.asList(files));
    }

    public static void createZipFile(File zipFile, List<File> files) throws IOException {
        try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile))) {
            for (File f : files) {
                out.putNextEntry(new ZipEntry(f.getName()));
                out.write(FileUtil.readFile(f));
                out.flush();
            }
            out.closeEntry();
        }
    }

    public static void addFileToZip(File zipFile, String name, byte[] b) throws IOException {
        addFileToZip(zipFile, name, null, b);
    }

    public static String getComment(File zipFile) throws IOException {
        ZipFile zip = new ZipFile(zipFile);
        return zip.getComment();
    }

    public static void addFileToZip(File zipFile, String name, String comment, byte[] b) throws IOException {
        // Cria um arquivo temporário
        File outZip = new File(zipFile.getParentFile(), "___tmp.zip");
        // Grava o novo arquivo.
        try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outZip))) {
            out.putNextEntry(new ZipEntry(name));
            if (comment != null && !comment.trim().isEmpty()) {
                out.setComment(comment);
            }
            out.write(b);
            out.flush();
            out.closeEntry();

            // Restaura os arquivos anteriores
            if (zipFile.exists()) {
                try (ZipFile zf = new ZipFile(zipFile)) {
                    Enumeration entries = zf.entries();
                    while (entries.hasMoreElements()) {
                        ZipEntry ze = (ZipEntry) entries.nextElement();
                        out.putNextEntry(ze);
                        byte[] BUFFER = new byte[4096];
                        int bytesRead;
                        InputStream is = zf.getInputStream(ze);
                        while ((bytesRead = is.read(BUFFER)) != -1) {
                            out.write(BUFFER, 0, bytesRead);
                        }
                        out.closeEntry();
                    }
                }
                zipFile.delete();
            }
        }
        FileUtil.moveFile(outZip, zipFile);
    }

    public static void validateExistFileInZip(File zipFile, String file) throws Exception {
        if (zipFile.exists()) {
            try (ZipFile zf = new ZipFile(zipFile)) {
                Enumeration entries = zf.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry ze = (ZipEntry) entries.nextElement();
                    if (ze.getName().equalsIgnoreCase(file)) {
                        throw new FileAlreadyExistsException(file);
                    }
                }
            }
        }
    }

    public static final boolean isZipFile(File file) {
        return file.getName().toString().endsWith(".zip");
    }

    public static final boolean isValidZip(File file) {
        ZipFile zipfile = null;
        ZipInputStream zis = null;
        try {
            zipfile = new ZipFile(file);
            zis = new ZipInputStream(new FileInputStream(file));
            ZipEntry ze = zis.getNextEntry();
            if (ze == null) {
                return false;
            }
            while (ze != null) {
                zipfile.getInputStream(ze);
                ze.getCrc();
                ze.getCompressedSize();
                ze.getName();
                ze = zis.getNextEntry();
            }
            return true;
        } catch (ZipException e) {
            return false;
        } catch (IOException e) {
            return false;
        } finally {
            try {
                if (zipfile != null) {
                    zipfile.close();
                    zipfile = null;
                }
            } catch (IOException e) {
                return false;
            }
            try {
                if (zis != null) {
                    zis.close();
                    zis = null;
                }
            } catch (IOException e) {
                return false;
            }
        }
    }

    public static boolean isZip(byte[] b) {
        return b.length > 4 && b[0] == 0x50 && b[1] == 0x4b && b[2] == 0x03 && b[3] == 0x04;
    }
}
