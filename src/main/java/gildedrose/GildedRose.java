package gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQualitiesAndSellIns() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            updateItemQuality(item);
            updateItemSellIn(item);
        }
    }

    private void updateItemSellIn(Item item) {
        if (!isLegendary(item)) {
            item.sellIn = item.sellIn - 1;
        }
    }

    private void updateItemQuality(Item item) {
        if (isAgedBrie(item)) {
            updateQualityAgedBrie(item);

        } else if (isBackstagePass(item)) {
            updateQualityBackstagePasses(item);

        } else if (isConjured(item)) {
            updateQualityConjured(item);

        } else {
            updateQualityDefault(item);
        }
    }

    private void updateQualityConjured(Item item) {
        if (item.sellIn <= 0) {
            decreaseQualityBy(item, 4);
        } else {
            decreaseQualityBy(item, 2);
        }
    }

    private boolean isConjured(Item item) {
        return item.name.equals("Conjured");
    }

    private void updateQualityDefault(Item item) {
        decreaseQuality(item);
        if (item.sellIn <= 0)
            decreaseQuality(item);
    }

    private void updateQualityBackstagePasses(Item item) {
        if (item.sellIn <= 0) {
            item.quality = 0;
        } else if (item.sellIn <= 5) {
            increaseQualityBy(item, 3);
        } else if (item.sellIn <= 10) {
            increaseQualityBy(item, 2);
        } else {
            increaseQualityBy(item, 1);
        }
    }

    private void updateQualityAgedBrie(Item item) {
        increaseQualityBy(item, 1);
        if (item.sellIn <= 0)
            increaseQualityBy(item, 1);
    }

    private void decreaseQualityBy(Item item, int number) {
        if (!isLegendary(item)) {
            item.quality = Math.max(0, item.quality - number);
        }
    }

    private void decreaseQuality(Item item) {
        decreaseQualityBy(item, 1);
    }

    private boolean canIncreaseQuality(Item item) {
        return item.quality < 50;
    }

    private boolean isBackstagePass(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isLegendary(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private void increaseQualityBy(Item item, int number) {
        if (canIncreaseQuality(item)) {
            item.quality = Math.min(50, item.quality + number);
        }
    }
}