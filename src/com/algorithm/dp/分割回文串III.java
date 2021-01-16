package com.algorithm.dp;

/*
*给你一个由小写字母组成的字符串 s，和一个整数 k。

请你按下面的要求分割字符串：

首先，你可以将 s 中的部分字符修改为其他的小写英文字母。
接着，你需要把 s 分割成 k 个非空且不相交的子串，并且每个子串都是回文串。
请返回以这种方式分割字符串所需修改的最少字符数。

 

示例 1：

输入：s = "abc", k = 2
输出：1
解释：你可以把字符串分割成 "ab" 和 "c"，并修改 "ab" 中的 1 个字符，将它变成回文串。

* */
class 分割回文串III {
    public int palindromePartition(String s, int k) {
        //pali[i][j]的含义是，从i到j内的字符串需要几次调整才能变为回文串
        int[][] pali = new int[s.length() + 1][s.length() + 1];
        for (int i = s.length(); i >= 1; i--) {
            for (int j = i; j <= s.length(); j++) {
                if (j - i >= 2) {
                    pali[i][j] = pali[i + 1][j - 1];
                }
                if (s.charAt(i - 1) != s.charAt(j - 1)) {
                    pali[i][j]++;
                }
            }
        }
        //dp[i][j]的含义，表示将从0开始长度为j的字符串，分割为i段，需要修改的最少字符数
        int[][] dp = new int[k + 1][s.length() + 1];
        for (int i = 1; i <= k; i++) {
            for (int j = i; j <= s.length(); j++) {
                if (i == 1) {
                    dp[i][j] = pali[i][j];
                } else {
                    dp[i][j] = dp[i - 1][i - 1] + pali[i][j];
                    //这里x不能是j,因为那将失去意义
                    for (int x = i; x < j; x++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][x] + pali[x + 1][j]);
                    }
                }
            }
        }
        return dp[k][s.length()];
    }
}
