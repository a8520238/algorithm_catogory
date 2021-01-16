package com.algorithm.前缀和;

import java.util.Arrays;

/*
* 给定一个二进制数组, 找到含有相同数量的 0 和 1 的最长连续子数组（的长度）。

 

示例 1:

输入: [0,1]
输出: 2
说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
示例 2:

输入: [0,1,0]
输出: 2
说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
 

注意: 给定的二进制数组的长度不会超过50000。
* */

public class 连续数组 {
    //使用前缀和加上哈希表
    // public int findMaxLength(int[] nums) {
    //     Map<Integer, Integer> map = new HashMap<>();
    //     map.put(0, -1);
    //     int maxlen = 0, count = 0;
    //     for (int i = 0; i < nums.length; i++) {
    //         count = count + (nums[i] == 1? 1: -1);
    //         if (map.containsKey(count)) {
    //             maxlen = Math.max(maxlen, i - map.get(count));
    //         } else {
    //             map.put(count, i);
    //         }
    //     }
    //     return maxlen;
    // }
    //我们从头开始遍历数组，在任何时候，如果 countcount 变成了 0 ，这表示我们从开头到当前位置遇到的 0 和 1 的数目一样多。另一个需要注意的是，如果我们在遍历数组的过程汇中遇到了相同的 countcount 2 次，这意味着这两个位置之间 0 和 1 的数目一样多。
    public int findMaxLength(int[] nums) {
        int[] arr = new int[2 * nums.length + 1];
        Arrays.fill(arr, -2);
        arr[nums.length] = -1;
        int maxlen = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 0? -1: 1);
            if (arr[count + nums.length] >= -1) {
                maxlen = Math.max(maxlen, i - arr[count + nums.length]);
            } else {
                arr[count + nums.length] = i;
            }
        }
        return maxlen;
    }
}
