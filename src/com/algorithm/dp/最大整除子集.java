package com.algorithm.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
* 给出一个由无重复的正整数组成的集合，找出其中最大的整除子集，子集中任意一对 (Si，Sj) 都要满足：Si % Sj = 0 或 Sj % Si = 0。

如果有多个目标子集，返回其中任何一个均可。

 

示例 1:

输入: [1,2,3]
输出: [1,2] (当然, [1,3] 也正确)
示例 2:

输入: [1,2,4,8]
输出: [1,2,4,8]

* */
class 最大整除子集 {
    //利用list存储重复值
    // public List<Integer> largestDivisibleSubset(int[] nums) {
    //     int n = nums.length;
    //     if (n == 0) {
    //         return new ArrayList<>();
    //     }
    //     List<ArrayList> EDS = new ArrayList<>();
    //     for (int num: nums) {
    //         EDS.add(new ArrayList());
    //     }
    //     Arrays.sort(nums);
    //     for (int i = 0; i < n; i++) {
    //         List<Integer> maxSubset = new ArrayList<>();
    //         for (int k = 0; k < i; k++) {
    //             if (nums[i] % nums[k] == 0 && maxSubset.size() < EDS.get(k).size()) {
    //                 maxSubset = EDS.get(k);
    //             }
    //         }
    //         EDS.get(i).addAll(maxSubset);
    //         EDS.get(i).add(nums[i]);
    //     }
    //     List<Integer> res = new ArrayList<>();
    //     for (int i = 0; i < n; i++) {
    //         if (res.size() < EDS.get(i).size()) {
    //             res = EDS.get(i);
    //         }
    //     }
    //     return res;
    // }
    //使用数组存储重复值下标
    // public List<Integer> largestDivisibleSubset(int[] nums) {
    //     int n = nums.length;
    //     if (n == 0) {
    //         return new ArrayList();
    //     }
    //     Integer[] dp = new Integer[n];
    //     Arrays.sort(nums);
    //     Integer maxSubsetSize = -1, maxSubsetIndex = -1;
    //     for (int i = 0; i < n; i++) {
    //         Integer subsetSize = 0;
    //         for (int k = 0; k < i; k++) {
    //             if (nums[i] % nums[k] == 0 && subsetSize < dp[k]) {
    //                 subsetSize = dp[k];
    //             }
    //         }
    //         dp[i] = subsetSize +1;
    //         if (maxSubsetSize < dp[i]) {
    //             maxSubsetSize = dp[i];
    //             maxSubsetIndex = i;
    //         }
    //     }
    //     LinkedList<Integer> subset = new LinkedList<>();
    //     Integer currSize = maxSubsetSize;
    //     Integer currTail = nums[maxSubsetIndex];
    //     for (int i = maxSubsetIndex; i >= 0; i--) {
    //         if (currSize == 0) {
    //             break;
    //         }
    //         //这个双重判断的目的是找到能整除并次数也对的前一个值
    //         if (currTail % nums[i] == 0 && currSize == dp[i]) {
    //             subset.addFirst(nums[i]);
    //             currTail = nums[i];
    //             currSize -= 1;
    //         }
    //     }
    //     return subset;
    // }
    //记忆化递归
    HashMap<Integer, List<Integer>> eds;
    int[] _nums;
    public List<Integer> EDS(Integer i) {
        if (this.eds.containsKey(i)) {
            return this.eds.get(i);
        }
        List<Integer> maxSubset = new ArrayList<>();
        for (int k = 0; k < i; k++) {
            if (this._nums[i] % this._nums[k] == 0) {
                List<Integer> subset = EDS(k);
                if (maxSubset.size() < subset.size()) {
                    maxSubset = subset;
                }
            }
        }
        List<Integer> newEntry = new ArrayList<>();
        newEntry.addAll(maxSubset);
        newEntry.add(this._nums[i]);
        this.eds.put(i, newEntry);
        return newEntry;
    }
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return new ArrayList<>();
        }
        this.eds = new HashMap<>();
        Arrays.sort(nums);
        this._nums = nums;
        List<Integer> maxSubset = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> subset = EDS(i);
            if (maxSubset.size() <subset.size()) {
                maxSubset = subset;
            }
        }
        return maxSubset;
    }
}