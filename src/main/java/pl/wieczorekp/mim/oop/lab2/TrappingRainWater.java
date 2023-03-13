package pl.wieczorekp.mim.oop.lab2;

// https://leetcode.com/problems/trapping-rain-water/
// Complexity:
//   T(n) = O(n)
//   M(n) = O(1)
public class TrappingRainWater {
    public static int solve(int[] height) {
        int minIdx = getMinIdx(height);
        int ans = calcSlope(height, minIdx+1);
        reverseArray(height);
        ans += calcSlope(height, height.length-minIdx);
        return ans;
    }

    public static int calcSlope(int[] height, int n) {
        int l = 0, sum = 0, ans = 0;
        for (int i = 0; i < n; i++) {
            if (height[i] >= height[l]) {
                // dodaj
                ans += (i-l)*height[l] - sum;
                // przesun
                l = i;
                sum = 0;
            }
            sum += height[i];
        }
        return ans;
    }

    private static int getMinIdx(int[] arr) {
        int  minIdx = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > arr[minIdx]){
                minIdx = i;
            }
        }
        return minIdx;
    }
    private static void reverseArray(int[] arr) {
        for (int i = 0; i < arr.length/2; i++) {
            int a = arr[arr.length-i-1];
            arr[arr.length-i-1] = arr[i];
            arr[i] = a;
        }
    }
}
