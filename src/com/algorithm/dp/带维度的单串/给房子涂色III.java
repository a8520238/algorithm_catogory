package com.algorithm.dp.带维度的单串;

public class 给房子涂色III {
    class Solution {
        public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
            final int INF = (int)Math.pow(10, 8); //INT_MAX / 2;
            //dp[i][j][k]下标为i的房子，到当前房子划分为j段，最后一个房子为k的最小值
            int[][][] dp = new int[m][target + 1][n + 1];
            //最后需要对各个颜色取最小值，所以先都赋值最大值
            for (int i = 0; i < m; i++) {
                for (int j = 0; j <= target; j++) {
                    for (int k = 0; k <= n; k++) {
                        dp[i][j][k] = INF;
                    }
                }
            }
            //这里初始化的时候，以房子为最外层，就只需要初始化第0个房子了
            if (houses[0] > 0) {
                dp[0][1][houses[0]] = 0;
            } else {
                for (int i = 0; i < n; i++) {
                    dp[0][1][i + 1] = cost[0][i];
                }
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j <= target; j++) {
                    if (houses[i] > 0) {
                        for (int k = 1; k <= n; k++) {
                            // 分成第i个房子和第i-1的房子
                            // 如果两个房子颜色相同，那么街区数就相同
                            // 如果两个房子颜色不同，那么第i个房子就独自成一个街区
                            if (houses[i] == k) {
                                dp[i][j][houses[i]] = Math.min(dp[i][j][houses[i]], dp[i - 1][j][k]);
                            } else {
                                dp[i][j][houses[i]] = Math.min(dp[i][j][houses[i]], dp[i - 1][j - 1][k]);
                            }
                        }
                    } else {
                        //k代表上一个房子的颜色
                        for (int k = 1; k <= n; k++) {
                            //l代表当前房子的颜色
                            for (int l = 1; l <= n; l++) {
                                if (k == l) {
                                    dp[i][j][l] = Math.min(dp[i][j][l], dp[i - 1][j][k] + cost[i][l - 1]);
                                } else {
                                    dp[i][j][l] = Math.min(dp[i][j][l], dp[i - 1][j - 1][k] + cost[i][l - 1]);
                                }
                            }
                        }
                    }
                }
            }
            int res = INF;
            for (int k = 1; k <= n; k++) {
                res = Math.min(res, dp[m - 1][target][k]);
            }
            return res == INF? -1: res;
        }
    }

}
