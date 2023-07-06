package pl.wieczorekp.mim.oop.auction;

import java.util.*;

public class Main {
    private static final Random RNG = new Random();
    private static final String[] COUNTRIES = {
            "Poland",
            "USA",
            "Japan",
            "Germany",
            "USSR"
    };

    public static void main(String[] args) {
        List<Client> clients = List.of(
                new Parsimonious(1, "PC1", 25000),
                new RandomClient(2, "RC2", 2000),
                new RandomClient(3, "SC3", 5000),
                new Historian(4, "H4", 4500, 1850, 1900),
                new SentimentalClient(5, "S5", 60000, "Poland")
        );
        List<Item> items = generateItems(10);
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
                System.out.printf("Item %s bought by %s%n", items.get(i), clientsMap.get(results[i]));
        }
    }

    private static List<Item> generateItems(int n) {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            items.add(new Item(RNG.nextInt(2023), RNG.nextInt(6000), COUNTRIES[RNG.nextInt(COUNTRIES.length)]));
        }
        return items;
    }
}
