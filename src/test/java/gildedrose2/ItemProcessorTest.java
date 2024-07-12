package gildedrose2;

import static org.assertj.core.api.BDDAssertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("An ItemProcessor")
class ItemProcessorTest {

    ItemProcessor itemProcessor;

    @ParameterizedTest
    @DisplayName("Conjured item decreases doubled in quality.")
    @CsvSource({
            "nonConjured,  1, 40, 39",
            "Conjured,     1, 40, 38",
            "nonConjured,  0, 40, 38",
            "Conjured,     0, 40, 36"
    })
    void conjuredItemDecreasesDoubledInQuality(final String itemName, final int sellIn, 
                                               final int quality, final int expectedQuality) {
        
        final ItemProcessor item = new ItemProcessor(new Item(itemName, sellIn, quality));

        item.updateQualityAndSellIn();

        then(item.getQuality()).isEqualTo(expectedQuality);
    }

    @Test
    @DisplayName("can age item.")
    void canAgeItem() {

        final ItemProcessor item = new ItemProcessor(new Item("foo", 10, 40));
        item.updateQualityAndSellIn();

        then(item.getSellIn()).isLessThan(10);
        then(item.getQuality()).isLessThan(40);
    }

    @Test
    @DisplayName("does not age when sellIn is zero")
    void doesNotAgeWhenSellInIsZero() {
        final ItemProcessor item = new ItemProcessor(new Item("foo", 0, 0));
        item.updateQualityAndSellIn();

        then(item.getSellIn()).isEqualTo(-1);
        then(item.getQuality()).isEqualTo(0);
    }

    @Test
    @DisplayName("no quality below zero")
    void noQualityBelowZero() {
        final ItemProcessor item = new ItemProcessor(new Item("foo", 1, 0));
        item.updateQualityAndSellIn();

        then(item.getSellIn()).isEqualTo(0);
        then(item.getQuality()).isEqualTo(0);
    }

    @Test
    @DisplayName("decrease quality twice as fast when sellIn is zero")
    void decreaseQualityTwiceAsFastWhenSellInIsZero() {
        final ItemProcessor item = new ItemProcessor(new Item("foo", 0, 10));
        item.updateQualityAndSellIn();

        then(item.getQuality()).isEqualTo(8); // TODO R1 says "twice as fast"
    }

    @Test
    @DisplayName("'Aged Brie' actually increases in 'Quality' the older it gets.")
    void agedBrieActuallyIncreasesInQualityTheOlderItGets() {
        final ItemProcessor agedBrie = new ItemProcessor(new Item("Aged Brie", 10, 40));
        agedBrie.updateQualityAndSellIn();

        then(agedBrie.getQuality()).isGreaterThan(40);
    }

    @Test
    @DisplayName("The Quality of an item is never more than 50")
    void theQualityOfAnItemIsNeverMoreThan50() {
        final ItemProcessor agedBrie = new ItemProcessor(new Item("Aged Brie", 10, 50));
        agedBrie.updateQualityAndSellIn();
        then(agedBrie.getQuality()).isEqualTo(50);
    }

    @ParameterizedTest(name = "Testfall {index}: {0}")
    @DisplayName("\"Sulfuras\", being a legendary item, never has to be sold or decreases in Quality")
    @ValueSource(strings = {
            "Sulfuras",
            "Sulfuras, Hand of Ragnaros"
    })
    void sulfurasNeverHasToBeSoldOrDecreasesInQuality(final String itemName) {
        final ItemProcessor legendaryItem = new ItemProcessor(new Item(itemName, 10, 40));
        legendaryItem.updateQualityAndSellIn();

        then(legendaryItem.getQuality()).isEqualTo(40);
        then(legendaryItem.getSellIn()).isEqualTo(10);
    }

    @Nested
    @DisplayName("Backstage passes")
    class BackstagePasses {

    }

    @ParameterizedTest
    @DisplayName("Quality increases by 2 when there are 10 days or less.")
    @CsvSource({
            "Backstage passes,                          10, 40, 42",
            "Backstage passes to a TAFKAL80ETC concert, 10, 40, 42",
            "Backstage passes,                           5, 40, 43",
            "Backstage passes to a TAFKAL80ETC concert,  1, 40, 43",
            "Backstage passes to a TAFKAL80ETC concert,  0, 40,  0",
            "Backstage passes,                           1, 40, 43",
            "Backstage passes,                           0, 40,  0"
    })
    void qualityIncreasesBy2WhenThereAre10DaysOrLess(final String itemName, final int sellIn, final int quality, final int expectedQuality) {
        final ItemProcessor backstagePasses = new ItemProcessor(new Item(itemName, sellIn, quality));
        backstagePasses.updateQualityAndSellIn();

        then(backstagePasses.getQuality()).isEqualTo(expectedQuality);
    }
}