package com.algorithm.dp;

/*
* 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。

示例 1:

输入: "(()"
输出: 2
解释: 最长有效括号子串为 "()"
示例 2:

输入: ")()())"
输出: 4
解释: 最长有效括号子串为 "()()"

* */
class 最长有效括号 {
    //dp
    // public int longestValidParentheses(String s) {
    //     int maxans = 0;
    //     int[] dp = new int[s.length()];
    //     for (int i = 1; i < s.length(); i++) {
    //         if (s.charAt(i) == ')') {
    //             if (s.charAt(i) == '(') {
    //                 dp[i] = (i >= 2? dp[i - 2]: 0) + 2;
    //             } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
    //                 //dp[i]=dp[i−1]+dp[i−dp[i−1]−2]+2，针对这样的情况()(())
    //                 dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2? dp[i - dp[i - 1] - 2]: 0) + 2;
    //             }
    //             maxans = Math.max(maxans, dp[i]);
    //         }
    //     }
    //     return maxans;
    // }
    //栈
    //关键在于栈里面不存括号，存下标
    // public int longestValidParentheses(String s) {
    //     int maxans = 0;
    //     Deque<Integer> stack = new LinkedList<>();
    //     //栈中存放最后一个没有被匹配的括号的下标
    //     stack.push(-1);
    //     for (int i = 0; i < s.length(); i++) {
    //         if (s.charAt(i) == '(') {
    //             stack.push(i);
    //         } else {
    //             stack.pop();
    //             if (stack.isEmpty()) {
    //                 stack.push(i);
    //             } else {
    //                 maxans = Math.max(maxans, i - stack.peek());
    //             }
    //         }
    //     }
    //     return maxans;
    // }
    public int longestValidParentheses(String s) {
        int left = 0, right = 0, maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxLength = Math.max(maxLength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        //之所以需要左右两次遍历，是为了防止"()((())",从左边遍历不能得到4
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxLength = Math.max(maxLength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxLength;
    }
}