package com.algorithm.区间动态规划;

/*
* 给出一些不同颜色的盒子，盒子的颜色由数字表示，即不同的数字表示不同的颜色。
你将经过若干轮操作去去掉盒子，直到所有的盒子都去掉为止。每一轮你可以移除具有相同颜色的连续 k 个盒子（k >= 1），这样一轮之后你将得到 k*k 个积分。
当你将所有盒子都去掉之后，求你能获得的最大积分和。

 

示例：

输入：boxes = [1,3,2,2,2,3,4,3,1]
输出：23
解释：
[1, 3, 2, 2, 2, 3, 4, 3, 1]
----> [1, 3, 3, 4, 3, 1] (3*3=9 分)
----> [1, 3, 3, 3, 1] (1*1=1 分)
----> [1, 1] (3*3=9 分)
----> [] (2*2=4 分)
 

提示：

1 <= boxes.length <= 100
1 <= boxes[i] <= 100
*
* */

public class 移除盒子 {
    //dp含义，从i-j移除相同颜色的盒子，j右边有k个与aj颜色相同的盒子的方案
    //策略1 先消除右边a[j]和k个与a[j]相同颜色的盒子
    //dp[i][j][k] = dp[i][j - 1][0] + (k + 1) * (k + 1);
    //如果a[t] == a[r], dp[i][j] = dp[t + 1][j - 1][0] + dp[i][t][k + 1]
    public int removeBoxes(int[] boxes) {
        int[][][] dp = new int[100][100][100];
        return process(boxes, dp, 0, boxes.length - 1, 0);
    }
    public int process(int[] boxes, int[][][] dp, int left, int right, int k) {
        if (left > right) {
            return 0;
        }
        if (dp[left][right][k] != 0) {
            return dp[left][right][k];
        }
        //第一种情况，最后一位数前方有连续相等
        while (right > left && boxes[right] == boxes[right - 1]) {
            right--;
            k++;
        }
        //process(boxes, dp, left, right - 1, 0)中k为何是0，因为这里是先找后缀的，后面就算有与当前值相等的，也会被先删除。
        dp[left][right][k] = process(boxes, dp, left, right - 1, 0) + (k + 1) * (k + 1);
        for (int i = left; i < right; i++) {
            if (boxes[i] == boxes[right]) {
                //相当于在尾部找完连续相等，如果在l到新的r中，存在与boxes[r]相等的值，计算先删除该点至新r的值再加上原k加上1后的继续迭代
                dp[left][right][k] = Math.max(dp[left][right][k], process(boxes, dp, left, i, k + 1) + process(boxes, dp, i + 1, right - 1, 0));
            }
        }
        return dp[left][right][k];
    }
}
