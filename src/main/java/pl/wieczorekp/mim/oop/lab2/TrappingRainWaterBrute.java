package pl.wieczorekp.mim.oop.lab2;

public class TrappingRainWaterBrute {
    private int[] height;

    public TrappingRainWaterBrute(int[] height) {
        this.height = height;
    }

    public int solve() {
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
