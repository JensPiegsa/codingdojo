package gildedrose2;

import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


/**
 * see:  
 */
class GildedRoseTest {

    @Disabled
    @ParameterizedTest @DisplayName("Conjured item decreases doubled in quality.")
    @CsvSource({
            "nonConjured,  1, 40, 39",
            "Conjured,     1, 40, 38",
            "nonConjured,  0, 40, 38",
            "Conjured,     0, 40, 36"
    })
    void conjuredItemDecreasesDoubledInQuality(String itemName, int sellIn, int quality, int expectedQuality) {
        final Item[] items = { new Item(itemName, sellIn, quality) };
        final GildedRose app = new GildedRose(items);

        app.updateQuality();

        then(items[0].quality).isEqualTo(expectedQuality);
    }

    @Test @DisplayName("can age item.")
    void canAgeItem() {
        
        final Item[] items = { new Item("foo", 10, 40) };
        final GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        
        then(app.items[0].sellIn).isLessThan(10);
        then(app.items[0].quality).isLessThan(40);
    }

    @Test @DisplayName("does not age when sellIn is zero")
    @Disabled
    void doesNotAgeWhenSellInIsZero() {
        final Item[] items = { new Item("foo", 0, 0) };
        final GildedRose app = new GildedRose(items);

        app.updateQuality();

        then(app.items[0].sellIn).isEqualTo(0);
        then(app.items[0].quality).isEqualTo(0);
    }

    @Test @DisplayName("no quality below zero")
    void noQualityBelowZero() {
        final Item[] items = { new Item("foo", 1, 0) };
        final GildedRose app = new GildedRose(items);

        app.updateQuality();

        then(app.items[0].sellIn).isEqualTo(0);
        then(app.items[0].quality).isEqualTo(0);
    }

    @Test @DisplayName("decrease quality twice as fast when sellIn is zero")
    void decreaseQualityTwiceAsFastWhenSellInIsZero() {
        final Item[] items = { new Item("foo", 0, 10) };
        final GildedRose app = new GildedRose(items);

        app.updateQuality();

        then(app.items[0].quality).isEqualTo(8); // TODO R1 says "twice as fast"
    }
    
    @Test @DisplayName("'Aged Brie' actually increases in 'Quality' the older it gets.")
    void agedBrieActuallyIncreasesInQualityTheOlderItGets() {
        final Item agedBrie = new Item("Aged Brie", 10, 40);
        final GildedRose app = new GildedRose(new Item[]{agedBrie});

        app.updateQuality();

        final Item updatedAgedBrie = app.items[0];
        then(updatedAgedBrie.quality).isGreaterThan(40);
    }

    @Test @DisplayName("The Quality of an item is never more than 50")
    void theQualityOfAnItemIsNeverMoreThan50() {
        final Item agedBrie = new Item("Aged Brie", 10, 50);
        final GildedRose app = new GildedRose(new Item[]{agedBrie});

        app.updateQuality();

        final Item updatedAgedBrie = app.items[0];
        then(updatedAgedBrie.quality).isEqualTo(50);
    }

    @ParameterizedTest(name = "Testfall {index}: {0}")
    @DisplayName("\"Sulfuras\", being a legendary item, never has to be sold or decreases in Quality")
    @ValueSource(strings = {
            "Sulfuras",
            "Sulfuras, Hand of Ragnaros"
    })
    void sulfurasNeverHasToBeSoldOrDecreasesInQuality(String itemName) {
        final Item legendaryItem = new Item(itemName, 10, 40);
        final GildedRose app = new GildedRose(new Item[]{legendaryItem});

        app.updateQuality();

        final Item legendaryUpdatedItem = app.items[0];
        then(legendaryUpdatedItem.quality).isEqualTo(40);
        then(legendaryUpdatedItem.sellIn).isEqualTo(10);
    }
    
    @Nested @DisplayName("Backstage passes")
    class BackstagePasses {

    }
    @ParameterizedTest @DisplayName("Quality increases by 2 when there are 10 days or less.")
    @CsvSource({
            "Backstage passes,                          10, 40, 42",
            "Backstage passes to a TAFKAL80ETC concert, 10, 40, 42",
            "Backstage passes,                           5, 40, 43",
            "Backstage passes to a TAFKAL80ETC concert,  1, 40, 43",
            "Backstage passes to a TAFKAL80ETC concert,  0, 40,  0",
            "Backstage passes,                           1, 40, 43",
            "Backstage passes,                           0, 40,  0"
    })
    void qualityIncreasesBy2WhenThereAre10DaysOrLess(String itemName, int sellIn, int quality, int expectedQuality) {
        final Item backstagePasses = new Item(itemName, sellIn, quality);
        final GildedRose app = new GildedRose(new Item[]{backstagePasses});

        app.updateQuality();

        final Item updated = app.items[0];
        then(updated.quality).isEqualTo(expectedQuality);
    }
}