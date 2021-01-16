package com.algorithm.区间动态规划;

/*
* 有台奇怪的打印机有以下两个特殊要求：

打印机每次只能打印同一个字符序列。
每次可以在任意起始和结束位置打印新字符，并且会覆盖掉原来已有的字符。
给定一个只包含小写英文字母的字符串，你的任务是计算这个打印机打印它需要的最少次数。

示例 1:

输入: "aaabbb"
输出: 2
解释: 首先打印 "aaa" 然后打印 "bbb"。
示例 2:

输入: "aba"
输出: 2
解释: 首先打印 "aaa" 然后在第二个位置打印 "b" 覆盖掉原来的字符 'a'。
提示: 输入字符串的长度不会超过 100。
* */



//这道题的思路很难想， dp[left][right]代表打印left到right的最少打印次数，然后我们要理解一点：dp[left][right] 其实只可能有两个取值，dp[left][right-1]或者dp[left][right-1]+1，我们求最小打印次数其实就是希望增加一个字符后打印次数不变。
//枚举所有的区间[left,right]，然后枚举区间[left,right-1]内的字符，如果区间[left,right-1]内的某个字符i和right相等，那么right就可以跟随[i,right-1]一起顺带打印出来，这样该区间的

//dp[left][right]=dp[left][i-1]+dp[i][right-1]

//我的理解，如果在i,j区间内，某个index（k）与i相等，那么i就可以一次把它也打印了，那么dp[i][j] = dp[i][k - 1] + dp[k + 1][j]

// class Solution {
//     public int strangePrinter(String s) {
//         if (s == null || s.length() == 0) {
//             return 0;
//         }
//         int n = s.length();
//         int[][] dp = new int[n + 1][n + 1];
//         for (int i = 0; i < n; i++) {
//             dp[i][i] = 1;
//         }
//         for (int i = n - 2; i >= 0; i--) {
//             for (int j = i + 1; j < n; j++) {
//                 dp[i][j] = dp[i + 1][j] + 1;
//                 for (int k = i + 1; k <= j; k++) {
//                     if (s.charAt(k) == s.charAt(i)) {
//                         dp[i][j] = Math.min(dp[i][j], dp[i][k - 1] + dp[k + 1][j]);
//                     }
//                     // else {
//                     //     dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
//                     // }
//                 }
//             }
//         }
//         return dp[0][n - 1];
//     }
// }

// class 奇怪的打印机 {
//     public int strangePrinter(String s) {

//         int n = s.length();
//         if(n == 0) return 0;
//         int[][] dp = new int[n + 1][n + 1];

//         for(int i = 0; i < n; i++){
//             dp[i][i] = 1;
//         }
//         for(int len = 2; len <= n; len++){
//             for(int i = 0; i + len - 1 < n; i++){
//                 int j = i + len - 1;
//                 dp[i][j] = dp[i + 1][j] + 1;
//                 for(int k = i + 1; k <= j; k++){
//                     if(s.charAt(i) == s.charAt(k))
//                         dp[i][j] = Math.min(dp[i][j], dp[i][k - 1] + dp[k + 1][j]);
//                 }
//             }
//         }
//         return dp[0][n - 1];
//     }
// }


//这题递归的效率居然比dp高
class 奇怪的打印机 {
    int[][] memo;
    public int strangePrinter(String s) {
        int N = s.length();
        memo = new int[N][N];
        return dp(s, 0, N - 1);
    }
    public int dp(String s, int i, int j) {
        if (i > j) return 0;
        if (memo[i][j] == 0) {
            int ans = dp(s, i+1, j) + 1;
            for (int k = i+1; k <= j; ++k)
                if (s.charAt(k) == s.charAt(i))
                    ans = Math.min(ans, dp(s, i, k-1) + dp(s, k+1, j));
            memo[i][j] = ans;
        }
        return memo[i][j];
    }
}