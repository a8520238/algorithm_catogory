package com.algorithm.前缀和;

import java.util.HashMap;
import java.util.Map;

/*
* 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。

示例 1 :

输入:nums = [1,1,1], k = 2
输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
说明 :

数组的长度为 [1, 20,000]。
数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
* */

public class 和为k的子数组 {
    //list前缀和法，效率很低
    // public int subarraySum(int[] nums, int k) {
    //     int[] sum = new int[nums.length + 1];
    //     Map<Integer, List<Integer>> map = new HashMap<>();
    //     map.put(0, new ArrayList<>());
    //     map.get(0).add(-1);
    //     for (int i = 1; i <= nums.length; i++) {
    //         sum[i] = sum[i - 1] + nums[i - 1];
    //         if (!map.containsKey(sum[i])) {
    //             map.put(sum[i], new ArrayList<>());
    //         }
    //         map.get(sum[i]).add(i - 1);
    //     }
    //     int res = 0;
    //     for (int i = 1; i <= nums.length; i++) {
    //         List<Integer> list = map.get(sum[i] - k);
    //         if (list == null) {
    //             continue;
    //         }
    //         for (int j = 0; j < list.size(); j++) {
    //             if (list.get(j) < i - 1) {
    //                 res++;
    //             }
    //         }
    //     }
    //     return res;
    // }
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        int res = 0;
        //利用从前向后遍历，省去了list,避免了后面的index比前面小的问题
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
