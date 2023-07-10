package pl.wieczorekp.mim.oop.auction;

import pl.wieczorekp.mim.oop.auction.clients.Client;

import java.util.List;

public class Auction {
    private Auctioneer auctioneer;
    private List<Client> clients;
    private List<Item> items;

    public Auction(Auctioneer auctioneer, List<Client> clients, List<Item> items) {
        this.auctioneer = auctioneer;
        this.clients = clients;
        this.items = items;
    }

    public int[] makeAuction() {
        auctioneer.reorderClients(clients);
        return auctioneer.makeAuction(items, clients);
    }
}

