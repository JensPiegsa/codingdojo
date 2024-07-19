package gildedrose2;

public class ItemProcessor {
    public static final int MAXIMUM_QUALITY = 50;
    public static final String SULFURAS = "Sulfuras";
    public static final String BACKSTAGE_PASSES = "Backstage passes";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String CONJURED = "Conjured";

    private final Item item;

    public ItemProcessor(final Item item) {
        this.item = item;
    }

    public void updateQualityAndSellIn() {
        if (isSulfuras()) {
            // nothing to do
        } else if (isAgedBrie()) {
            decreaseSellInByOne();
            if (isSellIn(-1)) {
                increaseQualitySafelyBy(2);
            } else {
                increaseQualitySafelyBy(1);
            }
        } else if (isBackstagePasses()) {
            decreaseSellInByOne();
            if (isSellInBetweenInclusive(6, 10)) {
                increaseQualitySafelyBy(2);
            } else if (isSellInBetweenInclusive(0, 5)) {
                increaseQualitySafelyBy(3);
            } else if (isSellIn(-1)) {
                resetQuality();
            } else {
                increaseQualitySafelyBy(1);
            }
        } else if (isConjured()) {
            decreaseSellInByOne();
            if (isSellIn(-1)) {
                decreaseQualitySafelyBy(4);
            } else {
                decreaseQualitySafelyBy(2);
            }
        } else { // default item
            decreaseSellInByOne();
            if (isSellIn(-1)) {
                decreaseQualitySafelyBy(2);
            } else {
                decreaseQualitySafelyBy(1);
            }
        }
    }

    private void decreaseQualitySafelyBy(final int count) {
        item.quality = Math.max(item.quality - count, 0);
    }

    private boolean isSellInBetweenInclusive(final int lowerBoundInclusive, final int upperBoundInclusive) {
        return item.sellIn >= lowerBoundInclusive && item.sellIn <= upperBoundInclusive;
    }

    private boolean isSellIn(int i) {
        return item.sellIn == i;
    }

    @Override
    public String toString() {
        return item.name + ", " + item.sellIn + ", " + item.quality;
    }

    public void resetQuality() {
        item.quality = 0;
    }

    public boolean isAgedBrie() {
        return item.name.equals(AGED_BRIE);
    }

    private boolean isConjured() {
        return item.name.startsWith(CONJURED);
    }

    public boolean isBackstagePasses() {
        return item.name.startsWith(BACKSTAGE_PASSES);
    }

    public boolean isSulfuras() {
        return item.name.startsWith(SULFURAS);
    }

    private void increaseQualitySafelyBy(int count) {
        item.quality = Math.min(MAXIMUM_QUALITY, item.quality + count);
    }

    public void decreaseSellInByOne() {
        item.sellIn -= 1;
    }

    public boolean isSellInNegative() {
        return item.sellIn < 0;
    }

    public int getQuality() {
        return item.quality;
    }

    public int getSellIn() {
        return item.sellIn;
    }
}
