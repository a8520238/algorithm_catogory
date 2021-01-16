package com.algorithm.区间动态规划;

/*
* 给定 N，想象一个凸 N 边多边形，其顶点按顺时针顺序依次标记为 A[0], A[i], ..., A[N-1]。

假设您将多边形剖分为 N-2 个三角形。对于每个三角形，该三角形的值是顶点标记的乘积，三角剖分的分数是进行三角剖分后所有 N-2 个三角形的值之和。

返回多边形进行三角剖分后可以得到的最低分。
 

示例 1：

输入：[1,2,3]
输出：6
解释：多边形已经三角化，唯一三角形的分数为 6。
示例 2：



输入：[3,7,4,5]
输出：144
解释：有两种三角剖分，可能得分分别为：3*7*5 + 4*5*7 = 245，或 3*4*5 + 3*4*7 = 144。最低分数为 144。
示例 3：

输入：[1,3,1,4,1,5]
输出：13
解释：最低分数三角剖分的得分情况为 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13。
 

提示：

3 <= A.length <= 50
1 <= A[i] <= 100
* */


public class 多边形三角剖分的最低得分 {
    public int minScoreTriangulation(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        //从 0 ~ N-1 形成一个环
        //    1—3
        //   /    \
        //  5      1
        //   \    /
        //    1—4
        // dp[left][right] 代表left~right区间形成的环的最小得分值
        int[][] dp = new int[n][n];
        for (int len = 3; len <= n; len++) {//枚举长度,从3开始
            for (int left = 0; left + len <= n; left++) {//枚举左端点
                //left+len-1<N
                int right = left + len - 1;
                dp[left][right] = Integer.MAX_VALUE;
                for (int i = left + 1; i < right; i++) {//枚举区间内的所有的点(不包括端点)),将环分割成左右两部分,这里试了一下，这种方法确实能行，如果right - left + 1 < 3值一定是0，其他情况确实可以划分三角形。
                    dp[left][right] = Math.min(dp[left][right], dp[left][i] + dp[i][right] + A[i] * A[left] * A[right]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
