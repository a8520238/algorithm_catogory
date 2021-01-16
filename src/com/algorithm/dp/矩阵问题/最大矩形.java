package com.algorithm.dp.矩阵问题;

/*
* 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。

示例 1：

输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
输出：6
解释：最大矩形如上图所示。
示例 2：

输入：matrix = []
输出：0
示例 3：

输入：matrix = [["0"]]
输出：0
示例 4：

输入：matrix = [["1"]]
输出：1
示例 5：

输入：matrix = [["0","0"]]
输出：0
 

提示：

rows == matrix.length
cols == matrix.length
0 <= row, cols <= 200
matrix[i][j] 为 '0' 或 '1'
* */

import java.util.Stack;

public class 最大矩形 {
    //思路：记录每一行连起来的最长值，向上找，每一行宽的最长值，取宽度的最小值，记录面积最大值
    public int maximalRectangle(char[][] matrix) {
        int row = matrix.length;
        if (row == 0) {
            return 0;
        }
        int col = matrix[0].length;
        int maxArea = 0;
        int[][] dp = new int[row + 1][col + 1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = dp[i][j - 1] + 1;
                    int width = dp[i][j];
                    for (int k = i; k >= 1; k--) {
                        if (matrix[k - 1][j - 1] == '0') {
                            break;
                        }
                        width = Math.min(width, dp[k][j]);
                        maxArea = Math.max(maxArea, width * (i - k + 1));
                    }
                }
            }
        }
        return maxArea;
    }
    //这道题可以简化成柱状图中的最大矩形
//    public int maximalRectangle(char[][] matrix) {
//        int row = matrix.length;
//        if (row == 0) {
//            return 0;
//        }
//        int col = matrix[0].length;
//        int max = 0;
//        int[] dp = new int[col];
//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < col; j++) {
//                //计算到dp[i][j]的最大高度
//                dp[j] = matrix[i][j] == '1'? dp[j] + 1: 0;
//            }
//            max = Math.max(max, process(dp));
//        }
//        return max;
//    }
//    public int process(int[] heights) {
//        Stack< Integer > stack = new Stack <> ();
//        stack.push(-1);
//        int maxarea = 0;
//        for (int i = 0; i < heights.length; ++i) {
//            //如果当前值小，开始弹出 实际上的意思是，不算当前值，向左找一个矩形的面积
//            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i])
//                //如果peek为-1，左边都比它大
//                maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));
//            stack.push(i);
//        }
//        while (stack.peek() != -1)
//            //右侧都比其大, 仍要考虑左边可达-1的情况， 故先pop，后peek
//            maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() -1));
//        return maxarea;
//    }
}
