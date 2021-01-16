package com.algorithm.dp;

//暴力回溯
// class n个骰子的点数 {
//     int n;
//     public double[] dicesProbability(int n) {
//         this.n = n;
//         int len = 6 * n - n + 1;
//         int[] count = new int[len];
//         backward(count, 0, 0);
//         double q = Math.pow(6, n);
//         double[] res = new double[count.length];
//         for (int i = 0; i < res.length; i++) {
//             res[i] = count[i] / q;
//         }
//         return res;
//     }
//     public void backward(int[] count, int sum, int n) {
//         if (n == this.n) {
//             count[sum - n] += 1;
//             return;
//         }
//         for (int i = 1; i <= 6; i++) {
//             sum += i;
//             backward(count, sum, n + 1);
//             sum -= i;
//         }
//     }
// }
//概率dp tmp[j+k] = pre[j] * 1[k];
// class n个骰子的点数 {
//     public double[] dicesProbability(int n) {
//         double pre[] = {1/6d, 1/6d, 1/6d, 1/6d, 1/6d, 1/6d};
//         for (int i = 2; i <= n; i++) {
//             double[] tmp = new double[i * 5 + 1];
//             for (int j = 0; j < pre.length; j++) {
//                 for (int k = 0; k < 6; k++) {
//                     tmp[j + k] += pre[j] / 6;
//                 }
//             }
//             pre = tmp;
//         }
//         return pre;
//     }
// }
//计数dp
// class n个骰子的点数 {
//     public double[] dicesProbability(int n) {
//         int[][] dp = new int[n + 1][n*6 + 1];
//         for (int i = 1; i <= 6; i++) {
//             dp[1][i] = 1;
//         }
//         for (int i = 2; i <= n; i++) {
//             for (int j = i; j <= 6 * i; j++) {
//                 for (int cur = 1; cur <= 6; cur++) {
//                     if (j - cur <= 0) {
//                         break;
//                     }
//                     dp[i][j] += dp[i - 1][j -cur];
//                 }
//             }
//         }
//         double mod = Math.pow(6, n);
//         double[] res = new double[5 * n + 1];
//         for (int i = n; i <= 6 *n; i++) {
//             res[i - n] = dp[n][i] * 1.0 / mod;
//         }
//         return res;
//     }
// }
//计数dp空间优化
class n个骰子的点数 {
    public double[] dicesProbability(int n) {
        int[] dp = new int[6 * n + 1];
        for (int i = 1; i <= 6; i++) {
            dp[i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 6 * i; j >= i; j--) {
                dp[j] = 0;
                for (int cur = 1; cur <= 6; cur++) {
                    //i-1,即是对于只有对于少一个色子时，最小和最少为i-1，不可能再小了
                    if (j - cur < i - 1) {
                        // if (j - cur <= 0) {
                        break;
                    }
                    dp[j] += dp[j - cur];
                    // dp[i][j] += dp[i - 1][j -cur];
                }
            }
        }
        double mod = Math.pow(6, n);
        double[] res = new double[5 * n + 1];
        for (int i = n; i <= 6 *n; i++) {
            res[i - n] = dp[i] * 1.0 / mod;
        }
        return res;
    }
}
