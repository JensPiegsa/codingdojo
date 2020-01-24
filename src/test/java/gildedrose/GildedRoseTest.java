package gildedrose;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class GildedRoseTest {

    GildedRose gildedRose;

    /*
    - Once the sell by date has passed, Quality degrades twice as fast
	- The Quality of an item is never negative
	- "Aged Brie" actually increases in Quality the older it gets
	- The Quality of an item is never more than 50
	- "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
	- "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
	Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
	Quality drops to 0 after the concert

    We have recently signed a supplier of conjured items. This requires an update to our system:

	- "Conjured" items degrade in Quality twice as fast as normal items
     */

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
                arguments(new Item("border case 1", 0, 0), 0),
                arguments(new Item("border case 2", 0, 1), 0)
        );
    }
}
