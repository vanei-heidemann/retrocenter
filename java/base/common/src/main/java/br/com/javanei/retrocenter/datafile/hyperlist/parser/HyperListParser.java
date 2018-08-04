package br.com.javanei.retrocenter.datafile.hyperlist.parser;

import br.com.javanei.retrocenter.common.UnknownDatafileFormatException;
import br.com.javanei.retrocenter.common.UnknownTagException;
import br.com.javanei.retrocenter.datafile.common.Parser;
import br.com.javanei.retrocenter.datafile.hyperlist.HyperListGame;
import br.com.javanei.retrocenter.datafile.hyperlist.HyperListHeader;
import br.com.javanei.retrocenter.datafile.hyperlist.HyperListMenu;
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

public class HyperListParser implements Parser {
    private static final Logger LOG = LoggerFactory.getLogger(HyperListParser.class);

    @Override
    public HyperListMenu parse(File file) throws UnknownDatafileFormatException, IOException {
        LOG.info("parse(" + file + ")");
        try (FileInputStream is = new FileInputStream(file)) {
            return parse(is);
        }
    }

    @Override
    public HyperListMenu parse(InputStream is) throws UnknownDatafileFormatException, IOException {
        LOG.info("parse(is)");
        HyperListMenu r = new HyperListMenu();

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
            if (datafile.getNodeName().equals("menu")) {
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
                            HyperListHeader header = new HyperListHeader();
                            r.setHeader(header);
                            NodeList nl = n.getChildNodes();
                            for (int j = 0; j < nl.getLength(); j++) {
                                Node n1 = nl.item(j);
                                switch (n1.getNodeName()) {
                                    case "#text":
                                        break;
                                    default:
                                        ReflectionUtil.setValueByNodeContent(header, n1);
                                }
                            }
                            break;
                        case "game":
                            HyperListGame game = new HyperListGame();
                            ReflectionUtil.setValueByAttributes(game, n.getAttributes());
                            nl = n.getChildNodes();
                            for (int j = 0; j < nl.getLength(); j++) {
                                Node n1 = nl.item(j);
                                switch (n1.getNodeName().trim()) {
                                    case "#text":
                                        break;
                                    case "enabled":
                                        // Ignora
                                        break;
                                    default:
                                        ReflectionUtil.setValueByNodeContent(game, n1);
                                }
                            }
                            //Corrige alguns bugs no Datafile!!!
                            if (game.getName().equals("Million Shot (Shoot-'Em-Up)(IJI Team)")
                                    && game.getYear().equals("Shoot-'Em-Up")) {
                                //SHARP X68000
                                game.setYear("1993");
                            } else if (game.getName().equals("Chuka Taisen (Sharp - SPS)")
                                    && "Sharp - SPS".equals(game.getYear())) {
                                //SHARP X68000
                                game.setYear("1991");
                            } else if (game.getYear() != null) {
                                switch (game.getYear().trim()) {
                                    //Nintendo Super Game Boy
                                    case "1989 *special default palette":
                                    case "1995 *Custom SGB Music, palette changes disabled":
                                    case "1995 *Multiple Borders":
                                    case "1996 *Multiple Borders":
                                    case "1996 *Two Player Mode":
                                    case "1998 * Multiplayer up to 4 players, Custom SGB SFX":
                                    case "1994 *special border featuring the Chikkuns":
                                    case "1990 *special default palette":
                                    case "2000 *Multiple Borders":
                                    case "1991 *special default palette":
                                    case "1996 *Custom SGB Music":
                                    case "1997 *Multiple Borders":
                                    case "1998 *Multiple Borders":
                                    case "1999 *Multiple Borders":
                                    case "1995 *Two Player Mode":
                                    case "1997 *Two Player Mode, Uses SGB's internal sounds":
                                    case "1998 *Multiple Borders, Two Player Mode, Uses SGB's internal sounds":
                                    case "1992 *special default palette":
                                    case "1995 *Uses SGB's internal sounds":
                                    case "1993 *special default palette":
                                    case "1994 *Two Player Mode":
                                    case "1999 *Multiple borders, full SNES 16-bit version":
                                    case "1995 *Multiple Borders, SGB Two Player Mode":
                                    case "1993 *Multiple Borders":
                                    case "1994 *Multiplayer up to 4 players, Custom SGB SFX":
                                    case "1994 *special default palette":
                                    case "1994 *Multiple Borders":
                                    case "1998 *Custom SGB Music":
                                    case "1995 *SGB Two Player Mode":
                                    case "1999 *Multiple borders, full SNES 16-bit version with enhanced music and graphics":
                                        game.setYear(game.getYear().substring(0, 4));
                                        break;
                                }
                            }
                            r.addGame(game);
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
