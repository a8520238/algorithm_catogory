package com.algorithm.区间动态规划;

/*
* 给你一个字符串 s ，每一次操作你都可以在字符串的任意位置插入任意字符。

请你返回让 s 成为回文串的 最少操作次数 。

「回文串」是正读和反读都相同的字符串。

示例 1：

输入：s = "zzazz"
输出：0
解释：字符串 "zzazz" 已经是回文串了，所以不需要做任何插入操作。
示例 2：

输入：s = "mbadm"
输出：2
解释：字符串可变为 "mbdadbm" 或者 "mdbabdm" 。
示例 3：

输入：s = "leetcode"
输出：5
解释：插入 5 个字符后字符串变为 "leetcodocteel" 。
示例 4：

输入：s = "g"
输出：0
示例 5：

输入：s = "no"
输出：1
 

提示：

1 <= s.length <= 500
s 中所有字符都是小写字母。
* */

//与486和516相似
class 让字符串成为回文串的最少插入次数 {
    //区间动态规划思路
    // public int minInsertions(String s) {
    //     int len = s.length();
    //     int[][] dp = new int[len][len];
    //     for (int i = 0; i < len; i++) {
    //         dp[i][i] = 0;
    //     }
    //     for (int i = len - 2; i >= 0; i--) {
    //         for (int j = i + 1; j < len; j++) {
    //             if (s.charAt(i) == s.charAt(j)) {
    //                 dp[i][j] = dp[i + 1][j - 1];
    //             } else {
    //                 dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
    //             }
    //         }
    //     }
    //     return dp[0][len - 1];
    // }
    //官方题解分析，字符串长度n-最长回文子序列，即为最小插入次数
    public int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return n - dp[0][n - 1];
    }
}