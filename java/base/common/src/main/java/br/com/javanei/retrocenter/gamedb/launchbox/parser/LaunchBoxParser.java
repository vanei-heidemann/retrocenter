package br.com.javanei.retrocenter.gamedb.launchbox.parser;

import br.com.javanei.retrocenter.common.UnknownTagException;
import br.com.javanei.retrocenter.gamedb.launchbox.LBoxCompany;
import br.com.javanei.retrocenter.gamedb.launchbox.LBoxDatafile;
import br.com.javanei.retrocenter.gamedb.launchbox.LBoxGame;
import br.com.javanei.retrocenter.gamedb.launchbox.LBoxGameAlternateName;
import br.com.javanei.retrocenter.gamedb.launchbox.LBoxGameImage;
import br.com.javanei.retrocenter.gamedb.launchbox.LBoxGenre;
import br.com.javanei.retrocenter.gamedb.launchbox.LBoxPlatform;
import br.com.javanei.retrocenter.gamedb.launchbox.LBoxRegion;
import br.com.javanei.retrocenter.gamedb.launchbox.parser.metadata.LBoxMetadata;
import br.com.javanei.retrocenter.gamedb.launchbox.parser.metadata.LBoxMetadataAlternateName;
import br.com.javanei.retrocenter.gamedb.launchbox.parser.metadata.LBoxMetadataEmulator;
import br.com.javanei.retrocenter.gamedb.launchbox.parser.metadata.LBoxMetadataEmulatorPlatform;
import br.com.javanei.retrocenter.gamedb.launchbox.parser.metadata.LBoxMetadataGame;
import br.com.javanei.retrocenter.gamedb.launchbox.parser.metadata.LBoxMetadataGameAlternateName;
import br.com.javanei.retrocenter.gamedb.launchbox.parser.metadata.LBoxMetadataGameFileName;
import br.com.javanei.retrocenter.gamedb.launchbox.parser.metadata.LBoxMetadataGameImage;
import br.com.javanei.retrocenter.gamedb.launchbox.parser.metadata.LBoxMetadataPlatform;
import br.com.javanei.retrocenter.util.ReflectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LaunchBoxParser {
    private static final Logger LOG = LoggerFactory.getLogger(LaunchBoxParser.class);

    private List<String> invalidTags = new ArrayList<>();

    public static String adjustDate(String date) throws ParseException {
        if (date == null) {
            return null;
        }
        if (date.length() > 10) {
            date = date.substring(0, 10);
            //Só pra garantir que está no formato certo.
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.parse(date);
            if (date.endsWith("-01-01") || date.endsWith("-12-31")) {
                date = date.substring(0, 4);
            }
            return date;
        } else {
            throw new RuntimeException("Invalid date: " + date);
        }
    }

    public LBoxDatafile parse(InputStream isMetadata, InputStream isFiles) throws Exception {
        LOG.info("parse(isMetadata=" + isMetadata + ", isFiles=" + isFiles + ")");
        LBoxDatafile result = new LBoxDatafile();

        LBoxMetadata metadata = parseMetadata(isMetadata, isFiles);
        mergeData(result, metadata);
        return result;
    }

    public LBoxDatafile parse(File lbDir) throws Exception {
        LOG.info("parse(lbDir=" + lbDir.getAbsolutePath() + ")");
        LBoxDatafile result = new LBoxDatafile();

        LBoxMetadata metadata = parseMetadata(lbDir);
        mergeData(result, metadata);
        return result;
    }

    public LBoxMetadata parseMetadata(InputStream isMetadata, InputStream isFiles) throws Exception {
        LOG.info("parseMetadata(isMetadata=" + isMetadata + ", isFiles=" + isFiles + ")");
        LBoxMetadata metadata = new LBoxMetadata();
        if (isMetadata != null) {
            this.parseMetadata(metadata, isMetadata);
        }
        if (isFiles != null) {
            this.parseFiles(metadata, isFiles);
        }
        return metadata;
    }

    public LBoxMetadata parseMetadata(File lbDir) throws Exception {
        LOG.info("parseMetadata(lbDir=" + lbDir.getAbsolutePath() + ")");
        return parseMetadata(
                new FileInputStream(new File(lbDir, "Metadata/Metadata.xml")),
                new FileInputStream(new File(lbDir, "Metadata/Files.xml")));
    }

    private void parseFiles(LBoxMetadata metadata, InputStream is) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringComments(true);
        factory.setValidating(false);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(is);
        NodeList nodes = doc.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node nLB = nodes.item(i);
            if (nLB.getChildNodes().getLength() == 0)
                continue;
            NodeList lbChildList = nLB.getChildNodes();

            for (int j = 0; j < lbChildList.getLength(); j++) {
                Node gameNode = lbChildList.item(j);
                String nodeName = gameNode.getNodeName();
                switch (nodeName) {
                    case "#text":
                        break;
                    case "File":
                        LBoxMetadataGameFileName gameFile = parseFile(gameNode);
                        if (!metadata.addGameFile(gameFile)) {
                            //TODO: System.err.println("EEEE: Game File ja existe:\n" + gameFile);
                        }
                        break;
                    default:
                        throw new UnknownTagException(nodeName);
                }
            }
        }
    }

    private void parseMetadata(LBoxMetadata metadata, InputStream is) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringComments(true);
        factory.setValidating(false);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(is);
        NodeList nodes = doc.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node nLB = nodes.item(i);
            if (nLB.getChildNodes().getLength() == 0)
                continue;
            NodeList lbChildList = nLB.getChildNodes();

            for (int j = 0; j < lbChildList.getLength(); j++) {
                Node gameNode = lbChildList.item(j);
                String nodeName = gameNode.getNodeName();
                switch (nodeName) {
                    case "#text":
                        break;
                    case "Game":
                        LBoxMetadataGame game = parseGame(gameNode);
                        if (!metadata.addGame(game)) {
                            System.err.println("EEEE: Game ja existe:\n" + game);
                        }
                        break;
                    case "Platform":
                        LBoxMetadataPlatform platform = parsePlatform(gameNode);
                        if (!metadata.addPlatform(platform)) {
                            System.err.println("EEEE: Plataforma ja existe:\n" + platform);
                        }
                        break;
                    case "PlatformAlternateName":
                        LBoxMetadataAlternateName alternateName = parseAlternateName(gameNode);
                        if (!metadata.addAlternateNames(alternateName)) {
                            System.err.println("EEEE: Platform alternate name ja existe:\n" + alternateName);
                        }
                        break;
                    case "Emulator":
                        LBoxMetadataEmulator emulator = parseEmulator(gameNode);
                        if (!metadata.addEmulator(emulator)) {
                            System.err.println("EEEE: Emulator ja existe: " + emulator);
                        }
                        break;
                    case "EmulatorPlatform":
                        LBoxMetadataEmulatorPlatform emulatorPlatform = parseEmulatorPlatform(gameNode);
                        if (!metadata.addEmulatorPlatform(emulatorPlatform)) {
                            System.err.println("EEEE: Emulator Platform ja existe:\n" + emulatorPlatform);
                        }
                        break;
                    case "GameImage":
                        LBoxMetadataGameImage gameImage = parseGameImage(gameNode);
                        if (gameImage.getRegion() != null && !gameImage.getRegion().isEmpty()) {
                            metadata.addRegion(gameImage.getRegion());
                        }
                        if (!metadata.addGameImage(gameImage)) {
                            System.err.println("EEEE: Game Image ja existe:\n" + gameImage);
                        }
                        break;
                    case "GameAlternateName":
                        LBoxMetadataGameAlternateName gameAlternateName = parseGameAlternateName(gameNode);
                        if (gameAlternateName.getRegion() != null && !gameAlternateName.getRegion().isEmpty()) {
                            metadata.addRegion(gameAlternateName.getRegion());
                        }
                        if (!metadata.addGameAlternateName(gameAlternateName)) {
                            System.err.println("EEEE: Game Alternate Name ja existe:\n" + gameAlternateName);
                        }
                        break;
                    default:
                        throw new UnknownTagException(nodeName);
                }
            }
        }
    }

    private LBoxMetadataPlatform parsePlatform(Node pNode) throws Exception {
        LBoxMetadataPlatform result = new LBoxMetadataPlatform();
        NodeList attrs = pNode.getChildNodes();
        if (attrs != null && attrs.getLength() > 0) {
            for (int i = 0; i < attrs.getLength(); i++) {
                Node node = attrs.item(i);
                if (node != null && node.getNodeName() != null) {
                    switch (node.getNodeName()) {
                        case "#text":
                            // Ignora
                            break;
                        default:
                            try {
                                ReflectionUtil.setValueByNodeContent(result, node);
                            } catch (UnknownTagException ex) {
                                if (!this.invalidTags.contains("Platform/" + node.getNodeName())) {
                                    this.invalidTags.add("Platform/" + node.getNodeName());
                                    System.err.println("Tag não tratada: Platform/" + node.getNodeName());
                                }
                            }
                            break;
                    }
                }
            }
        }
        return result;
    }

    private LBoxMetadataAlternateName parseAlternateName(Node pNode) {
        LBoxMetadataAlternateName result = new LBoxMetadataAlternateName();
        NodeList attrs = pNode.getChildNodes();
        if (attrs != null && attrs.getLength() > 0) {
            for (int i = 0; i < attrs.getLength(); i++) {
                Node node = attrs.item(i);
                if (node != null && node.getNodeName() != null) {
                    switch (node.getNodeName()) {
                        case "#text":
                            // Ignora
                            break;
                        default:
                            try {
                                ReflectionUtil.setValueByNodeContent(result, node);
                            } catch (UnknownTagException ex) {
                                if (!this.invalidTags.contains("AlternateName/" + node.getNodeName())) {
                                    this.invalidTags.add("AlternateName/" + node.getNodeName());
                                    System.err.println("Tag não tratada: AlternateName/" + node.getNodeName());
                                }
                            }
                            break;
                    }
                }
            }
        }
        return result;
    }

    private LBoxMetadataEmulator parseEmulator(Node pNode) {
        LBoxMetadataEmulator result = new LBoxMetadataEmulator();
        NodeList attrs = pNode.getChildNodes();
        if (attrs != null && attrs.getLength() > 0) {
            for (int i = 0; i < attrs.getLength(); i++) {
                Node node = attrs.item(i);
                if (node != null && node.getNodeName() != null) {
                    switch (node.getNodeName()) {
                        case "#text":
                            // Ignora
                            break;
                        default:
                            try {
                                ReflectionUtil.setValueByNodeContent(result, node);
                            } catch (UnknownTagException ex) {
                                if (!this.invalidTags.contains("Emulator/" + node.getNodeName())) {
                                    this.invalidTags.add("Emulator/" + node.getNodeName());
                                    System.err.println("Tag não tratada: Emulator/" + node.getNodeName());
                                }
                            }
                            break;
                    }
                }
            }
        }
        return result;
    }

    private LBoxMetadataEmulatorPlatform parseEmulatorPlatform(Node pNode) {
        LBoxMetadataEmulatorPlatform result = new LBoxMetadataEmulatorPlatform();
        NodeList attrs = pNode.getChildNodes();
        if (attrs != null && attrs.getLength() > 0) {
            for (int i = 0; i < attrs.getLength(); i++) {
                Node node = attrs.item(i);
                if (node != null && node.getNodeName() != null) {
                    switch (node.getNodeName()) {
                        case "#text":
                            // Ignora
                            break;
                        default:
                            try {
                                ReflectionUtil.setValueByNodeContent(result, node);
                            } catch (UnknownTagException ex) {
                                if (!this.invalidTags.contains("EmulatorPlatform/" + node.getNodeName())) {
                                    this.invalidTags.add("EmulatorPlatform/" + node.getNodeName());
                                    System.err.println("Tag não tratada: EmulatorPlatform/" + node.getNodeName());
                                }
                            }
                            break;
                    }
                }
            }
        }
        return result;
    }

    private LBoxMetadataGameImage parseGameImage(Node pNode) {
        LBoxMetadataGameImage result = new LBoxMetadataGameImage();
        NodeList attrs = pNode.getChildNodes();
        if (attrs != null && attrs.getLength() > 0) {
            for (int i = 0; i < attrs.getLength(); i++) {
                Node node = attrs.item(i);
                if (node != null && node.getNodeName() != null) {
                    switch (node.getNodeName()) {
                        case "#text":
                            // Ignora
                            break;
                        case "CRC32":
                            result.setCrc32(node.getTextContent().trim());
                            break;
                        default:
                            try {
                                ReflectionUtil.setValueByNodeContent(result, node);
                            } catch (UnknownTagException ex) {
                                if (!this.invalidTags.contains("GameImage/" + node.getNodeName())) {
                                    this.invalidTags.add("GameImage/" + node.getNodeName());
                                    System.err.println("Tag não tratada: GameImage/" + node.getNodeName());
                                }
                            }
                            break;
                    }
                }
            }
        }
        return result;
    }

    private LBoxMetadataGameFileName parseFile(Node gameNode) throws Exception {
        LBoxMetadataGameFileName result = new LBoxMetadataGameFileName();
        NodeList attrs = gameNode.getChildNodes();
        if (attrs != null && attrs.getLength() > 0) {
            for (int i = 0; i < attrs.getLength(); i++) {
                Node node = attrs.item(i);
                if (node != null && node.getNodeName() != null) {
                    switch (node.getNodeName()) {
                        case "#text":
                            // Ignora
                            break;
                        default:
                            try {
                                ReflectionUtil.setValueByNodeContent(result, node);
                            } catch (UnknownTagException ex) {
                                if (!this.invalidTags.contains("File/" + node.getNodeName())) {
                                    this.invalidTags.add("File/" + node.getNodeName());
                                    System.err.println("Tag não tratada: File/" + node.getNodeName());
                                }
                            }
                            break;
                    }
                }
            }
        }
        return result;
    }

    private LBoxMetadataGame parseGame(Node gameNode) throws Exception {
        LBoxMetadataGame result = new LBoxMetadataGame();
        NodeList attrs = gameNode.getChildNodes();
        if (attrs != null && attrs.getLength() > 0) {
            for (int i = 0; i < attrs.getLength(); i++) {
                Node node = attrs.item(i);
                if (node != null && node.getNodeName() != null) {
                    switch (node.getNodeName()) {
                        case "#text":
                            // Ignora
                            break;
                        case "ESRB":
                            result.setEsrb(node.getTextContent().trim());
                            break;
                        case "DOS":
                            result.setDos(node.getTextContent().trim());
                            break;
                        default:
                            try {
                                ReflectionUtil.setValueByNodeContent(result, node);
                            } catch (UnknownTagException ex) {
                                if (!this.invalidTags.contains("Game/" + node.getNodeName())) {
                                    this.invalidTags.add("Game/" + node.getNodeName());
                                    System.err.println("Tag não tratada: Game/" + node.getNodeName());
                                }
                            }
                            break;
                    }
                }
            }
        }
        return result;
    }

    private LBoxMetadataGameAlternateName parseGameAlternateName(Node pNode) {
        LBoxMetadataGameAlternateName result = new LBoxMetadataGameAlternateName();
        NodeList attrs = pNode.getChildNodes();
        if (attrs != null && attrs.getLength() > 0) {
            for (int i = 0; i < attrs.getLength(); i++) {
                Node node = attrs.item(i);
                if (node != null && node.getNodeName() != null) {
                    switch (node.getNodeName()) {
                        case "#text":
                            // Ignora
                            break;
                        default:
                            try {
                                ReflectionUtil.setValueByNodeContent(result, node);
                            } catch (UnknownTagException ex) {
                                throw ex;
                            }
                            break;
                    }
                }
            }
        }
        return result;
    }

    private void mergeData(LBoxDatafile result, LBoxMetadata metadata) {
        // Prepara lista de alternate names
        Map<String, Set<LBoxMetadataAlternateName>> alternateNames = new HashMap<>();
        for (LBoxMetadataAlternateName an : metadata.getAlternateNames()) {
            Set<LBoxMetadataAlternateName> ans = alternateNames.get(an.getName());
            if (ans == null) {
                ans = new HashSet<>();
                ans.add(an);
                alternateNames.put(an.getName(), ans);
            } else {
                ans.add(an);
            }
        }

        Map<String, LBoxPlatform> platforms = new HashMap<>();
        Map<String, LBoxCompany> companies = new HashMap<>();
        Map<String, LBoxRegion> regions = new HashMap<>();
        Map<String, LBoxGenre> genres = new HashMap<>();
        Map<String, LBoxGame> gamesByDatabaseid = new HashMap<>();
        Map<String, LBoxGame> gamesByPlatformAndName = new HashMap<>();
        Map<String, LBoxMetadataGameFileName> gameFiles = new HashMap<>();
        Map<String, List<LBoxMetadataGameAlternateName>> gameAlternateNames = new HashMap<>();

        for (LBoxMetadataGameAlternateName gameAltName : metadata.getGameAlternateNames()) {
            List<LBoxMetadataGameAlternateName> l = gameAlternateNames.get(gameAltName.getDatabaseID());
            if (l == null) {
                l = new ArrayList<>();
                gameAlternateNames.put(gameAltName.getDatabaseID(), l);
            }
            l.add(gameAltName);
            if (gameAltName.getRegion() != null && !gameAltName.getRegion().isEmpty()) {
                LBoxRegion region = new LBoxRegion(gameAltName.getRegion());
                result.addRegion(region);
                regions.put(region.getName(), region);
            }
        }

        for (LBoxMetadataPlatform platform : metadata.getPlatforms()) {
            LBoxPlatform p = new LBoxPlatform(platform.getName());
            p.setReleaseDate(platform.getReleaseDate());
            if (platform.getDeveloper() != null && !platform.getDeveloper().isEmpty()) {
                LBoxCompany company = companies.get(platform.getDeveloper());
                if (company == null) {
                    company = new LBoxCompany(platform.getDeveloper());
                    companies.put(company.getName(), company);
                }
                p.setDeveloper(company);
            }
            if (platform.getManufacturer() != null && !platform.getManufacturer().isEmpty()) {
                LBoxCompany company = companies.get(platform.getManufacturer());
                if (company == null) {
                    company = new LBoxCompany(platform.getManufacturer());
                    companies.put(company.getName(), company);
                }
                p.setManufacturer(company);
            }
            Set<LBoxMetadataAlternateName> altNames = alternateNames.get(platform.getName());
            if (altNames != null && !altNames.isEmpty()) {
                for (LBoxMetadataAlternateName an : altNames) {
                    p.addAlternateName(an.getAlternate());
                }
            }
            result.addPlatform(p);
            platforms.put(p.getName(), p);
        }

        for (LBoxMetadataGame game : metadata.getGames()) {
            LBoxGame g = new LBoxGame(game.getDatabaseID(), game.getName());
            g.setReleaseDate(game.getReleaseDate());
            g.setReleaseYear(game.getReleaseYear());
            g.setPlatform(platforms.get(game.getPlatform()));
            if (game.getDeveloper() != null && !game.getDeveloper().isEmpty()) {
                LBoxCompany company = companies.get(game.getDeveloper());
                if (company == null) {
                    company = new LBoxCompany(game.getDeveloper());
                    companies.put(company.getName(), company);
                }
                g.setDeveloper(company);
            }
            if (game.getPublisher() != null && !game.getPublisher().isEmpty()) {
                LBoxCompany company = companies.get(game.getPublisher());
                if (company == null) {
                    company = new LBoxCompany(game.getPublisher());
                    companies.put(company.getName(), company);
                }
                g.setPublisher(company);
            }
            for (String s : game.getGenres()) {
                LBoxGenre genre = genres.get(s);
                if (genre == null) {
                    genre = new LBoxGenre(s);
                    genres.put(genre.getName(), genre);
                    result.addGenre(genre);
                }
                g.addGenre(genre);
            }
            List<LBoxMetadataGameAlternateName> gameAlternateNameList = gameAlternateNames.get(game.getDatabaseID());
            if (gameAlternateNameList != null) {
                for (LBoxMetadataGameAlternateName gameAlternateName : gameAlternateNameList) {
                    g.addAlternateName(new LBoxGameAlternateName(gameAlternateName.getAlternateName(), gameAlternateName.getRegion()));
                }
            }
            gamesByDatabaseid.put(g.getDatabaseID(), g);
            gamesByPlatformAndName.put(g.getPlatform().getName().toLowerCase() + "|" + g.getName().toLowerCase(), g);
            result.addGame(g);
        }

        for (LBoxMetadataGameImage gameImage : metadata.getGameImages()) {
            LBoxGameImage gi = new LBoxGameImage(gameImage.getFileName(), gameImage.getType(), gameImage.getRegion(), gameImage.getCrc32());
            LBoxGame game = gamesByDatabaseid.get(gameImage.getDatabaseID());
            if (game != null) {
                game.addImage(gi);
            } else {
                System.err.println("EEEE Image sem Game: " + gi);
            }
            if (gameImage.getRegion() != null && !gameImage.getRegion().isEmpty()) {
                LBoxRegion region = new LBoxRegion(gameImage.getRegion());
                result.addRegion(region);
                regions.put(region.getName(), region);
                if (game != null) {
                    game.addRegion(region);
                }
            }
        }

        for (LBoxMetadataGameFileName fileName : metadata.getGameFiles()) {
            gameFiles.put(fileName.getPlatform().toLowerCase() + "|" + fileName.getGameName().toLowerCase(), fileName);
        }
        /*
        List<String> unknowsWords = new ArrayList<>();
        for (LBoxMetadataGameFileName fileName : metadata.getGameFiles()) {
            LBoxGame game = gamesByPlatformAndName.get(fileName.getPlatform().toLowerCase() + "|" + fileName.getGameName().toLowerCase());
            if (game != null) {
                game.addFileName(fileName.getFileName());
            } else {
                String fname = fileName.getFileName().trim();
                while (fname.endsWith(")") && game == null) {
                    int ini = fname.lastIndexOf("(");
                    if (ini > 0) {
                        String s = fname.substring(ini + 1, fname.length() - 1);
                        fname = fname.substring(0, ini).trim();
                        if (s.equals("Unl")
                                || s.equals("Proto")) {
                            //TODO: Deveria tratar?
                            continue;
                        }
                        Set<LBoxRegion> tmpRegions = new HashSet<>();
                        for (String sr : s.split(",")) {
                            if (sr.equals("USA")) sr = "United States";
                            if (sr.equals("USSR")) sr = "Russia";
                            LBoxRegion region = regions.get(sr.trim());
                            if (region != null) {
                                tmpRegions.add(region);
                            } else {
                                tmpRegions = null;
                                break;
                            }
                        }
                        if (tmpRegions != null) {
                            game = gamesByPlatformAndName.get(fileName.getPlatform().toLowerCase() + "|" + fname.toLowerCase());
                            if (game != null) {
                                game.addFileName(fileName.getFileName());
                                for (LBoxRegion region : tmpRegions) {
                                    game.addRegion(region);
                                }
                                break;
                            }
                        } else {
                            if (s.matches("Side \\w")
                                    || s.matches("Disc \\w")
                                    || s.matches("Disc \\w of \\w")
                                    || s.matches("Disk \\w")
                                    || s.matches("Disk \\w of \\w")
                                    || s.matches("Part \\d")
                                    || s.matches("Disk \\w Side \\w")
                                    ) {
                                //Ignora no nome
                            } else if (s.matches("\\d\\d\\d\\d")
                                    || s.matches("\\d\\d\\d\\w")
                                    || s.matches("\\d\\d\\w\\w")) {
                                //TODO: Tratar ano?
                            } else {
                                LBoxCompany company = companies.get(fname);
                                if (company != null) {
                                    //TODO: Developer ou Publisher?
                                } else {
                                    if (!unknowsWords.contains(fname)) {
                                        unknowsWords.add(fname);
                                    }
                                    gameFiles.put(fileName.getPlatform().toLowerCase() + "|" + fileName.getGameName().toLowerCase(), fileName);
                                    //System.out.println("===== " + fileName.getFileName() + " -> " + s + "=" + fname + " -> " + fileName.getPlatform());
                                    break;
                                }
                            }
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        */

        for (LBoxGame game : result.getGames()) {
            if (game.getFileNames().isEmpty()) {
                LBoxMetadataGameFileName fn = gameFiles.get(game.getPlatform().getName().toLowerCase() + "|" + game.getName().toLowerCase());
                if (fn != null) {
                    game.addFileName(fn.getFileName());
                } else {
                    fn = gameFiles.get(game.getPlatform().getName().toLowerCase() + "|" + game.getName().toLowerCase().replace(": ", " - "));
                    if (fn != null) {
                        game.addFileName(fn.getFileName());
                    }
                }
            }
        }
        //System.out.println(gamesByDatabaseid.values());
/*
        System.out.println("Genres=" + genres.size() + " - " + genres.values());
        System.out.println("Regions=" + regions.size() + " - " + regions.values());
        System.out.println("Companies=" + companies.size() + " - " + companies.values());
        System.out.println("Platforms=" + platforms.size() + " - " + platforms.values());
        System.out.println("Unknown word=" + unknowsWords.size() + " - " + unknowsWords);
        System.out.println("Game Images=" + metadata.getGameImages().size());
        System.out.println("Games=" + metadata.getGames().size());
        System.out.println("Game Files=" + metadata.getGameFiles().size());
        System.out.println("Achou: " + achou);
        System.out.println("Nao Achou: " + naoAchou);
*/
    }
}
