package com.algorithm.回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* 给定一个可包含重复数字的序列，返回所有不重复的全排列。

示例:

输入: [1,1,2]
输出:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
* */

public class 全排列2 {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    boolean[] visit;
    public List<List<Integer>> permuteUnique(int[] nums) {
        visit = new boolean[nums.length];
        Arrays.sort(nums);
        backward(nums);
        return res;
    }
    public void backward (int[] nums) {
        if (list.size() == nums.length) {
            res.add(new ArrayList(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visit[i] || (i > 0 && nums[i] == nums[i-1] && !visit[i-1])) {
                continue;
            }
            list.add(nums[i]);
            visit[i] = true;
            backward(nums);
            list.remove(list.size() - 1);
            visit[i] = false;
        }
    }
}
