package pl.wieczorekp.mim.oop.auction;

public class Historyk extends UczestnikLicytacji {
    private int favYearLb;
    private int favYearUb;

    Historyk(int id, String alias, int budget, int favYearLb, int favYearUb) {
        super(id, alias, budget);
        this.favYearLb = favYearLb;
        this.favYearUb = favYearUb;
    }

    public boolean czyKupuje(Przedmiot p) {
        if (!canAfford(p))
            return false;
        return favYearLb <= p.getRokProdukcji() && p.getRokProdukcji() <= favYearUb;
    }
}
