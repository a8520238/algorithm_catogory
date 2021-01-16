package com.algorithm.dp.需要两个位置的单串问题变形;

/*
* 如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：

n >= 3
对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
给定一个严格递增的正整数数组形成序列，找到 A 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。

（回想一下，子序列是从原序列 A 中派生出来的，它从 A 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）

 

示例 1：

输入: [1,2,3,4,5,6,7,8]
输出: 5
解释:
最长的斐波那契式子序列为：[1,2,3,5,8] 。
* */

//set思想，时间复杂度O(N2logM),空间复杂度O(N)
// class 最长的斐波那契子序列的长度 {
//     public int lenLongestFibSubseq(int[] A) {
//         int N = A.length;
//         Set<Integer> set = new HashSet<>();
//         for (int x: A) {
//             set.add(x);
//         }
//         int ans = 0;
//         for (int i = 0; i < N; i++) {
//             for (int j = i + 1; j < N; j++) {
//                 int x = A[j], y = A[i] + A[j];
//                 int length = 2;
//                 while (set.contains(y)) {
//                     int tmp = y;
//                     y += x;
//                     x = tmp;
//                     ans = Math.max(ans, ++length);
//                 }
//             }
//         }
//         return ans >= 3? ans: 0;
//     }
// }

//这种是答案给的，利用map给出每一个dp状态，下面会有参照这种思想的标准dp
// class 最长的斐波那契子序列的长度 {
//     public int lenLongestFibSubseq(int[] A) {
//         int N = A.length;
//         Map<Integer, Integer> index = new HashMap<>();
//         for (int i = 0; i < N; i++) {
//             index.put(A[i], i);
//         }
//         Map<Integer, Integer> longest = new HashMap<>();
//         int ans = 0;

//         for (int k = 0; k < N; k++) {
//             for (int j = 0; j < k; j++) {
//                 int i = index.getOrDefault(A[k] - A[j], -1);
//                 if (i >= 0 && i < j) {
//                     // Encoding tuple (i, j) as integer (i * N + j)
//                     int cand = longest.getOrDefault(i * N + j, 2) + 1;
//                     longest.put(j * N + k, cand);
//                     ans = Math.max(ans, cand);
//                 }
//             }
//         }
//         return ans >= 3? ans: 0;
//     }
// }

import java.util.HashMap;
import java.util.Map;

//dp[i][j] 以A[i],A[j] 结尾的最长的长度-2
//如果存在A[k] = A[j] - A[i] 且k <i， 则dp[i][j] = dp[k][i] + 1
class 最长的斐波那契子序列的长度 {
    public int lenLongestFibSubseq(int[] A) {
        int[][] dp = new int[A.length - 1][A.length];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            map.put(A[i], i);
        }
        int res = 0;
        for (int i = 1; i < A.length - 1; i++) {
            for (int j = i + 1; j < A.length; j++) {
                int step = A[j] - A[i];
                if (map.containsKey(step) && map.get(step) < i) {
                    int k = map.get(step);
                    dp[i][j] = dp[k][i] + 1;
                    res = Math.max(res, dp[i][j] + 2);
                }
            }
        }
        return res;
    }
}