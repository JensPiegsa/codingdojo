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
            if (!item.isAgedBrie() && !item.isBackstagePasses()) {
                if (!item.isSulfuras() && item.isQualityPositive()) {
                    item.decreaseQualityByOne();
                }
            } else {
                
                item.increaseQualityByOneSafely();

                if (item.isBackstagePasses())
                    if (item.isQualityBelowMax()) {
                        if (item.isSellInSmallerEleven()) {
                            item.increaseQualityByOneSafely();
                        }

                        if (item.isSellInSmallerSix()) {
                            item.increaseQualityByOneSafely();
                        }
                    }
            }

            if (!item.isSulfuras()) {
                item.decreaseSellInByOne();
            }

            if (item.isSellInNegative()) {
                if (item.isAgedBrie()) {
                    item.increaseQualityByOneSafely();
                } else if (item.isBackstagePasses()) {
                    item.resetQuality();
                } else if (!item.isSulfuras() && item.isQualityPositive()) {
                    item.decreaseQualityByOne();
                }
            }
        }
    }

}