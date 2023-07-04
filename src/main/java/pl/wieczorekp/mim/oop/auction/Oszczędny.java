package pl.wieczorekp.mim.oop.auction;

public class Oszczędny extends UczestnikLicytacji {
    private int sumOfPrices;
    private int proposedItemsCount;

    Oszczędny(int id, String alias, int budget) {
        super(id, alias, budget);
        sumOfPrices = 0;
        proposedItemsCount = 0;
    }

    public boolean czyKupuje(Przedmiot p) {
        proposedItemsCount++;
        sumOfPrices += p.getCena();
        if (!canAfford(p) || proposedItemsCount == 1)
            return false;
        return p.getCena() <= sumOfPrices / (float) proposedItemsCount;
    }
}
