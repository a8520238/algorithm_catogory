package com.algorithm.dp.双串字符串匹配;

public class 正则表达式匹配 {
    //反向dp
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[s.length()][p.length()] = true;
        for (int i = s.length(); i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                boolean first = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
                if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                    //第一项是啥也不匹，第二项是匹至少一个
                    dp[i][j] = dp[i][j + 2] || first && dp[i + 1][j];
                } else {
                    dp[i][j] = first && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }
    //正向dp 比较难想
    // public boolean isMatch(String s, String p) {
    //     int row = s.length();
    //     int col = p.length();
    //     //dp含义s串中前i个与p串中前j个是否匹配
    //     boolean[][] dp = new boolean[row + 1][col + 1];
    //     dp[0][0] = true;
    //     if (row > 0 && col > 0) {
    //         dp[1][1] = s.charAt(0) == p.charAt(0) || p.charAt(0) == '.';
    //     }
    //     for (int j = 2; j < col + 1; j += 2) {
    //         if (p.charAt(j-1) == '*') {
    //             dp[0][j] = true;
    //         } else {
    //             break;
    //         }
    //     }
    //     for (int i = 1; i < row + 1; i++) {
    //         for (int j = 2; j < col + 1; j++) {
    //             //不是*的时候直接匹配
    //             if (p.charAt(j - 1) != '*') {
    //                 dp[i][j] = (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') && dp[i-1][j-1];
    //             } else {
    //                 //是*的时候考虑两种情况
    //                 //1 前面的值能匹上while匹配s串
    //                 //2 p串为0
    //                 boolean match = false;
    //                 int cur = i;
    //                 while (cur - 1 >= 0 && (p.charAt(j - 2) == s.charAt(cur - 1) || p.charAt(j - 2) == '.')) {
    //                     if (dp[cur-1][j-2]) {
    //                         match = true;
    //                         break;
    //                     }
    //                     cur--;
    //                 }
    //                 if (dp[i][j-2]) {
    //                     match = true;
    //                 }
    //                 dp[i][j] = match;
    //             }
    //         }
    //     }
    //     return dp[row][col];
    // }
}
