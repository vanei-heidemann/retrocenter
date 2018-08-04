package br.com.javanei.retrocenter.datafile.logiqx.parser;

import br.com.javanei.retrocenter.common.UnknownDatafileFormatException;
import br.com.javanei.retrocenter.common.UnknownTagException;
import br.com.javanei.retrocenter.datafile.common.Parser;
import br.com.javanei.retrocenter.datafile.logiqx.LogiqxArchive;
import br.com.javanei.retrocenter.datafile.logiqx.LogiqxBiosset;
import br.com.javanei.retrocenter.datafile.logiqx.LogiqxDatafile;
import br.com.javanei.retrocenter.datafile.logiqx.LogiqxDisk;
import br.com.javanei.retrocenter.datafile.logiqx.LogiqxGame;
import br.com.javanei.retrocenter.datafile.logiqx.LogiqxHeader;
import br.com.javanei.retrocenter.datafile.logiqx.LogiqxRelease;
import br.com.javanei.retrocenter.datafile.logiqx.LogiqxRom;
import br.com.javanei.retrocenter.datafile.logiqx.LogiqxSample;
import br.com.javanei.retrocenter.util.ReflectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LogiqxParser implements Parser {
    private static final Logger LOG = LoggerFactory.getLogger(LogiqxParser.class);

    public LogiqxDatafile parse(File file) throws UnknownDatafileFormatException, IOException {
        LOG.info("parse(" + file + ")");
        try (FileInputStream is = new FileInputStream(file)) {
            return parse(is);
        }
    }

    public LogiqxDatafile parse(InputStream is) throws UnknownDatafileFormatException, IOException {
        LOG.info("parse(is)");
        LogiqxDatafile r = new LogiqxDatafile();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringComments(true);
        factory.setValidating(false);

        DocumentBuilder builder;
        Document doc;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(is);
        } catch (ParserConfigurationException e) {
            LOG.error(e.getMessage(), e);
            throw new UnknownDatafileFormatException();
        } catch (SAXException e) {
            LOG.error(e.getMessage(), e);
            throw new UnknownDatafileFormatException();
        }
        NodeList datafiles = doc.getChildNodes();
        for (int idatafile = 0; idatafile < datafiles.getLength(); idatafile++) {
            Node datafile = datafiles.item(idatafile);
            if (datafile.getNodeName().equals("datafile")) {
                for (int i = 0; i < datafile.getChildNodes().getLength(); i++) {
                    Node n = datafile.getChildNodes().item(i);
                    if (n.getNodeName().equals("#text")) {
                        continue;
                    }
                    switch (n.getNodeName()) {
                        case "header":
                            if (r.getHeader() != null) {
                                //TODO: Criar exception
                                throw new IllegalArgumentException("Duplicated header tag");
                            }
                            LogiqxHeader header = new LogiqxHeader();
                            r.setHeader(header);
                            NodeList nl = n.getChildNodes();
                            for (int j = 0; j < nl.getLength(); j++) {
                                Node n1 = nl.item(j);
                                switch (n1.getNodeName()) {
                                    case "#text":
                                        break;
                                    case "clrmamepro":
                                    case "romcenter":
                                        ReflectionUtil.setValueByAttributes(header, n1.getAttributes());
                                        break;
                                    default:
                                        ReflectionUtil.setValueByNodeContent(header, n1);
                                }
                            }
                            break;
                        case "game":
                            LogiqxGame game = new LogiqxGame();
                            ReflectionUtil.setValueByAttributes(game, n.getAttributes());
                            nl = n.getChildNodes();
                            for (int j = 0; j < nl.getLength(); j++) {
                                Node n1 = nl.item(j);
                                switch (n1.getNodeName().trim()) {
                                    case "#text":
                                        break;
                                    case "release":
                                        LogiqxRelease release = new LogiqxRelease();
                                        ReflectionUtil.setValueByAttributes(release, n1.getAttributes());
                                        game.addRelease(release);
                                        break;
                                    case "biosset":
                                        LogiqxBiosset biosset = new LogiqxBiosset();
                                        ReflectionUtil.setValueByAttributes(biosset, n1.getAttributes());
                                        game.addBiosset(biosset);
                                        break;
                                    case "rom":
                                        LogiqxRom rom = new LogiqxRom();
                                        ReflectionUtil.setValueByAttributes(rom, n1.getAttributes());
                                        game.addRom(rom);
                                        break;
                                    case "disk":
                                        LogiqxDisk disk = new LogiqxDisk();
                                        ReflectionUtil.setValueByAttributes(disk, n1.getAttributes());
                                        game.addDisk(disk);
                                        break;
                                    case "sample":
                                        LogiqxSample sample = new LogiqxSample();
                                        ReflectionUtil.setValueByAttributes(sample, n1.getAttributes());
                                        game.addSample(sample);
                                        break;
                                    case "archive":
                                        LogiqxArchive archive = new LogiqxArchive();
                                        ReflectionUtil.setValueByAttributes(archive, n1.getAttributes());
                                        game.addArchive(archive);
                                        break;
                                    default:
                                        ReflectionUtil.setValueByNodeContent(game, n1);
                                }
                            }
                            if (r.getGames().contains(game)) {
                                for (LogiqxGame lg : r.getGames()) {
                                    if (lg.getName().equals(game.getName())) {
                                        for (LogiqxRom lr : game.getRoms()) {
                                            lg.addRom(lr);
                                        }
                                    }
                                }
                            } else {
                                r.addGame(game);
                            }
                            break;
                        default:
                            throw new UnknownTagException(n.getNodeName());
                    }
                }
            }
        }
        return r;
    }
}
