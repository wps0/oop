package pl.wieczorekp.mim.oop.auction;

import pl.wieczorekp.mim.oop.auction.clients.Client;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Auctioneer {

    public void reorderClients(List<Client> clients) {
        // This auctioneer doesn't reorder clients.
    }

    public int[] makeAuction(List<Item> items, List<Client> clients) {
        int[] buyers = new int[items.size()];
        ListIterator<Item> it = items.listIterator();

        while (it.hasNext()) {
            buyers[it.nextIndex()] = bidAnItem(it.next(), clients);
        }

        return buyers;
    }

    private int bidAnItem(Item item, List<Client> clients) {
        int buyer = -1;

        Iterator<Client> it = clients.listIterator();
        while (it.hasNext()) {
            Client c = it.next();
            if (c.canAfford(item) && c.wantsToBuy(item)) {
                c.buyItem(item);
                buyer = c.id();

                it.remove();
                clients.add(c);
                break;
            }
        }

        return buyer;
    }
}

