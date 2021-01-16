package com.algorithm.字符串;

/*
* 给定两个字符串 a 和 b，寻找重复叠加字符串 a 的最小次数，使得字符串 b 成为叠加后的字符串 a 的子串，如果不存在则返回 -1。

注意：字符串 "abc" 重复叠加 0 次是 ""，重复叠加 1 次是 "abc"，重复叠加 2 次是 "abcabc"。

 

示例 1：

输入：a = "abcd", b = "cdabcdab"
输出：3
解释：a 重复叠加三遍后为 "abcdabcdabcd", 此时 b 是其子串。
示例 2：

输入：a = "a", b = "aa"
输出：2
示例 3：

输入：a = "a", b = "a"
输出：1
示例 4：

输入：a = "abc", b = "wxyz"
输出：-1
 

提示：

1 <= a.length <= 104
1 <= b.length <= 104
a 和 b 由小写英文字母组成
* */

import java.util.Arrays;

public class 重复叠加字符串匹配 {
    //调用api
    // public int repeatedStringMatch(String A, String B) {
    //     int q = B.length() / A.length();
    //     if (B.length() % A.length() != 0) {
    //         q++;
    //     }
    //     StringBuilder result = new StringBuilder();
    //     for (int i = 0; i < q; i++) {
    //         result.append(A);
    //     }
    //     if (result.toString().indexOf(B) != -1) {
    //         return q;
    //     }
    //     result.append(A);
    //     if (result.toString().indexOf(B) != -1) {
    //         return q + 1;
    //     }
    //     return -1;
    // }
    //升级版API
    // public int repeatedStringMatch(String A, String B) {
    //     int i = 1;
    //     StringBuilder s = new StringBuilder(A);
    //     int blength = B.length();
    //     while(s.length() < blength){
    //         s.append(A);
    //         i++;
    //     }
    //     return s.lastIndexOf(B) == -1?((s.append(A)).lastIndexOf(B)==-1?-1:i+1):i;
    // }
    public int[] next;
    public int repeatedStringMatch(String A, String B) {
        next = getNext(B);
        int i = 1;
        StringBuilder s = new StringBuilder(A);
        int blength = B.length();
        while (s.length() < blength) {
            s.append(A);
            i++;
        }
        char[] p = s.toString().toCharArray();
        char[] q = B.toCharArray();
        if (KMP(p, q) == -1) {
            s.append(A);
            p = s.toString().toCharArray();
            if (KMP(p, q) == -1) {
                return -1;
            } else {
                return i + 1;
            }
        } else {
            return i;
        }
    }
    public int[] getNext(String B) {
        int[] next = new int[B.length()];
        char[] strB = B.toCharArray();
        next[0] = -1;
        Arrays.fill(next, -1);
        for (int i = 1; i < next.length; i++) {
            int j = next[i - 1];
            while (j != -1 && strB[j + 1] != strB[i]) {
                j = next[j];
            }
            if (strB[j + 1] == strB[i]) {
                next[i] = j + 1;
            }
        }
        return next;
    }
    public int KMP(char[] p, char[] q) {
        int j = -1;
        for (int i = 0; i < p.length; i++) {
            while (j != -1 && q[j + 1] != p[i]) {
                j = next[j];
            }
            if (q[j + 1] == p[i]) {
                j++;
            }
            // System.out.println(j);
            if (j == q.length - 1) {
                return i - (q.length - 1);
            }
        }
        return -1;
    }
}
