package pl.wieczorekp.mim.oop.auction;

public class Sentymentalny extends UczestnikLicytacji {
    private String origin;

    Sentymentalny(int id, String pseudonim, int budzet, String origin) {
        super(id, pseudonim, budzet);
        this.origin = origin;
    }

    public boolean czyKupuje(Przedmiot p) {
        if (!canAfford(p)) {
            return false;
        }
        return p.getKrajPochodzenia().equalsIgnoreCase(origin);
    }
}
