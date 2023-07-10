package pl.wieczorekp.mim.oop.auction;

import pl.wieczorekp.mim.oop.auction.clients.*;

import java.util.*;

public class Main {
    private static final long SEED = 578329104L;
    private static final Random RNG = new Random(SEED);
    private static final String[] COUNTRIES = {
            "Poland",
            "USA",
            "Japan",
            "Germany",
            "USSR"
    };

    public static void main(String[] args) {
        List<Client> clients = new LinkedList<>();
        clients.add(new Parsimonious(1, "PC1", 25000));
        clients.add(new RandomClient(2, "RC2", 2000, RNG));
        clients.add(new RandomClient(3, "SC3", 5000, RNG));
        clients.add(new Historian(4, "H4", 4500, 1200, 1900));
        clients.add(new SentimentalClient(5, "S5", 60000, "Poland"));
        clients.add(new SentimentalClient(6, "S6", 60000, "USSR"));

        List<Item> items = generateItems(15);
        Auctioneer auctioneer = new Auctioneer();
        Auction auction = new Auction(auctioneer, clients, items);
        Map<Integer, Client> clientsMap = new TreeMap<>();

        for (Client c : clients) {
            clientsMap.put(c.id(), c);
        }

        int[] results = auction.makeAuction();
        for (int i = 0; i < results.length; i++) {
            if (results[i] == -1)
                System.out.printf("Item %s was not bought by anyone%n", items.get(i));
            else
                System.out.printf("Item %s was bought by %s%n", items.get(i), clientsMap.get(results[i]));
        }
    }

    private static List<Item> generateItems(int n) {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            items.add(new Item(RNG.nextInt(2023), RNG.nextInt(5000), COUNTRIES[RNG.nextInt(COUNTRIES.length)]));
        }
        return items;
    }
}
