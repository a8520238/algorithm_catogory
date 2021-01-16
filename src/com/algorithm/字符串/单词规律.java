package com.algorithm.字符串;

import java.util.HashMap;
import java.util.Map;

public class 单词规律 {
    //改进版本的双map
    public boolean wordPattern(String pattern, String str) {
        //这题关键是要想到两个map，一次遍历
        String[] s = str.split(" ");
        if (pattern.length() != s.length) {
            return false;
        }
        Map<String, Character> str2ch = new HashMap<>();
        Map<Character, String> ch2str = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            if (str2ch.containsKey(s[i]) && str2ch.get(s[i]) != ch) {
                return false;
            }
            if (ch2str.containsKey(ch) && !ch2str.get(ch).equals(s[i])) {
                return false;
            }
            str2ch.put(s[i], ch);
            ch2str.put(ch, s[i]);
        }
        return true;
    }
    //答案的双map求解
    // public boolean wordPattern(String pattern, String str) {
    //     Map<String, Character> str2ch = new HashMap<>();
    //     Map<Character, String> ch2str = new HashMap<>();
    //     int m = str.length();
    //     int i = 0;
    //     for (int p = 0; p < pattern.length(); p++) {
    //         char ch = pattern.charAt(p);
    //         if (i >= m) {
    //             return false;
    //         }
    //         int j = i;
    //         while (j < m && str.charAt(j) != ' ') {
    //             j++;
    //         }
    //         String tmp = str.substring(i, j);
    //         if (str2ch.containsKey(tmp) && str2ch.get(tmp) != ch) {
    //             return false;
    //         }
    //         if (ch2str.containsKey(ch) && !tmp.equals(ch2str.get(ch))) {
    //             return false;
    //         }
    //         str2ch.put(tmp, ch);
    //         ch2str.put(ch, tmp);
    //         i = j + 1;
    //     }
    //     return i >= m;
    // }
    //暴力法On2
    // public boolean wordPattern(String pattern, String s) {
    //     String[] str = s.split(" ");
    //     if (pattern.length() != str.length) {
    //         return false;
    //     }
    //     for (int i = 0; i < str.length; i++) {
    //         for (int j = i + 1; j < str.length; j++) {
    //             if (pattern.charAt(i) == pattern.charAt(j)) {
    //                 if (!str[i].equals(str[j])) {
    //                     return false;
    //                 }
    //             } else if (pattern.charAt(i) != pattern.charAt(j)) {
    //                 if (str[i].equals(str[j])) {
    //                     return false;
    //                 }
    //             }
    //         }
    //     }
    //     return true;
    // }
}
