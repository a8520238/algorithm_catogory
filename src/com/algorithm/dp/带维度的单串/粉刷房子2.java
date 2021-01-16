package com.algorithm.dp.带维度的单串;

/*
* 假如有一排房子，共 n 个，每个房子可以被粉刷成 k 种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。

当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x k 的矩阵来表示的。

例如，costs[0][0] 表示第 0 号房子粉刷成 0 号颜色的成本花费；costs[1][2] 表示第 1 号房子粉刷成 2 号颜色的成本花费，以此类推。请你计算出粉刷完所有房子最少的花费成本。

注意：

所有花费均为正整数。

示例：

输入: [[1,5,3],[2,9,4]]
输出: 5
解释: 将 0 号房子粉刷成 0 号颜色，1 号房子粉刷成 2 号颜色。最少花费: 1 + 4 = 5;
     或者将 0 号房子粉刷成 2 号颜色，1 号房子粉刷成 0 号颜色。最少花费: 3 + 2 = 5.
进阶：
您能否在 O(nk) 的时间复杂度下解决此问题？
* */

// class 粉刷房子2 {
//     public int minCostII(int[][] costs) {
//         if (costs.length == 0) {
//             return 0;
//         }
//         int n = costs.length;
//         int k = costs[0].length;
//         int[][] dp = new int[n][k];
//         for (int j = 0; j < k; j++) {
//             dp[0][j] = costs[0][j];
//         }
//         for (int i = 1; i < n; i++) {
//             for (int j = 0; j < k; j++) {
//                 int min = Integer.MAX_VALUE;
//                 for (int l = 0; l < k; l++) {
//                     if (l == j) {
//                         continue;
//                     }
//                     min = Math.min(dp[i - 1][l], min);
//                 }
//                 dp[i][j] = min + costs[i][j];
//             }
//         }
//         int res = Integer.MAX_VALUE;
//         for (int i = 0; i < k; i++) {
//             if (dp[n - 1][i] < res) {
//                 res = dp[n - 1][i];
//             }
//         }
//         return res;
//     }
// }
class 粉刷房子2 {
    public int minCostII(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        int minColor = -1;
        int minCost = 0;
        int secondMinCost = 0;
        for (int[] cost: costs) {
            //当前位置的值
            int tmpMinColor = -1;
            int tmpMinCost = Integer.MAX_VALUE;
            int tmpSecondMinCost = Integer.MAX_VALUE;
            for (int i = 0; i < cost.length; i++) {
                //当前位置的值要跟上一个位置有关，跟据上一次的值算出，当前位置的值
                int thisMinCost = cost[i] + (i == minColor? secondMinCost: minCost);
                if (thisMinCost < tmpMinCost) {
                    tmpSecondMinCost = tmpMinCost;
                    tmpMinCost = thisMinCost;
                    tmpMinColor = i;
                } else if (thisMinCost <tmpSecondMinCost) {
                    tmpSecondMinCost = thisMinCost;
                }
            }
            //将当前位置的值赋给上一次，位置前进
            minCost = tmpMinCost;
            minColor = tmpMinColor;
            secondMinCost = tmpSecondMinCost;
        }
        return minCost;
    }
}