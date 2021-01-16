package com.algorithm.单调栈;

import java.util.Deque;
import java.util.LinkedList;

/*
* 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。

注意:

num 的长度小于 10002 且 ≥ k。
num 不会包含任何前导零。
示例 1 :

输入: num = "1432219", k = 3
输出: "1219"
解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
示例 2 :

输入: num = "10200", k = 1
输出: "200"
解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
* */


//自己写的贪心做法
// class 移掉K位数组 {
//     public String removeKdigits(String num, int k) {
//         if (num.length() <= k) {
//             return "0";
//         }
//         char[] s = num.toCharArray();
//         int index = 0;
//         int begin = 0;
//         //先除0
//         while (index - begin < k) {
//             while (index < s.length && s[index] != '0') {
//                 index++; //退出循环时，s[index]为0.
//             }
//             int tmp = k - (index - begin);
//             if (tmp >= 0) {
//                 k -= index - begin;
//                 while (index < s.length && s[index] == '0') {
//                     index++;
//                 }
//                 begin = index;
//             }
//         }
//         Set<Integer> set = new HashSet<>();
//         int end = s.length;
//         // 删除策略如果当前值大于第二个删除第一个
//         while (k > 0) {
//             int i = begin;
//             int first = -1;
//             for (; i < end; i++) {
//                 if (set.contains(i)) {
//                     continue;
//                 }
//                 if (first == -1) {
//                     first = i;
//                     continue;
//                 }
//                 if (s[i] < s[first]) {
//                     set.add(first);
//                     break;
//                 } else {
//                     first = i;
//                 }
//             }
//             if (i == end) {
//                 set.add(i - 1);
//                 end--;
//             }
//             k--;
//         }
//         StringBuilder builder = new StringBuilder();
//         for (int i = begin; i < end; i++) {
//             if (!set.contains(i)) {
//                 builder.append(s[i]);
//             }
//         }
//         return builder.length() == 0? "0": builder.toString();
//     }
// }
//答案贪心加单调栈
//单调栈题https://leetcode-cn.com/problems/remove-k-digits/solution/yi-zhao-chi-bian-li-kou-si-dao-ti-ma-ma-zai-ye-b-5/
class 移掉K位数组 {
    public String removeKdigits(String num, int k) {
        Deque<Character> deque = new LinkedList<>();
        int length = num.length();
        for (int i = 0; i < length; i++) {
            char digit = num.charAt(i);
            //如果栈不空，并且栈中大于当前值，出栈，出栈意味着抛弃值，k--;
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > digit) {
                deque.pollLast();
                k--;
            }
            deque.offerLast(digit);
        }
        //如果还有剩余k,继续出栈
        for (int i = 0; i < k; i++) {
            deque.pollLast();
        }
        StringBuilder builder = new StringBuilder();
        boolean zero = true;
        while (!deque.isEmpty()) {
            char digit = deque.pollFirst();
            if (zero && digit == '0') {
                continue;
            }
            zero = false;
            builder.append(digit);
        }
        return builder.length() == 0? "0": builder.toString();
    }
}
