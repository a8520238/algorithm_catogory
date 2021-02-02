package com.algorithm.双指针;

/**
 * @Author Liguangzhe
 * @Date created in 2021:2:2 2021/2/2
 */

/*
* 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。

注意：字符串长度 和 k 不会超过 104。



示例 1：

输入：s = "ABAB", k = 2
输出：4
解释：用两个'A'替换为两个'B',反之亦然。


示例 2：

输入：s = "AABABBA", k = 1
输出：4
解释：
将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
子串 "BBBB" 有最长重复字母, 答案为 4。
* */
public class 替换后的最长重复字符 {
    public int characterReplacement(String s, int k) {
        int left = 0, right = 0, len = s.length();
        int[] letter = new int[26];
        int max = 0;
        //这里之所以right-left就是最大的，是因为它曾经辉煌过，就是之前大的节点找到了一个max，以及对应的right和left，如果后面达不到，没关系，right - left + 1 - max > k中的max没变，他也会一直保持left到right的长度
        while (right < len) {
            letter[s.charAt(right) - 'A']++;
            max = Math.max(max, letter[s.charAt(right) - 'A']);
            while (right - left + 1 - max > k) {
                letter[s.charAt(left) - 'A']--;
                left++;
            }
            right++;
        }
        return right - left;
    }
}
