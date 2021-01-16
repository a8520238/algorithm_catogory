package com.algorithm.dp;

public class 分割等和子集416 {
    // public boolean canPartition(int[] nums) {
    //     int res = 0;
    //     for (int i = 0; i < nums.length; i++) {
    //         res += nums[i];
    //     }
    //     if (res % 2 == 1) {
    //         return false;
    //     }
    //     res /= 2;
    //     //dp的含义是前i个数能否组成j，这样没次都只用判断第i-1个数
    //     boolean[][] dp = new boolean[nums.length + 1][res + 1];
    //     for (int i = 0; i < nums.length + 1; i++) {
    //         dp[i][0] = true;
    //     }

    //     for (int i = 1; i < nums.length + 1; i++) {
    //         for (int j = 1; j < res + 1; j++) {
    //             if (j - nums[i - 1] < 0) {
    //                 dp[i][j] = dp[i - 1][j];
    //             } else {
    //                 dp[i][j] = dp[i - 1][j - nums[i - 1]] | dp[i - 1][j];
    //             }
    //         }
    //     }
    //     return dp[nums.length][res];
    // }
    public boolean canPartition(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += nums[i];
        }
        if (res % 2 == 1) {
            return false;
        }
        res /= 2;
        boolean[] dp = new boolean[res + 1];
        dp[0] = true;
        for (int i = 0; i < nums.length; i++) {
            //j 应该从后往前反向遍历，因为每个物品（或者说数字）只能⽤⼀次，以免之前的结果影响其他的结果。
            for (int j = res; j >= 0; j--) {
                if (j - nums[i] >= 0) {
                    dp[j] = dp[j] || dp[j - nums[i]];
                }
            }
        }
        return dp[res];
    }
}
