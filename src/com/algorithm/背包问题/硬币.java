package com.algorithm.背包问题;

/*
* 硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)

示例1:

 输入: n = 5
 输出：2
 解释: 有两种方式可以凑成总金额:
5=5
5=1+1+1+1+1
示例2:

 输入: n = 10
 输出：4
 解释: 有四种方式可以凑成总金额:
10=10
10=5+5
10=5+1+1+1+1+1
10=1+1+1+1+1+1+1+1+1+1
说明：

注意:

你可以假设：

0 <= n (总金额) <= 1000000
* */

public class 硬币 {
    //这道题要注意硬币投放的顺序
    public int waysToChange(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int mod = 1000000007;
        int[] arr = new int[] {1, 5, 10, 25};
        //对arr遍历是为了投放顺序，比如6，规定了顺序就不会出现 1,5和5,1这样的重复值
        for (int num: arr) {
            for (int i = num; i <= n; i++) {
                dp[i] = (dp[i] + dp[i - num]) % mod;
            }
        }
        return dp[n];
        //下面就是错误示范
        // for (int i = 1; i <= n; i++) {
        //     if (i >= 1) {
        //         dp[i] = (dp[i - 1] + dp[i]) % mod;
        //     }
        //     if (i % 5 == 0) {
        //         dp[i] = (dp[i - 5] + dp[i]) % mod;
        //     }
        //     if (i % 10 == 0) {
        //         dp[i] = (dp[i - 10] + dp[i]) % mod;
        //     }
        //     if (i % 25 == 0) {
        //         dp[i] = (dp[i - 25] + dp[i]) % mod;
        //     }
        // }
        // System.out.println(Arrays.toString(dp));
    }
}
