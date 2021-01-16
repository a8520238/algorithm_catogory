package com.algorithm.前缀和;

/*
* 给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。

子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。

如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同。

 

示例 1：

输入：matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
输出：4
解释：四个只含 0 的 1x1 子矩阵。
示例 2：

输入：matrix = [[1,-1],[-1,1]], target = 0
输出：5
解释：两个 1x2 子矩阵，加上两个 2x1 子矩阵，再加上一个 2x2 子矩阵。
 

提示：

1 <= matrix.length <= 300
1 <= matrix[0].length <= 300
-1000 <= matrix[i] <= 1000
-10^8 <= target <= 10^8
* */

//要注意，二维转换为一维这种思路很重要
public class 元素和为目标值的子矩阵数量 {
    //二维变一维的思路
    // public int numSubmatrixSumTarget(int[][] matrix, int target) {
    //     int row = matrix.length;
    //     int col = matrix[0].length;
    //     int[] sum;
    //     int count = 0;
    //     //i为矩形起始头，二维变一维
    //     for (int i = 0; i < row; i++) {
    //         sum = new int[col];
    //         for (int j = i; j < row; j++) {
    //             for (int k = 0; k < col; k++) {
    //                 sum[k] += matrix[j][k];
    //             }
    //             for (int k = 0; k < sum.length; k++) {
    //                 int sum1 = 0;
    //                 for (int l = k; l < col; l++) {
    //                     sum1 += sum[l];
    //                     if (sum1 == target) {
    //                         count++;
    //                     }
    //                 }
    //             }
    //         }
    //     }
    //     return count;
    // }
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length;
        int[][] sum = new int[row + 1][col + 1];
        for (int i = 0; i < row; i++) {
            int cur = 0;
            for (int j = 0; j < col; j++) {
                cur += matrix[i][j];
                sum[i + 1][j + 1] = sum[i][j + 1] + cur;
            }
        }
        int count = 0;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                for (int x = 1; x <= i; x++) {
                    for (int y = 1; y <= j; y++) {
                        int v = sum[i][j] - sum[x - 1][j] - sum[i][y - 1] + sum[x - 1][y - 1];
                        if (v == target) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}
