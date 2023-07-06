package pl.wieczorekp.mim.oop.auction;

public class Historian extends Client {
    private int favYearLb;
    private int favYearUb;

    Historian(int id, String alias, int budget, int favYearLb, int favYearUb) {
        super(id, alias, budget);
        this.favYearLb = favYearLb;
        this.favYearUb = favYearUb;
    }

    public boolean wantsToBuy(Item p) {
        if (!canAfford(p))
            return false;
        return favYearLb <= p.year() && p.year() <= favYearUb;
    }
}
