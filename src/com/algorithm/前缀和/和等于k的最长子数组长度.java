package com.algorithm.前缀和;

/*
* 给定一个数组 nums 和一个目标值 k，找到和等于 k 的最长子数组长度。如果不存在任意一个符合要求的子数组，则返回 0。

注意:
 nums 数组的总和是一定在 32 位有符号整数范围之内的。

示例 1:

输入: nums = [1, -1, 5, -2, 3], k = 3
输出: 4
解释: 子数组 [1, -1, 5, -2] 和等于 3，且长度最长。
示例 2:

输入: nums = [-2, -1, 2, 1], k = 1
输出: 2
解释: 子数组 [-1, 2] 和等于 1，且长度最长。
进阶:
你能使时间复杂度在 O(n) 内完成此题吗?
* */

import java.util.HashMap;
import java.util.Map;

public class 和等于k的最长子数组长度 {
    //暴力解法
    // public int maxSubArrayLen(int[] nums, int k) {
    //     int[] sum = new int[nums.length + 1];
    //     for (int i = 1; i <= nums.length; i++) {
    //         sum[i] = sum[i - 1] + nums[i - 1];
    //     }
    //     int max = 0;
    //     for (int i = 0; i < nums.length; i++) {
    //         for (int j = 0; j <= i; j++) {
    //             if (sum[i + 1] - sum[j] == k) {
    //                 max = Math.max(max, i - j + 1);
    //             }
    //         }
    //     }
    //     return max;
    // }
    //利用map存储和的下标 效率高
    // public int maxSubArrayLen(int[] nums, int k) {
    //     int len = nums.length;
    //     int max = 0;
    //     int[] sum = new int[len + 1];
    //     // 求前缀和，并利用 Map<前缀和, 对应索引> 储存
    //     Map<Integer, Integer> map = new HashMap<>();
    //     map.put(0, 0);
    //     for (int i = 1; i <= len; i++) {
    //         sum[i] = sum[i - 1] + nums[i - 1];
    //         //把出现此前缀和的最小下标存储
    //         if (!map.containsKey(sum[i])) {
    //             map.put(sum[i], i);
    //         }
    //     }
    //     // 从后向前遍历数组，i为子数组的结尾，寻找符合条件的前缀和及其索引
    //     for (int i = len; i > max; i--) {
    //         if (map.containsKey(sum[i] - k)) {
    //             max = Math.max(max, i - map.get(sum[i] - k));
    //         }
    //     }
    //     return max;
    // }
    //利用map存储和的下标 更好理解版本
    public int maxSubArrayLen(int[] nums, int k) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            sum = sum + nums[i];
            //相当于窗口右移一位
            if (sum == k) {
                ans = i + 1;
            }
            if (map.containsKey(sum - k)) {
                ans = Math.max(ans, i - map.get(sum - k));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return ans;
    }
}
