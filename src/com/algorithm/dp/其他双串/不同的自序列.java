package com.algorithm.dp.其他双串;

import java.util.Arrays;

/*
*
* 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。

字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）

题目数据保证答案符合 32 位带符号整数范围。

示例 1：

输入：s = "rabbbit", t = "rabbit"
输出：3
解释：
如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
(上箭头符号 ^ 表示选取的字母)
rabbbit
^^^^ ^^
rabbbit
^^ ^^^^
rabbbit
^^^ ^^^

* */

public class 不同的自序列 {
    public int numDistinct(String s, String t) {
        int len1 = t.length(), len2 = s.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        //dp含义s的前j个字符中，包含多少个t的前i个的自序列。对任意dp[i][j],如果i和j位置的字符相等，要算上j-1之前有多少相等，还要算上i - 1中有多少j - 1中的自序列，如果不等后面的不用算上。
        //要进行初始化，s中没个都有一个长度为0的t的自序列，所以dp[0]初始化为1
        Arrays.fill(dp[0], 1);
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i][j - 1] +  dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[len1][len2];
    }
}
