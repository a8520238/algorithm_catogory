package com.algorithm.dp.无串线性问题;

/*
*给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。

示例 1:

输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1。
示例 2:

输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
说明: 你可以假设 n 不小于 2 且不大于 58。

* */
class 整数拆分 {
    // public int integerBreak(int n) {
    //     if (n <= 1) {
    //         return 0;
    //     }
    //     if (n <= 3) {
    //         return n - 1;
    //     }
    //     int res = 1;
    //     while (n > 3) {
    //         if (n == 4) {
    //             break;
    //         }
    //         res *= 3;
    //         n -= 3;
    //     }
    //     res *= n;
    //     return res;
    // }
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            int curMax = 0;
            for (int j = 1; j < i; j++) {
                //选定一个整数j后，i - j不继续拆分j * (i - j)， 与继续拆分j * dp[i - j])的最大值为curMax
                curMax = Math.max(curMax, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = curMax;
        }
        return dp[n];
    }
}