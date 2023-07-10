package pl.wieczorekp.mim.oop.algorithmic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrappingRainWaterTest {
    private static final int AUTOMATIC_TESTS_AMOUNT = 1_00;
    private static final int AUTOMATIC_TESTS_SEED = 910_518_740;
    private static final int AUTOMATIC_TESTS_HEIGHTS_LB = 0;
    private static final int AUTOMATIC_TESTS_HEIGHTS_UB = (int) 1e5;
    private static final int AUTOMATIC_TESTS_MAX_LEN = (int) 1e3;

    String getWaMsg(int testNr) {
        return "WA (test " + testNr + ")";
    }

    @Test
    void givenManualTestsShouldSolveTheProblem() {
        // given
        int[] in1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] in2 = {0, 1, 0, 2, 0};
        int[] in3 = {2, 10, 9, 11, 3};
        int[] in4 = {11, 10, 9, 5, 3};
        int[] in5 = {10, 10, 10, 10, 10};
        int[] in6 = {1, 2, 1, 2, 2, 2, 1, 2, 1, 3};

        // when
        int ans1 = TrappingRainWater.solve(in1);
        int ans2 = TrappingRainWater.solve(in2);
        int ans3 = TrappingRainWater.solve(in3);
        int ans4 = TrappingRainWater.solve(in4);
        int ans5 = TrappingRainWater.solve(in5);
        int ans6 = TrappingRainWater.solve(in6);

        // then
        assertEquals(6, ans1, getWaMsg(1));
        assertEquals(1, ans2, getWaMsg(2));
        assertEquals(1, ans3, getWaMsg(3));
        assertEquals(0, ans4, getWaMsg(4));
        assertEquals(0, ans5, getWaMsg(5));
        assertEquals(3, ans6, getWaMsg(6));
    }

    @Timeout(value = 1)
    @ParameterizedTest(name = "seed = {0}")
    @MethodSource("provideSeedsForAutomaticTests")
    void shouldTestSolveMethodAutomatically(long seed) {
        // given
        TrappingRainWaterGen gen = new TrappingRainWaterGen(seed, AUTOMATIC_TESTS_HEIGHTS_LB, AUTOMATIC_TESTS_HEIGHTS_UB, AUTOMATIC_TESTS_MAX_LEN);
        int[] heights1 = gen.genArray();
        int[] heights2 = heights1;

        // when
        int ans = TrappingRainWater.solve(heights1);
        int ansBrute = new TrappingRainWaterBrute(heights2).solve();

        // then
        assertEquals(ansBrute, ans, getWaMsg((int) seed));
    }

    @Test
    @Timeout(value = 85, unit = TimeUnit.MILLISECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
    void givenBigDataShouldRunInLinearTime1() {
        // given
        TrappingRainWaterGen gen = new TrappingRainWaterGen(92331132, 0, 2<<28, 100_000, 100_000);
        int[] bigTest = gen.genArray();

        // when
        int ans = TrappingRainWater.solve(bigTest);

        // then
        assertEquals(1101294971, ans);
    }

    @Test
    @Timeout(value = 65, unit = TimeUnit.MILLISECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
    void givenBigDataShouldRunInLinearTime2() {
        // given
        TrappingRainWaterGen gen = new TrappingRainWaterGen(2<<23+3, 0, 2<<11, 1_000_000, 1_000_000);
        int[] bigTest = gen.genArray();

        // when
        int ans = TrappingRainWater.solve(bigTest);

        // then
        assertEquals(980752610, ans);
    }

    private static LongStream provideSeedsForAutomaticTests() {
        LongStream.Builder seedStreamBuilder = LongStream.builder().add(1);
        Random rng = new Random(AUTOMATIC_TESTS_SEED);

        for (int i = 0; i < AUTOMATIC_TESTS_AMOUNT; i++) {
            seedStreamBuilder = seedStreamBuilder.add(rng.nextInt(1, Integer.MAX_VALUE));
        }

        return seedStreamBuilder.build();
    }
}