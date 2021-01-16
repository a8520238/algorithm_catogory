package com.algorithm.回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用一次。

说明：

所有数字（包括目标数）都是正整数。
解集不能包含重复的组合。 
示例 1:

输入: candidates = [10,1,2,7,6,1,5], target = 8,
所求解集为:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]

* */

public class 组合总和2两种解法 {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    int sum = 0;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates, target, 0, -1);
        return res;
    }
    //flag代表上一个被选择进res的数
    public void dfs(int[] candidates, int target, int index, int flag) {
        if (sum == target) {
            res.add(new ArrayList<>(list));
            return;
        }
        if (index == candidates.length ||  sum > target) {
            return;
        }
        if (!(index - flag > 1 && candidates[index] == candidates[index - 1])) {
            list.add(candidates[index]);
            sum += candidates[index];
            dfs(candidates, target, index + 1, index);
            list.remove(list.size() - 1);
            sum -= candidates[index];
        }

        dfs(candidates, target, index + 1, flag);
        // for (int i = index; i < candidates.length; i++) {
        //     if (i > index && candidates[i] == candidates[i-1]) {
        //         continue;
        //     }
        //     list.add(candidates[i]);
        //     sum += candidates[i];
        //     dfs(candidates, target, i + 1);
        //     list.remove(list.size() - 1);
        //     sum -= candidates[i];
        // }
    }
}
