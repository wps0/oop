package pl.wieczorekp.mim.oop.auction.clients;

import org.junit.jupiter.api.Test;
import pl.wieczorekp.mim.oop.auction.Item;

import static org.junit.jupiter.api.Assertions.*;

class HistorianTest {
    @Test
    void wantsToBuyTest() {
        // given
        Item iOk1 = new Item(2022, 250, "Scotland");
        Item iOk2 = new Item(1792, 250, "Scotland");
        Item iOk3 = new Item(1800, 250, "Scotland");
        Item iNotOk1 = new Item(2023, 200, "Scotland");
        Item iNotOk2 = new Item(1791, 280, "Poland");
        Item iNotOk3 = new Item(966, 99, "Roland");
        Client c = new Historian(123, "client", 2000, 1792, 2022);

        // when & then
        assertTrue(c.wantsToBuy(iOk1));
        assertTrue(c.wantsToBuy(iOk2));
        assertTrue(c.wantsToBuy(iOk3));
        assertFalse(c.wantsToBuy(iNotOk1));
        assertFalse(c.wantsToBuy(iNotOk2));
        assertFalse(c.wantsToBuy(iNotOk3));
    }
}