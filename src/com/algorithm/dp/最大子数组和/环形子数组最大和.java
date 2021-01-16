package com.algorithm.dp.最大子数组和;

/*
* 给定一个由整数数组 A 表示的环形数组 C，求 C 的非空子数组的最大可能和。

在此处，环形数组意味着数组的末端将会与开头相连呈环状。（形式上，当0 <= i < A.length 时 C[i] = A[i]，且当 i >= 0 时 C[i+A.length] = C[i]）

此外，子数组最多只能包含固定缓冲区 A 中的每个元素一次。（形式上，对于子数组 C[i], C[i+1], ..., C[j]，不存在 i <= k1, k2 <= j 其中 k1 % A.length = k2 % A.length）
*/
public class 环形子数组最大和 {
    public int maxSubarraySumCircular(int[] A) {
        //二维dp，OOM
        // int[][] dp = new int[A.length][A.length];
        // int res = A[0];
        // for (int i = 0; i < A.length; i++) {
        //     dp[i][0] = A[i];
        // }
        // for (int i = 0; i < A.length; i++) {
        //     for (int j = 1; j < A.length; j++) {
        //         dp[i][j] = dp[i][j - 1]  <= 0? A[(i + j) % A.length]: A[(i + j) % A.length] + dp[i][j - 1];
        //         res = Math.max(dp[i][j], res);
        //     }
        // }
        // return res;
        //优化二维dp为一维dp, 仍然超时
        // int[] dp = new int[A.length];
        // int res = A[0];
        // for (int i = 0; i < A.length; i++) {
        //     dp[0] = A[i];
        //     for (int j = 1; j < A.length; j++) {
        //         dp[j] = dp[j - 1]  <= 0? A[(i + j) % A.length]: A[(i + j) % A.length] + dp[j - 1];
        //         res = Math.max(dp[j], res);
        //     }
        // }
        // return res;

        //方法一 双区间子段，左子端和右子段
         int res = A[0];
         int cur = A[0];
         for (int i = 1; i < A.length; i++) {
             cur = Math.max(cur, 0) + A[i];
             res = Math.max(res, cur);
         }
         int[] rightSum = new int[A.length];
         rightSum[A.length - 1] = A[A.length - 1];
         for (int j = A.length - 2; j >= 0; j--) {
             rightSum[j] = rightSum[j + 1] + A[j];
         }
         int[] maxRight = new int[A.length];
         maxRight[A.length - 1] = A[A.length - 1];
         for (int j = A.length - 2; j >= 0; j--) {
             maxRight[j] = Math.max(rightSum[j], maxRight[j + 1]);
         }
         int left = 0;
         for (int i = 0; i < A.length - 1; i++) {
             left += A[i];
             res = Math.max(left + maxRight[i + 1], res);
         }
         return res;

        // 方法二 双倍 + 前缀和 + 单调队列
        // int N = A.length;
        // //P[i] 代表到P[i - 1]的和
        // int[] P = new int[2 * N + 1];
        // for (int i = 0; i < 2 * N; i++) {
        //     P[i + 1] = P[i] + A[i % N];
        // }
        // int ans = A[0];
        // LinkedList<Integer> queue = new LinkedList<>();
        // queue.add(0);
        // for (int j = 1; j <= 2 * N; j++) {
        //     if (j - queue.peekFirst() > N) {
        //         queue.pollFirst();
        //     }
        //     ans = Math.max(ans, P[j] - P[queue.peekFirst()]);
        //     while (!queue.isEmpty() && P[j] <= P[queue.peekLast()]) {
        //         queue.pollLast();
        //     }
        //     queue.add(j);
        // }
        // return ans;
    }
}
