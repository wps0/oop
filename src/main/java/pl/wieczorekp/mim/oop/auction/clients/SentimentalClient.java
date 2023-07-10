package pl.wieczorekp.mim.oop.auction.clients;

import pl.wieczorekp.mim.oop.auction.Item;

// Sentymentalny
public class SentimentalClient extends Client {
    private final String origin;

    public SentimentalClient(int id, String nick, int budget, String origin) {
        super(id, nick, budget);
        this.origin = origin;
    }

    public boolean wantsToBuy(Item p) {
        return p.countryOfOrigin().equalsIgnoreCase(origin);
    }

    @Override
    public String toString() {
        return "SentimentalClient{" + super.toString() +
                ", origin='" + origin + '\'' +
                '}';
    }
}
