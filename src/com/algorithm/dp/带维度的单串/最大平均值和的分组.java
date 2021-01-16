package com.algorithm.dp.带维度的单串;

/*
* 我们将给定的数组 A 分成 K 个相邻的非空子数组 ，我们的分数由每个子数组内的平均值的总和构成。计算我们所能得到的最大分数是多少。

注意我们必须使用 A 数组中的每一个数进行分组，并且分数不一定需要是整数。

示例:
输入:
A = [9,1,2,3,9]
K = 3
输出: 20
解释:
A 的最优分组是[9], [1, 2, 3], [9]. 得到的分数是 9 + (1 + 2 + 3) / 3 + 9 = 20.
我们也可以把 A 分成[9, 1], [2], [3, 9].
这样的分组得到的分数为 5 + 2 + 6 = 13, 但不是最大值.
说明:

1 <= A.length <= 100.
1 <= A[i] <= 10000.
1 <= K <= A.length.
答案误差在 10^-6 内被视为是正确的。
* */

//dp含义前i个数分为j个产生的最大平均和
class 最大平均值和的分组 {
    public double largestSumOfAverages(int[] A, int K) {
        int row = A.length;
        double[][] dp = new double[row + 1][K + 1];
        double[] P = new double[row + 1];
        for (int i = 0; i < row; i++) {
            P[i + 1] = P[i] + A[i];
        }

        for (int i = 1; i <= row; i++) {
            dp[i][1] = P[i] / i;
        }
        //dp可以理解为下三角
        for (int i = 1; i <= row; i++) {
            for (int j = 2; j <= Math.min(K, i); j++) {
                for (int k = j - 1; k < i; k++) {
                    // double ave = 0;
                    // for (int m = k; m < i; m++) {
                    //     ave += A[m];
                    // }
                    // ave = ave / (i - k);
                    // dp[i][j] = Math.max(dp[i][j], dp[k][j - 1] + ave);
                    dp[i][j] = Math.max(dp[i][j], dp[k][j - 1] + (P[i] - P[k]) / (i - k));
                }
            }
        }
        return dp[row][K];
    }
}
//dp(i, k) = max(dp(j, k - 1) + average(j + 1, i))
// class 最大平均值和的分组 {
//     public double largestSumOfAverages(int[] A, int K) {
//         int N = A.length;
//         double[] P = new double[N + 1];
//         for (int i = 0; i < N; i++) {
//             P[i + 1] = P[i] + A[i];
//         }
//         //dp[i]表示数组A中从下标i（包括i）开始到
//         //最后一个数为止分成K个连续非空子数组的子数组的平均值的和的最大值（用xxx代指）
//         double[] dp = new double[N];
//         for (int i = 0; i < N; i++) {
//             dp[i] = (P[N] - P[i]) / (N - i);
//         }
//         for (int k = 0; k < K - 1; k++) {
//             for (int i = 0; i < N; i++) {
//                 for (int j = i + 1; j < N; j++) {
//                     dp[i] = Math.max(dp[i], (P[j] - P[i]) / (j - i) + dp[j]);
//                 }
//             }
//         }
//         return dp[0];
//     }
// }