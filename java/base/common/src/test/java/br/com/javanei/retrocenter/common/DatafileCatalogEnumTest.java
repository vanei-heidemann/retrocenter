package br.com.javanei.retrocenter.common;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DatafileCatalogEnumTest {

    @Test
    public void fromNameNull() {
        assertNull(DatafileCatalogEnum.fromName(null));
    }

    @Test
    public void fromNameNoIntro1() {
        assertEquals(DatafileCatalogEnum.NoIntro, DatafileCatalogEnum.fromName("nointro"));
    }

    @Test
    public void fromNameNoIntro2() {
        assertEquals(DatafileCatalogEnum.NoIntro, DatafileCatalogEnum.fromName("no-intro"));
    }

    @Test
    public void fromNameNoIntro3() {
        assertEquals(DatafileCatalogEnum.NoIntro, DatafileCatalogEnum.fromName("Nointro"));
    }

    @Test
    public void fromNameNoIntro4() {
        assertEquals(DatafileCatalogEnum.NoIntro, DatafileCatalogEnum.fromName("No-intro"));
    }

    @Test
    public void fromNameNoIntro5() {
        assertEquals(DatafileCatalogEnum.NoIntro, DatafileCatalogEnum.fromName("x-nointro-y"));
    }

    @Test
    public void fromNameNoIntro6() {
        assertEquals(DatafileCatalogEnum.NoIntro, DatafileCatalogEnum.fromName("x-no-intro-y"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromNameNoIntroInvalid() {
        DatafileCatalogEnum.fromName("n-ointro");
    }

    @Test
    public void fromNameTosec1() {
        assertEquals(DatafileCatalogEnum.TOSEC, DatafileCatalogEnum.fromName("tosec"));
    }

    @Test
    public void fromNameTosec2() {
        assertEquals(DatafileCatalogEnum.TOSEC, DatafileCatalogEnum.fromName("TOSEC"));
    }

    @Test
    public void fromNameTosec3() {
        assertEquals(DatafileCatalogEnum.TOSEC, DatafileCatalogEnum.fromName("Tosec"));
    }

    @Test
    public void fromNameTosec4() {
        assertEquals(DatafileCatalogEnum.TOSEC, DatafileCatalogEnum.fromName("x-tosec-y"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromNameTosecInvalid() {
        DatafileCatalogEnum.fromName("t-osec");
    }

    @Test
    public void fromNameMame1() {
        assertEquals(DatafileCatalogEnum.MAME, DatafileCatalogEnum.fromName("mame"));
    }

    @Test
    public void fromNameMame2() {
        assertEquals(DatafileCatalogEnum.MAME, DatafileCatalogEnum.fromName("MAME"));
    }

    @Test
    public void fromNameMame3() {
        assertEquals(DatafileCatalogEnum.MAME, DatafileCatalogEnum.fromName("Mame"));
    }

    @Test
    public void fromNameMame4() {
        assertEquals(DatafileCatalogEnum.MAME, DatafileCatalogEnum.fromName("x-mame-y"));
    }

    @Test
    public void fromNameHyperlist1() {
        assertEquals(DatafileCatalogEnum.HyperList, DatafileCatalogEnum.fromName("hyperlist"));
    }

    @Test
    public void fromNameHyperlist2() {
        assertEquals(DatafileCatalogEnum.HyperList, DatafileCatalogEnum.fromName("HYPERLIST"));
    }

    @Test
    public void fromNameHyperlist3() {
        assertEquals(DatafileCatalogEnum.HyperList, DatafileCatalogEnum.fromName("Hyperlist"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromNameHyperlistInvalid() {
        DatafileCatalogEnum.fromName("hyperlist-");
    }

    @Test
    public void fromNameGoodSet1() {
        assertEquals(DatafileCatalogEnum.GoodSet, DatafileCatalogEnum.fromName("goodset"));
    }

    @Test
    public void fromNameGoodSet2() {
        assertEquals(DatafileCatalogEnum.GoodSet, DatafileCatalogEnum.fromName("GOODSET"));
    }

    @Test
    public void fromNameGoodSet3() {
        assertEquals(DatafileCatalogEnum.GoodSet, DatafileCatalogEnum.fromName("Goodset"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromNameGoodSetInvalid() {
        DatafileCatalogEnum.fromName("good-set");
    }

    @Test
    public void isValid() {
        assertEquals(true, DatafileCatalogEnum.isValid("mame"));
    }

    @Test
    public void isNotValid() {
        assertEquals(false, DatafileCatalogEnum.isValid("abc"));
    }
}