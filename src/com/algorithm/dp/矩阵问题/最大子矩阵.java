package com.algorithm.dp.矩阵问题;

/*
* 给定一个正整数、负整数和 0 组成的 N × M 矩阵，编写代码找出元素总和最大的子矩阵。

返回一个数组 [r1, c1, r2, c2]，其中 r1, c1 分别代表子矩阵左上角的行号和列号，r2, c2 分别代表右下角的行号和列号。若有多个满足条件的子矩阵，返回任意一个均可。

注意：本题相对书上原题稍作改动

示例：

输入：
[
   [-1,0],
   [0,-1]
]
输出：[0,1,0,1]
解释：输入中标粗的元素即为输出所表示的矩阵
*
* */
public class 最大子矩阵 {
    //这道题与矩形区域不超过 K 的最大数值和 相同，可以采用起始行与结束行之间的和作为dp，做LIS，复杂度缩小到Om2n
    int max = Integer.MIN_VALUE;
    public int[] getMaxMatrix(int[][] matrix) {
        int[] res = new int[4];
        if (matrix.length == 0) {
            return res;
        }
        int len = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < len; i++) {
            int[] dp = new int[col];
            for (int j = i; j < len; j++) {
                for (int k = 0; k < col; k++) {
                    dp[k] += matrix[j][k];
                }
                process(dp, res, i, j);
            }
        }
        return res;
    }
    public void process(int[] arr, int[] res, int row1, int row2) {
        int dp = 0;
        int start = 0;
        for (int i = 0; i < arr.length; i++) {
            if (dp < 0) {
                dp = arr[i];
                start = i;
            } else {
                dp += arr[i];
            }
            if (dp > max) {
                res[0] = row1;
                res[1] = start;
                res[2] = row2;
                res[3] = i;
                max = dp;
            }
        }
    }
}
