package com.algorithm.dp.矩阵问题;

/*
* 给你一个 rows x cols 大小的矩形披萨和一个整数 k ，矩形包含两种字符： 'A' （表示苹果）和 '.' （表示空白格子）。你需要切披萨 k-1 次，得到 k 块披萨并送给别人。

切披萨的每一刀，先要选择是向垂直还是水平方向切，再在矩形的边界上选一个切的位置，将披萨一分为二。如果垂直地切披萨，那么需要把左边的部分送给一个人，如果水平地切，那么需要把上面的部分送给一个人。在切完最后一刀后，需要把剩下来的一块送给最后一个人。

请你返回确保每一块披萨包含 至少 一个苹果的切披萨方案数。由于答案可能是个很大的数字，请你返回它对 10^9 + 7 取余的结果。

 

示例 1：



输入：pizza = ["A..","AAA","..."], k = 3
输出：3
解释：上图展示了三种切披萨的方案。注意每一块披萨都至少包含一个苹果。
示例 2：

输入：pizza = ["A..","AA.","..."], k = 3
输出：1
示例 3：

输入：pizza = ["A..","A..","..."], k = 1
输出：1
 

提示：

1 <= rows, cols <= 50
rows == pizza.length
cols == pizza[i].length
1 <= k <= 10
pizza 只包含字符 'A' 和 '.' 。
* */

public class 切披萨的方案数 {
    public int ways(String[] pizza, int k) {
        if (pizza.length == 0 ||pizza[0].length() == 0 || k <= 0) {
            return 0;
        }
        int m = pizza.length, n = pizza[0].length();
        //dp的含义是第k次，以i,j为起始能分成的办法数量
        int[][] dp = new int[m][n];
        int maxRight = -1;
        //赋初始值，k为1的情况，此时k-1等于0，如果当前位置为A，则以当前位置的左、上、左上为起点的一定可以为1
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (j > maxRight) {
                    if (pizza[i].charAt(j) == 'A') {
                        maxRight = j;
                        dp[i][j] = 1;
                    }
                } else {
                    dp[i][j] = 1;
                }
            }
        }
        for (int ki = 1; ki < k; ki++) {
            for (int row = 0; row < m; row++) {
                for (int col = 0; col < n; col++) {
                    int ri = row, ci = col;
                    while (ri < m) {
                        if (pizza[ri].substring(col).indexOf('A') == -1) {
                            ri++;
                        } else {
                            break;
                        }
                    }
                    while (ci < n) {
                        boolean hasA = false;
                        int r = ri;
                        while (r < m) {
                            if (pizza[r].charAt(ci) == 'A') {
                                hasA = true;
                                break;
                            }
                            r++;
                        }
                        if (hasA) {
                            break;
                        } else {
                            ci++;
                        }
                    }
                    int res = 0;
                    //这里涉及到dp的复用，找到第一个为A的ri ci后，往下切，切分位置能完成的次数，即为上一次的dp[i1][j1].
                    for (int i = ri + 1; i < m; i++) {
                        res = (res + dp[i][ci]) % 1000000007;
                    }
                    for (int j = ci + 1; j < n; j++) {
                        res = (res + dp[ri][j]) % 1000000007;
                    }
                    dp[row][col] = res;
                    if (ki == k - 1) {
                        return res;
                    }
                }
            }
        }
        return dp[0][0];
    }
}
