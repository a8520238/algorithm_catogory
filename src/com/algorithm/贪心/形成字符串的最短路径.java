package com.algorithm.贪心;
/*
*
对于任何字符串，我们可以通过删除其中一些字符（也可能不删除）来构造该字符串的子序列。

给定源字符串 source 和目标字符串 target，找出源字符串中能通过串联形成目标字符串的子序列的最小数量。如果无法通过串联源字符串中的子序列来构造目标字符串，则返回 -1。

 

示例 1：

输入：source = "abc", target = "abcbc"
输出：2
解释：目标字符串 "abcbc" 可以由 "abc" 和 "bc" 形成，它们都是源字符串 "abc" 的子序列。
示例 2：

输入：source = "abc", target = "acdbc"
输出：-1
解释：由于目标字符串中包含字符 "d"，所以无法由源字符串的子序列构建目标字符串。
示例 3：

输入：source = "xyz", target = "xzyxz"
输出：3
解释：目标字符串可以按如下方式构建： "xz" + "y" + "xz"。

* */

// 这道题dp On3会超时，要用贪心来做，每次从source开始与target匹对，如果对不上，证明-1，如果成对成功返回
// 非-1的下标，证明到此下标为止，可以成为子串，res+1.重新匹对

//dp超时
// class 形成字符串的最短路径 {
//     //dp[i][j]代表target从i到j能表示source子串的最小数目
//     public int shortestWay(String source, String target) {
//         int n = target.length();
//         int[][] dp = new int[n][n];
//         for (int i = 0; i < n; i++) {
//             dp[i][i] = isSub(target.substring(i, i + 1), source)? 1: Integer.MAX_VALUE;
//         }
//         for (int i = 1; i < n; i++) {
//             for (int j = 0; j < n - i; j++) {
//                 //这里dp[j][i+j]代表了实际意义上的dp[i][j]
//                 dp[j][i + j] = Integer.MAX_VALUE;
//                 if (isSub(target.substring(j, i + j + 1), source)) {
//                     dp[j][i + j] = 1;
//                 } else {
//                     for (int k = j; k < i + j; k++) {
//                         int left = dp[j][k], right = dp[k + 1][i + j];
//                         if (left == Integer.MAX_VALUE || right == Integer.MAX_VALUE) {
//                             continue;
//                         }
//                         dp[j][i + j] = Math.min(dp[j][i + j], left + right);
//                     }
//                 }
//             }
//         }
//         return dp[0][n - 1] == Integer.MAX_VALUE? -1: dp[0][n-1];
//     }
//     public boolean isSub(String sub, String ori) {
//         int i = 0, j = 0;
//         while (i < sub.length() && j < ori.length()) {
//             if (sub.charAt(i) ==  ori.charAt(j)) {
//                 i++;
//                 j++;
//             } else {
//                 j++;
//             }
//         }
//         return i == sub.length();
//     }
// }
class 形成字符串的最短路径 {
    //贪心+双指针
    public int shortestWay(String source, String target) {
        int minCount = 0;
        char[] cTarget = target.toCharArray();
        int curPos = 0;
        int targetLen = cTarget.length;
        while (curPos < targetLen) {
            int nextPos = find(source, cTarget, curPos);
            if (nextPos == -1) {
                return -1;
            } else {
                curPos = nextPos;
                minCount++;
            }
        }
        return minCount;
    }
    int find(String source, char[] target, int curPos) {
        int targetPos = curPos;
        int sourcePos = 0;
        char[] str = source.toCharArray();
        while (targetPos < target.length && sourcePos < str.length) {
            if (str[sourcePos] == target[targetPos]) {
                targetPos++;
            }
            sourcePos++;
        }
        if (targetPos == curPos) {
            return -1;
        }
        return targetPos;
    }
}

