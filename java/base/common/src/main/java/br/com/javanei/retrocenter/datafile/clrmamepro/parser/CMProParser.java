package br.com.javanei.retrocenter.datafile.clrmamepro.parser;

import br.com.javanei.retrocenter.common.DuplicatedItemException;
import br.com.javanei.retrocenter.common.UnknownDatafileFormatException;
import br.com.javanei.retrocenter.common.UnknownTagException;
import br.com.javanei.retrocenter.datafile.clrmamepro.CMProDatafile;
import br.com.javanei.retrocenter.datafile.clrmamepro.CMProDisk;
import br.com.javanei.retrocenter.datafile.clrmamepro.CMProGame;
import br.com.javanei.retrocenter.datafile.clrmamepro.CMProHeader;
import br.com.javanei.retrocenter.datafile.clrmamepro.CMProResource;
import br.com.javanei.retrocenter.datafile.clrmamepro.CMProRom;
import br.com.javanei.retrocenter.datafile.common.Parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CMProParser implements Parser {
    private static CMProDisk parseDisk(String line) {
        String romLine = line.substring(line.indexOf("(") + 1, line.length() - 1).trim();
        CMProDisk r = new CMProDisk();

        int pos = romLine.indexOf("\"") + 1;
        int endpos = romLine.indexOf("\"", pos + 1);
        r.setName(romLine.substring(pos, endpos));
        String[] ss = romLine.substring(endpos + 2).trim().split(" ");
        for (int i = 0; i < ss.length; i++) {
            switch (ss[i]) {
                case "md5":
                    r.setMd5(ss[++i]);
                    break;
                case "sha1":
                    r.setSha1(ss[++i]);
                    break;
                default:
                    throw new UnknownTagException(ss[i]);
            }
        }
        return r;
    }

    private static CMProRom parseRom(String line) {
        String romLine = line.substring(line.indexOf("(") + 1, line.length() - 1).trim();
        CMProRom r = new CMProRom();

        int pos = romLine.indexOf("\"") + 1;
        int endpos = romLine.indexOf("\"", pos + 1);
        r.setName(romLine.substring(pos, endpos));
        String[] ss = romLine.substring(endpos + 2).trim().split(" ");
        for (int i = 0; i < ss.length; i++) {
            switch (ss[i]) {
                case "size":
                    r.setSize(Long.parseLong(ss[++i]));
                    break;
                case "crc":
                    r.setCrc(ss[++i]);
                    break;
                case "md5":
                    r.setMd5(ss[++i]);
                    break;
                case "sha1":
                    r.setSha1(ss[++i]);
                    break;
                case "region":
                    r.setRegion(ss[++i]);
                    break;
                case "flags":
                    r.setFlags(ss[++i]);
                    break;
                case "nodump":
                    // There is no dump info
                    break;
                default:
                    throw new UnknownTagException(ss[i]);
            }
        }
        return r;
    }

    private static String extractLineValue(String key, String line) {
        return normalizeString(line.substring(key.length()).trim());
    }

    private static String normalizeString(String s) {
        if (s.startsWith("\"") && s.endsWith("\"")) {
            return s.substring(1, s.length() - 1).trim();
        }
        return s.trim();
    }

    public CMProDatafile parse(File file) throws UnknownDatafileFormatException, IOException {
        try (FileInputStream is = new FileInputStream(file)) {
            return parse(is);
        }
    }

    public CMProDatafile parse(InputStream is) throws UnknownDatafileFormatException, IOException {
        CMProDatafile r = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line = reader.readLine().trim();
            String[] ss = line.split(" ");
            if (!ss[0].trim().equals("clrmamepro")) {
                throw new UnknownDatafileFormatException();
            }

            CMProHeader header = new CMProHeader();
            r = new CMProDatafile(header);
            line = reader.readLine().trim();
            while (!line.equals(")")) {
                if (line.startsWith("name")) {
                    header.setName(extractLineValue("name", line));
                } else if (line.startsWith("description")) {
                    header.setDescription(extractLineValue("description", line));
                } else if (line.startsWith("category")) {
                    header.setCategory(extractLineValue("category", line));
                } else if (line.startsWith("version")) {
                    header.setVersion(extractLineValue("version", line));
                } else if (line.startsWith("author")) {
                    header.setAuthor(extractLineValue("author", line));
                } else if (line.startsWith("url")) {
                    header.setUrl(extractLineValue("url", line));
                } else if (line.startsWith("homepage")) {
                    header.setHomepage(extractLineValue("homepage", line));
                } else if (line.startsWith("forcemerging")) {
                    header.setForcemerging(extractLineValue("forcemerging", line));
                } else if (line.startsWith("forcezipping")) {
                    header.setForcezipping(extractLineValue("forcezipping", line));
                } else if (line.startsWith("comment")) {
                    header.setComment(extractLineValue("comment", line));
                } else {
                    String key = line.substring(0, line.indexOf(" ")).trim();
                    if (!header.addCustomField(key, extractLineValue(key, line))) {
                        throw new DuplicatedItemException("customField: " + key);
                    }
                }

                line = reader.readLine().trim();
            }

            line = reader.readLine();
            while (line != null) {
                if (line.startsWith("game")) {
                    CMProGame game = new CMProGame();
                    line = reader.readLine().trim();
                    while (!line.equals(")")) {
                        if (line.startsWith("name")) {
                            game.setName(extractLineValue("name", line));
                        } else if (line.startsWith("description")) {
                            game.setDescription(extractLineValue("description", line));
                        } else if (line.startsWith("year")) {
                            game.setYear(extractLineValue("year", line));
                        } else if (line.startsWith("manufacturer")) {
                            game.setManufacturer(extractLineValue("manufacturer", line));
                        } else if (line.startsWith("cloneof")) {
                            game.setCloneof(extractLineValue("cloneof", line));
                        } else if (line.startsWith("romof")) {
                            game.setRomof(extractLineValue("romof", line));
                        } else if (line.startsWith("serial")) {
                            game.setSerial(extractLineValue("serial", line));
                        } else if (line.startsWith("sampleof")) {
                            String sampleof = extractLineValue("sampleof", line);
                            if (!game.addSampleOf(sampleof)) {
                                throw new DuplicatedItemException("sampleof: " + sampleof + " for game: " + game.getName());
                            }
                        } else if (line.startsWith("rom")) {
                            CMProRom rom = parseRom(line);
                            if (!game.addRom(rom)) {
                                throw new DuplicatedItemException("rom: " + rom + " for game: " + game.getName());
                            }
                        } else if (line.startsWith("disk")) {
                            CMProDisk disk = parseDisk(line);
                            if (!game.addDisk(disk)) {
                                throw new DuplicatedItemException("disk: " + disk + " for game: " + game.getName());
                            }
                        } else if (line.startsWith("sample")) {
                            String sample = extractLineValue("sample", line);
                            if (!game.addSample(sample)) {
                                throw new DuplicatedItemException("sample: " + sample + " for game: " + game.getName());
                            }
                        } else {
                            throw new UnknownTagException(line.substring(0, line.indexOf(" ")).trim());
                        }
                        line = reader.readLine().trim();
                    }
                    if (!r.addGame(game)) {
                        throw new DuplicatedItemException("game: " + game.getName());
                    }
                } else if (line.startsWith("resource")) {
                    CMProResource resource = new CMProResource();
                    line = reader.readLine().trim();
                    while (!line.equals(")")) {
                        if (line.startsWith("name")) {
                            resource.setName(extractLineValue("name", line));
                        } else if (line.startsWith("description")) {
                            resource.setDescription(extractLineValue("description", line));
                        } else if (line.startsWith("year")) {
                            resource.setYear(extractLineValue("year", line));
                        } else if (line.startsWith("manufacturer")) {
                            resource.setManufacturer(extractLineValue("manufacturer", line));
                        } else if (line.startsWith("rom")) {
                            CMProRom rom = parseRom(line);
                            if (!resource.addRom(rom)) {
                                throw new DuplicatedItemException("rom: " + rom.getName() + " for resource: " + resource.getName());
                            }
                        } else {
                            throw new UnknownTagException(line.substring(0, line.indexOf(" ")).trim());
                        }
                        line = reader.readLine().trim();
                    }
                    if (!r.addResource(resource)) {
                        throw new DuplicatedItemException("resource: " + resource.getName());
                    }
                } else if (!line.isEmpty()) {
                    throw new UnknownTagException(line);
                }

                line = reader.readLine();
                if (line != null) line = line.trim();
            }
        }

        return r;
    }
}
