package com.algorithm.区间动态规划;
/*
给定一个布尔表达式和一个期望的布尔结果 result，布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR) 符号组成。实现一个函数，算出有几种可使该表达式得出 result 值的括号方法。

        示例 1:

        输入: s = "1^0|0|1", result = 0

        输出: 2
        解释: 两种可能的括号方法是
        1^(0|(0|1))
        1^((0|0)|1)
        示例 2:

        输入: s = "0&0&0&1^1|0", result = 1

        输出: 10
        提示：

        运算符的数量不超过 19 个
*/

public class 布尔运算 {
    public int countEval(String s, int result) {
        if (s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return (s.charAt(0) - '0') == result? 1: 0;
        }
        char[] ch = s.toCharArray();
        int[][][] dp = new int[ch.length][ch.length][2];
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '0' || ch[i] == '1') {
                dp[i][i][ch[i] - '0'] = 1;
            }
        }
        for (int len = 2; len <= ch.length; len += 2) {
            for (int i = 0; i <= ch.length - len; i += 2) {
                int j = i + len;
                for (int k = i + 1; k <= j - 1; k += 2) {
                    if (ch[k] == '&') {
                        dp[i][j][0] += dp[i][k - 1][0] * dp[k + 1][j][0] + dp[i][k - 1][0] * dp[k + 1][j][1] + dp[i][k - 1][1] * dp[k + 1][j][0];
                        dp[i][j][1] += dp[i][k - 1][1] * dp[k + 1][j][1];
                    }
                    if (ch[k] == '|') {
                        dp[i][j][0] += dp[i][k - 1][0] * dp[k + 1][j][0];
                        dp[i][j][1] += dp[i][k - 1][0] * dp[k + 1][j][1] + dp[i][k - 1][1] * dp[k + 1][j][0] + dp[i][k - 1][1] * dp[k + 1][j][1];
                    }
                    if (ch[k] == '^') {
                        dp[i][j][0] += dp[i][k - 1][0] * dp[k + 1][j][0] + dp[i][k - 1][1] * dp[k + 1][j][1];
                        dp[i][j][1] += dp[i][k - 1][1] * dp[k + 1][j][0] + dp[i][k - 1][0] * dp[k + 1][j][1];
                    }
                }
            }
        }
        return dp[0][ch.length - 1][result];
    }
}
