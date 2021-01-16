package com.algorithm.数组;

/*
* 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。

注意：

答案中不可以包含重复的四元组。

示例：

给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。

满足要求的四元组集合为：
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]

* */

//要注意剪枝和去重

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class 四数之和 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int left = j + 1, right = nums.length - 1;
                if (nums[i] + nums[j] + nums[left] + nums[left] > target) {
                    break;
                }
                if (nums[i] + nums[j] + nums[right] + nums[right - 1] < target) {
                    continue;
                }
                while (left < right) {
                    if (left > j + 1 && nums[left] == nums[left - 1]) {
                        left++;
                        continue;
                    }
                    if (nums[i] + nums[j] + nums[left] + nums[right] > target) {
                        right--;
                    } else if (nums[i] + nums[j] + nums[left] + nums[right] < target) {
                        left++;
                    } else {
                        res.add(Arrays.asList(new Integer[]{nums[i], nums[j], nums[left], nums[right]}));
                        // res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        // right--;
                    }
                }
            }
        }
        return res;
    }
}