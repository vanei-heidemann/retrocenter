package br.com.javanei.retrocenter.common;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LanguageEnumTest {
    private void fromName(String code, String name) {
        LanguageEnum language = LanguageEnum.fromName(name);
        assertEquals(name, language.getName());
        assertEquals(code, language.getCode());
    }

    private void fromCode(String code, String name) {
        List<LanguageEnum> list = LanguageEnum.fromCode(code);

        assertEquals(1, list.size());

        LanguageEnum language = list.get(0);
        assertEquals(name, language.getName());
        assertEquals(code, language.getCode());
    }

    @Test
    public void fromNameEnglish() {
        fromName("En", "English");
    }

    @Test
    public void fromCodeEn() {
        fromCode("En", "English");
    }

    @Test
    public void fromNameJapanese() {
        fromName("Ja", "Japanese");
    }

    @Test
    public void fromCodeJa() {
        fromCode("Ja", "Japanese");
    }

    @Test
    public void fromNameFrench() {
        fromName("Fr", "French");
    }

    @Test
    public void fromCodeFr() {
        fromCode("Fr", "French");
    }

    @Test
    public void fromNameGerman() {
        fromName("De", "German");
    }

    @Test
    public void fromCodeDe() {
        fromCode("De", "German");
    }

    @Test
    public void fromNameSpanish() {
        fromName("Es", "Spanish");
    }

    @Test
    public void fromCodeEs() {
        fromCode("Es", "Spanish");
    }

    @Test
    public void fromNameItalian() {
        fromName("It", "Italian");
    }

    @Test
    public void fromCodeIt() {
        fromCode("It", "Italian");
    }

    @Test
    public void fromNameDutch() {
        fromName("Nl", "Dutch");
    }

    @Test
    public void fromCodeNl() {
        fromCode("Nl", "Dutch");
    }

    @Test
    public void fromNamePortuguese() {
        fromName("Pt", "Portuguese");
    }

    @Test
    public void fromCodePt() {
        fromCode("Pt", "Portuguese");
    }

    @Test
    public void fromNameSwedish() {
        fromName("Sv", "Swedish");
    }

    @Test
    public void fromCodeSv() {
        fromCode("Sv", "Swedish");
    }

    @Test
    public void fromNameNorwegian() {
        fromName("No", "Norwegian");
    }

    @Test
    public void fromCodeNo() {
        fromCode("No", "Norwegian");
    }

    @Test
    public void fromNameDanish() {
        fromName("Da", "Danish");
    }

    @Test
    public void fromCodeDa() {
        fromCode("Da", "Danish");
    }

    @Test
    public void fromNameFinish() {
        fromName("Fi", "Finish");
    }

    @Test
    public void fromCodeFi() {
        fromCode("Fi", "Finish");
    }

    @Test
    public void fromNameChinese() {
        fromName("Zh", "Chinese");
    }

    @Test
    public void fromCodeZh() {
        fromCode("Zh", "Chinese");
    }

    @Test
    public void fromNameKorean() {
        fromName("Ko", "Korean");
    }

    @Test
    public void fromCodeKo() {
        fromCode("Ko", "Korean");
    }

    @Test
    public void fromNamePolish() {
        fromName("Pl", "Polish");
    }

    @Test
    public void fromCodePl() {
        fromCode("Pl", "Polish");
    }

    @Test
    public void fromNameGrenada() {
        fromName("Gd", "Grenada");
    }

    @Test
    public void fromCodeGd() {
        fromCode("Gd", "Grenada");
    }

    @Test
    public void fromNameCroatian() {
        fromName("Hr", "Croatian");
    }

    @Test
    public void fromCodeHr() {
        fromCode("Hr", "Croatian");
    }

    @Test
    public void fromNameCatalan() {
        fromName("Ca", "Catalan");
    }

    @Test
    public void fromCodeCa() {
        fromCode("Ca", "Catalan");
    }

    @Test
    public void fromLanguageInvalid() {
        assertNull(LanguageEnum.fromName("abc"));
    }

    @Test
    public void fromCodeInvalid() {
        assertNull(LanguageEnum.fromCode("abc"));
    }

    @Test
    public void toListString() {
        List<String> list = LanguageEnum.toListString(Arrays.asList(LanguageEnum.Ca, LanguageEnum.Da));

        assertEquals(2, list.size());
    }

    @Test
    public void toStringTest() {
        assertEquals("Ca,Da", LanguageEnum.toString(Arrays.asList(LanguageEnum.Ca, LanguageEnum.Da)));
    }

    @Test
    public void sizeTest() {
        assertEquals(18, LanguageEnum.values().length);
    }
}
