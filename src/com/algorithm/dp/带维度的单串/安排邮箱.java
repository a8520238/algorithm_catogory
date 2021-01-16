package com.algorithm.dp.带维度的单串;

import java.util.Arrays;

/*
* 给你一个房屋数组houses 和一个整数 k ，其中 houses[i] 是第 i 栋房子在一条街上的位置，现需要在这条街上安排 k 个邮筒。

请你返回每栋房子与离它最近的邮筒之间的距离的 最小 总和。

答案保证在 32 位有符号整数范围以内。

输入：houses = [1,4,8,10,20], k = 3
输出：5
解释：将邮筒分别安放在位置 3， 9 和 20 处。
每个房子到最近邮筒的距离和为 |3-1| + |4-3| + |9-8| + |10-9| + |20-20| = 5 。
* */

public class 安排邮箱 {
    public int minDistance(int[] houses, int K) {
        Arrays.sort(houses);
        int len = houses.length;
        int[][] single = new int[len][len];
        //左右的中位数为邮箱，获得的总损失最小
        //single存的是从下标i到下标j，一个K的最小值
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                int mid = (j - i) / 2 + i;
                for (int k = i; k <= j; k++) {
                    single[i][j] += Math.abs(houses[mid] - houses[k]);
                }
            }
        }
        //dp[i][j]代表下标i之前，j个邮局的最小划分方法
        //dp[i][j] = min(dp[k - 1][j - 1] + rec[k][i], dp[i][j]) 对于k >= j - 1 && k <= i
        int[][] dp = new int[len][K + 1];
        for (int i = 0; i < len; i++) {
            dp[i][1] = single[0][i];
        }
        for (int i = 0; i < len; i++) {
            for (int j = 2; j <= Math.min(i + 1, K); j++) {
                dp[i][j] = Integer.MAX_VALUE;
                //前j - 1个值都对应一个邮箱为开始，对应下标j - 2，从下标j - 1到i都归最后邮箱管
                //右边界，最后一个邮箱只会负责i下标
                for (int k = j - 1; k <= i; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[k - 1][j - 1] + single[k][i]);
                }
            }
        }
        return dp[len - 1][K];
    }
}
