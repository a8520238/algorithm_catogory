package com.algorithm.dp.单串LIS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
* 给定一个无序的整数数组，找到其中最长上升子序列的长度。

示例:

输入: [10,9,2,5,3,7,101,18]
输出: 4
解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
*/

//list代表扑克数组，其是升序，二分找到list中第一个大于n的，把n放在上面，
// 这样做的意义是使得list一直保持升序如果找不到，新开一个list[i]
public class 最长上升子序列300 {
    public int lengthOfLIS(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int left = 0;
            int right = list.size();
            while (left < right) {
                //这里二分的意义是找到第一个list中值大于当前n的。
                int mid = (right - left) / 2 + left;
                if (list.get(mid) >= nums[i]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            //如果找不到新建一堆
            if (left == list.size()) {
                list.add(nums[i]);
            } else {
                //如果找到了把较小的值放在上面
                list.set(left, nums[i]);
            }
        }
        return list.size();
    }
    //dp on2
//    public int lengthOfLIS(int[] nums) {
//        if (nums.length == 0) {
//            return 0;
//        }
//        int len = nums.length;
//        int[] dp = new int[len];
//        Arrays.fill(dp, 1);
//        for (int i = 1; i < len; i++) {
//            for (int j = 0; j < i; j++) {
//                if (nums[i] > nums[j]) {
//                    dp[i] = Math.max(dp[i], dp[j] + 1);
//                }
//            }
//        }
//        int max = 0;
//        for (int i = 0; i < len; i++) {
//            max = Math.max(max, dp[i]);
//        }
//        return max;
//    }
}
