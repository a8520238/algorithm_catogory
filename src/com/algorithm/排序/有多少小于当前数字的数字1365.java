package com.algorithm.排序;
/*
* 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。

换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。

以数组形式返回答案。
*/
public class 有多少小于当前数字的数字1365 {
    //排序思想
    // public int[] smallerNumbersThanCurrent(int[] nums) {
    //     int n = nums.length;
    //     int[][] data = new int[n][2];
    //     for (int i = 0; i < n; i++) {
    //         data[i][0] = nums[i];
    //         data[i][1] = i;
    //     }
    //     Arrays.sort(data, (a, b) -> a[0] - b[0]);
    //     int[] res = new int[n];
    //     int pre = -1;
    //     for (int i = 0; i < n; i++) {
    //         if (pre == -1 || data[i][0] != data[i - 1][0]) {
    //             pre = i;
    //         }
    //         res[data[i][1]] = pre;
    //     }
    //     return res;
    // }
    //计数排序
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] count = new int[101];
        for (int n: nums) {
            count[n] += 1;
        }
        for (int i = 1; i < 101; i++) {
            count[i] += count[i - 1];
        }
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = nums[i] == 0? 0: count[nums[i] - 1];
        }
        return res;
    }
}
