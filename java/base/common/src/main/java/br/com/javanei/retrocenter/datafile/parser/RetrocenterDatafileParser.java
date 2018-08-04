package br.com.javanei.retrocenter.datafile.parser;

import br.com.javanei.retrocenter.common.UnknownDatafileFormatException;
import br.com.javanei.retrocenter.datafile.common.Datafile;
import br.com.javanei.retrocenter.datafile.common.DatafileArtifact;
import br.com.javanei.retrocenter.datafile.common.DatafileArtifactFile;
import br.com.javanei.retrocenter.datafile.common.Parser;
import br.com.javanei.retrocenter.datafile.common.Release;
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

public class RetrocenterDatafileParser implements Parser {
    private static final Logger LOG = LoggerFactory.getLogger(RetrocenterDatafileParser.class);

    public Datafile parse(File file) throws UnknownDatafileFormatException, IOException {
        LOG.info("parse(" + file + ")");
        try (FileInputStream is = new FileInputStream(file)) {
            return parse(is);
        }
    }

    public Datafile parse(InputStream is) throws UnknownDatafileFormatException, IOException {
        LOG.info("parse(is)");
        Datafile r = new Datafile();

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
            if (datafile.getNodeName().equals("retrocenter")) {
                ReflectionUtil.setValueByAttributes(r, datafile.getAttributes());
                for (int i = 0; i < datafile.getChildNodes().getLength(); i++) {
                    Node n = datafile.getChildNodes().item(i);
                    if (n.getNodeName().equals("#text")) {
                        continue;
                    }
                    switch (n.getNodeName()) {
                        case "artifact":
                            DatafileArtifact game = new DatafileArtifact();
                            ReflectionUtil.setValueByAttributes(game, n.getAttributes());
                            NodeList nl = n.getChildNodes();
                            for (int j = 0; j < nl.getLength(); j++) {
                                Node n1 = nl.item(j);
                                switch (n1.getNodeName().trim()) {
                                    case "#text":
                                        break;
                                    case "release":
                                        Release release = new Release();
                                        ReflectionUtil.setValueByAttributes(release, n1.getAttributes());
                                        game.addRelease(release);
                                        break;
                                    case "file":
                                        DatafileArtifactFile rom = new DatafileArtifactFile();
                                        ReflectionUtil.setValueByAttributes(rom, n1.getAttributes());
                                        NodeList nfile = n1.getChildNodes();
                                        for (int k = 0; k < nfile.getLength(); k++) {
                                            Node k1 = nfile.item(k);
                                            switch (k1.getNodeName().trim()) {
                                                case "#text":
                                                    break;
                                                default:
                                                    ReflectionUtil.setValueByNodeContent(rom, k1);
                                            }
                                        }
                                        game.addFile(rom);
                                        break;
                                    default:
                                        ReflectionUtil.setValueByNodeContent(game, n1);
                                }
                            }
                            r.addArtifact(game);
                            break;
                        default:
                            ReflectionUtil.setValueByNodeContent(r, n);
                    }
                }
            }
        }
        return r;
    }
}
