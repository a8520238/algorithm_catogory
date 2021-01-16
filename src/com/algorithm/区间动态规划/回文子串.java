package com.algorithm.区间动态规划;

/*
* 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。

具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。

 

示例 1：

输入："abc"
输出：3
解释：三个回文子串: "a", "b", "c"
示例 2：

输入："aaa"
输出：6
解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 

提示：

输入的字符串长度不会超过 1000 。

* */

public class 回文子串 {
    //中心扩展
    // public int countSubstrings(String s) {
    //     int len = s.length();
    //     int res = 0;
    //     for (int i = 0; i < len; i++) {
    //         res += process(s, i, i);
    //         if (i + 1 < len) {
    //             res += process(s, i, i + 1);
    //         }
    //     }
    //     return res;
    // }
    // public int process(String s, int left, int right) {
    //     int count = 0;
    //     while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
    //         left--;
    //         right++;
    //         count++;
    //     }
    //     return count;
    // }
    //马拉车
    public int countSubstrings(String s) {
        int n = s.length();
        StringBuilder builder = new StringBuilder("*");
        for (int i = 0; i < n; i++) {
            builder.append(s.charAt(i));
            builder.append("*");
        }
        int m = 2 * n + 1;
        //len代表半径
        int[] len = new int[m];
        int maxRight = 0, maxIndex = 0;
        int res = 0;
        for (int i = 0; i < m; i++) {
            len[i] = i < maxRight? Math.min(len[2 * maxIndex - i], maxRight - i): 0;
            int left = i - len[i] - 1;
            int right = i + len[i] + 1;
            while (left >= 0 && right < m && builder.charAt(left) == builder.charAt(right)) {
                left--;
                right++;
                len[i]++;
            }
            if (i + len[i] > maxRight) {
                maxRight = i + len[i];
                maxIndex = i;
            }
            //这里注意要加上1，因为如果是只有一个数"*1*",此时len[i]为1，需要加上1位2，再除2方为1
            res += (len[i] + 1) / 2;
        }
        return res;
    }
}
