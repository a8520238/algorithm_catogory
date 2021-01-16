package com.algorithm.dp.带维度的单串;

public class 鸡蛋掉落887 {
    //反向思维dp数组为k个鸡蛋m次能否达到target N
    public int superEggDrop(int K, int N) {
        int[][] dp = new int[K + 1][N + 1];
        int m = 0;
        while (dp[K][m] < N) {
            m++;
            for (int k = 1; k <= K; k++) {
                //能到达的最高楼层
                dp[k][m] = dp[k][m - 1] + dp[k - 1][m - 1] + 1;
            }
        }
        return m;
    }
    //二分dp
    // public int superEggDrop(int K, int N) {
    //      k个鸡蛋试N层最多要几次
    //     int[][] dp = new int[K + 1][N + 1];
    //     for (int j = 1; j <= N; j++) {
    //         dp[1][j] = j;
    //     }
    //     for (int i = 1; i <= K; i++) {
    //         dp[i][0] = 0;
    //         //dp[i][1] = i;
    //     }
    //     for (int i = 2; i <= K; i++) {
    //         for (int j = 1; j <= N; j++) {
    //             dp[i][j] = Integer.MAX_VALUE;
    //             // for (int k = 1; k <= j; k++) {
    //              第k层碎了，找到dp[i-1][k-1]即可，第k层没碎，找到dp[i][j-k]即可
    //             //     dp[i][j] = Math.min(dp[i][j], Math.max(dp[i-1][k-1], dp[i][j-k]) + 1);
    //             // }
    //              二分思想 上述 dp[i-1][k-1], dp[i][j-k] 分别是k的增减函数，
    //              要获得二者极小值的最大值，取极点即可
    //             int low = 1, high = j;
    //             while (low < high) {  //low <= high
    //                 int mid = (high - low) / 2 + low;
    //                 if (dp[i-1][mid-1] >= dp[i][j-mid]) {
    //                     dp[i][j] = Math.min(dp[i][j], dp[i-1][mid-1]+1);
    //                     high = mid;  // mid - 1
    //                 } else {
    //                     dp[i][j] = Math.min(dp[i][j], dp[i][j-mid]+1);
    //                     low = mid + 1;
    //                 }
    //             }
    //             dp[i][j] = Math.max(dp[i-1][low-1], dp[i][j-low]) + 1;
    //         }
    //     }
    //     return dp[K][N];
    // }
}
