package com.algorithm.dp.打家劫舍;

public class 打家劫舍2_213 {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        return Math.max(dpRob(nums, 0, n - 2), dpRob(nums, 1, n - 1));
    }
    private int dpRob(int nums[], int start, int end) {
        int dp1 = 0, dp2 = 0, dp = 0;
        for (int i = start; i <= end; i++) {
            dp = Math.max(dp2, dp1 + nums[i]);
            dp1 = dp2;
            dp2 = dp;
        }
        return dp;
    }
}
