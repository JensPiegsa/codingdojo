package gildedrose2;

public class ItemProcessor {
    public static final int MAXIMUM_QUALITY = 50;
    public static final String SULFURAS = "Sulfuras";
    public static final String BACKSTAGE_PASSES = "Backstage passes";
    public static final String AGED_BRIE = "Aged Brie";

    private final Item item;

    public ItemProcessor(Item item) {
        this.item = item;
    }

    public void updateQuality() {
        if (isAgedBrie()) {
            increaseQualityByOneSafely();
            decreaseSellInByOne();
            if (isSellInNegative()) {
                increaseQualityByOneSafely();
            }
        } else if (isBackstagePasses()) {
            increaseQualityByOneSafely();
            if (isQualityBelowMax()) {
                if (isSellInSmallerEleven()) {
                    increaseQualityByOneSafely();
                }
                if (isSellInSmallerSix()) {
                    increaseQualityByOneSafely();
                }
            }
            decreaseSellInByOne();
            if (isSellInNegative()) {
                resetQuality();
            }
        } else if (isSulfuras()) {
            // nothing to do
        } else {
            decreaseQualityByOneSafely();
            decreaseSellInByOne();
            if (isSellInNegative()) {
                decreaseQualityByOneSafely();
            }
        }
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
        return this.item.name + ", " + this.item.sellIn + ", " + this.item.quality;
    }

    public void resetQuality() {
        item.quality = 0;
    }

    public boolean isAgedBrie() {
        return item.name.equals(AGED_BRIE);
    }

    public boolean isBackstagePasses() {
        return item.name.startsWith(BACKSTAGE_PASSES);
    }

    public boolean isSulfuras() {
        return item.name.startsWith(SULFURAS);
    }

    public void increaseQualityByOneSafely() {
        if (isQualityBelowMax()) {
            item.quality++;
        }
    }

    public void decreaseQualityByOneSafely() {
        if (isQualityPositive()) {
            decreaseQualityByOne();
        }
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
