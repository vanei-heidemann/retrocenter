package br.com.javanei.retrocenter.catalog.nointro.parser;

public class NoIntroRegionNotIdentifiedException extends Exception {
    NoIntroRegionNotIdentifiedException(String game) {
        super("Region not identified from game name: " + game);
    }
}
