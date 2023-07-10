package pl.wieczorekp.mim.oop.auction.clients;

import pl.wieczorekp.mim.oop.auction.Item;

// Historyk
public class Historian extends Client {
    private final int favYearLb;
    private final int favYearUb;

    public Historian(int id, String alias, int budget, int favYearLb, int favYearUb) {
        super(id, alias, budget);
        this.favYearLb = favYearLb;
        this.favYearUb = favYearUb;
    }

    public boolean wantsToBuy(Item p) {
        return favYearLb <= p.year() && p.year() <= favYearUb;
    }

    @Override
    public String toString() {
        return "Historian{" + super.toString() +
                ", favYearLb=" + favYearLb +
                ", favYearUb=" + favYearUb +
                '}';
    }
}
