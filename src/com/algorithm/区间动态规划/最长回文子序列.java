package com.algorithm.区间动态规划;

/*
* 给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。

 

示例 1:
输入:

"bbbab"
输出:

4
一个可能的最长回文子序列为 "bbbb"。

示例 2:
输入:

"cbbd"
输出:

2
一个可能的最长回文子序列为 "bb"。

 

提示：

1 <= s.length <= 1000
s 只包含小写英文字母
* */

public class 最长回文子序列 {
    //这道题要想到dp才能好做，不然都白扯
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }
        for (int k = 1; k < len; k++) {
            for (int i = 0; i + k < len; i++) {
                if (s.charAt(i) == s.charAt(i + k)) {
                    dp[i][i + k] = dp[i + 1][i + k - 1] + 2;
                } else {
                    dp[i][i + k] = Math.max(dp[i + 1][i + k], dp[i][i + k - 1]);
                }
            }
        }
        return dp[0][len - 1];
    }
}
