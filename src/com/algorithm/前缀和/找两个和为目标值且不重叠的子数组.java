package com.algorithm.前缀和;

/*
* 给你一个整数数组 arr 和一个整数值 target 。

请你在 arr 中找 两个互不重叠的子数组 且它们的和都等于 target 。可能会有多种方案，请你返回满足要求的两个子数组长度和的 最小值 。

请返回满足要求的最小长度和，如果无法找到这样的两个子数组，请返回 -1 。

 

示例 1：

输入：arr = [3,2,2,4,3], target = 3
输出：2
解释：只有两个子数组和为 3 （[3] 和 [3]）。它们的长度和为 2 。
示例 2：

输入：arr = [7,3,4,7], target = 7
输出：2
解释：尽管我们有 3 个互不重叠的子数组和为 7 （[7], [3,4] 和 [7]），但我们会选择第一个和第三个子数组，因为它们的长度和 2 是最小值。
示例 3：

输入：arr = [4,3,2,6,2,3,4], target = 6
输出：-1
解释：我们只有一个和为 6 的子数组。
示例 4：

输入：arr = [5,5,4,4,5], target = 3
输出：-1
解释：我们无法找到和为 3 的子数组。
示例 5：

输入：arr = [3,1,1,1,5,1,2,1], target = 3
输出：3
解释：注意子数组 [1,2] 和 [2,1] 不能成为一个方案因为它们重叠了。
 

提示：

1 <= arr.length <= 10^5
1 <= arr[i] <= 1000
1 <= target <= 10^8
* */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 找两个和为目标值且不重叠的子数组 {
    //dp 加 滑动窗口
    // public int minSumOfLengths(int[] arr, int target) {
    //     int n = arr.length;
    //dp的含义是到当前index之前所能取得的最小值
    //     int[] dp = new int[n];
    //     //注意不能设置为最大值，因为相加会溢出
    //     Arrays.fill(dp, Integer.MAX_VALUE / 2);
    //     int ans = Integer.MAX_VALUE;
    //     for (int i = 0, j = 0, sum = 0; j < n; j++) {
    //         sum += arr[j];
    //         while (i <= j && sum > target) {
    //             sum -= arr[i++];
    //         }
    //         //找到满足条件的一个区间
    //         if (sum == target) {
    //             dp[j] = j - i + 1;
    //             if (i != 0) {
    //                 ans = Math.min(ans, dp[i - 1] + j - i + 1);
    //             }
    //         }
    //         if (j != 0) {
    //             dp[j] = Math.min(dp[j], dp[j - 1]);
    //         }
    //     }
    //     return ans > arr.length? -1: ans;
    // }
    public int minSumOfLengths(int[] arr, int target) {
        Map<Integer, Integer> sums = new HashMap<>();
        sums.put(0, -1);
        int notFound = Integer.MAX_VALUE;

        int min = notFound;
        // 可以取前i个数字时组成target的1个子数组的最短长度
        int[] record  = new int[arr.length + 1];
        Arrays.fill(record, notFound);

        int sum = 0;
        //这里的边界要注意index与长度之间的转换
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            //如果找到了子数组的和为target
            if (sums.get(sum - target) != null) {
                //先找到子数组的左index
                int leftIdx = sums.get(sum - target);
                //找到左index之前的最小长度值
                int lastRecord = record[leftIdx + 1];
                //当前子数组的长度
                int nowRecord = i - leftIdx;
                record[i + 1] = nowRecord;
                if (lastRecord != notFound) {
                    //min与当前数组的长度加上之前的最小值取最小值
                    min = Math.min(min, lastRecord + nowRecord);
                }
            }
            //记录当前位置的最小值
            record[i + 1] = Math.min(record[i], record[i + 1]);
            sums.put(sum, i);
        }
        return min == notFound? -1: min;
    }
}
