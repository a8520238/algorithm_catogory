package com.algorithm.技巧和数学;

import java.util.Random;

public class 随机数索引398 {
    int[] nums;
    public 随机数索引398(int[] nums) {
        this.nums = nums;
    }

    public int pick(int target) {
        Random r = new Random();
        int res = 0;
        int index = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (r.nextInt(index++) == 0) {
                    res = i;
                }
            }
        }
        return res;
    }
}
