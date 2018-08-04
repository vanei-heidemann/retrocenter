package br.com.javanei.retrocenter.datafile.mame.parser;

import br.com.javanei.retrocenter.common.UnknownDatafileFormatException;
import br.com.javanei.retrocenter.datafile.common.Parser;
import br.com.javanei.retrocenter.datafile.mame.Mame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MameParser implements Parser {
    private static final Logger LOG = LoggerFactory.getLogger(MameParser.class);

    public Mame parse(File file) throws UnknownDatafileFormatException, IOException {
        LOG.info("Parsing file: " + file.getAbsolutePath());
        try (FileInputStream is = new FileInputStream(file)) {
            return parse(is);
        }
    }

    public Mame parse(InputStream is) throws UnknownDatafileFormatException, IOException {
        LOG.info("parse(is)");
        try {
            Mame mame = MameSaxParserHandler.parse(is);
            LOG.info("parse - END");
            return mame;
        } catch (ParserConfigurationException | SAXException e) {
            throw new UnknownDatafileFormatException();
        }
    }
}
