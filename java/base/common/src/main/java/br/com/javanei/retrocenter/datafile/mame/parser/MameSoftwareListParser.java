package br.com.javanei.retrocenter.datafile.mame.parser;

import br.com.javanei.retrocenter.datafile.mame.mamesoftwarelist.MameSoftware;
import br.com.javanei.retrocenter.datafile.mame.mamesoftwarelist.MameSoftwareDataarea;
import br.com.javanei.retrocenter.datafile.mame.mamesoftwarelist.MameSoftwareDipswitch;
import br.com.javanei.retrocenter.datafile.mame.mamesoftwarelist.MameSoftwareDipvalue;
import br.com.javanei.retrocenter.datafile.mame.mamesoftwarelist.MameSoftwareDisk;
import br.com.javanei.retrocenter.datafile.mame.mamesoftwarelist.MameSoftwareDiskarea;
import br.com.javanei.retrocenter.datafile.mame.mamesoftwarelist.MameSoftwareFeature;
import br.com.javanei.retrocenter.datafile.mame.mamesoftwarelist.MameSoftwareInfo;
import br.com.javanei.retrocenter.datafile.mame.mamesoftwarelist.MameSoftwareList;
import br.com.javanei.retrocenter.datafile.mame.mamesoftwarelist.MameSoftwarePart;
import br.com.javanei.retrocenter.datafile.mame.mamesoftwarelist.MameSoftwareRom;
import br.com.javanei.retrocenter.datafile.mame.mamesoftwarelist.MameSoftwareSharedfeat;
import br.com.javanei.retrocenter.util.FileUtil;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class MameSoftwareListParser {
    /*
    public static void main(String[] args) {
        try {
            List<MameSoftwareList> l = parseSoftwareList(new File("F:/tmp/mame173/listsoftware.txt"));
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            out.write(("<softwarelists>" + System.getProperty("line.separator")).getBytes());
            for (MameSoftwareList s : l) {
                out.write(s.toString().getBytes());
            }
            out.write(("</softwarelists>" + System.getProperty("line.separator")).getBytes());
            FileUtil.writeFile(new File("F:/tmp/mame173/_listsoftware.txt"), out.toByteArray());
            //System.out.println(out.toString());
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    */

    /**
     * O arquivo tem que ter sido gerado pela opção -listsoftware do MAME.
     *
     * @param softwareListTxt
     * @return
     * @throws Exception
     */
    public static List<MameSoftwareList> parseSoftwareList(File softwareListTxt) throws Exception {
        List<MameSoftwareList> result = new LinkedList<>();

        List<String> lines = FileUtil.readFileAsLines(softwareListTxt);
        if (lines.isEmpty()) {
            return result;
        }

        // Acha o início
        String line = lines.get(0).trim();
        while (!lines.isEmpty() && !line.equals("<softwarelists>")) {
            line = nextLineInList(lines);
        }

        if (lines.isEmpty()) return result;
        line = nextLineInList(lines);

        MameSoftwareList mml = null;
        MameSoftware ms = null;
        MameSoftwarePart part = null;
        MameSoftwareDataarea dataarea = null;
        MameSoftwareDiskarea diskarea = null;
        MameSoftwareDipswitch dipswitch = null;

        while (!lines.isEmpty()) {
            if (line.equals("<softwarelists>")) {
                line = nextLineInList(lines);
                continue;
            } else if (line.equals("</softwarelists>")) {
                break;
            }

            if (line.startsWith("<softwarelist ")) {
                mml = new MameSoftwareList();
                ms = null;
                part = null;
                dataarea = null;
                diskarea = null;
                dipswitch = null;

                Map<String, String> m = parseAttributes(line);
                mml.setName(m.get("name"));
                mml.setDescription(m.get("description"));
                result.add(mml);
            } else if (line.startsWith("<software")) {
                ms = new MameSoftware();
                part = null;
                dataarea = null;
                diskarea = null;
                dipswitch = null;

                Map<String, String> m = parseAttributes(line);
                ms.setName(m.get("name"));
                ms.setCloneof(m.get("cloneof"));
                ms.setSupported(m.get("supported"));
                mml.addSoftware(ms);
            } else if (line.startsWith("<description>")) {
                if (ms != null) {
                    ms.setDescription(extractTagContent(line, "description"));
                } else {
                    System.err.println("ERRO: TAG 'description' perdida");
                }
            } else if (line.startsWith("<year>")) {
                if (ms != null) {
                    ms.setYear(extractTagContent(line, "year"));
                } else {
                    System.err.println("ERRO: TAG 'year' perdida");
                }
            } else if (line.startsWith("<publisher>")) {
                if (ms != null) {
                    ms.setPublisher(extractTagContent(line, "publisher"));
                } else {
                    System.err.println("ERRO: TAG 'publisher' perdida");
                }
            } else if (line.startsWith("<info")) {
                MameSoftwareInfo info = new MameSoftwareInfo();
                Map<String, String> m = parseAttributes(line);
                info.setName(m.get("name"));
                info.setValue(m.get("value"));
                ms.addInfo(info);
            } else if (line.startsWith("<sharedfeat")) {
                MameSoftwareSharedfeat feat = new MameSoftwareSharedfeat();
                Map<String, String> m = parseAttributes(line);
                feat.setName(m.get("name"));
                feat.setValue(m.get("value"));
                ms.addSharedfeat(feat);
            } else if (line.startsWith("<part")) {
                part = new MameSoftwarePart();
                dataarea = null;
                diskarea = null;
                dipswitch = null;

                Map<String, String> m = parseAttributes(line);
                part.setName(m.get("name"));
                part.setInterface(m.get("interface"));
                ms.addPart(part);
            } else if (line.startsWith("<feature")) {
                MameSoftwareFeature feature = new MameSoftwareFeature();
                Map<String, String> m = parseAttributes(line);
                feature.setName(m.get("name"));
                feature.setValue(m.get("value"));
                part.addFeature(feature);
            } else if (line.startsWith("<dataarea")) {
                dataarea = new MameSoftwareDataarea();
                Map<String, String> m = parseAttributes(line);
                dataarea.setName(m.get("name"));
                dataarea.setSize(m.get("size"));
                dataarea.setDatabits(m.get("databits"));
                dataarea.setEndian(m.get("endian"));
                part.addDataarea(dataarea);
            } else if (line.startsWith("<rom")) {
                MameSoftwareRom rom = new MameSoftwareRom();
                Map<String, String> m = parseAttributes(line);
                rom.setName(m.get("name"));
                if (m.get("size") != null) {
                    rom.setSize(Integer.parseInt(m.get("size")));
                }
                rom.setLength(m.get("length"));
                rom.setCrc(m.get("crc"));
                rom.setSha1(m.get("sha1"));
                rom.setOffset(m.get("offset"));
                rom.setStatus(m.get("status"));
                rom.setLoadflag(m.get("loadflag"));
                dataarea.addRom(rom);
            } else if (line.startsWith("<diskarea")) {
                diskarea = new MameSoftwareDiskarea();
                Map<String, String> m = parseAttributes(line);
                diskarea.setName(m.get("name"));
                part.addDiskarea(diskarea);
            } else if (line.startsWith("<disk")) {
                MameSoftwareDisk disk = new MameSoftwareDisk();
                Map<String, String> m = parseAttributes(line);
                disk.setName(m.get("name"));
                disk.setSha1(m.get("sha1"));
                disk.setStatus(m.get("status"));
                if (m.get("writeable") != null) {
                    disk.setWriteable(m.get("writeable"));
                }
                diskarea.addDisk(disk);
            } else if (line.startsWith("<dipswitch")) {
                dipswitch = new MameSoftwareDipswitch();
                Map<String, String> m = parseAttributes(line);
                dipswitch.setName(m.get("name"));
                dipswitch.setTag(m.get("tag"));
                dipswitch.setMask(m.get("mask"));
                part.addDipswitch(dipswitch);
                if (line.startsWith("<dipvalue")) {
                    MameSoftwareDipvalue dipvalue = new MameSoftwareDipvalue();
                    m = parseAttributes(line);
                    dipvalue.setName(m.get("name"));
                    dipvalue.setValue(m.get("value"));
                    if (m.get("default") != null) {
                        dipvalue.setDefault(m.get("default"));
                    }
                    dipswitch.addDipvalue(dipvalue);
                }
            } else if (line.equals("</softwarelist>") || line.equals("</software>") || line.equals("</part>")
                    || line.equals("</dataarea>") || line.equals("</diskarea>") || line.equals("</dipswitch>")) {
                // Ignorar
            } else {
                System.err.println("ERRO: Linha nao tratada: " + line);
            }
            line = nextLineInList(lines);
        }

        return result;
    }

    private static String nextLineInList(List<String> lines) {
        if (!lines.isEmpty()) {
            lines.remove(0);
            return lines.get(0).trim();
        }
        return null;
    }

    private static final Map<String, String> parseAttributes(String line) {
        Map<String, String> m = new HashMap<>();

        StringBuilder attrName = null;
        StringBuilder attrValue = null;
        char lastChar = '\0';
        for (char c : line.toCharArray()) {
            if (attrValue != null) {
                if (c == '\"') {
                    // Encerrou o value
                    m.put(attrName.toString(), attrValue.toString());
                    attrName = null;
                    attrValue = null;
                } else {
                    attrValue.append(c);
                }
            } else if (attrName != null) {
                if (c == '=') {
                    // Ignora
                } else if (c == '\"' && lastChar == '=') {
                    attrValue = new StringBuilder();
                } else {
                    attrName.append(c);
                }
            } else {
                if (c == ' ') {
                    attrName = new StringBuilder();
                }
            }

            lastChar = c;
        }
        return m;
    }

    private static final String extractTagContent(String content, String tagName) {
        int pos = content.indexOf("<" + tagName + ">") + tagName.length() + 2;
        int endPos = content.indexOf("</" + tagName + ">", pos);
        return content.substring(pos, endPos);
    }
}
