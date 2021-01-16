package com.algorithm.dp;

import java.util.Arrays;

/*
* 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。

返回符合要求的最少分割次数。

示例:

输入: "aab"
输出: 1
解释: 进行一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。

* */

//方案一，两次dp,第一次得到从j到i那些能成为回文串，第二次得到到j能得到回文串的最小值
// class 分割回文串II {
//     public int minCut(String s) {
//         if (s == null || s.length() == 0) {
//             return 0;
//         }
//         int len = s.length();
//         //dp代表从j到i能否成为回文串
//         boolean[][] dp = new boolean[len][len];
//         // for (int i = 0; i < len; i++) {
//         //     for (int j = i ; j < len; j++) {
//         //         if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1])) {
//         //             dp[i][j] = true;
//         //         }
//         //     }
//         // }
//         //这样遍历可以保证dp[start+1][end-1]在dp[start][end]之前执行
//         for (int end = 0; end < len; end++) {
//             for (int start = 0; start <= end; start++) {
//                 if (s.charAt(start) == s.charAt(end) && (end-start <= 2 || dp[start+1][end-1])) {
//                     dp[start][end] = true;
//                 }
//             }
//         }
//         //dp2代表到当前i长度为止能分割的最小次数
//         int[] dp2 = new int[len + 1];
//         for (int i = 1; i <= len; i++) {
//             dp2[i] = len;
//         }
//         for (int i = 0; i < len; i++) {
//             for (int j = 0; j <= i; j++) {
//                 if (dp[j][i]) {
//                     dp2[i + 1] = Math.min(dp2[j] + 1, dp2[i + 1]);
//                 }
//             }
//         }

//         return dp2[len] - 1;
//     }
// }
//方案二， 与方案一的不同在于判断回文子串的方式
class 分割回文串II {
    public int minCut(String s) {
        int len = s.length();
        boolean[][] can = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            prePro(can, s, i, i, len);
            prePro(can, s, i, i + 1, len);
        }
        int[] dp = new int[len + 1];
        Arrays.fill(dp, len);
        dp[0] = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                if (can[j][i]) {
                    dp[i + 1] = Math.min(dp[j] + 1, dp[i + 1]);
                }
            }
        }
        return dp[len] - 1;
    }
    private void prePro(boolean[][] dp, String str, int left, int right, int len) {
        while (left >= 0 && right < len && str.charAt(left) == str.charAt(right)) {
            dp[left][right] = true;
            left--;
            right++;
        }
    }
}