package pl.wieczorekp.mim.oop.lab2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class TappingRainWaterTest {
    private static final int TESTS_NUM = (int) 1e4;
    private static final int HEIGHTS_LB = 0;
    private static final int HEIGHTS_UB = (int) 1e5;
    private static final int MAX_N = (int) 1e2;

    String getWaMsg(long got, long expected, int testNr) {
        return "WA (test " + testNr + "): (got: " + got + "; expected: " + expected + ")";
    }
    String getWaMsg(long got, long expected) {
        return getWaMsg(got, expected, -1);
    }

    @Test
    void solveManualTests() {
        int[] in = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int ans = TappingRainWater.solve(in);
        assertTrue(ans == 6, getWaMsg(ans, 6));

        in = new int[]{0, 1, 0, 2, 0};
        ans = TappingRainWater.solve(in);
        assertTrue(ans == 1, getWaMsg(ans, 1));

        in = new int[]{2, 10, 9, 11, 3};
        ans = TappingRainWater.solve(in);
        assertTrue(ans == 1, getWaMsg(ans, 1));

        in = new int[]{11, 10, 9, 5, 3};
        ans = TappingRainWater.solve(in);
        assertTrue(ans == 0, getWaMsg(ans, 0));

        in = new int[]{10, 10, 10, 10, 10};
        ans = TappingRainWater.solve(in);
        assertTrue(ans == 0, getWaMsg(ans, 0));

        in = new int[]{1, 2, 1, 2, 2, 2, 1, 2, 1, 3};
        ans = TappingRainWater.solve(in);
        assertTrue(ans == 3, getWaMsg(ans, 3));
    }

    @Test
    void solveAutomaticTests() {
        for (int i = 0; i < TESTS_NUM; i++) {
            long seed = f(i);
            Random r = new Random(seed);
            int[] heights1 = genArray(seed, HEIGHTS_LB, HEIGHTS_UB, r.nextInt(1, MAX_N));
            int[] heights2 = heights1;
            int ans = TappingRainWater.solve(heights1);
            int ansBrute = solveBrute(heights2);
            assertTrue(ans == ansBrute, getWaMsg(ans, ansBrute, i));
        }
    }

    private long f(long x) {
        final long MOD = 1000000007;
        final long n1 = (((x * x % MOD * 319 % MOD * x % MOD + 5) * x + 1) % MOD + x*120 % MOD * x % MOD + x % MOD + 32) % MOD;
        return (n1 + n1^x + 3) % MOD;
    }

    private int[] genArray(long seed, int lb, int ub, int len) {
        assert ub >= lb;
        assert lb >= 0;
        Random r = new Random(seed);
        return r.ints(len).map(x -> Math.abs(x)).map(x -> x % (ub-lb+1) + lb).toArray();
    }

    private int solveBrute(int[] height) {
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i+1; j < height.length; j++) {
                int lvl = Math.min(height[i], height[j]);
                for (int k = i+1; k < j; k++) {
                    if (height[k] < lvl) {
                        ans += lvl - height[k];
                        height[k] = lvl;
                    }
                }
            }
        }
        return ans;
    }
}