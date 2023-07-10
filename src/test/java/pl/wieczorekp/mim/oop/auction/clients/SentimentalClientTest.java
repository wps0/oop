package pl.wieczorekp.mim.oop.auction.clients;

import org.junit.jupiter.api.Test;
import pl.wieczorekp.mim.oop.auction.Item;

import static org.junit.jupiter.api.Assertions.*;

class SentimentalClientTest {

    @Test
    void wantsToBuyTest() {
        // given
        Item iCorrect = new Item(100, 100, "Poland");
        Item iIncorrect = new Item(100, 100, "Roland");
        Client c = new SentimentalClient(123, "client", 2000, "Poland");

        // when
        boolean iCorrectActual = c.wantsToBuy(iCorrect);
        boolean iIncorrectActual = c.wantsToBuy(iIncorrect);

        // then
        assertTrue(iCorrectActual);
        assertFalse(iIncorrectActual);
    }
}