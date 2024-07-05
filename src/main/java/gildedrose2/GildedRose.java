package gildedrose2;

class GildedRose {

    private final ItemUpdater itemUpdater;
    Item[] items;

    public GildedRose(final ItemUpdater itemUpdater, final Item[] items) {
        this.itemUpdater = itemUpdater;
        this.items = items;
    }

    public void updateAllItemQualities() {
        for (final Item item : items) {
            final ItemProcessor itemProcessor = new ItemProcessor(item);
            itemProcessor.updateQuality();
        }
    }
}