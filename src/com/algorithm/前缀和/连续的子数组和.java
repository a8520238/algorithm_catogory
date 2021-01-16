package com.algorithm.前缀和;

/*
* 给定一个包含 非负数 的数组和一个目标 整数 k，编写一个函数来判断该数组是否含有连续的子数组，其大小至少为 2，且总和为 k 的倍数，即总和为 n*k，其中 n 也是一个整数。

 

示例 1：

输入：[23,2,4,6,7], k = 6
输出：True
解释：[2,4] 是一个大小为 2 的子数组，并且和为 6。
示例 2：

输入：[23,2,6,4,7], k = 6
输出：True
解释：[23,2,6,4,7]是大小为 5 的子数组，并且和为 42。
 

说明：

数组的长度不会超过 10,000 。
你可以认为所有数字总和在 32 位有符号整数范围内。
* */

public class 连续的子数组和 {
    //On解法
    //设位置 j < i :
// 0 到 j 的前缀和 preSum1 = 某常数1 * k + 余数1
// 0 到 i 的前缀和 preSum2 = 某常数2 * k + 余数2
// 当找到 余数1 等于 余数2时， 则 j + 1 到 i 的连续和 = preSum2 - preSum1 = (某常数2 - 某常数1) * k， 必为 k 的倍数， 返回true
    // public boolean checkSubarraySum(int[] nums, int k) {
    //     int sum = 0;
    //     Map<Integer, Integer> map = new HashMap<>();
    //     map.put(0, -1);
    //     for (int i = 0; i < nums.length; i++) {
    //         sum += nums[i];
    //         if (k != 0) {
    //             sum = sum % k;
    //         }
    //         if (map.containsKey(sum)) {
    //             if (i - map.get(sum) > 1) {
    //                 return true;
    //             }
    //         } else {
    //             map.put(sum, i);
    //         }
    //     }
    //     return false;
    // }
    //On2解法
    public boolean checkSubarraySum(int[] nums, int k) {
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        for (int start = 0; start < nums.length - 1; start++) {
            for (int end = start + 1; end < nums.length; end++) {
                int summ = sum[end] - sum[start] + nums[start];
                if (summ == k || (k != 0 && summ % k == 0)) {
                    return true;
                }
            }
        }
        return false;
    }
}
