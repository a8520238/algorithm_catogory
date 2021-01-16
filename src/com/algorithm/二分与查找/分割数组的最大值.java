package com.algorithm.二分与查找;

/*
* 给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。

注意:
数组长度 n 满足以下条件:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
*/
public class 分割数组的最大值 {
    //这题看的第一眼感觉是个二分
    public int splitArray(int[] nums, int m) {
        int sum = 0;
        int min = 0;
        for (int num: nums) {
            sum += num;
            min = Math.max(num, min);
        }
        int left = min;
        int right = sum;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (match(nums, mid, m)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    public boolean match(int[] nums, int mid, int m) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > mid) {
                i--;
                m--;
                sum = 0;
            }
        }
        //如果m没有匹配完，证明mid小了，返回true，让mid变小
        return m >= 1;
    }
}
