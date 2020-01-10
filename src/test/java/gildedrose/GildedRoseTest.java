package gildedrose;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Assertions.assertEquals("fixme", app.items[0].name);
    }



}
