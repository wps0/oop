package pl.wieczorekp.mim.oop.auction;

import java.util.Random;

public class RandomClient extends Client {
    private static final Random rng = new Random();

    public RandomClient(int id, String alias, int budget) {
        super(id, alias, budget);
    }

    public boolean wantsToBuy(Item p) {
        if (!canAfford(p))
            return false;
        return rng.nextBoolean();
    }
}
