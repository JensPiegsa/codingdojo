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
            if (isSellInZero()) {
                increaseQualitySafelyBy(2);
            } else {
                increaseQualitySafelyBy(1);
            }
            decreaseSellInByOne();
        } else if (isBackstagePasses()) {
            if (isSellinBetweenSevenAndEleven()) {
                increaseQualitySafelyBy(2);
            } else if (isSellInBetweenOneAndSix()) {
                increaseQualitySafelyBy(3);
            } else if (isSellInZero()) {
                resetQuality();
            } else {
                increaseQualitySafelyBy(1);
            }
            decreaseSellInByOne();
        } else if (isConjured()) {
            if (isSellInZero()) {
                decreaseQualitySafelyBy(4);
            } else {
                decreaseQualitySafelyBy(2);
            }
            decreaseSellInByOne();
        } else { // default item
            if (isSellInZero()) {
                decreaseQualitySafelyBy(2);
            } else {
                decreaseQualitySafelyBy(1);
            }
            decreaseSellInByOne();
        }
    }

    private void decreaseQualitySafelyBy(final int count) {
        item.quality = Math.max(item.quality - count, 0);
    }

    private boolean isSellInBetweenOneAndSix() {
        return item.sellIn <= 6 && item.sellIn >= 1;
    }

    private boolean isSellinBetweenSevenAndEleven() {
        return item.sellIn <= 11 && item.sellIn >= 7;
    }

    private boolean isSellInZero() {
        return item.sellIn == 0;
    }

    public boolean isSellInSmallerSix() {
        return item.sellIn < 6;
    }

    public boolean isSellInSmallerEleven() {
        return item.sellIn < 11;
    }

    public void decreaseQualityByOne() {
        item.quality--;
    }

    public boolean isQualityPositive() {
        return item.quality > 0;
    }

    public boolean isQualityBelowMax() {
        return item.quality < MAXIMUM_QUALITY;
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
