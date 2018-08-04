package br.com.javanei.retrocenter.datafile.parser;

import br.com.javanei.retrocenter.common.UnknownDatafileFormatException;
import br.com.javanei.retrocenter.datafile.clrmamepro.parser.CMProParser;
import br.com.javanei.retrocenter.datafile.common.DatafileObject;
import br.com.javanei.retrocenter.datafile.common.Parser;
import br.com.javanei.retrocenter.datafile.hyperlist.parser.HyperListParser;
import br.com.javanei.retrocenter.datafile.logiqx.parser.LogiqxParser;
import br.com.javanei.retrocenter.datafile.mame.parser.MameParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class DatafileParser implements Parser {
    private static final Logger LOG = LoggerFactory.getLogger(DatafileParser.class);

    public DatafileObject parse(File file) throws UnknownDatafileFormatException, IOException {
        LOG.info("parse(" + file + ")");
        try (FileInputStream is = new FileInputStream(file)) {
            return parse(is);
        }
    }

    public DatafileObject parse(InputStream is) throws UnknownDatafileFormatException, IOException {
        LOG.info("parse(is)");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] b = new byte[8192];
        int size = is.read(b);
        String s = new String(b, 0, size, Charset.forName("UTF-8"));
        Parser parser = null;
        while (size > 0) {
            os.write(b, 0, size);
            if (parser == null) {
                if (s.contains("<retrocenter")) {
                    parser = new RetrocenterDatafileParser();
                    LOG.info("parser class: " + parser.getClass());
                } else if (s.contains("<mame ")) {
                    parser = new MameParser();
                    LOG.info("parser class: " + parser.getClass());
                } else if (s.contains("clrmamepro (")) {
                    parser = new CMProParser();
                    LOG.info("parser class: " + parser.getClass());
                } else if (s.contains("<menu>") && s.contains("<listname>")) {
                    parser = new HyperListParser();
                    LOG.info("parser class: " + parser.getClass());
                } else if (s.contains("logiqx") || s.contains("<datafile")) {
                    parser = new LogiqxParser();
                    LOG.info("parser class: " + parser.getClass());
                }
            }
            size = is.read(b);
        }
        if (parser == null) {
            throw new UnknownDatafileFormatException();
        }

        return parser.parse(new ByteArrayInputStream(os.toByteArray()));
    }
}
