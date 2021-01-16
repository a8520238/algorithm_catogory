package com.algorithm.贪心;

/*
* 有一个二维矩阵 A 其中每个元素的值为 0 或 1 。

移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。

在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。

返回尽可能高的分数。

 

示例：

输入：[[0,0,1,1],[1,0,1,0],[1,1,0,0]]
输出：39
解释：
转换为 [[1,1,1,1],[1,0,0,1],[1,1,1,1]]
0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 

提示：

1 <= A.length <= 20
1 <= A[0].length <= 20
A[i][j] 是 0 或 1
* */

//自己写的战胜了29%， 反转行的规则，如果反转后数变大反转，反转列的规则，反转后1变多则反转
// class Solution {
//     public int matrixScore(int[][] A) {
//         if (A.length == 0 || A[0].length == 0) {
//             return 0;
//         }
//         int row = A.length, col = A[0].length;
//         for (int i = 0; i < row; i++) {
//             processRow(A, i);
//         }
//         for (int j = 0; j < col; j++) {
//             processCol(A, j);
//         }
//         return count(A);
//     }
//     public void processRow(int[][] A, int index) {
//         int[] cur;
//         cur = A[index];
//         int[] reverse = new int[cur.length];
//         for (int i = 0; i < cur.length; i++) {
//             reverse[i] = cur[i] == 0? 1: 0;
//         }
//         boolean change = false;
//         for (int i = 0; i < cur.length; i++) {
//             if (cur[i] > reverse[i]) {
//                 break;
//             } else if (reverse[i] > cur[i]) {
//                 change = true;
//                 break;
//             }
//         }
//         if (change) {
//             for (int i = 0; i < cur.length; i++) {
//                 A[index][i] = reverse[i];
//             }
//         }
//     }
//     public void processCol(int[][] A, int index) {
//         int[] cur = new int[A.length];
//         for (int i = 0; i < A.length; i++) {
//             cur[i] = A[i][index];
//         }
//         int[] reverse = new int[cur.length];
//         for (int i = 0; i < cur.length; i++) {
//             reverse[i] = cur[i] == 0? 1: 0;
//         }
//         int countCur = 0;
//         int countReverse = 0;
//         for (int i = 0; i < cur.length; i++) {
//             countCur += cur[i];
//             countReverse += reverse[i];
//         }
//         if (countReverse > countCur) {
//             for (int i = 0; i < cur.length; i++) {
//                 A[i][index] = reverse[i];
//             }
//         }
//     }
//     public int count(int[][] A) {
//         int len = A.length;
//         int col = A[0].length;
//         int res = 0;
//         for (int i = 0; i < len; i++) {
//             int[] cur = A[i];
//             int curCount = 0;
//             for (int j = 0; j < col; j++) {
//                 if (cur[j] == 1) {
//                     curCount += (int)Math.pow(2, col - j - 1);
//                 }
//             }
//             res += curCount;
//         }
//         return res;
//     }
// }

class 翻转矩阵后的得分 {
    public int matrixScore(int[][] A) {
        int m = A.length, n = A[0].length;
        //第一列的贡献
        int ret = m * (1 << (n - 1));
        for (int j = 1; j < n; j++) {
            int nOnes = 0;
            for (int i = 0; i < m; i++) {
                if (A[i][0] == 1) {
                    nOnes += A[i][j];
                } else {
                    nOnes += (1 - A[i][j]);// 如果这一行进行了行反转，则该元素的实际取值为 1 - A[i][j]
                }
            }
            int k = Math.max(nOnes, m - nOnes);
            //生成二进制，优先考虑左右移动
            ret += k * (1 << (n - j - 1));
        }
        return ret;
    }
}