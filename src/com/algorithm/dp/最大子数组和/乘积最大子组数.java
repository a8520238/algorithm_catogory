package com.algorithm.dp.最大子数组和;

/*
* 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。

 

示例 1:

输入: [2,3,-2,4]
输出: 6
解释: 子数组 [2,3] 有最大乘积 6。
示例 2:

输入: [-2,0,-1]
输出: 0
解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。

* */

public class 乘积最大子组数 {
    public int maxProduct(int[] nums) {
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];
        max[0] = nums[0];
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // max[i] = Math.max(Math.max(nums[i], nums[i] * max[i - 1]), nums[i] * min[i - 1]);
            // min[i] = Math.min(Math.min(nums[i], nums[i] * max[i - 1]), nums[i] * min[i - 1]);
            if (nums[i] > 0) {
                max[i] = Math.max(nums[i], nums[i] * max[i - 1]);
                min[i] = Math.min(nums[i], nums[i] * min[i - 1]);
            } else if (nums[i] == 0) {
                continue;
            } else {
                max[i] = Math.max(nums[i], nums[i] * min[i - 1]);
                min[i] = Math.min(nums[i], nums[i] * max[i - 1]);
            }
        }
        int res = max[0];
        for (int i = 1; i < max.length; i++) {
            res = Math.max(res, max[i]);
        }
        return res;
    }
}
