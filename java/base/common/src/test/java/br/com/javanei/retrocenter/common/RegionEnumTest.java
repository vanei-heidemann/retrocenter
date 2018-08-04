package br.com.javanei.retrocenter.common;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RegionEnumTest {
    private void fromName(RegionEnum region, String name) {
        List<RegionEnum> list = RegionEnum.fromName(name);

        assertEquals(1, list.size());

        RegionEnum r = list.get(0);
        assertEquals(region, r);
    }

    @Test
    public void fromNameAsia() {
        fromName(RegionEnum.Asia, "Asia");
    }

    @Test
    public void fromNameAustralia() {
        fromName(RegionEnum.Australia, "Australia");
    }

    @Test
    public void fromNameBrazil() {
        fromName(RegionEnum.Brazil, "Brazil");
    }

    @Test
    public void fromNameCanada() {
        fromName(RegionEnum.Canada, "Canada");
    }

    @Test
    public void fromNameChina() {
        fromName(RegionEnum.China, "China");
    }

    @Test
    public void fromNameDenmark() {
        fromName(RegionEnum.Denmark, "Denmark");
    }

    @Test
    public void fromNameEurope() {
        fromName(RegionEnum.Europe, "Europe");
    }

    @Test
    public void fromNameFinland() {
        fromName(RegionEnum.Finland, "Finland");
    }

    @Test
    public void fromNameFrance() {
        fromName(RegionEnum.France, "France");
    }

    @Test
    public void fromNameGermany() {
        fromName(RegionEnum.Germany, "Germany");
    }

    @Test
    public void fromNameHongKong() {
        fromName(RegionEnum.HongKong, "Hong Kong");
    }

    @Test
    public void fromNameItaly() {
        fromName(RegionEnum.Italy, "Italy");
    }

    @Test
    public void fromNameJapan() {
        fromName(RegionEnum.Japan, "Japan");
    }

    @Test
    public void fromNameKorea() {
        fromName(RegionEnum.Korea, "Korea");
    }

    @Test
    public void fromNameNetherlands() {
        fromName(RegionEnum.Netherlands, "Netherlands");
    }

    @Test
    public void fromNameRussia() {
        fromName(RegionEnum.Russia, "Russia");
    }

    @Test
    public void fromNameSpain() {
        fromName(RegionEnum.Spain, "Spain");
    }

    @Test
    public void fromNameSweden() {
        fromName(RegionEnum.Sweden, "Sweden");
    }

    @Test
    public void fromNameTaiwan() {
        fromName(RegionEnum.Taiwan, "Taiwan");
    }

    @Test
    public void fromNameUnknown() {
        fromName(RegionEnum.Unknown, "Unknown");
    }

    @Test
    public void fromNameUSA() {
        fromName(RegionEnum.USA, "USA");
    }

    @Test
    public void fromNameWorld() {
        fromName(RegionEnum.World, "World");
    }

    @Test
    public void fromNameMultiple() {
        assertEquals(2, RegionEnum.fromName("USA,World").size());
    }

    @Test
    public void toListString() {
        assertEquals(2, RegionEnum.toListString(Arrays.asList(RegionEnum.Asia, RegionEnum.Australia)).size());
    }

    @Test
    public void toListStringTest() {
        assertEquals("USA, World", RegionEnum.toString(Arrays.asList(RegionEnum.USA, RegionEnum.World)));
    }

    @Test
    public void toStringTest() {
        assertEquals("USA", RegionEnum.USA.toString());
    }

    @Test
    public void sizeTest() {
        assertEquals(22, RegionEnum.values().length);
    }
}