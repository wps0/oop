package pl.wieczorekp.mim.oop.auction;

public abstract class UczestnikLicytacji {
    protected int id;
    protected String pseudonim;
    protected int budzet;

    public UczestnikLicytacji(int id, String pseudonim, int budzet) {
        this.id = id;
        this.pseudonim = pseudonim;
        this.budzet = budzet;
    }

    public abstract boolean czyKupuje(Przedmiot p);

    public void kupPrzedmiot(Przedmiot p) {
        budzet -= p.getCena();
    }

    public int getId() {
        return id;
    }

    public boolean canAfford(Przedmiot p) {
        return p.getCena() <= budzet;
    }
}
