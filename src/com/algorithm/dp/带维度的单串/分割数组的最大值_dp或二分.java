package com.algorithm.dp.带维度的单串;

public class 分割数组的最大值_dp或二分 {
    //dp解法
    public int splitArray(int[] nums, int m) {
        int len = nums.length;
        int[][] dp = new int[m + 1][len];
        dp[1][0] = nums[0];
        for (int i = 1; i < len; i++) {
            dp[1][i] = nums[i] + dp[1][i - 1];
        }
        //思路：对于当前位置cur，将0至cur-1，配给前m-1个(最大情况)，将0至m-2配给前m-1个（最小情况）
        for (int i = 2; i <= m; i++) {
            for (int j = i - 1; j < len; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i - 2; k < j; k++) {
                    // dp[i][j] = Math.min(dp[i][j], Math.max(dp[i - 1][k], sum(nums, k + 1, j)));
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[i - 1][k], dp[1][j] - dp[1][k]));
                }
            }
        }
        return dp[m][len - 1];
    }
    //这题看的第一眼感觉是个二分
//    public int splitArray(int[] nums, int m) {
//        int sum = 0;
//        int min = 0;
//        for (int num: nums) {
//            sum += num;
//            min = Math.max(num, min);
//        }
//        int left = min;
//        int right = sum;
//        while (left < right) {
//            int mid = (right - left) / 2 + left;
//            if (match(nums, mid, m)) {
//                right = mid;
//            } else {
//                left = mid + 1;
//            }
//        }
//        return left;
//    }
//    public boolean match(int[] nums, int mid, int m) {
//        int sum = 0;
//        for (int i = 0; i < nums.length; i++) {
//            sum += nums[i];
//            if (sum > mid) {
//                i--;
//                m--;
//                sum = 0;
//            }
//        }
//        return m >= 1;
//    }
}
