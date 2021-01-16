package com.algorithm.区间动态规划;

/*
* 给定一个 非空 字符串，将其编码为具有最短长度的字符串。

编码规则是：k[encoded_string]，其中在方括号 encoded_string 中的内容重复 k 次。

注：

k 为正整数
如果编码的过程不能使字符串缩短，则不要对其进行编码。如果有多种编码方式，返回 任意一种 即可
 

示例 1：

输入：s = "aaa"
输出："aaa"
解释：无法将其编码为更短的字符串，因此不进行编码。
示例 2：

输入：s = "aaaaa"
输出："5[a]"
解释："5[a]" 比 "aaaaa" 短 1 个字符。
示例 3：

输入：s = "aaaaaaaaaa"
输出："10[a]"
解释："a9[a]" 或 "9[a]a" 都是合法的编码，和 "10[a]" 一样长度都为 5。
示例 4：

输入：s = "aabcaabcd"
输出："2[aabc]d"
解释："aabc" 出现两次，因此一种答案可以是 "2[aabc]d"。
示例 5：

输入：s = "abbbabbbcabbbabbbc"
输出："2[2[abbb]c]"
解释："abbbabbbc" 出现两次，但是 "abbbabbbc" 可以编码为 "2[abbb]c"，因此一种答案可以是 "2[2[abbb]c]"。
 

提示：

1 <= s.length <= 150
s 由小写英文字母组成
*
* */


public class 编码最短长度的字符串 {
    String[][] dp;
    public String encode(String s) {
        int n = s.length();
        dp = new String[n][n];

        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                dp[i][j] = lc459(s, i, j);
                if (len > 4) {
                    for (int k = i; k < j; k++) {
                        String split = dp[i][k] + dp[k + 1][j];
                        if (dp[i][j].length() > split.length()) {
                            dp[i][j] = split;
                        }
                    }
                }
            }
        }
        return dp[0][n - 1];
    }
    /*
     * 另 t = s + s, 从下标 1 的字符开始查找字符串s， 找到下标p，
     * 如果p != n, 存在连续重复的子字符串ps = s.substring(0, p), 个数为 n / p
     * 否则， 不存在连续重复子字符串， 无法进行编码
     */
    public String lc459(String s, int i, int j) {
        s = s.substring(i, j + 1);
        if (s.length() < 5) {
            return s;
        }
        int p = (s + s).indexOf(s, 1);
        if (p != s.length()) {
            int cnt = s.length() / p;
            return cnt + "[" + dp[i][i + p - 1] + "]";
        }
        return s;
    }
}
