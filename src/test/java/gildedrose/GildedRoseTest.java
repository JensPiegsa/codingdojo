package gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class GildedRoseTest {

    GildedRose gildedRose;

    // TODO items with invalid initial quality (> 50)

    @Test
    @DisplayName("\"Conjured\" items degrade in Quality twice as fast as normal items")
    void nm2() {
        // given
        Item conjured = new Item("Conjured", 10, 10);
        gildedRose = new GildedRose(new Item[] {conjured});

        // when
        gildedRose.updateQualitiesAndSellIns();

        // then
        assertThat(conjured.quality).isEqualTo(8);
    }

    @ParameterizedTest
    @DisplayName("\"Backstage passes\", increases in Quality as its SellIn value approaches")
    @MethodSource("threeBackstagePassesItems")
    void nm1(Item item, int expectedQuality) {
        // given
        gildedRose = new GildedRose(new Item[] {item});

        // when
        gildedRose.updateQualitiesAndSellIns();

        // then
        assertThat(item.quality).isEqualTo(expectedQuality);
    }

    private static Stream<Arguments> threeBackstagePassesItems() {
        return Stream.of(
                arguments(new Item("Backstage passes to a TAFKAL80ETC concert", 11, 10), 11),
                arguments(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10), 12),
                arguments(new Item("Backstage passes to a TAFKAL80ETC concert", 6, 10), 12),
                arguments(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10), 13),
                arguments(new Item("Backstage passes to a TAFKAL80ETC concert", 3, 10), 13),
                arguments(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10), 0)
        );
    }


    @Test @DisplayName("\"Sulfuras\", being a legendary item, never has to be sold or decreases in Quality")
    void sulfurasBeingALegendaryItemNeverHasToBeSoldOrDecreasesInQuality() {
        // given
        Item legendary = new Item("Sulfuras, Hand of Ragnaros", 1, 80);

        gildedRose = new GildedRose(new Item[] {legendary});

        // when
        gildedRose.updateQualitiesAndSellIns();

        // then
        int actualPostQualityOfBrie = legendary.quality;
        int actualSellIn = legendary.sellIn;
        assertThat(actualPostQualityOfBrie).isEqualTo(80);
        assertThat(actualSellIn).isEqualTo(1);
    }

    @ParameterizedTest @DisplayName("The Quality of an item is never more than 50")
    @MethodSource("maxQualityItems")
    void theQualityOfAnItemIsNeverMoreThan50(Item item) {
        // given
        gildedRose = new GildedRose(new Item[] {item});

        // when
        gildedRose.updateQualitiesAndSellIns();

        // then
        assertThat(item.quality).isLessThanOrEqualTo(50);
    }

    private static Stream<Arguments> maxQualityItems() {
        return Stream.of(
                arguments(new Item("Aged Brie", 1, 50)),
                arguments(new Item("Backstage passes to a TAFKAL80ETC concert", 1, 50)),
                arguments(new Item("Backstage passes to a TAFKAL80ETC concert", 6, 49)),
                arguments(new Item("Backstage passes to a TAFKAL80ETC concert", 3, 49))
        );
    }

    @Test @DisplayName("\"Aged Brie\" actually increases in Quality the older it gets")
    void agedBrieActuallyIncreasesInQualityTheOlderItGets() {

        for (int initialQuality = 0; initialQuality < 50; initialQuality++) {
            // given
            Item agedBrie = new Item("Aged Brie", 1, initialQuality);
            gildedRose = new GildedRose(new Item[] {agedBrie});

            // when
            gildedRose.updateQualitiesAndSellIns();

            // then
            int actualPostQuality = agedBrie.quality;
            assertThat(actualPostQuality).isGreaterThan(initialQuality);
        }
    }

    @ParameterizedTest @DisplayName("The Quality of an item is never negative")
    @MethodSource("positiveQualityItems")
    void theQualityOfAnItemIsNeverNegative(Item item, int expectedQuality) {
        // given
        gildedRose = new GildedRose(new Item[] {item});

        // when
        gildedRose.updateQualitiesAndSellIns();

        // then
        assertThat(item.quality).isEqualTo(expectedQuality);
    }

    private static Stream<Arguments> positiveQualityItems() {
        return Stream.of(
                arguments(new Item("border case 0", 10, 0), 0),
                arguments(new Item("border case 1", 0, 0), 0),
                arguments(new Item("border case 2", 0, 1), 0)
        );
    }

    @ParameterizedTest
    @MethodSource("normalItems")
    void qualityAndSellInIsLoweredByDay(Item item) {

        //given
        int yesterdaysQuality = item.quality;
        int yesterdaysSellIn = item.sellIn;

        gildedRose = new GildedRose(new Item[] {item});

        // when
        gildedRose.updateQualitiesAndSellIns();

        // then
        assertThat(item.quality).isLessThan(yesterdaysQuality);
        assertThat(item.sellIn).isLessThan(yesterdaysSellIn);
    }

    private static Stream<Item> normalItems() {
        return Stream.of(
//                new Item("Aged Brie", 2, 1), //
                new Item("Elixir of the Mongoose", 5, 7), //
//                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
//                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
//                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
//                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
//                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                new Item("+5 Dexterity Vest", 10, 20)
        );
    }


    @ParameterizedTest
    @MethodSource("normalItemsWithZeroSellIn")
    void qualityLowersTwiceAsFastIfSellInIsExpired(Item item, int expectedQuality) {

        gildedRose = new GildedRose(new Item[] {item});

        // when
        gildedRose.updateQualitiesAndSellIns();

        // then
        assertThat(item.quality).isEqualTo(expectedQuality);
    }

    private static Stream<Arguments> normalItemsWithZeroSellIn() {
        return Stream.of(
                arguments(new Item("Elixir of the Mongoose", 0, 7), 5),
                arguments(new Item("+5 Dexterity Vest", 0, 20), 18),
                arguments(new Item("Conjured", 0, 10), 6)
        );
    }
}
