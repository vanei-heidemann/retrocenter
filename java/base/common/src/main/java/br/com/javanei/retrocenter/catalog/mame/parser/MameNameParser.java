package br.com.javanei.retrocenter.catalog.mame.parser;


import br.com.javanei.retrocenter.catalog.common.TagValue;
import br.com.javanei.retrocenter.catalog.mame.MameGame;
import br.com.javanei.retrocenter.catalog.mame.flags.MameBootleg;
import br.com.javanei.retrocenter.catalog.mame.flags.MamePrototype;
import br.com.javanei.retrocenter.catalog.mame.flags.MameRegionEnum;
import br.com.javanei.retrocenter.catalog.mame.flags.MameVersion;

import java.util.List;

public class MameNameParser {
    public MameGame parseName(String name) {
        MameGame game = new MameGame(name);

        String s = name;

        if (name.matches(".*(IP\\d\\d\\d\\d).*")) {
            String[] ss = name.split("-");
            game.setMainName(ss[0].trim());
            for (int i = 1; i < ss.length; i++) {
                String s1 = ss[i].trim();
                List<MameRegionEnum> regions = MameRegionEnum.fromName(s1);
                if (regions != null) {
                    game.setRegions(MameRegionEnum.toListString(regions));
                } else {
                    game.addComplement(s1);
                }
            }
            return game;
        }

        List<TagValue> tags = TagValue.parseNameInTags(s);

        StringBuilder mainName = new StringBuilder();
        mainName.append(tags.get(0).getValue());
        int pos = 1;
        while (pos < tags.size()) {
            TagValue tag = tags.get(pos);
            for (String t : tag.getValue().trim().split(",")) {
                t = t.trim();
                if (t.isEmpty()) continue;
                if (t.matches("\\d\\d\\d\\d\\d\\d\\dV")
                        || t.matches("\\d\\d\\d\\d\\d\\d\\d")
                        || t.matches("\\d\\d\\w\\d\\d\\d\\d\\d")
                        || t.matches("\\d\\d\\d\\d\\d\\d")
                        || t.matches("\\d\\d\\d\\d\\d\\dx\\d")
                        || t.matches("\\d\\w\\d\\d\\d\\d\\d\\d")
                        || t.matches("\\d\\w\\w\\w\\w\\d\\d\\d")
                        || t.matches("\\d\\d\\d\\d\\-\\d\\d")
                        || t.matches("with .*")
                        || t.matches("\\d [Pp]layer[\\w\\s]*")
                ) {
                    //O que é isso?
                    //TODO: game.addComplement(t);
                    continue;
                }
                if (t.matches("\\d\\d\\d\\d\\d\\d .*")) {
                    String[] ss = t.split(" ");
                    if (ss.length == 2) {
                        List<MameRegionEnum> regions = MameRegionEnum.fromName(ss[1]);
                        if (regions != null) {
                            //TODO: game.addComplement(ss[0]);
                            game.setRegions(MameRegionEnum.toListString(regions));
                        } else {
                            //game.addComplement(ss[0]);
                            //game.addComplement(ss[1]);
                            //TODO: game.addComplement(t);
                        }
                    } else {
                        game.addComplement(t);
                    }
                    continue;
                }
                if (t.matches("\\d\\.\\d\\ \\w*")
                        || t.matches("\\d\\.\\d\\d\\ \\w*")
                        || t.matches("\\d\\d\\.\\d\\d\\ \\w*")) {
                    String[] ss = t.split(" ");
                    if (ss.length == 2) {
                        game.setVersion(ss[0]);
                        List<MameRegionEnum> regions = MameRegionEnum.fromName(ss[1]);
                        if (regions != null) {
                            //TODO: game.addComplement(ss[0]);
                            game.setRegions(MameRegionEnum.toListString(regions));
                        } else {
                            game.addComplement(ss[1]);
                        }
                    } else {
                        game.addComplement(t);
                    }
                    continue;
                }

                List<MameRegionEnum> region = MameRegionEnum.fromName(t.trim());
                if (region != null) {
                    for (MameRegionEnum r : region) {
                        game.addRegion(r.getName());
                    }
                    continue;
                }
                String prototype = MamePrototype.parsePrototype(t.toString());
                if (prototype != null) {
                    game.setPrototype(prototype);
                    continue;
                }
                String version = MameVersion.parseVersion(t.trim());
                if (version != null) {
                    game.setVersion(version);
                    continue;
                }
                String bootleg = MameBootleg.parseBootleg(t.trim());
                if (bootleg != null) {
                    game.setBootleg(bootleg);
                }
                game.addComplement(t.trim());
            }

            pos++;
        }
        game.setMainName(mainName.toString());

        return game;
    }
}
