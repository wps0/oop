package pl.wieczorekp.mim.oop.lab1;

public class CountNumbers {
    // amount of ones in binary representations of numbers [0, 2^i-1]
    private static long[] ones;
    static {
        init_tab();
    }

    private static void init_tab() {
        ones = new long[Integer.SIZE];
        ones[0] = 0;
        for (int i = 1; i < Integer.SIZE; i++) {
            ones[i] = 2* ones[i-1] + (1<<(i-1));
        }
    }

    public static long count_bits(int n) {
        long cnt = 0, before = 0;
        for (int i = Integer.SIZE-1; i >= 0; i--) {
            final int CUR_BIT = 1<<i;

            if ((CUR_BIT & n) != 0) {
                cnt += before*CUR_BIT + ones[i] + 1;
                before++;
            }
        }
        return cnt;
    }

}