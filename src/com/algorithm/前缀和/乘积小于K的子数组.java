package com.algorithm.前缀和;

/*
* 给定一个正整数数组 nums。

找出该数组内乘积小于 k 的连续的子数组的个数。

示例 1:

输入: nums = [10,5,2,6], k = 100
输出: 8
解释: 8个乘积小于100的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
说明:

0 < nums.length <= 50000
0 < nums[i] < 1000
0 <= k < 10^6
* */

public class 乘积小于K的子数组 {
    //直接暴力，由于溢出结果错误
    // public int numSubarrayProductLessThanK(int[] nums, int k) {
    //     int[] mul = new int[nums.length + 1];
    //     mul[0] = 1;
    //     int res = 0;
    //     for (int i = 1; i <= nums.length; i++) {
    //         mul[i] = mul[i - 1] * nums[i - 1];
    //     }
    //     for (int i = 0; i < nums.length; i++) {
    //         for (int j = i + 1; j <= nums.length; j++) {
    //             int cur = mul[j] / mul[i];
    //             if (cur < k) {
    //                 res++;
    //             }
    //         }
    //     }
    //     return res;
    // }
    //二分查找 log(log是真的妙)
    // public int numSubarrayProductLessThanK(int[] nums, int k) {
    //     if (k == 0) {
    //         return 0;
    //     }
    //     double logk = Math.log(k);
    //     double[] prefix = new double[nums.length + 1];
    //     for (int i = 0; i < nums.length; i++) {
    //         prefix[i + 1] = prefix[i] + Math.log(nums[i]);
    //     }
    //     int ans = 0;
    //     for (int i = 0; i < prefix.length; i++) {
    //         int lo = i + 1, hi = prefix.length;
    //         //这里二分的意义是找到以i为起始点，lo为终点都能小于k,因为都是正整数所以是有序的
    //         while (lo < hi) {
    //             int mi = lo + (hi - lo) / 2;
    //             if (prefix[mi] - prefix[i] < logk - 1e-8) {
    //                 lo = mi + 1;
    //             } else {
    //                 hi = mi;
    //             }
    //         }
    //         //因为上述二分找到的是第一个比logk的值，不应该被包括，所以总数直接lo - (i + 1)
    //         ans += lo - i - 1;//lo - (i + 1)
    //     }
    //     return ans;
    // }
    //双指针滑动窗口（这思路绝了）
    /*
    我们使用一重循环枚举 \mathrm{right}right，同时设置 \mathrm{left}left 的初始值为 0。在循环的每一步中，表示 \mathrm{right}right 向右移动了一位，将乘积乘以 \mathrm{nums}[\mathrm{right}]nums[right]。此时我们需要向右移动 \mathrm{left}left，直到满足乘积小于 kk 的条件。在每次移动时，需要将乘积除以 \mathrm{nums}[\mathrm{left}]nums[left]。当 \mathrm{left}left 移动完成后，对于当前的 \mathrm{right}right，就包含了 \mathrm{right} - \mathrm{left} + 1right−left+1 个乘积小于 kk 的连续子数组。
    */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }
        int prod = 1;//当前的积和
        int ans = 0;//最终的数量
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            prod *= nums[right];
            while (prod >= k) {
                prod /= nums[left++];
            }
            ans += right - left + 1;
        }
        return ans;
    }
}
