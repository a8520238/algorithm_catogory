package com.algorithm.dp.最大子数组和;

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
*/

public class 最大子矩阵 {
    public int[] getMaxMatrix(int[][] matrix) {
        int[] res = new int[4];
        int beginR = 0, beginC = 0;
        int m = matrix.length, n = matrix[0].length;
        int[] curRow = new int[n];
        int max = Integer.MIN_VALUE;
        //i代表起始行
        for (int i = 0; i < m; i++) {
            //curRow代表起始行往下每一列的和
            for (int t = 0; t < n; t++) {
                curRow[t] = 0;
            }
            //j代表结束行
            for (int j = i; j < m; j++) {
                //把每一次遍历k都看成一次最大子序和，sum代表当前正数值
                int sum = 0;
                //k代表每一列
                for (int k = 0; k < n; k++) {
                    curRow[k] += matrix[j][k];
                    if (sum > 0) {
                        sum += curRow[k];
                    } else {
                        sum = curRow[k];
                        beginR = i;
                        beginC = k;
                    }
                    if (sum > max) {
                        max = sum;
                        res[0] = beginR;
                        res[1] = beginC;
                        res[2] = j;
                        res[3] = k;
                    }
                }
            }
        }
        return res;
    }
}
