package com.algorithm.dp.双串字符串匹配;

/*
* 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。

'?' 可以匹配任何单个字符。
'*' 可以匹配任意字符串（包括空字符串）。
两个字符串完全匹配才算匹配成功。

说明:

s 可能为空，且只包含从 a-z 的小写字母。
p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
示例 1:

输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
* */

public class 通配符匹配 {
    //标准双重for 效率低
    // public boolean isMatch(String s, String p) {
    //     if (s.equals(p) || p.equals("*")) {
    //         return true;
    //     }
    //     if (p.isEmpty()) {
    //         return false;
    //     }
    //     if (s.isEmpty()) {
    //         for (int i = 0; i < p.length(); i++) {
    //             if (p.charAt(i) != '*') {
    //                 return false;
    //             }
    //         }
    //         return true;
    //     }
    //     int len1 = s.length(), len2 = p.length();
    //     boolean[][] dp = new boolean[len1 + 1][len2 + 1];
    //     int index = 1;
    //     while (index < len2 + 1 && p.charAt(index - 1) == '*') {
    //         dp[0][index] = true;
    //         index++;
    //     }
    //     dp[0][0] = true;
    //     for (int i = 1; i <= len1; i++) {
    //         for (int j = 1; j <= len2; j++) {
    //             if (p.charAt(j - 1) == '?') {
    //                 dp[i][j] = dp[i - 1][j - 1];
    //             } else if (p.charAt(j - 1) == '*') {
    //                 for (int k = i; k >= 0; k--) {
    //                     if (dp[k][j - 1]) {
    //                         dp[i][j] = true;
    //                         break;
    //                     }
    //                 }
    //             } else {
    //                 dp[i][j] = s.charAt(i - 1) == p.charAt(j - 1) && dp[i - 1][j - 1];
    //             }
    //         }
    //     }
    //     return dp[len1][len2];
    // }
    //单for循环，中间on赋值每一行 效率高
    public boolean isMatch(String s, String p) {
        if (s.equals(p) || p.equals("*")) {
            return true;
        }
        if (p.isEmpty()) {
            return false;
        }
        if (s.isEmpty()) {
            for (int i = 0; i < p.length(); i++) {
                if (p.charAt(i) != '*') {
                    return false;
                }
            }
            return true;
        }
        boolean[][] dp = new boolean[p.length()+1][s.length()+1];
        dp[0][0] = true;
        for (int i = 1; i < p.length()+1; i++) {
            if (p.charAt(i-1) == '*') {
                int j = 1;
                while (dp[i-1][j-1] == false && j < s.length()+1) {
                    j++;
                }
                dp[i][j-1] = dp[i-1][j-1];
                while (j < s.length()+1) {
                    dp[i][j++] = true;
                }
            }
            else if (p.charAt(i-1) == '?') {
                for (int j = 1; j < s.length()+1; j++) {
                    dp[i][j] = dp[i-1][j-1];
                }
            }
            else {
                for (int j =1; j < s.length()+1; j++) {
                    dp[i][j] = dp[i-1][j-1] && p.charAt(i-1) == s.charAt(j-1);
                }
            }
        }
        return dp[p.length()][s.length()];
    }
}
