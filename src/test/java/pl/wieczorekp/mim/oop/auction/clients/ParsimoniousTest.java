package pl.wieczorekp.mim.oop.auction.clients;

import org.junit.jupiter.api.Test;
import pl.wieczorekp.mim.oop.auction.Item;

import static org.junit.jupiter.api.Assertions.*;

class ParsimoniousTest {

    @Test
    void wantsToBuyTest() {
        // given
        Item iFirst = new Item(100, 200, "Poland");
        Item iSecond = new Item(100, 99, "Roland");
        Client c = new Parsimonious(123, "client", 2000);

        // when
        boolean iFirst1 = c.wantsToBuy(iFirst);
        boolean iSecond1 = c.wantsToBuy(iSecond);
        boolean iSecond2 = c.wantsToBuy(iSecond);

        // then
        assertFalse(iFirst1);
        assertTrue(iSecond1);
        assertTrue(iSecond2);
    }
}