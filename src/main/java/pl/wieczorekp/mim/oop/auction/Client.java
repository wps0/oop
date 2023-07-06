package pl.wieczorekp.mim.oop.auction;

public abstract class Client {
    private int id;
    private String nick;
    private int budget;

    protected Client(int id, String nick, int budget) {
        this.id = id;
        this.nick = nick;
        this.budget = budget;
    }

    public abstract boolean wantsToBuy(Item p);

    public void buyItem(Item p) {
        budget -= p.price();
    }

    public int id() {
        return id;
    }

    public String nick() {
        return nick;
    }

    public int budget() {
        return budget;
    }

    public boolean canAfford(Item p) {
        return p.price() <= budget;
    }
}
