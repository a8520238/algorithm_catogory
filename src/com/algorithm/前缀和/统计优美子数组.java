package com.algorithm.前缀和;

/*
* 给你一个整数数组 nums 和一个整数 k。

如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。

请返回这个数组中「优美子数组」的数目。

 

示例 1：

输入：nums = [1,1,2,1,1], k = 3
输出：2
解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
示例 2：

输入：nums = [2,4,6], k = 1
输出：0
解释：数列中不包含任何奇数，所以不存在优美子数组。
示例 3：

输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
输出：16
 

提示：

1 <= nums.length <= 50000
1 <= nums[i] <= 10^5
1 <= k <= nums.length
* */

public class 统计优美子数组 {
    //使用map存奇数的数量，效率很低
    // public int numberOfSubarrays(int[] nums, int k) {
    //     Map<Integer, Integer> map = new HashMap<>();
    //     map.put(0, 1);
    //     int res = 0;
    //     int n = 0;
    //     for (int i = 0; i < nums.length; i++) {
    //         if (nums[i] % 2 == 1) {
    //             n++;
    //         }
    //         if (map.containsKey(n - k)) {
    //             res += map.get(n - k);
    //         }
    //         map.put(n, map.getOrDefault(n, 0) + 1);
    //     }
    //     return res;
    // }
    //利用数组
    public int numberOfSubarrays(int[] nums, int k) {
        int[] f = new int[nums.length + 1];
        f[0] = 1;
        int res = 0, sum = 0;
        for (int n: nums) {
            sum += n & 1;
            f[sum] += 1;
            if (sum >= k) {
                res += f[sum - k];
            }
        }
        return res;
    }
}
