package com.algorithm.dp;

/*
* 我们有两个长度相等且不为空的整型数组 A 和 B 。

我们可以交换 A[i] 和 B[i] 的元素。注意这两个元素在各自的序列中应该处于相同的位置。

在交换过一些元素之后，数组 A 和 B 都应该是严格递增的（数组严格递增的条件仅为A[0] < A[1] < A[2] < ... < A[A.length - 1]）。

给定数组 A 和 B ，请返回使得两个数组均保持严格递增状态的最小交换次数。假设给定的输入总是有效的。

示例:
输入: A = [1,3,5,4], B = [1,2,3,7]
输出: 1
解释:
交换 A[3] 和 B[3] 后，两个数组如下:
A = [1, 3, 5, 7] ， B = [1, 2, 3, 4]
两个数组均为严格递增的。

* */

class 使序列递增的最小交换次数 {
    //定义两个状态，当前位置交换的最小次数，当前位置不交换的最小次数。
    //会有两个转移条件，1，不交换都比之前的大，2，交换后都比之前的大，这两者会同时发生，这两个条件也是解题的关键。
    // public int minSwap(int[] A, int[] B) {
    //     int change = 1, unchange = 0;
    //     for (int i = 1; i < A.length; i++) {
    //         int changeCur = Integer.MAX_VALUE, unchangeCur = Integer.MAX_VALUE;
    //         if (A[i - 1] < A[i] && B[i - 1] < B[i]) {
    //             unchangeCur = Math.min(unchangeCur, unchange);
    //             changeCur = Math.min(changeCur, change + 1);
    //         }
    //         if (A[i - 1] < B[i] && B[i - 1] < A[i]) {
    //             unchangeCur = Math.min(unchangeCur, change);
    //             changeCur = Math.min(changeCur, unchange + 1);
    //         }
    //         change = changeCur;
    //         unchange = unchangeCur;
    //     }
    //     return Math.min(change, unchange);
    // }
    public int minSwap(int[] A, int[] B) {
        int change = 1, unchange = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] < B[i] && B[i - 1] < A[i] && A[i - 1] < A[i] && B[i - 1] < B[i]) {
                int tmp = unchange;
                unchange = Math.min(unchange, change);
                change = Math.min(tmp, change) + 1;
                continue;
            } else if (A[i - 1] < A[i] && B[i - 1] < B[i]) {
                change += 1;
            } else if (A[i - 1] < B[i] && B[i - 1] < A[i]) {
                int tmp = unchange;
                unchange = change;
                change = tmp + 1;
            }
        }
        return Math.min(change, unchange);
    }
}