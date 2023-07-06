package pl.wieczorekp.mim.oop.auction;

public class SentimentalClient extends Client {
    private final String origin;

    public SentimentalClient(int id, String nick, int budget, String origin) {
        super(id, nick, budget);
        this.origin = origin;
    }

    public boolean wantsToBuy(Item p) {
        if (!canAfford(p)) {
            return false;
        }
        return p.countryOfOrigin().equalsIgnoreCase(origin);
    }
}
