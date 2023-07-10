package pl.wieczorekp.mim.oop.auction.clients;

import pl.wieczorekp.mim.oop.auction.Item;

// Oszczędny
public class Parsimonious extends Client {
    private int sumOfPrices;
    private int proposedItemsCount;

    public Parsimonious(int id, String alias, int budget) {
        super(id, alias, budget);
        sumOfPrices = 0;
        proposedItemsCount = 0;
    }

    public boolean wantsToBuy(Item p) {
        proposedItemsCount++;
        sumOfPrices += p.price();
        if (proposedItemsCount == 1)
            return false;
        return p.price() <= sumOfPrices / (float) proposedItemsCount;
    }

    @Override
    public String toString() {
        return "Parsimonious{" + super.toString() +
                ", sumOfPrices=" + sumOfPrices +
                ", proposedItemsCount=" + proposedItemsCount +
                '}';
    }
}
