package br.com.javanei.retrocenter.catalog.goodset.parser;


import br.com.javanei.retrocenter.catalog.common.TagValue;
import br.com.javanei.retrocenter.catalog.goodset.GoodSetGame;

import java.util.List;

public class GoodSetNameParser {
    public GoodSetGame parseName(String name) {
        GoodSetGame game = new GoodSetGame(name);

        String s = name;
        List<TagValue> tags = TagValue.parseNameInTags(s);

        StringBuilder mainName = new StringBuilder();
        mainName.append(tags.get(0).getValue());

        //https://en.wikipedia.org/wiki/GoodTools
        /*
(Unl)	Unlicensed
(PD)	Public domain, free software and freeware
         */

        return game;
    }
}
