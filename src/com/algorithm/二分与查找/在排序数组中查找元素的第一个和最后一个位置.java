package com.algorithm.二分与查找;

/*
* 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 target，返回 [-1, -1]。

进阶：

你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 

示例 1：

输入：nums = [5,7,7,8,8,10], target = 8
输出：[3,4]
示例 2：

输入：nums = [5,7,7,8,8,10], target = 6
输出：[-1,-1]
* */

public class 在排序数组中查找元素的第一个和最后一个位置 {
    public int[] searchRange(int[] nums, int target) {
        // 找最左相等，就普通思想，要注意如果target很大，最终可能到达nums.length
        int left = 0, right = nums.length;
        int[] res = new int[2];
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        res[0] = left != nums.length && nums[left] == target? left: -1;
        //找最右相等，相等的时候left = mid + 1，这样会把值错过，所以left最后要减去1，这样的问题会导致，如果target很小，最后left == -1，所以也要检测
        left = 0;
        right = nums.length;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        left = left - 1;
        res[1] = left != -1 && nums[left] == target? left: -1;
        return res;
    }
}
