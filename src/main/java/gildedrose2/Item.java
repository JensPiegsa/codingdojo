package gildedrose2;

public class Item {
    public static final int MAXIMUM_QUALITY = 50;
    public static final String SULFURAS = "Sulfuras";
    public static final String BACKSTAGE_PASSES = "Backstage passes";
    public static final String AGED_BRIE = "Aged Brie";

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public boolean isSellInSmallerSix() {
        return sellIn < 6;
    }

    public boolean isSellInSmallerEleven() {
        return sellIn < 11;
    }

    public void decreaseQualityByOne() {
        quality--;
    }

    public boolean isQualityPositive() {
        return quality > 0;
    }

    public boolean isQualityBelowMax() {
        return quality < MAXIMUM_QUALITY;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    public void resetQuality() {
        quality = 0;
    }

    public boolean isAgedBrie() {
        return name.equals(AGED_BRIE);
    }

    public boolean isBackstagePasses() {
        return name.startsWith(BACKSTAGE_PASSES);
    }

    public boolean isSulfuras() {
        return name.startsWith(SULFURAS);
    }

    void increaseQualityByOneSafely() {
        if (isQualityBelowMax()) {
            quality++;
        }
    }

    public void decreaseSellInByOne() {
        sellIn -= 1;
    }

    public boolean isSellInNegative() {
        return sellIn < 0;
    }
}