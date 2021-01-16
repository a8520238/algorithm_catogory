package com.algorithm.dp.矩阵问题;

//在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
public class 最大正方形 {
    //dp含义，到i,j位置，能形成最大正方形的宽度
    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row + 1][col + 1];
        int max = 0;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (matrix[i - 1][j - 1] == '0') {
                    dp[i][j] = 0;
                    continue;
                }
                int min = Math.min(dp[i - 1][j], dp[i][j - 1]);
                //判断左、上两个正方形没有覆盖到的值是否为1
                int n = matrix[i - min - 1][j - min - 1] == '1'? 1: 0;
                min += n;
                max = Math.max(min, max);
                dp[i][j] = min;
            }
        }
        return max * max;
    }
}
