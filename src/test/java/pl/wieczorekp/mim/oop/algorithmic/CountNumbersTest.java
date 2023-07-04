package pl.wieczorekp.mim.oop.algorithmic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CountNumbersTest {
    private static final int MAX_N = (int) 1e4;

    String get_wa_msg(int n, long got, long expected) {
        return "WA: n=" + n + " (got: " + got + "; expected: " + expected + ")";
    }

    @Test
    void count_bits_pow2() {
        for (int i = 0; i <= 29; i++) {
            long ans = CountNumbers.countBits(1<<i);
            long ans_exp = count_bits_brute(1<<i);
            assertTrue(ans == ans_exp, get_wa_msg(1<<i, ans, ans_exp));
        }
    }

    @Test
    void count_bits_selected() {
        int n = Integer.parseInt("1001000", 2);
        long ans = CountNumbers.countBits(n);
        long brute = count_bits_brute(n);
        assertTrue(ans == brute, get_wa_msg(n, ans, brute));
    }

    @Test
    void count_bits_all() {
        for (int i = 0; i < MAX_N; i++) {
            long ans = CountNumbers.countBits(i);
            long ans_exp = count_bits_brute(i);
            assertTrue(ans == ans_exp, get_wa_msg(i, ans, ans_exp));
        }
    }

    private long count_bits_brute(int n) {
        long cnt = 0;
        for (int i = 0; i <= n; i++) {
            cnt += Integer.bitCount(i);
        }
        return cnt;
    }
}