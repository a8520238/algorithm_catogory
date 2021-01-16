package com.algorithm.dp.带维度的单串;

/*
* 一只青蛙想要过河。 假定河流被等分为 x 个单元格，并且在每一个单元格内都有可能放有一石子（也有可能没有）。 青蛙可以跳上石头，但是不可以跳入水中。

给定石子的位置列表（用单元格序号升序表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一个石子上）。 开始时， 青蛙默认已站在第一个石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格1跳至单元格2）。

如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1个单位。 另请注意，青蛙只能向前方（终点的方向）跳跃。

请注意：

石子的数量 ≥ 2 且 < 1100；
每一个石子的位置序号都是一个非负整数，且其 < 231；
第一个石子的位置永远是0。
示例 1:

[0,1,3,5,6,8,12,17]

总共有8个石子。
第一个石子处于序号为0的单元格的位置, 第二个石子处于序号为1的单元格的位置,
第三个石子在序号为3的单元格的位置， 以此定义整个数组...
最后一个石子处于序号为17的单元格的位置。

返回 true。即青蛙可以成功过河，按照如下方案跳跃：
跳1个单位到第2块石子, 然后跳2个单位到第3块石子, 接着
跳2个单位到第4块石子, 然后跳3个单位到第6块石子,
跳4个单位到第7块石子, 最后，跳5个单位到第8个石子（即最后一块石子）。

* */

import java.util.Arrays;

//dp On2
// class Solution {
//     public boolean canCross(int[] stones) {
//         HashMap<Integer, Set<Integer>> map = new HashMap<>();
//         for (int i = 0; i < stones.length; i++) {
//             map.put(stones[i], new HashSet<Integer>());
//         }
//         map.get(0).add(0);
//         for (int i = 0; i < stones.length; i++) {
//             for (int k: map.get(stones[i])) {
//                 for (int step = k - 1; step <= k + 1; step++) {
//                     if (step > 0 && map.containsKey(stones[i] + step)) {
//                         map.get(stones[i] + step).add(step);
//                     }
//                 }
//             }
//         }
//         return map.get(stones[stones.length - 1]).size() > 0;
//     }
// }
// class Solution {
//     public boolean canCross(int[] stones) {
//         //第i个位置步数为j能否到达
//         int[][] memo = new int[stones.length][stones.length];
//         for (int[] row: memo) {
//             Arrays.fill(row, -1);
//         }
//         return can_Cross(stones, 0, 0, memo) == 1;
//     }
//     public int can_Cross(int[] stones, int ind, int jumpsize, int[][] memo) {
//         if (memo[ind][jumpsize] >= 0) {
//             return memo[ind][jumpsize];
//         }
//         for (int i = ind + 1; i < stones.length; i++) {
//             int gap = stones[i] - stones[ind];
//             if (gap >= jumpsize - 1 && gap <= jumpsize + 1) {
//                 if (can_Cross(stones, i, gap, memo) == 1) {
//                     memo[ind][gap] = 1;
//                     return 1;
//                 }
//             }
//         }
//         memo[ind][jumpsize] = (ind == stones.length - 1)? 1: 0;
//         return memo[ind][jumpsize];
//     }
// }
class 青蛙过河 {
    public boolean canCross(int[] stones) {
        int[][] memo = new int[stones.length][stones.length];
        for (int[] row: memo) {
            Arrays.fill(row, -1);
        }
        return can_Cross(stones, 0, 0, memo) == 1;
    }
    public int can_Cross(int[] stones, int ind, int jumpsize, int[][] memo) {
        if (memo[ind][jumpsize] >= 0) {
            return memo[ind][jumpsize];
        }
        int ind1 = Arrays.binarySearch(stones, ind + 1, stones.length, stones[ind] + jumpsize);
        if (ind1 >= 0 && can_Cross(stones, ind1, jumpsize, memo) == 1) {
            memo[ind][jumpsize] = 1;
            return 1;
        }
        int ind2 = Arrays.binarySearch(stones, ind + 1, stones.length, stones[ind] + jumpsize - 1);
        if (ind2 >= 0 && can_Cross(stones, ind2, jumpsize - 1, memo) == 1) {
            memo[ind][jumpsize - 1] = 1;
            return 1;
        }
        int ind3 = Arrays.binarySearch(stones, ind + 1, stones.length, stones[ind] + jumpsize + 1);
        if (ind3 >= 0 && can_Cross(stones, ind3, jumpsize + 1, memo) == 1) {
            memo[ind][jumpsize + 1] = 1;
            return 1;
        }
        memo[ind][jumpsize] = ((ind == stones.length - 1)? 1: 0);
        return memo[ind][jumpsize];
    }
}