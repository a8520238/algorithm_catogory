package com.algorithm.dp.矩阵问题;

import java.util.List;

public class 三角形最小路径和 {
    //自己写的dp，每一行新建一个新的数组，记录当前位置最小值
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0) {
            return 0;
        }
        int[] dp = new int[1];
        dp[0] = triangle.get(0).get(0);
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> cur = triangle.get(i);
            int[] curDp = new int[dp.length + 1];
            curDp[0] = dp[0] + cur.get(0);
            for (int j = 1; j < cur.size() - 1; j++) {
                curDp[j] = Math.min(dp[j], dp[j - 1]) + cur.get(j);
            }
            curDp[curDp.length - 1] = dp[curDp.length - 2] + cur.get(curDp.length - 1);
            dp = curDp;
        }
        for (int i = 0; i < dp.length; i++) {
            res = Math.min(res, dp[i]);
        }
        return res;
    }
    //不新建int[]，因为只与当前与之前有关，所以可以服用
//    public int minimumTotal(List<List<Integer>> triangle) {
//        int n = triangle.size();
//        int[] f = new int[n];
//        f[0] = triangle.get(0).get(0);
//        for (int i = 1; i < n; i++) {
//            f[i] = f[i - 1] + triangle.get(i).get(i);
//            for (int j = i - 1; j > 0; j--) {
//                f[j] = Math.min(f[j - 1], f[j]) + triangle.get(i).get(j);
//            }
//            f[0] += triangle.get(i).get(0);
//        }
//        int min = f[0];
//        for (int i = 1; i < n; i++) {
//            min = Math.min(min, f[i]);
//        }
//        return min;
//    }
}
