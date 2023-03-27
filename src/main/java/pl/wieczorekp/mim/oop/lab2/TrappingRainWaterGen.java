package pl.wieczorekp.mim.oop.lab2;

import java.util.Random;

public class TrappingRainWaterGen {
    private Random rng;
    private int lb;
    private int ub;
    private final int MAX_ARRAY_LEN;
    private final int MIN_ARRAY_LEN;
    private final long MOD = 1000000007;

    public TrappingRainWaterGen(long seed, int lb, int ub, int maxArrayLen) {
        this(seed, lb, ub, 0, maxArrayLen);
    }

    public TrappingRainWaterGen(long seed, int lb, int ub, int minArrayLen, int maxArrayLen) {
        rng = new Random(deriveSeed(seed));
        this.lb = lb;
        this.ub = ub;
        this.MIN_ARRAY_LEN = minArrayLen;
        this.MAX_ARRAY_LEN = maxArrayLen;
    }

    public int[] genArray(int len) {
        assert ub >= lb;
        assert lb >= 0;

        return rng.ints(len).map(x -> Math.abs(x)).map(x -> x % (ub-lb+1) + lb).toArray();
    }

    public int[] genArray() {
        return genArray(rng.nextInt(1, MAX_ARRAY_LEN+1));
    }

    private long deriveSeed(long x) {
        long y = (((x * x % MOD * 319 % MOD * x % MOD + 5) * x + 1) % MOD + x*120 % MOD * x % MOD + x % MOD + 32) % MOD;
        return (y + y^x + 3) % MOD;
    }
}
