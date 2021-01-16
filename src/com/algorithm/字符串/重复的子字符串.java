package com.algorithm.字符串;

/*
* 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。

示例 1:

输入: "abab"

输出: True

解释: 可由子字符串 "ab" 重复两次构成。
示例 2:

输入: "aba"

输出: False
示例 3:

输入: "abcabcabcabc"

输出: True

解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
* */

public class 重复的子字符串 {
    public boolean repeatedSubstringPattern(String s) {
        int[] next = new int[s.length() + 1];
        char[] str = s.toCharArray();
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        for (int i = 2; i < next.length; i++) {
            if (str[i - 1] == str[cn]) {
                next[i] = ++cn;
            } else if (cn != 0) {
                cn = next[cn];
                i--;
            } else {
                next[i] = 0;
            }
        }
        int right = next[s.length()];
        if (right == 0) {
            return false;
        }
        int left =  0;
        if (s.length() - right < right) {
            left = right;
            right = s.length();
        }
        String tmp = s.substring(left, right);
        int len = right - left;
        for (int start = 0; start < s.length(); start += len) {
            if (!s.substring(start, start + len).equals(tmp)) {
                return false;
            }
        }
        return true;
        //return next[s.length()] != 0;
    }
}
