package gildedrose2;

class GildedRose {

    Item[] items;

    public GildedRose(final Item[] items) {
        this.items = items;
    }

    // TODO refactor:
    // option 1: join nested ifs
    // option 2: find duplicated code and refactor methods
    public void updateQuality() {
        for (final Item item : items) {
            
            if (item.isAgedBrie()) {
                item.increaseQualityByOneSafely();
                item.decreaseSellInByOne();
                if (item.isSellInNegative()) {
                    item.increaseQualityByOneSafely();
                }
            } else if (item.isBackstagePasses()) {
                item.increaseQualityByOneSafely();
                if (item.isQualityBelowMax()) {
                    if (item.isSellInSmallerEleven()) {
                        item.increaseQualityByOneSafely();
                    }
                    if (item.isSellInSmallerSix()) {
                        item.increaseQualityByOneSafely();
                    }
                }
                item.decreaseSellInByOne();
                if (item.isSellInNegative()) {
                    item.resetQuality();
                }
            } else if (item.isSulfuras()) {
                // nothing to do
            } else {
                item.decreaseQualityByOneSafely();
                item.decreaseSellInByOne();
                if (item.isSellInNegative()) {
                    item.decreaseQualityByOneSafely();
                }
            }
        }
    }

}