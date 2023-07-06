package pl.wieczorekp.mim.oop.auction;

import java.util.List;

public class Auctioneer {

    public void reorderClients(List<Client> clients) {
    }

    public int[] makeAuction(List<Item> items, List<Client> clients) {
        int[] buyer = new int[items.size()];
        for (int i = 0; i < items.size(); i++) {
            buyer[i] = wylicytujPrzedmiot(items.get(i), clients);
        }
        return buyer;
    }

    private int wylicytujPrzedmiot(Item item, List<Client> clients) {
        int buyer = -1;

        for (Client u : clients) {
            if (u.wantsToBuy(item)) {
                u.buyItem(item);
                buyer = u.id();
                break;
            }
        }

        return buyer;
    }
}

