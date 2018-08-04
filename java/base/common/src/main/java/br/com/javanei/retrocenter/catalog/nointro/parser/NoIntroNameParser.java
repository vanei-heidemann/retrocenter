package br.com.javanei.retrocenter.catalog.nointro.parser;

import br.com.javanei.retrocenter.catalog.common.TagValue;
import br.com.javanei.retrocenter.catalog.nointro.NoIntroGame;
import br.com.javanei.retrocenter.catalog.nointro.flags.NoIntroAlternate;
import br.com.javanei.retrocenter.catalog.nointro.flags.NoIntroCompilation;
import br.com.javanei.retrocenter.catalog.nointro.flags.NoIntroDevStatus;
import br.com.javanei.retrocenter.catalog.nointro.flags.NoIntroLanguageEnum;
import br.com.javanei.retrocenter.catalog.nointro.flags.NoIntroLoaderEnum;
import br.com.javanei.retrocenter.catalog.nointro.flags.NoIntroProtectionEnum;
import br.com.javanei.retrocenter.catalog.nointro.flags.NoIntroRegionEnum;
import br.com.javanei.retrocenter.catalog.nointro.flags.NoIntroReleaseDate;
import br.com.javanei.retrocenter.catalog.nointro.flags.NoIntroSystemEnum;
import br.com.javanei.retrocenter.catalog.nointro.flags.NoIntroVersion;
import br.com.javanei.retrocenter.catalog.nointro.flags.NoIntroVideoEnum;

import java.util.List;

public class NoIntroNameParser {
    private static void parseUnknowTag(NoIntroGame game, String tag) {
        game.addComplement(tag);
        /*
        if (tag.equalsIgnoreCase("program")
                || tag.toLowerCase().startsWith("addon")
                || tag.toLowerCase().startsWith("budget")
                || tag.toLowerCase().endsWith("sides")
                || tag.toLowerCase().startsWith("side")
                || tag.toUpperCase().startsWith("PAL")
                || tag.toUpperCase().startsWith("NTSC")
                || tag.equalsIgnoreCase("Bonus Game")
                || tag.equalsIgnoreCase("No Title Screen")
                || tag.equalsIgnoreCase("Data Update Disk")
                || tag.equalsIgnoreCase("HD Version")
                || tag.equalsIgnoreCase("1MB")
                || tag.equalsIgnoreCase("8k")
                || tag.equalsIgnoreCase("16k")
                || tag.equalsIgnoreCase("32k")
                || tag.equalsIgnoreCase("64k")
                || tag.equalsIgnoreCase("128k")
                || tag.equalsIgnoreCase("512KB")
                || tag.equalsIgnoreCase("Enhanced")
                ) {
            // Ignore
            return;
        }
        if (platform.toLowerCase().contains("nintendo")) {
            if (tag.toLowerCase().startsWith("gamecube edition")
                    || tag.toLowerCase().startsWith("aladdin compact cartridge")) {
                return;
            }
        }
        if (platform.toLowerCase().contains("satellaview")) {
            if (tag.equals("BS")
                    || tag.equals("BS SoundLink")) {
                return;
            }
        }
        if (tag.toLowerCase().contains("mastered with virus")) {
            game.setVersion("Mastered with virus");
            return;
        }
        if (platform.toLowerCase().contains("amiga")) {
            if (tag.toLowerCase().startsWith("coverdisk")
                    || tag.equals("Amiga + ST")
                    || tag.equals("Amiga + PC")
                    || tag.equals("AmigaDOS")
                    || tag.equals("HLS")
                    || tag.equals("SCI")
                    || tag.equals("CDS")
                    || tag.startsWith("UK-B")
                    ) {
                return;
            }
        }
        if (platform.toLowerCase().contains("gameboy") || platform.toLowerCase().contains("game boy")) {
            if (tag.equals("GBDSO")
                    || tag.equals("GBD1")
                    || tag.toLowerCase().startsWith("sgb enhanced")
                    || tag.equals("Rumble Version")
                    || tag.equals("NP")
                    ) {
                return;
            }
        }
        if (platform.toLowerCase().contains("sega")) {
            if (tag.equals("J-Cart")
                    || tag.equals("SegaNet")
                    || tag.equals("Sega Channel")
                    || tag.equals("Sega Smash Pack")
                    || tag.equals("NGen")
                    || tag.equals("Virtual Console")
                    ) {
                return;
            }
        }
        if (!game.getAudited()) {
            GameProcessingMessage msg = new GameProcessingMessage(GameProcessingMessage.Type.WARN, "Unknown string in '" + game.getName() + "' ===> '" + tag + "'");
            this.messages.add(msg);
        }
         */
    }

    public NoIntroGame parseName(String name) throws NoIntroRegionNotIdentifiedException {
        NoIntroGame game = new NoIntroGame(name);

        String s = name;
        List<TagValue> tags = TagValue.parseNameInTags(s);

        StringBuilder mainName = new StringBuilder();

        // A primeira parte é o nome da ROM (exceto quando é BIOS....)
        int pos = 0;
        // Se for BIOS, a primeira tag é BIOS
        if (tags.get(0).getValue().equals("BIOS")) {
            game.setIsBios(Boolean.TRUE);
            pos++;
        }
        mainName.append(tags.get(pos++));


        // Depois do nome, sempre vem a região (Bom, o padrao diz isso, mas o HyperList nao usa a regiao)
        while (pos < tags.size()) {
            TagValue tag = tags.get(pos);
            List<NoIntroRegionEnum> regions = NoIntroRegionEnum.fromName(tag.getValue());
            if (regions == null) {
                mainName.append(" ").append(tag.toString());
            } else {
                game.setRegions(NoIntroRegionEnum.toListString(regions));
                pos++;
                break;
            }
            pos++;
        }
        game.setMainName(mainName.toString());

        if (game.getRegions().isEmpty()) {
            throw new NoIntroRegionNotIdentifiedException(game.getName());
        }


        while (pos < tags.size()) {
            TagValue tag = tags.get(pos);
            validate_block:
            {
                // Identifica a protection
                NoIntroProtectionEnum protection = NoIntroProtectionEnum.fromName(tag.getValue());
                if (protection != null) {
                    game.setProtection(protection.getName());
                    break validate_block;
                }

                // Identifica o Loader
                NoIntroLoaderEnum loader = NoIntroLoaderEnum.fromName(tag.getValue());
                if (loader != null) {
                    game.setLoader(loader.getName());
                    break validate_block;
                }

                // Identifica a linguagem
                if (game.getLanguages().isEmpty()) {
                    List<NoIntroLanguageEnum> langs = NoIntroLanguageEnum.fromName(tag.getValue());
                    if (langs != null) {
                        game.setLanguages(NoIntroLanguageEnum.toListString(langs));
                        break validate_block;
                    }
                }

                // Identifica se é uma ROM alternativa
                if (game.getAlternate() == null) {
                    String alternate = NoIntroAlternate.parseTag(tag.getValue());
                    if (alternate != null) {
                        game.setAlternate(alternate);
                        break validate_block;
                    }
                }

                // Verifica se é uma compilação
                if (game.getCompilation() == null) {
                    String compilation = NoIntroCompilation.parseTag(tag.getValue());
                    if (compilation != null) {
                        game.setCompilation(compilation);
                        break validate_block;
                    }
                }

                // Identifica uma data
                if (game.getReleaseDate() == null) {
                    String date = NoIntroReleaseDate.fromTag(tag.getValue());
                    if (date != null) {
                        game.setReleaseDate(date);
                        break validate_block;
                    }
                }

                // Identifica a versão
                if (game.getVersion() == null) {
                    String version = NoIntroVersion.parseTag(tag.getValue());
                    if (version != null) {
                        game.setVersion(version);
                        break validate_block;
                    }
                }

                // Identifica se e uma versão especial (Proto, Demo, Beta, Promo, Unl)
                List<NoIntroDevStatus> devStatus = NoIntroDevStatus.parseTag(tag.getValue());
                if (devStatus != null) {
                    for (NoIntroDevStatus status : devStatus) {
                        game.addDevStatus(status.getStatus().name(), status.toString());
                    }
                    break validate_block;
                }

                if (tag.getStartSeparator() == '[') {
                    if (tag.getValue().equals("b")) {
                        game.setBadDump("BadDump");
                        break validate_block;
                    }
                }

                if (game.getSystems() == null) {
                    List<NoIntroSystemEnum> systems = NoIntroSystemEnum.fromName(tag.getValue());
                    if (systems != null) {
                        game.setSystems(NoIntroSystemEnum.toListString(systems));
                        break validate_block;
                    }
                }

                if (game.getVideo() == null) {
                    NoIntroVideoEnum video = NoIntroVideoEnum.fromName(tag.getValue());
                    if (video != null) {
                        game.setVersion(video.getName());
                        break validate_block;
                    }
                }

                parseUnknowTag(game, tag.getValue());
            }
            pos++;
        }
        return game;
    }
}
