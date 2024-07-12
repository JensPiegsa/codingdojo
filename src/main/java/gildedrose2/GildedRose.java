package gildedrose2;

class GildedRose {
    Item[] items;

    public GildedRose(final Item[] items) {

        this.items = items;
    }

    public void updateAllItemQualities() {
        for (final Item item : items) {
            final ItemProcessor itemProcessor = new ItemProcessor(item);
            itemProcessor.updateQualityAndSellIn();
        }
    }
}