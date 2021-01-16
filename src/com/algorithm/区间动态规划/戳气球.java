package com.algorithm.区间动态规划;

/*
* 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。

现在要求你戳破所有的气球。如果你戳破气球 i ，就可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。

求所能获得硬币的最大数量。

说明:

你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
示例:

输入: [3,1,5,8]
输出: 167
解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
     coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
* */

//这题要切记dp是最后一个戳爆的，和开区间，闭区间之分
public class 戳气球 {
    //这道题的dp思路要明确，dp[i][j],表示i到j最多的钱，其中k表示最后一个戳爆的
    public int maxCoins(int[] nums) {
        int len = nums.length;
        int[] arr = new int[len + 2];
        System.arraycopy(nums, 0, arr, 1, len);
        arr[0] = 1;
        arr[len + 1] = 1;
        int[][] dp = new int[len + 2][len + 2];
        // for (int i = 1; i <= len; i++) {
        //     dp[i][i] = arr[i];
        // }
        for (int i = len; i >= 1; i--) {
            for (int j = i; j < len + 1; j++) {
                // for (int m = 0; m < len; m++) {
                //     for (int i = 1, j = i + m; i < len + 1 && j < len + 1; i++, j++) {
                int best = 0;
                for (int k = i; k <= j; k++) {
                    best = Math.max(best, dp[i][k - 1] + dp[k + 1][j] + arr[k] * arr[i - 1] * arr[j + 1]);
                }
                dp[i][j] = best;
            }
        }
        return dp[1][len];
    }
    //这里思路是将i,j看成开区间
    // public int maxCoins(int[] nums) {
    //     int n = nums.length;
    //     int[][] rec = new int[n + 2][n + 2];
    //     int[] val = new int[n + 2];
    //     val[0] = val[n + 1] = 1;
    //     for (int i = 1; i <= n; i++) {
    //         val[i] = nums[i - 1];
    //     }
    //     for (int i = n - 1; i >= 0; i--) {
    //         for (int j = i + 2; j <= n + 1; j++) {
    //             for (int k = i + 1; k < j; k++) {
    //                 int sum = val[i] * val[k] * val[j];
    //                 sum += rec[i][k] + rec[k][j];
    //                 rec[i][j] = Math.max(rec[i][j], sum);
    //             }
    //         }
    //     }
    //     return rec[0][n + 1];
    // }
}
