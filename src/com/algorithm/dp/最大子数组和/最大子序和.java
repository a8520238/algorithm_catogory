package com.algorithm.dp.最大子数组和;

/*
* 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

示例:

输入: [-2,1,-3,4,-1,2,1,-5,4]
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。

* */
// class 最大子序和 {
//     public int maxSubArray(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
//         int[] dp = new int[nums.length];
//         int max = nums[0];
//         dp[0] = nums[0];
//         for (int i = 1; i < dp.length; i++) {
//             dp[i] = dp[i - 1] <= 0? nums[i]: dp[i - 1] + nums[i];
//             max = Math.max(max, dp[i]);
//         }
//         return max;
//     }
// }

//分治
class 最大子序和 {
    class Status {
        public int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }
    public int maxSubArray(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).mSum;
    }
    public Status getInfo(int[] a, int l, int r) {
        if (l == r) {
            return new Status(a[l], a[l], a[l], a[l]);
        }
        int m = (r - l) / 2 + l;
        Status lSub = getInfo(a, l, m);
        Status rSub = getInfo(a, m + 1, r);
        return pushUp(lSub, rSub);
    }
    public Status pushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(Math.max(l.mSum, r.mSum), r.lSum + l.rSum);
        return new Status(lSum, rSum, mSum, iSum);
    }
}