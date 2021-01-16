package com.algorithm.dp.矩阵问题;

/*
* 给定一个非空二维矩阵 matrix 和一个整数 k，找到这个矩阵内部不大于 k 的最大矩形和。

示例:

输入: matrix = [[1,0,1],[0,-2,3]], k = 2
输出: 2
解释: 矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
说明：

矩阵内的矩形区域面积必须大于 0。
如果行数远大于列数，你将如何解答呢？
*/

//暴力dp 超时
// class Solution {
//     public int maxSumSubmatrix(int[][] matrix, int k) {
//         int m = matrix.length;
//         int n = matrix[0].length;
//         int max = Integer.MIN_VALUE;
//         int[][][][] dp = new int[m + 1][n + 1][m + 1][n + 1];
//         for (int i1 = 1; i1 <= m; i1++) {
//             for (int j1 = 1; j1 <= n; j1++) {
//                 dp[i1][j1][i1][j1] = matrix[i1 - 1][j1 - 1];
//                 for (int i2 = i1; i2 <= m; i2++) {
//                     for (int j2 = j1; j2 <= n; j2++) {
//                         dp[i1][j1][i2][j2] = dp[i1][j1][i2 - 1][j2] + dp[i1][j1][i2][j2 - 1] - dp[i1][j1][i2 - 1][j2 - 1] + matrix[i2 - 1][j2 - 1];
//                         if (dp[i1][j1][i2][j2] <= k && dp[i1][j1][i2][j2] > max) {
//                             max = dp[i1][j1][i2][j2];
//                         }
//                     }
//                 }
//             }
//         }
//         return max;
//     }
// }
//状态压缩 尝试每次更换左上角时重复利用dp
// class Solution {
//     public int maxSumSubmatrix(int[][] matrix, int k) {
//         int m = matrix.length, n = matrix[0].length, max = Integer.MIN_VALUE;
//         for (int i1 = 1; i1 <= m; i1++) {
//             for (int j1 = 1; j1 <= n; j1++) {
//                 int[][] dp = new int[m + 1][n + 1];
//                 dp[i1][j1] = matrix[i1 - 1][j1 - 1];
//                 for (int i2 = i1; i2 <= m; i2++) {
//                     for (int j2 = j1; j2 <= n; j2++) {
//                         dp[i2][j2] = dp[i2 - 1][j2] + dp[i2][j2 - 1] - dp[i2 - 1][j2 - 1] + matrix[i2 - 1][j2 - 1];
//                         if (dp[i2][j2] <= k && dp[i2][j2] > max) {
//                             max = dp[i2][j2];
//                         }
//                     }
//                 }
//             }
//         }
//         return max;
//     }
// }
//数组滚动，先固定左右边界，不断压入，行累计数组 On2m2
// class Solution {
//     public int maxSumSubmatrix(int[][] matrix, int k) {
//         int m = matrix.length, n = matrix[0].length, max = Integer.MIN_VALUE;
//         for (int l = 0; l < n; l++) {
//             int[] rowSum = new int[m];
//             for (int r = l; r < n; r++) {
//                 for (int i = 0; i < m; i++) {
//                     rowSum[i] += matrix[i][r];
//                 }
//                 max = Math.max(max, dpmax(rowSum, k));
//             }
//         }
//         return max;
//     }
//     public int dpmax(int[] arr, int k) {
//         int max = Integer.MIN_VALUE;
//         for (int l = 0; l < arr.length; l++) {
//             int sum = 0;
//             for (int r = l; r < arr.length; r++) {
//                 sum += arr[r];
//                 if (sum > max && sum <= k) {
//                     max = sum;
//                 }
//             }
//         }
//         return max;
//     }
// }
// 再利用最大子数组和优化 这是答案上的以行为基准的dp
// class Solution {
//     public int maxSumSubmatrix(int[][] matrix, int k) {
//         int m = matrix.length, n = matrix[0].length, max = Integer.MIN_VALUE;
//         for (int l = 0; l < n; l++) {
//             int[] rowSum = new int[m];
//             for (int r = l; r < n; r++) {
//                 for (int i = 0; i < m; i++) {
//                     rowSum[i] += matrix[i][r];
//                 }
//                 max = Math.max(max, dpmax(rowSum, k));
//             }
//         }
//         return max;
//     }
//      这里直接使用On2即可
//     public int dpmax(int[] arr, int k) {
//         int rollSum = arr[0], rollMax = rollSum;
//         for (int i = 1; i < arr.length; i++) {
//             if (rollSum > 0) {
//                 rollSum += arr[i];
//             } else {
//                 rollSum = arr[i];
//             }
//             if (rollSum > rollMax) {
//                 rollMax = rollSum;
//             }
//         }
//         if (rollMax <= k) {
//             return rollMax;
//         }
//         int max = Integer.MIN_VALUE;
//         for (int l = 0; l < arr.length; l++) {
//             int sum = 0;
//             for (int r = l; r < arr.length; r++) {
//                 sum += arr[r];
//                 if (sum > max && sum <= k) max = sum;
//                 if (max == k) return k; // 尽量提前
//             }
//         }
//         return max;
//     }
// }


class 矩形区域不超过K最大数值和 {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] b = new int[n];
        int rmax = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            b = new int[n];
            for (int j = i; j < m; j++) {
                int cur = 0;
                int max = 0;
                for (int p = 0; p < n; p++) {
                    b[p] += matrix[j][p];
                    if (p == 0) {
                        max = b[0];
                    }
                    if (cur > 0) {
                        cur += b[p];
                    } else {
                        cur = b[p];
                    }
                    max = Math.max(max, cur);
                }
                //先用On的方法判断，如果找到了直接进入下一轮，On失效的情况，如果sum数组为[-1, -3, 11],target为8，就无法得到8
                if (max <= k) {
                    rmax = Math.max(rmax, max);
                    continue;
                }
                max = Integer.MIN_VALUE;
                for (int p = 0; p < n; p++) {
                    int sum = 0;
                    for (int q = p; q < n; q++) {
                        sum += b[q];
                        if (sum > max  && sum <= k) {
                            max = sum;
                        }
                        if (max == k) {
                            return k;
                        }
                    }
                }
                rmax = Math.max(rmax, max);
            }
        }
        return rmax;
    }
}