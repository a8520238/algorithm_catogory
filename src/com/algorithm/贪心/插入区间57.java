package com.algorithm.贪心;

import java.util.ArrayList;
import java.util.List;

//题解中求并集的思想，更简洁
public class 插入区间57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        List<int[]> ansList = new ArrayList<int[]>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                // 在插入区间的右侧且无交集
                if (!placed) {
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                ansList.add(interval);
            } else if (interval[1] < left) {
                // 在插入区间的左侧且无交集
                ansList.add(interval);
            } else {
                // 与插入区间有交集，计算它们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});
        }
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); ++i) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }
}
// 自己写的判断
// class Solution {
//     public int[][] insert(int[][] intervals, int[] newInterval) {
//         List<int[]> list = new ArrayList<>();
//         for (int i = 0; i < intervals.length; i++) {
//             int[] cur = intervals[i];
//             if (cur[1] < newInterval[0]) {
//                 list.add(cur);
//             } else if (cur[0] <= newInterval[0] && newInterval[1] <= cur[1]) {
//                 return intervals;
//             } else if (newInterval[0] <= cur[1] && cur[1] < newInterval[1]) {
//                 if (cur[0] <= newInterval[0]) {
//                     newInterval = new int[] {cur[0], newInterval[1]};
//                 } else {
//                     newInterval = new int[] {newInterval[0], newInterval[1]};
//                 }
//             } else if (newInterval[0] < cur[0] && newInterval[1] >= cur[0]) {
//                 newInterval = new int[] {newInterval[0], cur[1]};
//             } else if (newInterval[1] < cur[0]) {
//                 list.add(newInterval);
//                 for (int j = i; j < intervals.length; j++) {
//                     list.add(intervals[j]);
//                 }
//                 return list.toArray(new int[0][]);
//             }
//         }
//         list.add(newInterval);
//         return list.toArray(new int[0][]);
//     }
// }