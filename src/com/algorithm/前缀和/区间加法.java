package com.algorithm.前缀和;

/*
* 假设你有一个长度为 n 的数组，初始情况下所有的数字均为 0，你将会被给出 k​​​​​​​ 个更新的操作。

其中，每个操作会被表示为一个三元组：[startIndex, endIndex, inc]，你需要将子数组 A[startIndex ... endIndex]（包括 startIndex 和 endIndex）增加 inc。

请你返回 k 次操作后的数组。

示例:

输入: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
输出: [-2,0,3,5,3]
解释:

初始状态:
[0,0,0,0,0]

进行了操作 [1,3,2] 后的状态:
[0,2,2,2,0]

进行了操作 [2,4,3] 后的状态:
[0,2,5,5,3]

进行了操作 [0,2,-2] 后的状态:
[-2,0,3,5,3]
* */

public class 区间加法 {
    //思路一个数组的差分数组的前缀和数组为该数组本身
    /*
对差分序列求前缀和序列，就得到原序列。

差分序列的好处是如果要对原序列的一个区间 [l, r] 上所有值加 val，原序列上要操作 r-l+1r−l+1 次 (a[l .. r] + val)，在差分序列上只需要操作 2 次(b[l] + val, b[r+1] - val)。如果这种区间操作需要很多次，最后的查询只有一次的话，就非常适合在差分序列上操作。
    */
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] arr = new int[length];
        for (int i = 0; i < updates.length; i++) {
            int[] cur = updates[i];
            arr[cur[0]] += cur[2];
            if (cur[1] + 1 < arr.length) {
                arr[cur[1] + 1] -= cur[2];
            }
        }
        int[] res = new int[length];
        res[0] = arr[0];
        for (int i = 1; i < length; i++) {
            res[i] = res[i - 1] + arr[i];
        }
        return res;
    }
}
