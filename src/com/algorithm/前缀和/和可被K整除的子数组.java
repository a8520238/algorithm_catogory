package com.algorithm.前缀和;

import java.util.HashMap;
import java.util.Map;

/*
* 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
示例：
输入：A = [4,5,0,-2,-3,1], K = 5
输出：7
解释：
有 7 个子数组满足其元素之和可被 K = 5 整除：
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 
提示：
1 <= A.length <= 30000
-10000 <= A[i] <= 10000
2 <= K <= 10000
* */

public class 和可被K整除的子数组 {
    public int subarraysDivByK(int[] A, int K) {
        // int[] sum = new int[A.length + 1];
        int sum = 0;
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 1; i <= A.length; i++) {
            // sum[i] = sum[i - 1] + A[i - 1];
            sum += A[i - 1];
            // int m = sum[i] % K;
            int m = sum % K;
            if (m  < 0) {
                m += K;
            }
            if (map.containsKey(m)) {
                res += map.get(m);
            }
            map.put(m, map.getOrDefault(m, 0) + 1);
        }
        return res;
    }
}
