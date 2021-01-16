package com.algorithm.贪心;

import java.util.Arrays;

public class 无重叠区间435 {
    //贪心策略，每次找最早结束的，将与之重叠的删除，重复
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int min = 1;
        int xEnd = intervals[0][1];
        for (int[] arr: intervals) {
            if (arr[0] >= xEnd) {
                min++;
                xEnd = arr[1];
            }
        }
        return intervals.length - min;
    }
}
