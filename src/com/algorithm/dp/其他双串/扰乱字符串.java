package com.algorithm.dp.其他双串;

/*
* 给定一个字符串 s1，我们可以把它递归地分割成两个非空子字符串，从而将其表示为二叉树。

下图是字符串 s1 = "great" 的一种可能的表示形式。

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
在扰乱这个字符串的过程中，我们可以挑选任何一个非叶节点，然后交换它的两个子节点。

例如，如果我们挑选非叶节点 "gr" ，交换它的两个子节点，将会产生扰乱字符串 "rgeat" 。

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
我们将 "rgeat” 称作 "great" 的一个扰乱字符串。

同样地，如果我们继续交换节点 "eat" 和 "at" 的子节点，将会产生另一个新的扰乱字符串 "rgtae" 。

* */
//贪心失败
// class Solution {
//     public boolean isScramble(String s1, String s2) {
//         if (s1.length() != s2.length()) {
//             return false;
//         }
//         Map<Character, Integer> map = new HashMap<>();
//         for (char c: s1.toCharArray()) {
//             map.put(c, map.getOrDefault(c, 0) + 1);
//         }
//         for (char c: s2.toCharArray()) {
//             if (!map.containsKey(c)) {
//                 return false;
//             }
//             int cur = map.get(c);
//             if (cur == 1) {
//                 map.remove(c);
//             } else {
//                 map.put(c, cur - 1);
//             }
//         }
//         if (s1.length() <= 2) {
//             return true;
//         }
//         Set<Character> set = new HashSet<>();
//         set.add(s1.charAt(0));
//         int index1 = 1;
//         int index2 = 0;
//         while (!set.isEmpty()) {
//             char c = s2.charAt(index2);
//             if (set.contains(c)) {
//                 set.remove(c);
//                 index2++;
//             } else {
//                 // if (index1 == s1.length()) {
//                 //     return false;
//                 // }
//                 set.add(s1.charAt(index1++));
//             }
//         }
//         // if (!set.isEmpty()) {
//         //     return false;
//         // }

//         if (index1 == s1.length()) {
//             return false;
//         }
//         return true;
//         // return isScramble(s1.substring(0, index1), s2.substring(0, index2)) && isScramble(s1.substring(index1, s1.length()), s2.substring(index2, s2.length()));
//     }
// }

class 扰乱字符串 {
    public boolean isScramble(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int m = str1.length;
        int n = str2.length;
        if (m != n) {
            return false;
        }
        boolean[][][] dp = new boolean[n][n][n+1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][1] = str1[i] == str2[j];
            }
        }
        //dp含义，以i,j为开始，长度为len的区域能否拆分为两个
        //注意这里左右子树未必按照数组顺序排列
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                for (int j = 0; j < n - len + 1; j++) {
                    for (int k = 1; k <= len - 1; k++) {
                        if (dp[i][j][k] && dp[i + k][j + k][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                        if (dp[i][j + len - k][k] && dp[i + k][j][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][n];
    }
}