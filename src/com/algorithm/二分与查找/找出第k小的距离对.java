package com.algorithm.二分与查找;

import java.util.Arrays;
/*
* 给定一个整数数组，返回所有数对之间的第 k 个最小距离。一对 (A, B) 的距离被定义为 A 和 B 之间的绝对差值。

示例 1:

输入：
nums = [1,3,1]
k = 1
输出：0
解释：
所有数对如下：
(1,3) -> 2
(1,1) -> 0
(3,1) -> 2
因此第 1 个最小距离的数对是 (1,1)，它们之间的距离为 0。
提示:

2 <= len(nums) <= 10000.
0 <= nums[i] < 1000000.
1 <= k <= len(nums) * (len(nums) - 1) / 2.
*/
public class 找出第k小的距离对 {
    public int smallestDistancePair(int[] nums, int k) {
        int len = nums.length;
        Arrays.sort(nums);
        int low = 0;
        int high = nums[len-1] - nums[0];
        while (low < high) {
            int mid = (high - low) / 2 + low;
            int left = 0;
            int count = 0;
            for (int i = left; i < len; i++) {
                while (nums[i] - nums[left] > mid) {
                    left++;
                }
                count += i - left;
            }
            if (count >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
