package com.algorithm.贪心;

/*
* 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。

若可行，输出任意可行的结果。若不可行，返回空字符串。

示例 1:

输入: S = "aab"
输出: "aba"
示例 2:

输入: S = "aaab"
输出: ""
注意:

S 只包含小写字母并且长度在[1, 500]区间内。
* */

//基于优先队列的贪心
// class Solution {
//     public String reorganizeString(String S) {
//         if (S.length() < 2) {
//             return S;
//         }
//         int[] map = new int[26];
//         int maxCount = 0;
//         for (int i = 0; i < S.length(); i++) {
//             char c = S.charAt(i);
//             map[c - 'a']++;
//             maxCount = Math.max(maxCount, map[c - 'a']);
//         }
//         if (maxCount > (S.length() + 1) / 2) {
//             return "";
//         }
//         PriorityQueue<Character> queue = new PriorityQueue<>((a, b) -> map[b - 'a'] - map[a - 'a']);
//         for (int i = 0; i < map.length; i++) {
//             if (map[i] != 0) {
//                 queue.add((char)(i + 'a'));
//             }
//         }
//         StringBuilder builder = new StringBuilder();
//         //这里的思路一定要想到一次拿出两个，不然想不通
//         while (queue.size() > 1) {
//             char c1 = queue.poll();
//             char c2 = queue.poll();
//             builder.append(c1);
//             builder.append(c2);
//             map[c1 - 'a']--;
//             map[c2 - 'a']--;
//             if (map[c1 - 'a'] > 0) {
//                 queue.add(c1);
//             }
//             if (map[c2 - 'a'] > 0) {
//                 queue.add(c2);
//             }
//         }
//         if (queue.size() > 0) {
//             builder.append(queue.poll());
//         }
//         return builder.toString();
//     }
// }

//利用奇数排序来构造最后解，因为每个位置都只能出现一次，依次放入可构造解
class 重构字符串 {
    public String reorganizeString(String S) {
        if (S.length() < 2) {
            return S;
        }
        int[] counts = new int[26];
        int maxCount = 0;
        int length = S.length();
        for (int i = 0; i < length; i++) {
            char c = S.charAt(i);
            counts[c - 'a']++;
            maxCount = Math.max(maxCount, counts[c - 'a']);
        }
        if (maxCount > (length + 1) / 2) {
            return "";
        }
        char[] reorganizeArray = new char[length];
        int evenIndex = 0, oddIndex = 1;
        int halfLength = length / 2;
        for (int i = 0; i < 26; i++) {
            char c = (char)(i + 'a');
            //如果总体长度是偶数， counts[i] == halfLength，此时放在奇数或是偶数都可，其他情况>halfLength必须放在偶数位。
            while (counts[i] > 0 && counts[i] <= halfLength && oddIndex < length) {
                reorganizeArray[oddIndex] = c;
                counts[i]--;
                oddIndex += 2;
            }
            while (counts[i] > 0) {
                reorganizeArray[evenIndex] = c;
                counts[i]--;
                evenIndex += 2;
            }
        }
        return new String(reorganizeArray);
    }
}