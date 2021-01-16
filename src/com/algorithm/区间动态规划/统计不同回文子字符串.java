package com.algorithm.区间动态规划;

/*
* 给定一个字符串 S，找出 S 中不同的非空回文子序列个数，并返回该数字与 10^9 + 7 的模。

通过从 S 中删除 0 个或多个字符来获得子序列。

如果一个字符序列与它反转后的字符序列一致，那么它是回文字符序列。

如果对于某个  i，A_i != B_i，那么 A_1, A_2, ... 和 B_1, B_2, ... 这两个字符序列是不同的。

 

示例 1：

输入：
S = 'bccb'
输出：6
解释：
6 个不同的非空回文子字符序列分别为：'b', 'c', 'bb', 'cc', 'bcb', 'bccb'。
注意：'bcb' 虽然出现两次但仅计数一次。
示例 2：

输入：
S = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
输出：104860361
解释：
共有 3104860382 个不同的非空回文子序列，对 10^9 + 7 取模为 104860361。
 

提示：

字符串 S 的长度将在[1, 1000]范围内。
每个字符 S[i] 将会是集合 {'a', 'b', 'c', 'd'} 中的某一个。
* */

//三维dp,dp[x][i][j]为S[i,j]拥有不同回文子字符串的答案，其中x值的是当前串前后字符都为x+'a'
// class Solution {
//     public int countPalindromicSubsequences(String S) {
//         int n = S.length();
//         int mod = 1000000007;
//         int[][][] dp = new int[4][n][n];
//         for (int i = n - 1; i >= 0; i--) {
//             for (int j = i; j < n; j++) {
//                 for (int k = 0; k < 4; k++) {
//                     char c = (char)('a' + k);
//                     if (j == i) {
//                         if (S.charAt(i) == c) {
//                             dp[k][i][j] = 1;
//                         } else {
//                             dp[k][i][j] = 0;
//                         }
//                     } else { // j > i
//                         if (S.charAt(i) != c) {
//                             dp[k][i][j] = dp[k][i + 1][j];
//                         } else if (S.charAt(j) != c) {
//                             dp[k][i][j] = dp[k][i][j - 1];
//                         } else {
//                             if (j == i + 1) { //"aa" : {"a", "aa"}
//                                 dp[k][i][j] = 2;
//                             } else {
//                                 dp[k][i][j] = 2;
//                                 for (int m = 0; m < 4; m++) {
//                                     dp[k][i][j] += dp[m][i + 1][j - 1];
//                                     dp[k][i][j] %= mod;
//                                 }
//                             }
//                         }
//                     }
//                 }
//             }
//         }
//         int ans = 0;
//         for (int k = 0; k < 4; k++) {
//             ans += dp[k][0][n - 1];
//             ans %= mod;
//         }
//         return ans;
//     }
// }

//二维dp
/*
if s[i] == s[j]:
    dp[i][j] = dp[i+1][j-1] * 2 + 1  # 中间这个数就是两端的数 类似 “bcbcb”
             = dp[i+1][j-1] * 2 + 2 # 中间有两个数类似 “bcab”
             = dp[i+1][j-1] * 2 - dp[l + 1][r - 1]

*/

class 统计不同回文子字符串 {
    public int countPalindromicSubsequences(String S) {
        int n = S.length();
        int[][] dp = new int[n][n]; // dp[i][j]表示[i,j]下标范围内的回文子序列数量
        char[] ch = S.toCharArray();
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                if (ch[i] != ch[j]) {
                    dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
                } else {
                    dp[i][j] = dp[i + 1][j - 1] * 2;
                    int l = i + 1, r = j - 1;
                    while (l <= r && ch[l] != ch[i]) {
                        l += 1;
                    }
                    while (l <= r && ch[r] != ch[i]) {
                        r -= 1;
                    }
                    if (l > r) {
                        dp[i][j] += 2; // "bcab"
                    } else if (l == r) {
                        dp[i][j] += 1; // "bcbcb"
                    } else {
                        dp[i][j] -= dp[l + 1][r - 1]; // l < r , "bbcabb"
                    }
                }
                dp[i][j] = Math.floorMod(dp[i][j], 1000000007);
            }
        }
        return dp[0][n - 1];
    }
}