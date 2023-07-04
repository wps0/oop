package pl.wieczorekp.mim.oop.elections;

import java.util.Random;

public class Losowy extends UczestnikLicytacji {
    Losowy(int id, String alias, int budget) {
        super(id, alias, budget);
    }

    public boolean czyKupuje(Przedmiot p) {
        if (!canAfford(p))
            return false;
        Random r = new Random();
        return r.nextBoolean();
    }
}
