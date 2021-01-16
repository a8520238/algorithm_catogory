package com.algorithm.dp.带维度的单串;

/*
* 有一些不规则的硬币。在这些硬币中，prob[i] 表示第 i 枚硬币正面朝上的概率。

请对每一枚硬币抛掷 一次，然后返回正面朝上的硬币数等于 target 的概率。

 

示例 1：

输入：prob = [0.4], target = 1
输出：0.40000
示例 2：

输入：prob = [0.5,0.5,0.5,0.5,0.5], target = 0
输出：0.03125
 

提示：

1 <= prob.length <= 1000
0 <= prob[i] <= 1
0 <= target <= prob.length
如果答案与标准答案的误差在 10^-5 内，则被视为正确答案。

* */

public class 抛掷硬币 {
    public double probabilityOfHeads(double[] prob, int target) {
        int n = prob.length;
        //dp代表前j个数和为i的概率
        double[][] dp = new double[target + 1][n + 1];
        double unChoose = 1.;
        //初始化，前0个何为0概率为1
        //初始化第一行，前j个和为0的概率，前j- 1为0乘以当前也不选
        dp[0][0] = 1.;
        for (int j = 1; j <= n; j++) {
            unChoose *= (1 - prob[j - 1]);
            dp[0][j] = unChoose;
        }
        //dp过程， 前j个和为i = 前j-1和为i-1 + 前j-1和为i (分别乘以各自对应的改了吧)
        for (int i = 1; i <= target; i++) {
            for (int j = i; j <= n; j++) {
                dp[i][j] = (1 - prob[j - 1]) * dp[i][j - 1] + prob[j - 1] * dp[i - 1][j - 1];
            }
        }
        return dp[target][n];
    }
}
