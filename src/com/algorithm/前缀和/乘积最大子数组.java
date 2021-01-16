package com.algorithm.前缀和;

public class 乘积最大子数组 {
    public int maxProduct(int[] nums) {
        //这里min、max的含义是到当前index为止，能到达的最大值和最小值
        int len = nums.length;
        int max = nums[0];
        int min = nums[0];
        int res = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i] > 0) {
                max = Math.max(nums[i], nums[i] * max);
                min = Math.min(nums[i], nums[i] * min);
            } else if (nums[i] == 0) {
                max = 0;
                min = 0;
            } else {
                int tmp = max;
                max = Math.max(nums[i], nums[i] * min);
                min = Math.min(nums[i], nums[i] * tmp);
            }
            res = Math.max(res, max);
        }
        return res;
    }
    //使用数组存
    // public int maxProduct(int[] nums) {
    //     int len = nums.length;
    //     int[] max = new int[len];
    //     int[] min = new int[len];
    //     max[0] = nums[0];
    //     min[0] = nums[0];
    //     for (int i = 1; i < len; i++) {
    //         if (nums[i] > 0) {
    //             max[i] = Math.max(nums[i], nums[i] * max[i - 1]);
    //             min[i] = Math.min(nums[i], nums[i] * min[i - 1]);
    //         } else if (nums[i] == 0) {
    //             continue;
    //         } else {
    //             max[i] = Math.max(nums[i], nums[i] * min[i - 1]);
    //             min[i] = Math.min(nums[i], nums[i] * max[i - 1]);
    //         }
    //     }
    //     int res = max[0];
    //     for (int i = 1; i < max.length; i++) {
    //         res = Math.max(res, max[i]);
    //     }
    //     return res;
    // }
}
