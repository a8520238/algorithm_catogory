package com.algorithm.回溯;
/*
找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。

说明：

所有数字都是正整数。
解集不能包含重复的组合。 
*/

import java.util.ArrayList;
import java.util.List;

public class 组合总和3 {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    int sum = 0;
    public List<List<Integer>> combinationSum3(int k, int n) {
        back(k, n, 1);
        return res;
    }
    public void back (int k, int n, int value) {
        if (list.size() == k) {
            if (sum == n) {
                res.add(new ArrayList<>(list));
            }
            return;
        }
        if (sum > n) {
            return;
        }
        for (int i = value; i <= 9; i++) {
            list.add(i);
            sum += i;
            back(k, n, i + 1);
            list.remove(list.size() - 1);
            sum -= i;
        }
    }
}
