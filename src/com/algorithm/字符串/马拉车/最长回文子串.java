package com.algorithm.字符串.马拉车;

//不同马拉车， 奇数偶数分别判断回文
// class Solution {
//     int max = 0;
//     String str;
//     public String longestPalindrome(String s) {
//         int len = s.length();
//         for (int i = 0; i < len; i++) {
//             process(s, i, i);
//             if (i + 1 < len) {
//                 process(s, i, i + 1);
//             }
//         }
//         return str;
//     }
//     public void process(String s, int left, int right) {
//         while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
//             left--;
//             right++;
//         }
//         if (right - left - 1 > max) {
//             max = right - left - 1;
//             str = s.substring(left + 1, right);
//         }
//     }
// }
//也是判断回文串的思路，但是是On2的
// class Solution {
//     public String longestPalindrome(String s) {
//         int length = s.length();
//         int maxLen = 0;
//         int maxStart = 0, maxEnd = 0;
//         for (int i = 0; i < length; i++) {
//             int start = i;
//             int end = length - 1;
//             while (start < end && !isPalindrome(s, start, end)) {
//                 end--;
//             }
//             if (end - start + 1 > maxLen) {
//                 maxLen = end - start + 1;
//                 maxStart = start;
//                 maxEnd = end;
//             }
//         }
//         return s.substring(maxStart, maxEnd + 1);
//     }
//     public boolean isPalindrome(String s, int start, int end) {
//         while (start < end) {
//             if (s.charAt(start) != s.charAt(end)) {
//                 return false;
//             } else {
//                 start++;
//                 end--;
//             }
//         }
//         return true;
//     }
// }

public class 最长回文子串 {
    public String longestPalindrome(String s) {
        int length = s.length();
        if (length < 2) {
            return s;
        }
        StringBuilder builder = new StringBuilder("*");
        for (int i = 0; i < length; i++) {
            if (i > 0) {
                builder.append("*");
            }
            builder.append(s.charAt(i));
        }
        builder.append("*");
        int m = length * 2 + 1;
        int[] len = new int[m];
        int maxRight = 0, cur = 0, maxLen = 0;
        int start = 0;
        for (int i = 0; i < len.length; i++) {
            //这个len存的是半径
            //cur代表的是当前最大maxRight对应的中心点
            //i代表的是当前需要判断len的点
            len[i] = maxRight > i? Math.min(len[2 * cur - i], maxRight - i): 0;
            int left = i - 1 - len[i];
            int right = i + 1 + len[i];
            while (left >= 0 && right < m && builder.charAt(left) == builder.charAt(right)) {
                len[i]++;
                left--;
                right++;
            }
            if (i + len[i] > maxRight) {
                maxRight = i + len[i];
                cur = i;
            }
            if (len[i] > maxLen) {
                maxLen = len[i];
                //因为插入了*所以要/2
                start = (i - maxLen) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }
}
