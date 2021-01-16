package com.algorithm.排序;

import java.util.Arrays;

/*
* 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。

如果数组元素个数小于 2，则返回 0。

示例 1:

输入: [3,6,9,1]
输出: 3
解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
示例 2:

输入: [10]
输出: 0
解释: 数组元素个数小于 2，因此返回 0。
说明:

你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。

* */

//暴力法
// class Solution {
//     public int maximumGap(int[] nums) {
//         if (nums.length < 2) {
//             return 0;
//         }
//         Arrays.sort(nums);
//         int res = 0;
//         for (int i = 0; i < nums.length - 1; i++) {
//             if (nums[i + 1] - nums[i] > res) {
//                 res = nums[i + 1] - nums[i];
//             }
//         }
//         return res;
//     }
// }

// class Solution {
//     public int maximumGap(int[] nums) {
//         int n = nums.length;
//         if (n < 2) {
//             return 0;
//         }
//         List<ArrayList<Integer>> lists = new ArrayList<>();
//         for (int i = 0; i < 10; i++) {
//             lists.add(new ArrayList<>());
//         }
//         int max = nums[0];
//         for (int i = 1; i < n; i++) {
//             if (max < nums[i]) {
//                 max = nums[i];
//             }
//         }
//         int m = max;
//         int exp = 1;
//         //这里计数排序的思想是先排个位，再十位，依次向前
//         while (max > 0) {
//             //之前元素清空
//             for (int i = 0; i < 10; i++) {
//                 lists.set(i, new ArrayList<>());
//             }
//             //将数字放入对应的位置
//             for (int i = 0; i < n; i++) {
//                 lists.get(nums[i] / exp % 10).add(nums[i]);
//             }
//             //将数字依次拿出来
//             int index = 0;
//             for (int i = 0; i < 10; i++) {
//                 for (int j = 0; j < lists.get(i).size(); j++) {
//                     nums[index++] = lists.get(i).get(j);
//                 }
//             }
//             max /= 10;
//             exp *= 10;
//         }
//         int maxGap = 0;
//         for (int i = 0; i < nums.length - 1; i++) {
//             if (nums[i + 1] - nums[i] > maxGap) {
//                 maxGap = nums[i + 1] - nums[i];
//             }
//         }
//         return maxGap;
//     }
// }

// class Solution {
//     public int maximumGap(int[] nums) {
//         int n = nums.length;
//         if (n < 2) {
//             return 0;
//         }
//         long exp = 1;
//         int[] buf = new int[n];
//         int maxVal = Arrays.stream(nums).max().getAsInt();

//         while (maxVal >= exp) {
//             int[] cnt = new int[10];
//             //记录每个当前位的个数
//             for (int i = 0; i < n; i++) {
//                 int digit = (nums[i] / (int) exp) % 10;
//                 cnt[digit]++;
//             }
//             //记录到当前位（包括当前位）之前所有的个数，其实本质上是找下标
//             for (int i = 1; i < 10; i++) {
//                 cnt[i] += cnt[i - 1];
//             }
//             //根据下标分配
//             for (int i = n - 1; i >= 0; i--) {
//                 int digit = (nums[i] / (int) exp) % 10;
//                 buf[cnt[digit] - 1] = nums[i];
//                 cnt[digit]--;
//             }
//             System.arraycopy(buf, 0, nums, 0, n);
//             exp *= 10;
//         }

//         int ret = 0;
//         for (int i = 1; i < n; i++) {
//             ret = Math.max(ret, nums[i] - nums[i - 1]);
//         }
//         return ret;
//     }
// }


class 最大间距_计数排序 {
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        int min = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            min = Math.min(nums[i], min);
            max = Math.max(nums[i], max);
        }
        if (max - min == 0) {
            return 0;
        }
        //算出每个箱子的范围
        int interval = (int) Math.ceil((double)(max - min) / (n - 1));
        //算出每个箱子的最大值和最小值
        int[] bucketMin = new int[n - 1];
        int[] bucketMax = new int[n - 1];
        //最小值初始为 Integer.MAX_VALUE
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        //最小值初始化为 -1，因为题目告诉我们所有数字是非负数
        Arrays.fill(bucketMax, -1);
        //考虑每个数字
        for (int i = 0; i < nums.length; i++) {
            //当前数字所在箱子编号
            int index = (nums[i] - min) / interval;
            //最大数和最小数不需要考虑
            if (nums[i] == min || nums[i] == max) {
                continue;
            }

            //更新当前数字所在箱子的最小值和最大值
            bucketMin[index] = Math.min(nums[i], bucketMin[index]);
            bucketMax[index] = Math.max(nums[i], bucketMax[index]);
        }
        int maxGap = 0;
        //min看做第-1个箱子的最大值
        int previousMax = min;
        //从第 0 个箱子开始计算
        for (int i = 0; i < n - 1; i++) {
            //最大值是 -1 说明箱子中没有数字，直接跳过
            if (bucketMax[i] == -1) {
                continue;
            }

            //当前箱子的最小值减去前一个箱子的最大值
            maxGap = Math.max(bucketMin[i] - previousMax, maxGap);
            previousMax = bucketMax[i];
        }
        //最大值可能处于边界，不在箱子中，需要单独考虑
        maxGap = Math.max(max - previousMax, maxGap);
        return maxGap;

    }
}