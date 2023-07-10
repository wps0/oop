package pl.wieczorekp.mim.oop.auction.clients;

import pl.wieczorekp.mim.oop.auction.Item;

import java.util.Random;

// Losowy
public class RandomClient extends Client {
    private final Random rng;

    public RandomClient(int id, String alias, int budget, Random rng) {
        super(id, alias, budget);
        this.rng = rng;
    }

    public boolean wantsToBuy(Item p) {
        return rng.nextBoolean();
    }

    @Override
    public String toString() {
        return "RandomClient{" + super.toString() + '}';
    }
}
