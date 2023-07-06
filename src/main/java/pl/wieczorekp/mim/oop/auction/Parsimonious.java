package pl.wieczorekp.mim.oop.auction;

public class Parsimonious extends Client {
    private int sumOfPrices;
    private int proposedItemsCount;

    Parsimonious(int id, String alias, int budget) {
        super(id, alias, budget);
        sumOfPrices = 0;
        proposedItemsCount = 0;
    }

    public boolean wantsToBuy(Item p) {
        proposedItemsCount++;
        sumOfPrices += p.price();
        if (!canAfford(p) || proposedItemsCount == 1)
            return false;
        return p.price() <= sumOfPrices / (float) proposedItemsCount;
    }
}
