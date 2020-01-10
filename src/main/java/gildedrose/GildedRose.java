package gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            updateItemQuality(item);
            updateItemSellIn(item);
            postUpdateItemQuality(item);
        }
    }

    private void postUpdateItemQuality(Item item) {
        if (item.sellIn < 0) {
            if (isAgedBrie(item)) {
                increaseQualityBy(item,1);
            } else if (isBackstagePass(item)) {
                item.quality = 0;
            } else decreaseQualityByOne(item);
        }
    }

    private void updateItemSellIn(Item item) {
        if (!isLegendary(item)) {
            item.sellIn = item.sellIn - 1;
        }
    }

    private void updateItemQuality(Item item) {
        if (isAgedBrie(item)
                || isBackstagePass(item)) {
            if (canIncreaseQuality(item)) {
                increaseQualityBy(item,1);
                if (isBackstagePass(item)) {
                    if (item.sellIn < 6) {
                        increaseQualityBy(item,2);
                    }
                    else if (item.sellIn < 11) {
                        increaseQualityBy(item,1);
                    }
                }
            }
        } else {
            decreaseQualityByOne(item);
        }
    }

    private void decreaseQualityByOne(Item item) {
        if (item.quality > 0 && !isLegendary(item)) {
            item.quality = item.quality - 1;
        }
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
            item.quality = item.quality + number;
        }
    }
}