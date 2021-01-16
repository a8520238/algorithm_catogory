package com.algorithm.字符串;

import java.util.Arrays;


public class KMP {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        if (haystack.length() == 0 || haystack.length() < needle.length()) {
            return -1;
        }
        int n = needle.length();
        int[] next = new int[n];
        Arrays.fill(next, -1);
        //next[0] 为 -1，next数组中的含义，前后缀想同的back位置的最后。
        for (int i = 1; i < n; i++) {
            //找到上一个值得回退位置
            //要牢记j代表当前匹配者的前一个值的next值
            int j = next[i - 1];
            //如果当前值不匹配，找回退位置，直到是-1
            while (j != -1 && needle.charAt(j + 1) != needle.charAt(i)) {
                j = next[j];
            }
            if (needle.charAt(j + 1) == needle.charAt(i)) {
                next[i] = j + 1;
            }
        }
        //牢记这个best代表当前q匹配点的前一个值的下标
        int best = -1;
        for (int i = 0; i < haystack.length(); i++) {
            while (best != -1 && needle.charAt(best + 1) != haystack.charAt(i)) {
                best = next[best];
            }
            if (needle.charAt(best + 1) == haystack.charAt(i)) {
                best++;
            }
            if (best == n - 1) {
                return i - n + 1;
            }
        }
        return -1;
    }
}
