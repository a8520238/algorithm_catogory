package com.algorithm.dp.需要两个位置的单串问题变形;

import java.util.HashMap;
import java.util.Map;

/*
* 给定一个整数数组 A，返回 A 中最长等差子序列的长度。

回想一下，A 的子序列是列表 A[i_1], A[i_2], ..., A[i_k] 其中 0 <= i_1 < i_2 < ... < i_k <= A.length - 1。并且如果 B[i+1] - B[i]( 0 <= i < B.length - 1) 的值都相同，那么序列 B 是等差的。

示例 1：

输入：[3,6,9,12]
输出：4
解释：
整个数组是公差为 3 的等差数列。
示例 2：

输入：[9,4,7,2,10]
输出：3
解释：
最长的等差子序列是 [4,7,10]。

* */
class 最长等差数列 {
    public int longestArithSeqLength(int[] A) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = A.length;
        // for (int i = 0; i < n; i++) {
        //     map.put(A[i], i);
        // }
        int[][] dp = new int[n - 1][n];
        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int pre = 2 * A[i] - A[j];
                // if (map.containsKey(pre)) {
                if (map.containsKey(pre) && map.get(pre) < i) {
                    dp[i][j] = dp[map.get(pre)][i] + 1;
                }
                res = Math.max(res, dp[i][j] + 2);
            }
            //map在这里赋值 意味着当前值已经过了再赋值，之所以不能像上面那样赋值，是因为会有重复值
            map.put(A[i], i);
        }
        return res;
    }
}


/*
这个思路可以这么理解，dp[i] > dp[j]，dp[i]与dp[j]的差为diff，那么dp[i][diff] = max(dp[i][diff], dp[j][diff] + 1);
dp的含义是，到i为止，差为diff的最大值
*/
// class 最长等差数列 {
//     public int longestArithSeqLength(int[] A) {
//         if (A == null || A.length == 0) {
//             return 0;
//         }
//         int n = A.length;
//         int[][] dp = new int[n][20010];
//         int res = 0;
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < i; j++) {
//                 int diff = A[i] - A[j];
//                 int offset = 10000;
//                 diff += offset;
//                 dp[i][diff] = Math.max(dp[i][diff], dp[j][diff] + 1);
//                 res = Math.max(dp[i][diff] + 1, res);
//             }
//         }
//         return res;
//     }
// }